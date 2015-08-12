package idv.hsiehpinghan.goraassistant.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.gora.compiler.cli.GoraCompilerCLI;

public class GoraCompilerUtility {
	public static void compile(String inputFolderPath, String outputFolderPath) {
		String[] args = generateArgs(inputFolderPath, outputFolderPath);
		GoraCompilerCLI.main(args);
	}

	private static String[] generateArgs(String inputFolderPath,
			String outputFolderPath) {
		File inputFolder = new File(inputFolderPath);
		File[] inputFiles = inputFolder.listFiles();
		List<String> args = new ArrayList<String>(inputFiles.length + 1);
		for (File file : inputFiles) {
			args.add(file.getAbsolutePath());
		}
		args.add(outputFolderPath);
		return args.toArray(new String[args.size()]);
	}
}
