/**
 * 
 */
package TestMain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import Tools.FreqStatisWithKeywords;
import Tools.SortAndCount.MapSorting;

import jp.uclab.io.IOhandle;

/**
 * @author mangohero1985
 * @create-time Jun 20, 2014 1:06:55 PM
 */
public class MutualInformation {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String DishName = "味噌煮込みうどん";
		int N = 185493;

		ArrayList<String> arrayList = new ArrayList<String>();

		IOhandle iOhandle = new IOhandle();
		String InputPathDishName = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/" + DishName + "1/DishNameSorted.txt";
		String InputPathReviews = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/AllReviewsPure.txt";
		String OutputPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/" + DishName + "Result.txt";

		// 互信息计算
		BufferedReader br = iOhandle.FileReader(InputPathDishName);
		BufferedReader br1 = iOhandle.FileReader(InputPathReviews);
		BufferedWriter bw = iOhandle.FileWriter(OutputPath);

		String ReadLine = "";

		while ((ReadLine = br1.readLine()) != null) {
			arrayList.add(ReadLine);
		}
		br1.close();

		FreqStatisWithKeywords freqStatisWithKeywords = new FreqStatisWithKeywords();
		int A = freqStatisWithKeywords.Statistic(arrayList, DishName);
		double Result = 0.0;
		while ((ReadLine = br.readLine()) != null) {

			String[] Splite = ReadLine.split("\\(");
			String Elements = Splite[0];

			int B = freqStatisWithKeywords.Statistic(arrayList, Elements);
			int AB = freqStatisWithKeywords.Statistic(arrayList, DishName, Elements);
			if ((N * AB) != 0.0 && (A * B) != 0.0) {
				Result = ((double) AB / (double) A) * Math.log((N * AB) / (A * B)) / Math.log(2);
				// Result = (double)AB/(double)A;
				if (Result < 0) {
					Result = 0;
				}
			}
			else {
				Result = 0;
			}

			bw.write(Splite[0] + "\t" + Result);
			bw.newLine();
			bw.flush();

		}

		br.close();
		bw.close();
		// 结果排序
		String InputPath1 = OutputPath;
		String OutputPath1 = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/" + DishName + "ResultSorted.txt";

		BufferedReader br2 = iOhandle.FileReader(InputPath1);
		BufferedWriter bw2 = iOhandle.FileWriter(OutputPath1);

		MapSorting mapSorting = new MapSorting();
		mapSorting.Sorting(br2, bw2, "\t");
		bw2.close();
		br2.close();
	}

}
