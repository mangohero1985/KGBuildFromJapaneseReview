/**
 * 
 */
package DBpediaProceeding.DBpediaProceedingToolBySparqlQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DBpediaProceeding.SparqlQuery.QueryFromDBpedia;

/**
 * @author mangohero1985
 * @create-time Aug 21, 2014 2:57:39 PM
 */

//根据输入的dishElements, 查找对应的redirect和redirectof, 并返回对应的Stringbuffer
public class CheckRedirect {

	public ArrayList<StringBuffer> Check(BufferedReader brMulti, BufferedReader brMono) throws Exception {

		// 读取multiName文件,内容存储在arralist中
		ArrayList<StringBuffer> multiArrayList = new ArrayList<StringBuffer>();

		String ReadLine = "null";
		while ((ReadLine = brMulti.readLine()) != null) {
			StringBuffer sb = new StringBuffer(ReadLine);
			multiArrayList.add(sb);
		}

		// 保存由于网络请求失败被漏掉的dishElements
		ArrayList<String> missedElements = new ArrayList<String>();

		// 读取MonoName文件,判断Key返回的redirect结果是否包含在MultiName中
		while ((ReadLine = brMono.readLine()) != null) {
			int flag = 0;
			try {
				QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
				if(ReadLine.isEmpty()) continue;
				String RedirectResult = queryFromDBpedia.Redirect(ReadLine);
				String[] spliteRedirect = RedirectResult.split(",");
				for (int i = 0; i < spliteRedirect.length; i++) {
					if (RedirectResult.isEmpty()||flag==1) {
						break;
					}
					// System.out.println(splite[i]);
					for (int j = 0; j < multiArrayList.size(); j++) {
						String[] spliteMultiName = multiArrayList.get(j).toString().split("::");
						for (int k = 0; k < spliteMultiName.length ; k++) {
							if (spliteMultiName[k].equals(spliteRedirect[i])) {
								// System.out.println(multiArrayList.get(j).toString());
								multiArrayList.get(j).append(ReadLine + "::");
								flag = 1;
							}
						}
					}
				}
				if (flag == 0) {
					StringBuffer sb = new StringBuffer(ReadLine + "::");
					multiArrayList.add(sb);
				}
			}
			catch (Exception e) {
				System.err.println(ReadLine);
				System.out.println(e);
				missedElements.add(ReadLine);
			}

		}
		while (!missedElements.isEmpty()) {
			for (int m = 0; m < missedElements.size(); m++) {
				int flag = 0;
				try {
					QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
					if(missedElements.get(m).isEmpty()) continue;
					String RedirectResult = queryFromDBpedia.Redirect(missedElements.get(m));
					String[] splite = RedirectResult.split(",");
					for (int i = 0; i < splite.length; i++) {
						if (RedirectResult.isEmpty()||flag==1) {
							break;
						}
						// System.out.println(splite[i]);
						for (int j = 0; j < multiArrayList.size(); j++) {
							String[] splite1 = multiArrayList.get(j).toString().split("::");
							for (int k = 0; k < splite1.length - 1; k++) {
								if (splite1[k].equals(splite[i])) {
									// System.out.println(multiArrayList.get(j).toString());
									multiArrayList.get(j).append(missedElements.get(m)+ "::");
									flag = 1;
								}
							}
						}
					}
					if (flag == 0) {
						StringBuffer sb = new StringBuffer(missedElements.get(m) + "::");
						multiArrayList.add(sb);
					}
					missedElements.remove(missedElements.get(m));

				}
				catch (Exception e) {
					System.err.println(missedElements.get(m));
					System.out.println(e);
				}
			}
		}
		return multiArrayList;
	}

}
