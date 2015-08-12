package idv.hsiehpinghan.goraassistant.utility;

import java.io.File;

import org.junit.Assert;
import org.testng.annotations.Test;

public class GoraCompilerUtilityTest {

	@Test
	public void compile() {
		String inputFolderPath = "/home/thank/git/assistant/gora-assistant/src/test/avro";
		String outputFolderPath = "/home/thank/git/assistant/gora-assistant/src/test/java/";
		File outputEntityFolder = new File("/home/thank/git/assistant/gora-assistant/src/test/java/idv/hsiehpinghan/goraassistant/gora");
		Assert.assertTrue(deleteSubFile(outputEntityFolder));
		Assert.assertTrue(outputEntityFolder.list().length == 0);
		GoraCompilerUtility.compile(inputFolderPath, outputFolderPath);
		Assert.assertTrue(outputEntityFolder.list().length > 0);
	}

	private boolean deleteSubFile(File beforeFile) {
		for (File subFile : beforeFile.listFiles()) {
			if (subFile.delete() == false) {
				return false;
			}
		}
		return true;
	}
}
