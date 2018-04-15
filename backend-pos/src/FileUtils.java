import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {

	public File createFile(String fileName) {
		File file = new File(fileName);
		try {
			if (file.createNewFile()) {
				System.out.println("Output file is created!");
			} else {
				System.out.println("File already exists. Overriding it");
			}
			return file;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public int createOutputFile(String fileName, String data) {
		File file = createFile(fileName);
		if (file == null) {
			return Constant.FAILURE;
		}
		// Write Content
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			writer.write(data);
			writer.close();
			return Constant.SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Constant.FAILURE;

	}

	public static String readLineByLineJava8(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}
}
