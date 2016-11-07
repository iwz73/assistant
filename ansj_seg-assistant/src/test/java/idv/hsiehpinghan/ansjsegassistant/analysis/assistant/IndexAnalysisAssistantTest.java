package idv.hsiehpinghan.ansjsegassistant.analysis.assistant;

import java.io.UnsupportedEncodingException;

import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.util.MyStaticValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import idv.hsiehpinghan.ansjsegassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class IndexAnalysisAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private IndexAnalysisAssistant assistant;

	@Test
	public void parse() {
		// List<Term> parse = IndexAnalysis.parse("主副食品");
		// System.out.println(parse);
		// System.out.println(IndexAnalysis.parse("上海虹桥机场南路"));
		// System.out.println(IndexAnalysis.parse("主副食品"));
		// System.out.println(IndexAnalysis.parse("花生油"));
		// System.err.println(assistant.parse("主副食品")
		// .toString());
		// Assert.assertEquals(
		// assistant.parse("2013年特偵組指控立法院長王金平替民進黨立委柯建銘關說，卻扯出檢察官林秀濤遭監聽，林因此向檢察總長黃世銘等4人提告求償，北院今天判決黃世銘賠償30萬元，其餘駁回。")
		// .toString(),
		// "2013年/m,特/d,偵,組,指控/v,立法/vn,院/n,長/nr,王金平/nr,替/p,民/ng,進/nr,黨,立委柯/nr,建/v,銘/nr,關/nr,說,，/w,卻,扯/v,出/v,檢,察/j,官林秀/nr,濤,遭/v,監,聽/nr,，/w,林/ng,因/p,此/r,向/p,檢,察/j,總,長/nr,黃世銘/nr,等/u,4/m,人/n,提/v,告/v,求/v,償,，/w,北/f,院/n,今天/t,判/v,決,黃世銘/nr,賠,償,30/m,萬/nr,元/q,，/w,其/r,餘,駁,回/v,。/w");
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
//		 List<Term> parse = IndexAnalysis.parse("主副食品");
//		System.out.println(IndexAnalysis.parse("而无需再花大量的时间和精力去学习不很自然和习惯的各种计算机语言").toString());
		// System.out.println(IndexAnalysis.parse("上海虹桥机场南路"));
		// System.out.println(IndexAnalysis.parse("主副食品"));
		// System.out.println(IndexAnalysis.parse("花生油"));
		
//		MyStaticValue.isNumRecognition=true;
//		MyStaticValue.isQuantifierRecognition = false;
//		MyStaticValue.DIC.put("dic", null);
//		UserDefineLibrary.insertWord("蛇药片", "n", 1000);
//		UserDefineLibrary.insertWord("蛇药", "n", 1000);
//		UserDefineLibrary.insertWord("鲁花", "n", 1000);
		UserDefineLibrary.insertWord("隐形", "n", 1000);
		UserDefineLibrary.insertWord("眼镜", "n", 1000);
//		UserDefineLibrary.insertWord("隐形眼镜", "n", 1000);
//		UserDefineLibrary.insertWord("海昌 ", "n", 1000);
//		UserDefineLibrary.insertWord("美瞳", "n", 1000);
//
		System.out.println(IndexAnalysis.parse("海昌 润洁除蛋白隐形眼镜美瞳护理液 500+120ml"));
//
//		System.out.println(IndexAnalysis.parse("隐形眼镜 护理"));
//
//		System.out.println(IndexAnalysis.parse("海昌 润洁除蛋白隐形眼镜美瞳护理液 500+120ml"));
//
//		System.out.println(IndexAnalysis.parse("季德胜蛇药片"));
//
//		System.out.println(IndexAnalysis.parse("鲁花一级花生油"));
//
//		System.out.println(IndexAnalysis.parse("上海虹桥机场旅游和服务都是一流的"));
//
//		System.out.println(IndexAnalysis.parse("北京地铁"));
//		
//
//		UserDefineLibrary.insertWord("隐形", "attr", 1000);
//		UserDefineLibrary.insertWord("眼镜", "attr", 1000);
//		UserDefineLibrary.insertWord("隐形", "attr", 1000);
//		UserDefineLibrary.insertWord("形眼", "attr", 1000);
//		UserDefineLibrary.insertWord("隐形眼", "attr", 1000);
//		System.out.println(IndexAnalysis.parse("隐形眼镜"));
		
//		long start = System.currentTimeMillis() ;
//				
//		IndexAnalysis indexAnalysis = new IndexAnalysis(new InputStreamReader(new FileInputStream("/home/ansj/temp/one_day_all.txt"))) ;
//		
//		while(indexAnalysis.next()!=null){
//			
//		}
//		System.out.println(System.currentTimeMillis()-start);

	}

}
