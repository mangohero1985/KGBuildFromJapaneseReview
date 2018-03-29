/**
 * 
 */
package ScaledLikelihood;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author mangohero1985
 * @create-time May 22, 2014 7:45:38 PM 
 * 给特征词scaledlikelihood的结果进行排序
 */
public class ScaledLikelihoodSort {

	public static void main(String[] args) throws Exception {
		
		String inPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/scaled_feature/classify_by_score_word/ScaledLikelihoodResultByPhaseAndSum.txt";
		String outPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/scaled_feature/classify_by_score_word/ScaledLikelihoodResultByPhaseAndSumSorted.txt";
		String readString = null;

		FileReader fr = new FileReader(inPath);
		BufferedReader br = new BufferedReader(fr);

		FileWriter fw = new FileWriter(outPath);
		BufferedWriter bw = new BufferedWriter(fw);

		Map<String, Double> keyfreqs = new HashMap<String, Double>();

		while ((readString = br.readLine()) != null) {

//			String[] splitReadStrings = readString.split("&");
//			String menuNameAndCount = splitReadStrings[0];
            //把读取的数据分割并保存
			String[] spliteMenuNameAndCount = readString.split("\t");
			
			//给result排序
			//keyfreqs.put(spliteMenuNameAndCount[0], Double.parseDouble(spliteMenuNameAndCount[1]));
			
			//给phaseAndSum排序
			keyfreqs.put(spliteMenuNameAndCount[0], Double.parseDouble(spliteMenuNameAndCount[2]));
			

		}
		// 对map按照value进行排序

		ArrayList<Entry<String, Double>> l = new ArrayList<Entry<String, Double>>(keyfreqs.entrySet());

		Collections.sort(l, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				Double v1 = o1.getValue();
				Double v2 = o2.getValue();
				return v1.compareTo(v2);
			}
		});

		for (Entry<String, Double> e : l) {
			System.out.println(e.getKey() + " " + e.getValue());
			bw.write(e.getKey() + " " + e.getValue());
			bw.newLine();
			bw.flush();
		}

		System.out.println("处理完成");
		bw.close();
		br.close();

	}

}
