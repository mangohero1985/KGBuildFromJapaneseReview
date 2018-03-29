/**
 * 
 */
package ScaledLikelihood;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import jp.uclab.io.IOhandle;

/**
 * @author mangohero1985
 * @create-time May 22, 2014 7:59:24 PM
 */
public class ScaledLikelihoodByPhase {
	// 所有词数统计(旧版)
	// static double NumAllZeroToOne = 146439;
	// static double NumAllOneToTwo = 528403;
	// static double NumAllTwoToThree = 3942138;
	// static double NumAllThreeToFour = 7805822;
	// static double NumAllFourToFive = 2958956;
	// static double NumAll = 15381758;
	// 词数统计(新版)single
	static double NumAllZeroToOne = 201469;
	static double NumAllOneToTwo = 730622;
	static double NumAllTwoToThree = 5558721;
	static double NumAllThreeToFour = 11060553;
	static double NumAllFourToFive = 4181713;
	static double NumAll = 21733078;

	// 词数统计(新版)Binary
	// static double NumAllZeroToOne = 199724;
	// static double NumAllOneToTwo = 724199;
	// static double NumAllTwoToThree = 5511444;
	// static double NumAllThreeToFour = 10975031;
	// static double NumAllFourToFive = 4149160;
	// static double NumAll = 21559558;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String FilePath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/scaled_feature/classify_by_score_word/wordfreq_";
		// String[] FileNumber = { FilePath + "0-1.txt", FilePath + "1-2.txt",
		// FilePath + "2-3.txt", FilePath + "3-4.txt", FilePath + "4-5.txt",
		// FilePath + "allWord.txt" };
		String[] FileNumber = { FilePath + "0-1.txt", FilePath + "1-2.txt", FilePath + "2-3.txt", FilePath + "3-4.txt", FilePath + "4-5.txt" };
		ArrayList<ArrayList<Double>> ArrayListSet = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> GradientResult = new ArrayList<ArrayList<Double>>();
		// 循环传入文件路径
		for (int i = 0; i < 5; i++) {
			ArrayList arryListTemp = ReadFeatureFreq(FileNumber[i]);
			ArrayListSet.add(arryListTemp);
		}
		Double FeatureFreqInClassOne;
		Double FeatureFreqInClassTwo;
		Double FeatureFreqInClassThree;
		Double FeatureFreqInClassFour;
		Double FeatureFreqInClassFive;
		for (int j = 0; j < ArrayListSet.get(0).size(); j++) {
			if (ArrayListSet.get(0).get(j) == 0) {
				FeatureFreqInClassOne = 1.0;
			}
			else {
				 FeatureFreqInClassOne = ArrayListSet.get(0).get(j);
			}
			if (ArrayListSet.get(1).get(j) == 0) {
				 FeatureFreqInClassTwo = 1.0;
			}
			else {
				 FeatureFreqInClassTwo = ArrayListSet.get(1).get(j);
			}
			if (ArrayListSet.get(2).get(j) == 0) {
				 FeatureFreqInClassThree = 1.0;
			}
			else {
				 FeatureFreqInClassThree = ArrayListSet.get(2).get(j);
			}
			if (ArrayListSet.get(3).get(j) < 10.0) {
				 FeatureFreqInClassFour = 100.0;
			}
			else {
				 FeatureFreqInClassFour = ArrayListSet.get(3).get(j);
			}
			if (ArrayListSet.get(4).get(j) == 0) {
				 FeatureFreqInClassFive = 1.0;
			}
			else {
				 FeatureFreqInClassFive = ArrayListSet.get(4).get(j);
			}

			// Double FeatureFreq = ArrayListSet.get(5).get(j);
			Double FeatureFreq = FeatureFreqInClassOne + FeatureFreqInClassTwo + FeatureFreqInClassThree + FeatureFreqInClassFour + FeatureFreqInClassFive;
			ArrayList<Double> Difference = ScaledlikeliCalculate(FeatureFreqInClassOne, FeatureFreqInClassTwo, FeatureFreqInClassThree, FeatureFreqInClassFour,
					FeatureFreqInClassFive, FeatureFreq);
			GradientResult.add(Difference);
		}
		// 读取词名称
		ArrayList<String> FeatureName = new ArrayList<String>();
		IOhandle io = new IOhandle();
		BufferedReader br = io.FileReader(FileNumber[0]);
		String Line = "";
		while ((Line = br.readLine()) != null) {
			// System.out.println(Line);
			String[] lineSplit = Line.split(" ");
			String Name = lineSplit[0];
			FeatureName.add(Name);
		}
		BufferedWriter bw = io
				.FileWriter("/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/scaled_feature/classify_by_score_word/ScaledLikelihoodResultByPhaseAndSum.txt");
		for (int k = 0; k < FeatureName.size(); k++) {
			// // 写入文件 名称+各段差值
			// bw.write(FeatureName.get(k) + " " + GradientResult.get(k));
			// bw.newLine();
			// bw.flush();
			// 写入文件 名称+各段差值+各段差值求和
			double sum = 0.0;
			for (int f = 0; f < 4; f++) {
				sum += GradientResult.get(k).get(f);
			}
			bw.write(FeatureName.get(k) + "\t" + GradientResult.get(k) + "\t" + sum);
			bw.newLine();
			bw.flush();
		}

	}

	// 从每个文件中读取特征词频
	public static ArrayList<Double> ReadFeatureFreq(String FileTemp) throws IOException {
		ArrayList<Double> FreqArralist = new ArrayList<Double>();
		IOhandle io = new IOhandle();
		BufferedReader br = io.FileReader(FileTemp);
		String Line = "";
		while ((Line = br.readLine()) != null) {
			// System.out.println(Line);
			String[] lineSplit = Line.split(" ");
			Double Frequence = Double.parseDouble(lineSplit[1]);
			FreqArralist.add(Frequence);
		}
		return FreqArralist;
	}

	public static ArrayList<Double> ScaledlikeliCalculate(Double F1, Double F2, Double F3, Double F4, Double F5, Double FAll) {
		// Double Gradient = null;
		// 计算每个词在每个类中的scaledLikelihood
		// Double ScaledLikehood1 = ((Weight+F1) / NumAllZeroToOne) /
		// ((FAll+5*Weight)/ NumAll);
		// Double ScaledLikehood2 = ((Weight+F2) / NumAllOneToTwo) /
		// ((FAll+5*Weight) / NumAll);
		// Double ScaledLikehood3 = ((Weight+F3) / NumAllTwoToThree) /
		// ((FAll+5*Weight) / NumAll);
		// Double ScaledLikehood4 = ((Weight+F4) / NumAllThreeToFour) /
		// ((FAll+5*Weight) / NumAll);
		// Double ScaledLikehood5 = ((Weight+F5) / NumAllFourToFive) /
		// ((FAll+5*Weight) / NumAll);
		Double ScaledLikehood1 = (F1 / NumAllZeroToOne) / (FAll / NumAll);
		Double ScaledLikehood2 = (F2 / NumAllOneToTwo) / (FAll / NumAll);
		Double ScaledLikehood3 = (F3 / NumAllTwoToThree) / (FAll / NumAll);
		Double ScaledLikehood4 = (F4 / NumAllThreeToFour) / (FAll / NumAll);
		Double ScaledLikehood5 = (F5 / NumAllFourToFive) / (FAll / NumAll);

		// Normalization(没有处理某个点出现特殊情况的情况)
		Double Normal1 = ScaledLikehood1 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
		Double Normal2 = ScaledLikehood2 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
		Double Normal3 = ScaledLikehood3 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
		Double Normal4 = ScaledLikehood4 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
		Double Normal5 = ScaledLikehood5 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
		// Sort
		Double[] NormalSort = { Normal1, Normal2, Normal3, Normal4, Normal5 };
		// 存储Normalization之后的差值
		ArrayList<Double> NormalMinus = new ArrayList<Double>();
		NormalMinus.add(Normal2 - Normal1);
		NormalMinus.add(Normal3 - Normal2);
		NormalMinus.add(Normal4 - Normal3);
		NormalMinus.add(Normal5 - Normal4);
		System.out.println(NormalMinus);
		return NormalMinus;
	}

}
