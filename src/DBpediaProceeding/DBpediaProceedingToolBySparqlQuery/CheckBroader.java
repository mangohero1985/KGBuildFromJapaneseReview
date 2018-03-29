/**
 * 
 */
package DBpediaProceeding.DBpediaProceedingToolBySparqlQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import weka.gui.treevisualizer.TreeBuild;

import DBpediaProceeding.SparqlQuery.QueryFromDBpedia;

/**
 * @author mangohero1985
 * @create-time Aug 22, 2014 10:29:14 PM
 */

// 根据输入的dishElements, 查找对应的broader和broadof,并返回上下边界的词条
public class CheckBroader {

	public ArrayList<String> Check(ArrayList<String> seeds) throws Exception {

		ArrayList<String> conceptArrayList = new ArrayList<String>();
		QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
		ArrayList<String> resultArrayList = new ArrayList<String>();

		for (int i = 0; i < seeds.size(); i++) {
			if (seeds.get(i).contains(" ")) {
				//continue;
				String temp = seeds.get(i).replace(" ", "_");
				seeds.remove(i);
				seeds.add(i, temp);
			}
			resultArrayList = queryFromDBpedia.Broader(seeds.get(i));
			for (int j = 0; j < resultArrayList.size(); j++) {
				String[] resultArrayListSplite = resultArrayList.get(j).split(",");
				for (int k = 0; k < resultArrayListSplite.length; k++) {
					if (!resultArrayListSplite[k].isEmpty()) {
						if (!conceptArrayList.contains(resultArrayListSplite[k])) conceptArrayList.add(resultArrayListSplite[k]);
					}
				}
			}
		}
		return conceptArrayList;
	}

	// 通过输入的原始词典以及新扩展的上下边界,计算新扩展的subject的语义相关度.并返回subject以及对应的得分
	public Map<String, Double> ComputeSemanticDistance(ArrayList<String> Concept, ArrayList<String> dictionary) {

		Map<String, Double> resultMap = new TreeMap<String, Double>();
		ArrayList<List<String>> relationArrayList = new ArrayList<List<String>>();
		QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();

		for (int i = 0; i < Concept.size(); i++) {
			int flag = 0;
			// if (Concept.get(i).contains(")")) break;
			// if (Concept.get(i).contains("(") || Concept.get(i).contains(")"))
			// Concept.get(i).replace("(", "_(");
			if (Concept.get(i).contains(" ")) {
				//continue;
				String temp = Concept.get(i).replace(" ", "_");
				Concept.remove(i);
				Concept.add(i, temp);
			}
			relationArrayList = queryFromDBpedia.CheckSemanticRela(Concept.get(i));
			for (int j = 0; j < relationArrayList.size(); j++) {
				List<String> temp = relationArrayList.get(j);
				String[] subjectSplite = temp.get(1).split(",");
				for (int k = 0; k < subjectSplite.length; k++) {
					if (!subjectSplite[k].equals(Concept.get(i)) && Concept.contains(subjectSplite[k])) {
						flag++;
						break;
					}
				}
			}
			double result = (double) flag / (double) relationArrayList.size();
			resultMap.put(Concept.get(i), result);
		}
		return resultMap;
	}

}
