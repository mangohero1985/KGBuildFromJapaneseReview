/**
 * 
 */
package ScaledLikelihood;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import jp.uclab.io.IOhandle;

/**
 * @author mangohero1985
 * @create-time May 21, 2014 6:06:44 PM
 */
public class ScaledLikelihoodCalculate {

	/**
	 * @param args
	 */
	// 所有词数统计
	static double NumAllZeroToOne = 146439;
	static double NumAllOneToTwo = 528403;
	static double NumAllTwoToThree = 3942138;
	static double NumAllThreeToFour = 7805822;
	static double NumAllFourToFive = 2958956;
	static double NumAll = 15381758;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String FilePath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/scaled_feature/classify_by_score_word/wordfreq_";
		String[] FileNumber = { FilePath + "0-1.txt", FilePath + "1-2.txt", FilePath + "2-3.txt", FilePath + "3-4.txt", FilePath + "4-5.txt", FilePath + "allWord.txt" };
		ArrayList<ArrayList<Double>> ArrayListSet = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> GradientResult = new ArrayList<Double>();
		// 循环传入文件路径
		for (int i = 0; i < 6; i++) {
			ArrayList arryListTemp = ReadFeatureFreq(FileNumber[i]);
			ArrayListSet.add(arryListTemp);
		}
		for (int j = 0; j < ArrayListSet.get(0).size(); j++) {
			Double FeatureFreqInClassOne = ArrayListSet.get(0).get(j);
			Double FeatureFreqInClassTwo = ArrayListSet.get(1).get(j);
			Double FeatureFreqInClassThree = ArrayListSet.get(2).get(j);
			Double FeatureFreqInClassFour = ArrayListSet.get(3).get(j);
			Double FeatureFreqInClassFive = ArrayListSet.get(4).get(j);
			Double FeatureFreq = ArrayListSet.get(5).get(j);
			Double Gradient = ScaledlikeliCalculate(FeatureFreqInClassOne, FeatureFreqInClassTwo, FeatureFreqInClassThree, FeatureFreqInClassFour, FeatureFreqInClassFive,
					FeatureFreq);
			GradientResult.add(Gradient);
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
				.FileWriter("/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/scaled_feature/classify_by_score_word/ScaledLikelihoodResult1.txt");
		for (int k = 0; k < FeatureName.size(); k++) {
			bw.write(FeatureName.get(k) + " " + GradientResult.get(k));
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

	public static Double ScaledlikeliCalculate(Double F1, Double F2, Double F3, Double F4, Double F5, Double FAll) {
		Double Gradient = null;
		// 计算每个词在每个类中的scaledLikelihood
		Double ScaledLikehood1 = (F1 / NumAllZeroToOne) / (FAll / NumAll);
		Double ScaledLikehood2 = (F2 / NumAllOneToTwo) / (FAll / NumAll);
		Double ScaledLikehood3 = (F3 / NumAllTwoToThree) / (FAll / NumAll);
		Double ScaledLikehood4 = (F4 / NumAllThreeToFour) / (FAll / NumAll);
		Double ScaledLikehood5 = (F5 / NumAllFourToFive) / (FAll / NumAll);
		//if (ScaledLikehood2 < 3 * ScaledLikehood4) {
			// Normalization
			Double Normal1 = ScaledLikehood1 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
			Double Normal2 = ScaledLikehood2 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
			Double Normal3 = ScaledLikehood3 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
			Double Normal4 = ScaledLikehood4 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
			Double Normal5 = ScaledLikehood5 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
			// Sort
			Double[] NormalSort = { Normal1, Normal2, Normal3, Normal4, Normal5 };
			if (NormalSort[0] > NormalSort[4]) {
				Arrays.sort(NormalSort);
				Gradient = NormalSort[0] - NormalSort[4];
			}
			else {
				Arrays.sort(NormalSort);
				if (NormalSort[0] == 0.0) {
					Gradient = NormalSort[4] - NormalSort[1];
				}
				else {
					Gradient = NormalSort[4] - NormalSort[0];
				}
			}
		//}
//		else {
//			// Normalization(出现某个点严重偏离的情况,这里默认为第二个值)
//			Double Normal1 = ScaledLikehood1 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
//			//Double Normal2 = ScaledLikehood2 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
//			Double Normal3 = ScaledLikehood3 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
//			Double Normal4 = ScaledLikehood4 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
//			Double Normal5 = ScaledLikehood5 / (ScaledLikehood1 + ScaledLikehood2 + ScaledLikehood3 + ScaledLikehood4 + ScaledLikehood5);
//			// Sort
//			Double[] NormalSort = { Normal1, Normal3, Normal4, Normal5 };
//			if (NormalSort[0] > NormalSort[3]) {
//				Arrays.sort(NormalSort);
//				Gradient = NormalSort[0] - NormalSort[3];
//			}
//			else {
//				Arrays.sort(NormalSort);
//				if (NormalSort[0] == 0.0) {
//					Gradient = NormalSort[3] - NormalSort[1];
//				}
//				else {
//					Gradient = NormalSort[3] - NormalSort[0];
//				}
//			}
//
//		}
		System.out.println(Gradient);
		return Gradient;
	}

}
