/**
 * 
 */
package DBpediaProceeding.DBpediaProceedingToolBySparqlQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import DBpediaProceeding.SparqlQuery.QueryFromDBpedia;

/**
 * @author mangohero1985
 * @create-time Aug 24, 2014 8:16:43 PM
 */
public class CheckHierarchy {

	public ArrayList<List<StringBuffer>> Hierarchy(BufferedReader br, String dishName) throws Exception {

		String Elements = dishName;
		ArrayList<List<String>> dishElemGroupArrayList = new ArrayList<List<String>>();

		String ReadLine = "";
		while ((ReadLine = br.readLine()) != null) {
			// 截取前半部分存入ElementGroup
			String[] elementGroup = ReadLine.split("=");
			// 继续截取形成每个Elements
			String[] elements = elementGroup[0].split("::");
			ArrayList<String> dishElementArrayList = new ArrayList<String>();
			for (int i = 0; i < elements.length; i++) {
				dishElementArrayList.add(elements[i]);
			}
			dishElemGroupArrayList.add(dishElementArrayList);
		}
		ArrayList<StringBuffer> tuplesArrayList = new ArrayList<StringBuffer>();
		ArrayList<List> resuArrayList = new ArrayList<List>();
		ArrayList<List<StringBuffer>> finalResultArrayList = new ArrayList<List<StringBuffer>>();
		StringBuffer firstTuple = new StringBuffer(Elements);
		tuplesArrayList.add(firstTuple);
		for (int Num = 0; Num < 4; Num++) {
			resuArrayList = ElementIterator(tuplesArrayList, dishElemGroupArrayList);
			tuplesArrayList = (ArrayList<StringBuffer>) resuArrayList.get(0);
			dishElemGroupArrayList = (ArrayList<List<String>>) resuArrayList.get(1);

			finalResultArrayList.add(tuplesArrayList);
		}
		return finalResultArrayList;
	}

	private static Map<Integer, ArrayList<List<String>>> ExistCheck(String Element, ArrayList<List<String>> dishElemGroupArrayList) {

		Map<Integer, ArrayList<List<String>>> resultMap = new TreeMap<Integer, ArrayList<List<String>>>();
		int flag = 0;
		for (int i = 0; i < dishElemGroupArrayList.size(); i++) {
			if (dishElemGroupArrayList.get(i).contains(Element)) {
				dishElemGroupArrayList.remove(i);
				flag++;
				resultMap.put(flag, dishElemGroupArrayList);
				break;
			}
		}

		return resultMap;
	}

	private static ArrayList<List> ElementIterator(ArrayList<StringBuffer> tuplesArrayList, ArrayList<List<String>> dishElemGroupArrayList) {

		ArrayList<StringBuffer> NewTuplesArrayList = new ArrayList<StringBuffer>();
		ArrayList<List> resultArrayList = new ArrayList<List>();
		try {
			for (int k = 0; k < tuplesArrayList.size(); k++) {
				String[] splitTuplesArrayList = tuplesArrayList.get(k).toString().split(",");
				StringBuffer tuples = new StringBuffer(tuplesArrayList.get(k).toString());
				QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
				String WikiLinkes = queryFromDBpedia.WikiLink(splitTuplesArrayList[splitTuplesArrayList.length - 1]);
				String[] spliteWikiLinkes = WikiLinkes.split(",");

				for (int i = 0; i < spliteWikiLinkes.length; i++) {
					Map<Integer, ArrayList<List<String>>> flagMap = ExistCheck(spliteWikiLinkes[i], dishElemGroupArrayList);
					Integer flag = 0;
					for (Iterator it = flagMap.keySet().iterator(); it.hasNext();) {
						flag = (Integer) it.next();
						dishElemGroupArrayList = flagMap.get(flag);
					}
					if (flag != 0) {
						StringBuffer NewTuple = new StringBuffer(tuples);
						NewTuple.append("," + spliteWikiLinkes[i]);
						NewTuplesArrayList.add(NewTuple);
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("checkHierarchy出错");
			System.out.println(e);
		}
		resultArrayList.add(NewTuplesArrayList);
		resultArrayList.add(dishElemGroupArrayList);

		return resultArrayList;
	}

}
