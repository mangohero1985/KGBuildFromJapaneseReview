/**
 * 
 */
package TextProceeding.DishRecognition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import Tools.SearchTwitterLexicon;

/**
 * @author mangohero1985
 * @create-time     Jun 14, 2014   7:37:21 PM   
 */
public class UploadDicAndKata {
	
	public void upload(BufferedReader br , BufferedWriter bw, String dictionary){
		
		String regex = "^[\\u30A0-\\u30FF]+$";
		SearchTwitterLexicon SearchTwitterLexicon = new SearchTwitterLexicon();
		try {
			ArrayList<String> arrayListName = SearchTwitterLexicon.Searching(dictionary);
			String readLine = "";

			String[] splitString = null;

			while ((readLine = br.readLine()) != null) {
				splitString = readLine.split("\t");
				if (!readLine.isEmpty()) {
					if (arrayListName.contains(splitString[0])) {
						// System.out.println(line);
						if (splitString[0].matches(regex)) {
							bw.write(splitString[0] + "\t" + splitString[1] + "\t" + "Y" + "\t" + "K" + "\t" +"0");
							bw.newLine();
							bw.flush();
						}else{
							bw.write(splitString[0] + "\t" + splitString[1] + "\t" + "Y" + "\t" + "H" + "\t" +"0");
							bw.newLine();
							bw.flush();
						}
						}
					
					else {
						// System.out.println(line);
						if (arrayListName.contains(splitString[0])){
						bw.write(splitString[0] + "\t" + splitString[1] + "\t" + "N" + "\t" + "K" + "\t" +"0");
						bw.newLine();
						bw.flush();
						}else{
							bw.write(splitString[0] + "\t" + splitString[1] + "\t" + "N" + "\t" + "H" + "\t" +"0");
							bw.newLine();
							bw.flush();
							
						}
					}
			  }
				}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("上传词典和Kata完成");
	}

}
