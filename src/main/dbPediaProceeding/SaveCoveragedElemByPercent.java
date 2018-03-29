/**
 * 
 */
package main.dbPediaProceeding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import TextProceeding.ioProceeing.IOhandle;

import DBpediaProceeding.DBpediaProceedingToolBySparqlQuery.SaveCoveragedElements;

/**
 * @author mangohero1985
 * @create-time Aug 20, 2014 2:15:27 PM
 */
public class SaveCoveragedElemByPercent {

	/**
	 * @param args
	 */

	//static String[] dish = { "あんかけスパ", "エビフライ", "カレーうどん", "きしめん", "どて煮", "ひつまぶし", "モーニング", "台湾ラーメン", "名古屋コーチン", "味噌おでん", "味噌カツ", "味噌煮込みうどん", "天むす", "手羽先" };
	static String[] dish = {"コーヒー"};
	static int SentenceNum = 1;
	static int Percent = 10;

	// //子菜名处理
	// static String[] dish = { "ひつまぶし" };
	// static String Sondish ="薬味";
	// static int SentenceNum = 1;
	// static int Percent = 10;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String DishName = "";
		for (int d = 0; d < dish.length; d++) {
			// 读取文件
			DishName = dish[d];
			System.out.println("读取文件" + DishName + SentenceNum);
			String InputPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/" + DishName + "/" + DishName + SentenceNum
					+ "/DishNameSorted.txt";
			String OutputPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/" + DishName + "/" + DishName + SentenceNum
					+ "/CoveragedElements" + Percent + ".txt";

			IOhandle iOhandle = new IOhandle();
			BufferedReader br = iOhandle.FileReader(InputPath);
			BufferedWriter bw = iOhandle.FileWriter(OutputPath);

			SaveCoveragedElements saveCoveragedElements = new SaveCoveragedElements();
			ArrayList<String> coveragedElements = saveCoveragedElements.save(Percent, br);
			for (int i = 0; i < coveragedElements.size(); i++) {
				bw.write(coveragedElements.get(i));
				bw.newLine();
				bw.flush();
			}

		}
		System.out.println("全部读取完成");
	}

}
