/**
 * 
 */
package TestMain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import FeatureSelection.FeatureOccurrenceCounter;
import FeatureSelection.FeatureSelectionMetrics;
import Tools.FreqStatisWithKeywords;
import Tools.ReservNSentence;
import Tools.SortAndCount.MapSorting;

import jp.uclab.io.IOhandle;

/**
 * @author mangohero1985
 * @create-time Jun 23, 2014 11:34:18 PM
 */
public class TestComputeFeatures {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		int N = 3;
		String DishName = "モーニング";
		String Symbol = "CHI";
		IOhandle iOhandle = new IOhandle();

		String InputPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/" + DishName + ".txt";
		String OutputPathNSentence = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/" + DishName + N + ".txt";
		String OutputPathWithoutNSentence = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/" + DishName + N + "Without" + ".txt";

		// 数据准备
		BufferedReader br = iOhandle.FileReader(InputPath);
		BufferedWriter bw1 = iOhandle.FileWriter(OutputPathNSentence);
		BufferedWriter bw2 = iOhandle.FileWriter(OutputPathWithoutNSentence);

		ReservNSentence reservNSentence = new ReservNSentence();
		ArrayList<String> arrayList = reservNSentence.ResearvWithoutN(br, bw1, N, DishName);

		String ReadLine = "";
		BufferedReader br2 = iOhandle.FileReader(InputPath);

		while ((ReadLine = br2.readLine()) != null) {
			String[] splite = ReadLine.split("。");
			for (int i = 0; i < splite.length; i++) {
				if (!arrayList.contains(splite[i])) {
					bw2.write(splite[i]);
					bw2.newLine();
					bw2.flush();
				}

			}
		}
		br.close();
		br2.close();
		bw1.close();
		bw2.close();

		// 词频计算
		ArrayList<String> arrayListTPorFN = new ArrayList<String>();
		ArrayList<String> arrayListFPorTN = new ArrayList<String>();
		String InputPathTPorFN = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/" + DishName + N + ".txt";
		String InputPathFPorTN = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/" + DishName + N + "Without.txt";
		String InputPathElements = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/" + DishName + N + "/DishNameSorted.txt";

		BufferedReader br3 = iOhandle.FileReader(InputPathTPorFN);
		BufferedReader br5 = iOhandle.FileReader(InputPathFPorTN);
		BufferedReader br4 = iOhandle.FileReader(InputPathElements);
		while ((ReadLine = br3.readLine()) != null) {
			arrayListTPorFN.add(ReadLine);
		}
		br3.close();

		while ((ReadLine = br5.readLine()) != null) {
			arrayListFPorTN.add(ReadLine);
		}
		br5.close();

		FreqStatisWithKeywords freqStatisWithKeywords = new FreqStatisWithKeywords();
		String OutputPathChi = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/" + DishName + N + Symbol + ".txt";
		BufferedWriter bw = iOhandle.FileWriter(OutputPathChi);

		while ((ReadLine = br4.readLine()) != null) {

			String[] Splite = ReadLine.split("\\(");
			String Element = Splite[0];
			// 计算TP(特征词存在于指定类)
			double tp = (double) freqStatisWithKeywords.Statistic(arrayListTPorFN, Element);

			// 计算FN(指定类中不包含特征词)
			double fn = (double) freqStatisWithKeywords.StatisticFalse(arrayListTPorFN, DishName);

			// 计算FP(特征词存在于非指定类)
			double fp = (double) freqStatisWithKeywords.Statistic(arrayListFPorTN, Element);

			// 计算TN(非指定类中不包含特征词)
			double tn = (double) freqStatisWithKeywords.StatisticFalse(arrayListFPorTN, Element);

			Compute(tp, fn, fp, tn, Symbol, Element, bw);

		}
		// 结果排序
		String InputPath1 = OutputPathChi;
		String OutputPathCHISouted = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/" + DishName + N + Symbol + "Sorted.txt";

		BufferedReader br6 = iOhandle.FileReader(InputPath1);
		BufferedWriter bw3 = iOhandle.FileWriter(OutputPathCHISouted);

		MapSorting mapSorting = new MapSorting();
		mapSorting.Sorting(br6, bw3, " ");
		bw2.close();
		br2.close();
	}

	public static void Compute(double tp, double fn, double fp, double tn, String Symbol, String Element, BufferedWriter bw) throws IOException {

		FeatureSelectionMetrics featureSelectionMetrics = new FeatureSelectionMetrics(tp, fn, fp, tn);
		if (Symbol.contains("CHI")) {
			if (tp == 0 || fp == 0) {
				bw.write(Element + " " + 0.0);
				bw.newLine();
				bw.flush();
			}
			else {
				// 计算CHI
				// double Chisquare = featureSelectionMetrics.getChiSquare();
				// 计算IG
				double CHI = featureSelectionMetrics.getChiSquare();
				bw.write(Element + " " + CHI);
				bw.newLine();
				bw.flush();
			}
		}
		else if (Symbol.contains("IG")) {
			if (tp == 0 || fp == 0) {
				bw.write(Element + " " + 0.0);
				bw.newLine();
				bw.flush();
			}
			else {
				// 计算CHI
				// double Chisquare = featureSelectionMetrics.getChiSquare();
				// 计算IG
				double IG = featureSelectionMetrics.getIG();
				bw.write(Element + " " + IG);
				bw.newLine();
				bw.flush();
			}
		}
		else {
			if (tp == 0 || fp == 0) {
				bw.write(Element + " " + 0.0);
				bw.newLine();
				bw.flush();
			}
			else {
				// 计算CHI
				// double Chisquare = featureSelectionMetrics.getChiSquare();
				// 计算IG
				double MI = featureSelectionMetrics.getMI();
				bw.write(Element + " " + MI);
				bw.newLine();
				bw.flush();
			}
		}

	}

}
