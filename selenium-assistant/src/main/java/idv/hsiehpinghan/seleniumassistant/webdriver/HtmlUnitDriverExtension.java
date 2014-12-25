package idv.hsiehpinghan.seleniumassistant.webdriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.ConfirmHandler;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.SgmlPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindowListener;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

//@Component
public class HtmlUnitDriverExtension extends HtmlUnitDriver {

	   public HtmlUnitDriverExtension() {
	    }
	    
	    public HtmlUnitDriverExtension(boolean enableJavascript) {
	        super(enableJavascript);
	    }
	    
	    /**
	     * 取得以utf-8編碼的內容。
	     */
	    @Override
	    public String getPageSource() {
	        Page page = lastPage();
	        if (page == null) {
	            return null;
	        }

	        if (page instanceof SgmlPage) {
	            return ((SgmlPage) page).asXml();
	        }
	        WebResponse response = page.getWebResponse();
	        try {
				FileUtils.copyInputStreamToFile(response.getContentAsStream(), new File("/tmp/ttt.zip"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return response.getContentAsString("utf-8");
	    }
	    
    // This is the magic. Keep a reference to the client instance 
     protected WebClient modifyWebClient(WebClient client) { 


       ConfirmHandler okHandler = new ConfirmHandler(){
              public boolean handleConfirm(Page page, String message) {
                  return true;
              }
       };
       client.setConfirmHandler(okHandler);

       client.addWebWindowListener(new WebWindowListener() {

          public void webWindowOpened(WebWindowEvent event) {
              // TODO Auto-generated method stub

          }

          public void webWindowContentChanged(WebWindowEvent event) {

              WebResponse response = event.getWebWindow().getEnclosedPage().getWebResponse();
              System.out.println(response.getLoadTime());
              System.out.println(response.getStatusCode());
              System.out.println(response.getContentType());

              List<NameValuePair> headers = response.getResponseHeaders();
              for(NameValuePair header: headers){
                  System.out.println(header.getName() + " : " + header.getValue());
              }

              // Change or add conditions for content-types that you would to like 
              // receive like a file.
              if(response.getContentType().equals("text/plain")){
                  getFileResponse(response, "target/testDownload.war");
              }



          }

          public void webWindowClosed(WebWindowEvent event) {



          }
      });          

       return client; 
     } 

     public static void getFileResponse(WebResponse response, String fileName){

         InputStream inputStream = null;

         // write the inputStream to a FileOutputStream
         OutputStream outputStream = null; 

         try {       

             inputStream = response.getContentAsStream();

             // write the inputStream to a FileOutputStream
             outputStream = new FileOutputStream(new File(fileName));

             int read = 0;
             byte[] bytes = new byte[1024];

             while ((read = inputStream.read(bytes)) != -1) {
                 outputStream.write(bytes, 0, read);
             }

             System.out.println("Done!");

         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             if (inputStream != null) {
                 try {
                     inputStream.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if (outputStream != null) {
                 try {
                     // outputStream.flush();
                     outputStream.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

             }
         }

     }
}
