import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
	
	public File createFile(String fileName) {
		File file = new File(fileName);
		try {
			if (file.createNewFile()) {
				System.out.println("Output file is created!");
			}else{
				System.out.println("File already exists. Overriding it");
			}
			return file;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	
	public int createOutputFile(String fileName, String data){
		File file = createFile(fileName);
		if (file == null) {
			return Constant.FAILURE;
		}
		//Write Content
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
}
