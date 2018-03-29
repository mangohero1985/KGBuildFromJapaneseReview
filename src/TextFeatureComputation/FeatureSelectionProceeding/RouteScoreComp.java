/**
 * 
 */
package TextFeatureComputation.FeatureSelectionProceeding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import TextProceeding.ioProceeing.IOhandle;
import Tools.SortAndCount.MapSorting;

/**
 * @author mangohero1985
 * @create-time Aug 25, 2014 5:07:45 PM
 */
public class RouteScoreComp {

	public void Compute(String DishName, String Num, String dir, String PercentName, String Symbol) {
		int N = Integer.parseInt(Num);
		String ReadLine = "";

		try {
			IOhandle iOhandle = new IOhandle();
			String SubjectAugmentInputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "subjectAugmentWithoutNoise.txt";
			String FeatureScoreInputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "TextFeatureComputation" + "/" + DishName + N + Symbol
					+ "WithoutNoise.txt";
			String HierarchyInputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "HierarchyExtraction.txt";
			String OutputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "TextFeatureComputation" + "/" + DishName + N + Symbol + "RountScore.txt";
			String SortingOutputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "TextFeatureComputation" + "/" + DishName + N + Symbol
					+ "RountScoreSorted.txt";

			Map<ArrayList<String>, Double> tempMap = new HashMap<ArrayList<String>, Double>();
			BufferedReader SubjectAugmentBr = iOhandle.FileReader(SubjectAugmentInputPath);

			// 读取subjectAugmentWithoutNoise.txt文件存入Map
			while ((ReadLine = SubjectAugmentBr.readLine()) != null) {
				ArrayList<String> elementsaArrayList = new ArrayList<String>();
				String[] spliteReadLine = ReadLine.split("=");
				String[] elementsGroup = spliteReadLine[0].split("::");
				for (int i = 0; i < elementsGroup.length; i++) {
					elementsaArrayList.add(elementsGroup[i]);
				}
				tempMap.put(elementsaArrayList, 0.0);
			}
			// System.out.println(tempMap.size());

			// 读取出去噪音的featureComp文件
			ArrayList<ArrayList> elementGroupArrayList = new ArrayList<ArrayList>();
			ArrayList<ArrayList> scoreArrayList = new ArrayList<ArrayList>();
			for (Iterator it = tempMap.keySet().iterator(); it.hasNext();) {
				BufferedReader FeatureScoreBr = iOhandle.FileReader(FeatureScoreInputPath);
				ArrayList<String> elememtsArrayList = (ArrayList<String>) it.next();
				double Score = 0.0;
				while ((ReadLine = FeatureScoreBr.readLine()) != null) {
					String[] spliteReadLine = ReadLine.split(" ");
					if (elememtsArrayList.contains(spliteReadLine[0])) {
						Score = Score + Double.parseDouble(spliteReadLine[1]);
					}
				}
				FeatureScoreBr.close();
				elementGroupArrayList.add(elememtsArrayList);
				ArrayList<Double> tempScoreArrayList = new ArrayList<Double>();
				tempScoreArrayList.add(Score);
				scoreArrayList.add(tempScoreArrayList);
				// featureScoreMap.put(elememtsArrayList, Score);
			}
			ArrayList<ArrayList<ArrayList>> keyValueArrayList = new ArrayList<ArrayList<ArrayList>>();
			keyValueArrayList.add(elementGroupArrayList);
			keyValueArrayList.add(scoreArrayList);

			// 计算routeScore
			ArrayList<ArrayList> resultArrayList = new ArrayList<ArrayList>();
			BufferedReader HierarchyBr = iOhandle.FileReader(HierarchyInputPath);
			while ((ReadLine = HierarchyBr.readLine()) != null) {
				ArrayList<ArrayList> tempArrayList = new ArrayList<ArrayList>();
				String[] spliteReadLine = ReadLine.split(",");
				ArrayList<String> elementArrayList = new ArrayList<String>();
				ArrayList<Double> scoreArrayList2 = new ArrayList<Double>();
				for (int i = 0; i < spliteReadLine.length; i++) {
					for (int j = 0; j < keyValueArrayList.get(0).size(); j++) {
						if (keyValueArrayList.get(0).get(j).contains(spliteReadLine[i])) {
							elementArrayList.add(spliteReadLine[i]);
							scoreArrayList2.add((Double) keyValueArrayList.get(1).get(j).get(0));
						}
					}
				}
				resultArrayList.add(elementArrayList);
				resultArrayList.add(scoreArrayList2);
			}

			BufferedWriter bw = iOhandle.FileWriter(OutputPath);
			for (int i = 0; i < resultArrayList.size(); i += 2) {
				Double SumScore = 0.0;
				ArrayList<String> elementArrayList = new ArrayList<String>();
				ArrayList<Double> scoreArrayList2 = new ArrayList<Double>();
				ArrayList<Double> sumScoreArrayList = new ArrayList<Double>();
				for (int j = 0; j < resultArrayList.get(i).size(); j++) {
					elementArrayList.add(resultArrayList.get(i).get(j).toString());
					scoreArrayList2.add((Double) resultArrayList.get(i + 1).get(j));
					SumScore += (Double) resultArrayList.get(i + 1).get(j) / resultArrayList.get(i).size();
					sumScoreArrayList.add(SumScore);
				}
				StringBuffer tempStringBuffer = new StringBuffer();
				for (int k = 0; k < elementArrayList.size(); k++) {
					tempStringBuffer.append(elementArrayList.get(k) + "=" + scoreArrayList2.get(k).toString() + ",");
				}
				bw.write(tempStringBuffer + "---" + sumScoreArrayList.get(sumScoreArrayList.size() - 1));
				bw.newLine();
				bw.flush();

			}
			System.out.println("routeScore计算完成!");
			// ArrayList<Map<String, Double>> resultArrayList = new
			// ArrayList<Map<String, Double>>();
			// BufferedReader HierarchyBr =
			// iOhandle.FileReader(HierarchyInputPath);
			// while ((ReadLine = HierarchyBr.readLine()) != null) {
			// Map<String, Double> tempMap2 = new TreeMap<String, Double>();
			// String[] spliteReadLine = ReadLine.split(",");
			// for (int i = 0; i < spliteReadLine.length; i++) {
			// Map<ArrayList<String>, Double> TempFeatureScoreMap =
			// featureScoreMap;
			// for (Iterator it = TempFeatureScoreMap.keySet().iterator();
			// it.hasNext();) {
			// ArrayList<String> elememtsArrayList = (ArrayList<String>)
			// it.next();
			// System.out.println("");
			// if (elememtsArrayList.contains(spliteReadLine[i])) {
			// tempMap2.put(spliteReadLine[i],
			// TempFeatureScoreMap.get(elememtsArrayList));
			// }
			// }
			// }
			// resultArrayList.add(tempMap2);
			// }
			// BufferedWriter bw = iOhandle.FileWriter(OutputPath);
			// for (int i = 0; i < resultArrayList.size(); i++) {
			// double Score = 0.0;
			// Double Number = (double) resultArrayList.get(i).size();
			// for (Iterator it = resultArrayList.get(i).keySet().iterator();
			// it.hasNext();) {
			// String Key = it.next().toString();
			//
			// Score = Score + resultArrayList.get(i).get(Key) / Number;
			// }
			// bw.write(resultArrayList.get(i) + "---" + Score);
			// bw.newLine();
			// bw.flush();
			// }
			// System.out.println("routeScore计算完成!");

			// 结果排序
			BufferedReader routeScoreInputPath = iOhandle.FileReader(OutputPath);
			BufferedWriter routeSortedOutputPath = iOhandle.FileWriter(SortingOutputPath);
			MapSorting mapSorting = new MapSorting();
			mapSorting.Sorting(routeScoreInputPath, routeSortedOutputPath, "---");
		}
		catch (Exception e) {
			System.err.println("计算路径分数出错");
			System.out.println(e);
		}
	}

}
