/**
 * 
 */
package DBpediaProceedingToolBySparqlQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import SparqlQuery.QueryFromDBpedia;

/**
 * @author mangohero1985
 * @create-time Aug 20, 2014 1:41:30 PM
 */
public class SaveCoveragedElements {

	public ArrayList<String> save(int Percent, BufferedReader br) throws Exception {

		// 创建query对象
		QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
		// 保存被覆盖的dishElements
		ArrayList<String> coveragedElements = new ArrayList<String>();

		// 保存由于网络请求失败被漏掉的dishElements
		ArrayList<String> missedElements = new ArrayList<String>();
		// 从文件读取已识别的菜名
		String Readline = "";
		ArrayList<String> dishArrayList = new ArrayList<String>();
		while ((Readline = br.readLine()) != null) {
			String[] splite = Readline.split("\\(");
			dishArrayList.add(splite[0]);
		}

		// 十等分
		int divide = dishArrayList.size() / 10;

		// 截取指定的percent
		List<String> sublist = dishArrayList.subList(0, divide * Percent);

		// 查询DBpedia

		for (int j = 0; j < sublist.size(); j++) {
			// 计算每个子列表的覆盖度

			int flag = 0;
			try {
				flag = queryFromDBpedia.IsEmptyJudgement(sublist.get(j));
				if (flag == 1) {
					coveragedElements.add(sublist.get(j));
				}
			}
			catch (Exception e) {
				System.err.println(sublist.get(j));
				System.out.println(e);
				missedElements.add(sublist.get(j));
			}

		}
		//迭代查询由于网络请求问题遗漏的elements
		while (!missedElements.isEmpty()) {
			for (int i = 0; i < missedElements.size(); i++) {
				int flag = 0;
				try {
					flag = queryFromDBpedia.IsEmptyJudgement(sublist.get(i));
					if (flag == 1) {
						if (!coveragedElements.contains(sublist.get(i))) {
							coveragedElements.add(sublist.get(i));
							missedElements.remove(sublist.get(i));
						}
					}
				}
				catch (Exception e) {
					System.err.println(sublist.get(i));
					System.out.println(e);
				}
			}
		}
		return coveragedElements;

	}

}
