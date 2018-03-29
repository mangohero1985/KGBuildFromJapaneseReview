/**
 * 
 */
package main.TextProceeding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import TextProceeding.ioProceeing.IOhandle;
import Tools.ReservNSentence;

/**
 * @author mangohero1985
 * @create-time Jun 23, 2014 9:31:45 AM
 */
public class GenerateNSentence {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws IOException {
		// 菜名处理
		int N = 1;
		String DishName = "ひつまぶし";
		String InputPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/" + DishName + "/" + DishName + ".txt";
		String OutputPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/" + DishName + "/" + DishName + N + ".txt";
		IOhandle iOhandle = new IOhandle();
		BufferedReader br = iOhandle.FileReader(InputPath);
		BufferedWriter bw = iOhandle.FileWriter(OutputPath);

		ReservNSentence reservNSentence = new ReservNSentence();
		reservNSentence.Researve(br, bw, N, DishName);
		// try {
		// int N = 1;
		// String DishName = Dish;
		// String ElementName =Element;
		// String InputPath =
		// "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/"
		// + DishName + "/" + DishName + ".txt";
		// String OutputPath =
		// "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/"
		// + DishName + "/" + ElementName + N + ".txt";
		// IOhandle iOhandle = new IOhandle();
		// BufferedReader br = iOhandle.FileReader(InputPath);
		// BufferedWriter bw = iOhandle.FileWriter(OutputPath);
		//
		// ReservNSentence reservNSentence = new ReservNSentence();
		// reservNSentence.Researve(br, bw, N, DishName);
		// }
		// catch (Exception e) {
		// System.out.println("创建子句出错");
		// System.out.println(e);
		// }

	}

}
