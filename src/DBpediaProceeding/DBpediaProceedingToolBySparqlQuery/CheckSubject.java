/**
 * 
 */
package DBpediaProceeding.DBpediaProceedingToolBySparqlQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DBpediaProceeding.SparqlQuery.QueryFromDBpedia;

/**
 * @author mangohero1985
 * @create-time Aug 20, 2014 9:36:22 PM
 */

//根据输入的dishElement 查找对应的subject,并返回 Map<dishElement, subjects>
public class CheckSubject {

	public ArrayList<Map<String, String>> Check(BufferedReader br) throws Exception {

		ArrayList<Map<String, String>> dishElementArrayList = new ArrayList<Map<String, String>>();
		// 保存由于网络请求失败被漏掉的dishElements
		ArrayList<String> missedElements = new ArrayList<String>();

		String Readline = "";
		QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
		String SubjResult = "";
		while ((Readline = br.readLine()) != null) {

			StringBuffer subjConcat = new StringBuffer();
			// 创建用于存储dishElement和subject的map
			Map<String, String> subjectMap = new HashMap<String, String>();
			String[] SpliteDishElements = Readline.split("::");
			for (int i = 0; i < SpliteDishElements.length; i++) {

				try {
					SubjResult = queryFromDBpedia.Subject(SpliteDishElements[i]);
					if (!SubjResult.equals("null")) {
						subjConcat.append(SubjResult + ",");
						continue;
					}
				}
				catch (Exception e) {
					System.err.println(Readline);
					System.out.println(e);
					missedElements.add(Readline);
				}

			}
			subjectMap.put(Readline, subjConcat.toString());
			dishElementArrayList.add(subjectMap);
		}
		while (!missedElements.isEmpty()) {
			StringBuffer subjConcat = new StringBuffer();
			// 创建用于存储dishElement和subject的map
			Map<String, String> subjectMap = new HashMap<String, String>();
			for (int i = 0; i < missedElements.size(); i++) {

				try {

					String[] SpliteDishElements = Readline.split("::");
					for (int k = 0; k < SpliteDishElements.length; k++) {
						SubjResult = queryFromDBpedia.Subject(SpliteDishElements[k]);
						if (!SubjResult.equals("null")) {
							subjConcat.append(SubjResult + ",");
						}
					}
									}
				catch (Exception e) {
					System.err.println(missedElements.get(i));
					System.out.println(e);
				}
				missedElements.remove(missedElements.get(i));
				subjectMap.put(Readline, subjConcat.toString());
				dishElementArrayList.add(subjectMap);
			}
			
		

		}

		return dishElementArrayList;
	}

}
