package PreProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import Classes.Path;

public class concatenateFiles {

	

	public static void main(String[] args) throws Exception {
		FileWriter fw = new FileWriter(Path.textInput);
		File file = new File(Path.textSource);
		File[] files = file.listFiles();
		
		
		for (int i = 0; i < files.length; i++) {
			BufferedReader br = new BufferedReader(new FileReader(files[i]
					.getPath()));
			String line = br.readLine();
			while (line != null) {
				fw.append(line + "\n");
				line = br.readLine();
			}
			br.close();
		}
		fw.close();

	}

}
