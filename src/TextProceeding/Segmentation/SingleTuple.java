/**
 * 
 */
package TextProceeding.Segmentation;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


import org.chasen.mecab.MeCab;
import org.chasen.mecab.Node;
import org.chasen.mecab.Tagger;

import TextProceeding.ioProceeing.IOhandle;

/**
 * @author mangohero1985
 * @create-time May 23, 2014 1:21:06 AM
 */
public class SingleTuple {

	/**
	 * @param args
	 */

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String FileName = "4-5";
		// 读取文件
		IOhandle io = new IOhandle();
		BufferedReader br = io.FileReader("/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/scaled_feature/" + FileName + "temp.txt");
		BufferedWriter bw = io.FileWriter("/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/scaled_feature/classify_by_score_word/" + FileName + ".txt");

		String readline = null;

		while ((readline = br.readLine()) != null) {
			String[] spliteString = readline.split("\\*");
			for (int i = 0; i < spliteString.length; i++) {
				bw.write(spliteString[i]);
				bw.newLine();
				bw.flush();

			}
		}

	}

}
