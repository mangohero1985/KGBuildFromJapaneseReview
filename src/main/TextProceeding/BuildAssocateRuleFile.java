/**
 * 
 */
package main.TextProceeding;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.swing.text.html.HTMLDocument.Iterator;

import TextProceeding.DishRecognition.DishName;
import TextProceeding.DishRecognition.SegmentationAndPOS;
import TextProceeding.DishRecognition.UploadDicAndKata;
import TextProceeding.Extractor.ReviewExtract;
import TextProceeding.ioProceeing.IOhandle;
import Tools.DelBlankLine;
import Tools.ExecuteFromTerminal;
import Tools.Algorithm.AssociateFpGrowth;
import weka.*;
import weka.associations.FPGrowth;

/**
 * @author mangohero1985
 * @create-time Jun 27, 2014 9:56:03 AM
 */
public class BuildAssocateRuleFile {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String fileName = "きしめん";
		int N =3;
		int Threshold = 20;
		String Patameter = "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.0010 -S";
		String filePath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/" + fileName + N + "/";
		IOhandle iOhandle = new IOhandle();

		// 读取多余阈值的菜名
		ArrayList<String> dishNameOverThresholdArrayList = new ArrayList<String>();
		String InpurPath7 = filePath + "DishNameSorted.txt";
		BufferedReader br = iOhandle.FileReader(InpurPath7);
		String ReadLineDish = "";
		while ((ReadLineDish = br.readLine()) != null) {
			String[] SplieThresold = ReadLineDish.split("\\(");
			if (Integer.parseInt(SplieThresold[1].substring(0, SplieThresold[1].length() - 1)) >= Threshold) {
				dishNameOverThresholdArrayList.add(SplieThresold[0]);
			}
		}

		// 提取被识别的菜名
		String inputPath4 = filePath + "RecognitionResult.txt";
		String outputPath4 = filePath + "DishNameForSentence.txt";

		BufferedReader br4 = iOhandle.FileReader(inputPath4);
		BufferedWriter bw4 = iOhandle.FileWriter(outputPath4);

		DishName dishName = new DishName();
		dishName.SelectionForSentence(br4, bw4, dishNameOverThresholdArrayList);

		// 删除空行
		String InputPath5 = filePath + "DishNameForSentence.txt";
		String OutputPath5 = filePath + "DishNameForSentenceWithoutBlank.txt";

		BufferedReader br5 = iOhandle.FileReader(InputPath5);
		BufferedWriter bw5 = iOhandle.FileWriter(OutputPath5);
		DelBlankLine delBlankLine = new DelBlankLine();
		delBlankLine.Delete(br5, bw5);

		// 写入标准格式(ARFF)文件
		String InputPath6 = filePath + "DishNameForSentenceWithoutBlank.txt";
		String OutputPath6 = filePath + "DishNameForSentenceWithoutBlank.arff";

		BufferedReader br6 = iOhandle.FileReader(InputPath6);
		BufferedWriter bw6 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputPath6), "UTF-8"));

		String ReadLine = "";
		ArrayList<String> arrayListElements = new ArrayList<String>();
		while ((ReadLine = br6.readLine()) != null) {
			String[] splite = ReadLine.split(" ");
			for (int j = 0; j < splite.length; j++) {
				if (!arrayListElements.contains(splite[j])) {
					arrayListElements.add(splite[j]);
				}
			}

		}
		if (!arrayListElements.contains(fileName)) {
			arrayListElements.add(fileName);
		}
		bw6.write("@relation 'dishes'");
		bw6.newLine();
		bw6.flush();
		int n = 0;
		Map<String, Integer> dishMap = new HashMap<String, Integer>();
		for (int k = 0; k < arrayListElements.size(); k++) {
			String[] splite1 = arrayListElements.get(k).split(" ");
			dishMap.put(splite1[0], n);
			bw6.write("@attribute" + " " + dishMap.get(splite1[0]) + " " + "{F,T}");
			bw6.newLine();
			bw6.flush();
			n++;
		}
		// for (Object o : dishMap.keySet()) {
		// System.out.println(o + " " + dishMap.get(o));
		// }
		// bw6.write("@attribute" + " " + "'" + fileName + "'" + " " + "{F,T}");
		// bw6.newLine();
		bw6.write("@data");
		bw6.newLine();
		bw6.flush();
		BufferedReader br7 = iOhandle.FileReader(InputPath6);
		ArrayList<Integer> valueNumArrayList = new ArrayList<Integer>();
		while ((ReadLine = br7.readLine()) != null) {
			String[] spliteLine = ReadLine.split(" ");
			for (int k = 0; k < spliteLine.length; k++) {
				if (!valueNumArrayList.contains(dishMap.get(fileName))) {
					valueNumArrayList.add(dishMap.get(fileName));
				}
				if (!valueNumArrayList.contains(dishMap.get(spliteLine[k]))) {
					valueNumArrayList.add(dishMap.get(spliteLine[k]));
				}

			}
			// valueNumArrayList.add(dishMap.get(fileName));
			bw6.write("{");
			Collections.sort(valueNumArrayList);
			for (int s = 0; s < valueNumArrayList.size(); s++) {
				if (s == valueNumArrayList.size() - 1) {
					bw6.write(valueNumArrayList.get(s) + " T");
				}
				else {
					bw6.write(valueNumArrayList.get(s) + " T,");
				}
			}
			bw6.write("}");
			bw6.newLine();
			bw6.flush();
			valueNumArrayList.clear();
		}
		System.out.println("得到关联ARFF文件");

		// 使用FpGrowth得到规则,并且给出结果
		String[] optionsAssociat = weka.core.Utils.splitOptions(Patameter);
		String OutputPath7 = filePath + "PfGrowthResults.txt";
		AssociateFpGrowth associateFpGrowth = new AssociateFpGrowth();
		BufferedWriter bw = iOhandle.FileWriter(OutputPath7);
		associateFpGrowth.FPGrowth(OutputPath6, bw, dishMap, fileName, optionsAssociat);

	}
}
