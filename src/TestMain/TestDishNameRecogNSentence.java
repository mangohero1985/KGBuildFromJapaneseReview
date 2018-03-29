/**
 * 
 */
package TestMain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import Tools.ReservNSentence;

import jp.uclab.io.IOhandle;

/**
 * @author mangohero1985
 * @create-time Jun 23, 2014 9:31:45 AM
 */
public class TestDishNameRecogNSentence {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		int N = 3;
		String keys = "手羽先";
		String InputPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/" + keys + "/" + keys + ".txt";
		String OutputPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/" + keys + "/" + keys + N + ".txt";

		IOhandle iOhandle = new IOhandle();
		BufferedReader br = iOhandle.FileReader(InputPath);
		BufferedWriter bw = iOhandle.FileWriter(OutputPath);

		ReservNSentence reservNSentence = new ReservNSentence();
		reservNSentence.Researve(br, bw, N, keys);

	}

}
