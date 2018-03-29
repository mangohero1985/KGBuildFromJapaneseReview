/**
 * 
 */
package TextProceeding.Segmentation;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.chasen.mecab.MeCab;
import org.chasen.mecab.Node;
import org.chasen.mecab.Tagger;

import TextProceeding.ioProceeing.IOhandle;


/**
 * @author mangohero1985
 * @create-time May 22, 2014 10:28:09 PM
 */
public class BinaryTuple {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String FileName = "4-5";
		// 读取文件
		IOhandle io = new IOhandle();
		BufferedReader br = io.FileReader("/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/scaled_feature/" + FileName + "temp.txt");
		BufferedWriter bw = io.FileWriter("/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/scaled_feature/classify_by_score_word_binary/" + FileName
				+ ".txt");
        BufferedWriter bw1 = io.FileWriterContinue("/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/scaled_feature/classify_by_score_word_binary/allWord.txt");		
        String readline = null;

		while ((readline = br.readLine()) != null) {
			String[] spliteString = readline.split("\\*");
			for (int i = 0; i < spliteString.length; i++) {
				if (i < spliteString.length - 1) {
					bw.write(spliteString[i] + spliteString[i + 1]);
					bw.newLine();
					bw.flush();
					bw1.write(spliteString[i] + spliteString[i + 1]);
					bw1.newLine();
					bw1.flush();
				}
				else {
					continue;
				}

			}
		}

	}

}
