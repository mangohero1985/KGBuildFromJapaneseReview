/**
 * 
 */
package TextFeatureComputation.FeatureSelectionProceeding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import TextFeatureComputation.FeatureSelectionTools.FeatureSelectionMetrics;
import TextFeatureComputation.FeatureSelectionTools.FreqStatisWithKeywords;
import TextProceeding.ioProceeing.IOhandle;
import Tools.SortAndCount.MapSorting;

/**
 * @author mangohero1985
 * @create-time Aug 25, 2014 3:32:18 PM
 */
public class FeatureComputation {

	public void GetResults(String DishName, String Num, String dir, String PercentName, String Symbol) {
		try {
			IOhandle iOhandle = new IOhandle();
			int N = Integer.parseInt(Num);
			// 词频计算
			ArrayList<String> arrayListTPorFN = new ArrayList<String>();
			ArrayList<String> arrayListFPorTN = new ArrayList<String>();
			// 子句路径
			String InputPathTPorFN = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "TextFeatureComputation" + "/" + DishName + N + ".txt";
			// 非子句路径
			String InputPathFPorTN = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "TextFeatureComputation" + "/" + DishName + "Without" + N + ".txt";
			// 输入elements路径
			String InputPathElements = dir + DishName + "/" + DishName + Num + "/" + "DishNameSorted.txt";
			// 输出结果路径
			String OutputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "TextFeatureComputation" + "/" + DishName + N + Symbol + ".txt";
			// 输出结果排序路径
			String OutputPathCHISouted = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "TextFeatureComputation" + "/" + DishName + N + Symbol + "Sorted.txt";

			BufferedReader br3 = iOhandle.FileReader(InputPathTPorFN);
			BufferedReader br5 = iOhandle.FileReader(InputPathFPorTN);
			BufferedReader br4 = iOhandle.FileReader(InputPathElements);

			String ReadLine = "";
			while ((ReadLine = br3.readLine()) != null) {
				arrayListTPorFN.add(ReadLine);
			}
			br3.close();

			while ((ReadLine = br5.readLine()) != null) {
				arrayListFPorTN.add(ReadLine);
			}
			br5.close();

			FreqStatisWithKeywords freqStatisWithKeywords = new FreqStatisWithKeywords();
			BufferedWriter bw = iOhandle.FileWriter(OutputPath);

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
			String InputPath1 = OutputPath;

			BufferedReader br6 = iOhandle.FileReader(InputPath1);
			BufferedWriter bw3 = iOhandle.FileWriter(OutputPathCHISouted);

			MapSorting mapSorting = new MapSorting();
			mapSorting.Sorting(br6, bw3, " ");

		}

		catch (Exception e) {
			System.out.println("特征计算过程出错");
			System.err.println(e);
		}
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
