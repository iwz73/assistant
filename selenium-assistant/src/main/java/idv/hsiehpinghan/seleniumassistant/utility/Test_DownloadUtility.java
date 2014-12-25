package idv.hsiehpinghan.seleniumassistant.utility;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.ConfirmHandler;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindowListener;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class Test_DownloadUtility {

    protected String baseUrl;
    protected static WebDriver driver;

//    @Before
    public void openBrowser() {
        baseUrl = "http://localhost/teste.html";
        driver = new CustomHtmlUnitDriver();
        ((HtmlUnitDriver) driver).setJavascriptEnabled(true);

    }


    @Test
    public void downloadAFile() throws Exception {

        driver.get(baseUrl);
        driver.findElement(By.linkText("click to Downloadfile")).click();

    }

    public class CustomHtmlUnitDriver extends HtmlUnitDriver { 


    } 



}