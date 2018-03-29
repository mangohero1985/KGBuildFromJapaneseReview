/**
 * 
 */
package TestMain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import jp.uclab.io.IOhandle;

import DishRecognition.DishName;
import DishRecognition.SegmentationAndPOS;
import DishRecognition.UploadDicAndKata;
import Extractor.ReviewExtract;
import Tools.ExecuteFromTerminal;
import Tools.SortAndCount.TestWord;

/**
 * @author mangohero1985
 * @create-time Jun 14, 2014 4:34:30 PM
 */
public class TestDishNameRecognition {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		int n = 3;
		String dictionary = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/AllBayesSorting.txt";
		String model = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/model.data";
		String fileName = "あんかけスパ";
		String dataSourceFile = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/" + fileName + "/" + fileName + n + ".txt";
		String filePath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/" + fileName + "/" + fileName + n + "/";
		File dirFile = null;
		dirFile = new File(filePath);
		if (!(dirFile.exists()) && !(dirFile.isDirectory())) {
			boolean creadok = dirFile.mkdirs();
			if (creadok) {
				System.out.println(" ok:创建文件夹成功！ ");
			}
			else {
				System.out.println(" err:创建文件夹失败！ ");
			}
		}
		IOhandle iOhandle = new IOhandle();

		// 从POI文件中抽取评论,并输出到文件
		String outputPath = filePath + "ReviewExtractResult.txt";

		BufferedReader br = iOhandle.FileReader(dataSourceFile);
		BufferedWriter bw = iOhandle.FileWriter(outputPath);
		ReviewExtract reviewExtract = new ReviewExtract();

		reviewExtract.ReviewResults(br, bw, dataSourceFile, filePath);
		// System.out.println(arrayListReview);

		// 分次,上传词性
		String inputPath1 = filePath + "ReviewExtractResult.txt";
		String outputPath1 = filePath + "SegmentationAndPOS.txt";
		//
		BufferedReader br1 = iOhandle.FileReader(inputPath1);
		BufferedWriter bw1 = iOhandle.FileWriter(outputPath1);
		SegmentationAndPOS segmentationAndPOS = new SegmentationAndPOS();

		segmentationAndPOS.SegmentAndAnotate(br1, bw1);

		// 上传词典,katakana
		String inputPath2 = filePath + "SegmentationAndPOS.txt";
		String outputPath2 = filePath + "UpLoadDicAndKata.txt";

		BufferedReader br2 = iOhandle.FileReader(inputPath2);
		BufferedWriter bw2 = iOhandle.FileWriter(outputPath2);
		UploadDicAndKata uploadDicAndKata = new UploadDicAndKata();

		uploadDicAndKata.upload(br2, bw2, dictionary);

		// 调用命令行并进行dishName识别
		String inputPath3 = filePath + "UpLoadDicAndKata.txt";
		String outputPath3 = filePath + "RecognitionResult.txt";
		ExecuteFromTerminal executeFromTerminal = new ExecuteFromTerminal();

		executeFromTerminal.execute("/Users/mangohero1985/Downloads/application/crf++/CRF++-0.58/crf_test" + " " + "-m" + " " + model + " " + inputPath3 + ">" + outputPath3);

		// 提取被识别的菜名
		String inputPath4 = filePath + "RecognitionResult.txt";
		String outputPath4 = filePath + "DishName.txt";

		BufferedReader br4 = iOhandle.FileReader(inputPath4);
		BufferedWriter bw4 = iOhandle.FileWriter(outputPath4);

		DishName dishName = new DishName();
		dishName.Selection(br4, bw4);

		// 菜名统计排序

		String inputPath5 = filePath + "DishName.txt";
		String outputPath5 = filePath + "DishNameSorted.txt";

		TestWord.sort(inputPath5, outputPath5);
		bw.close();
		br.close();
		bw1.close();
		br1.close();
		bw2.close();
		br2.close();
		bw4.close();
		br4.close();

	}

}
