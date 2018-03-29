/**
 * 
 */
package DBpediaProceedingToolBySparqlQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import SparqlQuery.QueryFromDBpedia;

/**
 * @author mangohero1985
 * @create-time Aug 22, 2014 1:09:52 AM
 */
public class CheckAugment {

	// 根据原始的suface通过subject扩展新的suface
	public ArrayList<StringBuffer> Augment(BufferedReader br) throws Exception {
		// // 装载返回值的resultArrayList
		// ArrayList<StringBuffer> resultArrayList = new
		// ArrayList<StringBuffer>();
		//
		// ArrayList<String> subjectArrayList = new ArrayList<String>();
		// String ReadLine = "";
		// // 把读取的内容存储到subjectArrayList中
		// while ((ReadLine = br.readLine()) != null) {
		//
		// subjectArrayList.add(ReadLine);
		// }
		// // 循环遍历subjectArrayList中的内容
		// for (int i = 0; i < subjectArrayList.size(); i++) {
		//
		// String[] spliteSubject =
		// subjectArrayList.get(i).toString().split("=");
		// String[] DishElements = spliteSubject[0].split("\\{");
		// String[] Subject = spliteSubject[1].split("\\}");
		// StringBuffer elemetnsStringBuffer = new StringBuffer();
		// // 本身带有subject行的操作
		// if (!spliteSubject[1].equals("}")) {
		//
		// String[] DishElementsSplite = DishElements[1].split("::");
		// for (int j = 0; j < DishElementsSplite.length; j++) {
		// int flag = 0;
		// // 判断element部分是不是已经包含原有的菜名
		// String[] elemetnsStringBufferSplite =
		// elemetnsStringBuffer.toString().split("::");
		// for (int s = 0; s < elemetnsStringBufferSplite.length; s++) {
		// if (!elemetnsStringBufferSplite[s].equals(DishElementsSplite[j])) {
		// if (!DishElementsSplite[j].contains("(")) {
		// flag++;
		// }
		// }
		// }
		// if (flag == elemetnsStringBufferSplite.length) {
		// elemetnsStringBuffer.append(DishElementsSplite[j] + "::");
		// flag = 0;
		// }
		// QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
		// try {
		// String RedirectResult =
		// queryFromDBpedia.Redirect(DishElementsSplite[j]);
		// if (!RedirectResult.isEmpty()) {
		// String[] RedirectResultSplite = RedirectResult.split(",");
		// for (int k = 0; k < RedirectResultSplite.length; k++) {
		// flag = 0;
		// String[] elemetnsStringBufferSplite2 =
		// elemetnsStringBuffer.toString().split("::");
		// for (int s = 0; s < elemetnsStringBufferSplite2.length; s++) {
		// if (!elemetnsStringBufferSplite2[s].equals(RedirectResultSplite[k]))
		// {
		// if (!RedirectResultSplite[k].contains("(")) {
		// flag++;
		//
		// }
		// }
		// }
		// if (flag == elemetnsStringBufferSplite2.length) {
		// elemetnsStringBuffer.append(RedirectResultSplite[k] + "::");
		// flag = 0;
		// }
		// }
		// }
		// }
		// catch (Exception e) {
		// System.out.println(DishElementsSplite[j]);
		// System.out.println(e);
		// }
		//
		// // System.out.println(RedirectResult);
		// // 判断返回结果并且继续向elements中添加新的surface
		//
		// }
		// elemetnsStringBuffer.insert(0, "{");
		// resultArrayList.add(elemetnsStringBuffer.append("=" + Subject[0] +
		// "}"));
		// }
		// else {
		//
		// String[] DishElementsSplite = DishElements[1].split("::");
		// for (int j = 0; j < DishElementsSplite.length; j++) {
		// int flag = 0;
		// // 判断element部分是不是已经包含原有的菜名
		// String[] elemetnsStringBufferSplite =
		// elemetnsStringBuffer.toString().split("::");
		// for (int s = 0; s < elemetnsStringBufferSplite.length; s++) {
		// if
		// (!elemetnsStringBufferSplite[s].toString().equals(DishElementsSplite[j]))
		// {
		// if (!DishElementsSplite[j].contains("(")) {
		// flag++;
		// }
		//
		// }
		// }
		// if (flag == elemetnsStringBufferSplite.length) {
		// elemetnsStringBuffer.append(DishElementsSplite[j] + "::");
		// flag = 0;
		// }
		// QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
		// try {
		// String RedirectResult =
		// queryFromDBpedia.Redirect(DishElementsSplite[j]);
		// // 判断返回结果并且继续向elements中添加新的surface
		// if (!RedirectResult.isEmpty()) {
		// String[] RedirectResultSplite = RedirectResult.split(",");
		// for (int k = 0; k < RedirectResultSplite.length; k++) {
		// flag = 0;
		// String[] elemetnsStringBufferSplite2 =
		// elemetnsStringBuffer.toString().split("::");
		// for (int s = 0; s < elemetnsStringBufferSplite2.length; s++) {
		// if
		// (!elemetnsStringBufferSplite2[s].toString().equals(RedirectResultSplite[k]))
		// {
		// if (!RedirectResultSplite[k].contains("(")) {
		// flag++;
		// }
		//
		// }
		// }
		// if (flag == elemetnsStringBufferSplite2.length) {
		// elemetnsStringBuffer.append(RedirectResultSplite[k] + "::");
		// flag = 0;
		// }
		//
		// }
		// }
		// }
		// catch (Exception e) {
		// System.out.println(DishElementsSplite[j]);
		// System.out.println(e);
		// }
		//
		// }
		// String[] elemetnsStringBufferSplite =
		// elemetnsStringBuffer.toString().split("::");
		// StringBuffer subjConcat = new StringBuffer();
		// for (int n = 0; n < elemetnsStringBufferSplite.length; n++) {
		// QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
		// try {
		// String SubjectResult =
		// queryFromDBpedia.Subject(elemetnsStringBufferSplite[n]);
		// if (!SubjectResult.equals("null")) {
		// subjConcat.append(SubjectResult + ",");
		// continue;
		// }
		// }
		// catch (Exception e) {
		// System.out.println(elemetnsStringBufferSplite[n]);
		// System.out.println(e);
		// }
		//
		// }
		// elemetnsStringBuffer.insert(0, "{");
		// resultArrayList.add(elemetnsStringBuffer.append("=" + subjConcat +
		// "}"));
		// }
		//
		// }
		// return resultArrayList;
		// }
		// 装载返回值的resultArrayList
		ArrayList<StringBuffer> resultArrayList = new ArrayList<StringBuffer>();

		ArrayList<String> subjectArrayList = new ArrayList<String>();
		String ReadLine = "";
		// 把读取的内容存储到subjectArrayList中
		while ((ReadLine = br.readLine()) != null) {

			subjectArrayList.add(ReadLine);
		}
		// 循环遍历subjectArrayList中的内容
		for (int i = 0; i < subjectArrayList.size(); i++) {

			String[] spliteSubject = subjectArrayList.get(i).toString().split("=");
			String[] DishElements = spliteSubject[0].split("\\{");
			String[] Subject = spliteSubject[1].split("\\}");
			StringBuffer elemetnsStringBuffer = new StringBuffer();
			// 本身带有subject行的操作
			if (!spliteSubject[1].equals("}")) {

				String[] DishElementsSplite = DishElements[1].split("::");
				for (int j = 0; j < DishElementsSplite.length; j++) {
					int flag = 0;
					// 判断element部分是不是已经包含原有的菜名
					String[] elemetnsStringBufferSplite = elemetnsStringBuffer.toString().split("::");
					for (int s = 0; s < elemetnsStringBufferSplite.length; s++) {
						if (!elemetnsStringBufferSplite[s].equals(DishElementsSplite[j])) {
							if (DishElementsSplite[j].contains(" ")) DishElementsSplite[j]=DishElementsSplite[j].replace(" ", "_");
							// if (!DishElementsSplite[j].contains("(")) {
							flag++;
							// }
						}
					}
					if (flag == elemetnsStringBufferSplite.length) {
						elemetnsStringBuffer.append(DishElementsSplite[j] + "::");
						flag = 0;
					}
					QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
					try {
						String RedirectResult = queryFromDBpedia.Redirect(DishElementsSplite[j]);
						if (!RedirectResult.isEmpty()) {
							String[] RedirectResultSplite = RedirectResult.split(",");
							for (int k = 0; k < RedirectResultSplite.length; k++) {
								flag = 0;
								String[] elemetnsStringBufferSplite2 = elemetnsStringBuffer.toString().split("::");
								for (int s = 0; s < elemetnsStringBufferSplite2.length; s++) {
									if (!elemetnsStringBufferSplite2[s].equals(RedirectResultSplite[k])) {
										if (RedirectResultSplite[k].contains(" ")) RedirectResultSplite[k]=RedirectResultSplite[k].replace(" ", "_");
										// if
										// (!RedirectResultSplite[k].contains("("))
										// {
										flag++;

										// }
									}
								}
								if (flag == elemetnsStringBufferSplite2.length) {
									elemetnsStringBuffer.append(RedirectResultSplite[k] + "::");
									flag = 0;
								}
							}
						}
					}
					catch (Exception e) {
						System.out.println(DishElementsSplite[j]);
						System.out.println(e);
					}

					// System.out.println(RedirectResult);
					// 判断返回结果并且继续向elements中添加新的surface

				}
				// 加入新找到的subject
				String[] SpliteElementStringBuffer = elemetnsStringBuffer.toString().split("::");
				QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
				ArrayList<String> subjectArrayList2 = new ArrayList<String>();
				for (int j = 0; j < SpliteElementStringBuffer.length; j++) {
					// if (SpliteElementStringBuffer[j].contains(" ") ||
					// SpliteElementStringBuffer[j].contains("(")) break;
					if (SpliteElementStringBuffer[j].contains(")")) {
						SpliteElementStringBuffer[j]=SpliteElementStringBuffer[j].replace(")", ")");
					}
//					if (SpliteElementStringBuffer[j].contains("(")) {
//						SpliteElementStringBuffer[j].replace(" (", "_(");
//					}
					if (SpliteElementStringBuffer[j].contains(" ")) {
						SpliteElementStringBuffer[j]=SpliteElementStringBuffer[j].replace(" ", "_");
					}

					String Subject2 = queryFromDBpedia.Subject(SpliteElementStringBuffer[j]);
					if (!subjectArrayList2.contains(Subject2) && !Subject2.contains("null")) {
						subjectArrayList2.add(Subject2);
					}
				}
				StringBuffer addNewSubject = new StringBuffer();
				for (int k = 0; k < subjectArrayList2.size(); k++) {
					addNewSubject.append(subjectArrayList2.get(k) + ",");
				}
				elemetnsStringBuffer.insert(0, "{");
				resultArrayList.add(elemetnsStringBuffer.append("=" + addNewSubject.toString() + "}"));
			}
			else {

				String[] DishElementsSplite = DishElements[1].split("::");
				for (int j = 0; j < DishElementsSplite.length; j++) {
					int flag = 0;
					// 判断element部分是不是已经包含原有的菜名
					String[] elemetnsStringBufferSplite = elemetnsStringBuffer.toString().split("::");
					for (int s = 0; s < elemetnsStringBufferSplite.length; s++) {
						if (!elemetnsStringBufferSplite[s].toString().equals(DishElementsSplite[j])) {
							if (DishElementsSplite[j].contains(" ")) DishElementsSplite[j]=DishElementsSplite[j].replace(" ", "_");
							// if (!DishElementsSplite[j].contains("(")) {
							flag++;
							// }

						}
					}
					if (flag == elemetnsStringBufferSplite.length) {
						elemetnsStringBuffer.append(DishElementsSplite[j] + "::");
						flag = 0;
					}
					QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
					try {
						String RedirectResult = queryFromDBpedia.Redirect(DishElementsSplite[j]);
						// 判断返回结果并且继续向elements中添加新的surface
						if (!RedirectResult.isEmpty()) {
							String[] RedirectResultSplite = RedirectResult.split(",");
							for (int k = 0; k < RedirectResultSplite.length; k++) {
								flag = 0;
								String[] elemetnsStringBufferSplite2 = elemetnsStringBuffer.toString().split("::");
								for (int s = 0; s < elemetnsStringBufferSplite2.length; s++) {
									if (!elemetnsStringBufferSplite2[s].toString().equals(RedirectResultSplite[k])) {
										if (RedirectResultSplite[k].contains(" ")) RedirectResultSplite[k]=RedirectResultSplite[k].replace(" ", "_");
										// if
										// (!RedirectResultSplite[k].contains("("))
										// {
										flag++;
										// }

									}
								}
								if (flag == elemetnsStringBufferSplite2.length) {
									elemetnsStringBuffer.append(RedirectResultSplite[k] + "::");
									flag = 0;
								}

							}
						}
					}
					catch (Exception e) {
						System.out.println(DishElementsSplite[j]);
						System.out.println(e);
					}

				}
				String[] elemetnsStringBufferSplite = elemetnsStringBuffer.toString().split("::");
				StringBuffer subjConcat = new StringBuffer();
				for (int n = 0; n < elemetnsStringBufferSplite.length; n++) {
					QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
					try {
						String SubjectResult = queryFromDBpedia.Subject(elemetnsStringBufferSplite[n]);
						if (!SubjectResult.equals("null")) {
							subjConcat.append(SubjectResult + ",");
							continue;
						}
					}
					catch (Exception e) {
						System.out.println(elemetnsStringBufferSplite[n]);
						System.out.println(e);
					}

				}
				// 加入新找到的subject
				String[] SpliteElementStringBuffer = elemetnsStringBuffer.toString().split("::");
				QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
				ArrayList<String> subjectArrayList2 = new ArrayList<String>();
				for (int j = 0; j < SpliteElementStringBuffer.length; j++) {
					if (SpliteElementStringBuffer[j].contains(")")) {
						SpliteElementStringBuffer[j]=SpliteElementStringBuffer[j].replace(")", ")");
					}
//					if (SpliteElementStringBuffer[j].contains("(")) {
//						SpliteElementStringBuffer[j].replace(" (", "_(");
//					}
					if (SpliteElementStringBuffer[j].contains(" ")) {
						SpliteElementStringBuffer[j]=SpliteElementStringBuffer[j].replace(" ", "_");
					}
					String Subject2 = queryFromDBpedia.Subject(SpliteElementStringBuffer[j]);
					if (!subjectArrayList2.contains(Subject2) && !Subject2.contains("null")) {
						subjectArrayList2.add(Subject2);
					}
				}
				StringBuffer addNewSubject = new StringBuffer();
				for (int k = 0; k < subjectArrayList2.size(); k++) {
					addNewSubject.append(subjectArrayList2.get(k) + ",");
				}
				elemetnsStringBuffer.insert(0, "{");
				resultArrayList.add(elemetnsStringBuffer.append("=" + subjConcat + "}"));
			}

		}
		return resultArrayList;
	}

}
