package idv.hsiehpinghan.goraassistant.utility;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GoraCompilerUtilityTest {

	@Test
	public void compile() {
		String inputFolderPath = "/home/hsiehpinghan/git/assistant/gora-assistant/src/test/avro";
		String outputFolderPath = "/home/hsiehpinghan/git/assistant/gora-assistant/src/test/java/";
		File outputEntity = new File(
				"/home/hsiehpinghan/git/assistant/gora-assistant/src/test/java/idv/hsiehpinghan/goraassistant/entity/Gora.java");
		Assert.assertTrue(deleteFile(outputEntity));
		Assert.assertFalse(outputEntity.exists());
		GoraCompilerUtility.compile(inputFolderPath, outputFolderPath);
		Assert.assertTrue(outputEntity.exists());
	}

	private boolean deleteFile(File outputEntity) {
		if (outputEntity.exists()) {
			return outputEntity.delete();
		}
		return true;
	}
}
