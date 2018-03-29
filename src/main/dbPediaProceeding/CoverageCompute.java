/**
 * 
 */
package main.dbPediaProceeding;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import TextProceeding.ioProceeing.IOhandle;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import DBpediaProceeding.SparqlQuery.QueryFromDBpedia;

/**
 * @author mangohero1985
 * @create-time Aug 14, 2014 8:45:10 PM
 */
public class CoverageCompute {

	/**
	 * @param args
	 */
	// 设置菜名数组
	static String[] dish = { "モーニング", "台湾ラーメン", "名古屋コーチン", "味噌おでん", "味噌カツ", "味噌煮込みうどん", "天むす", "手羽先" };
	// 设定变量名称
	//static String DishName = "ひつまぶし";

	public static void main(String[] args) throws IOException, RowsExceededException, WriteException {
		String DishName ="";
		for (int d = 0; d < 8; d++) {
			// 创建excel文件
			DishName= dish[d];
			WritableWorkbook book = Workbook.createWorkbook(new File("/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/" + DishName
					+ "/" + DishName + ".xls"));
			// 创建工作表
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 循环分别读取1,2,3
			for (int SentenceNum = 1; SentenceNum <= 3; SentenceNum++) {

				// 创建excel文件
				String[] excelTitle = { DishName.concat(Integer.toString(1)), DishName.concat(Integer.toString(2)), DishName.concat(Integer.toString(3)) };
				// 写入excel标题
				sheet.addCell(new Label(SentenceNum - 1, 0, excelTitle[SentenceNum - 1]));
				// 读取文件
				System.out.println("读取文件" + DishName + SentenceNum);
				String InputPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/" + DishName + "/" + DishName + SentenceNum
						+ "/DishNameSorted.txt";
				IOhandle iOhandle = new IOhandle();
				BufferedReader br = iOhandle.FileReader(InputPath);

				String Readline = "";
				ArrayList<String> dishArrayList = new ArrayList<String>();
				while ((Readline = br.readLine()) != null) {
					String[] splite = Readline.split("\\(");
					dishArrayList.add(splite[0]);
				}
				// 十等分
				int divide = dishArrayList.size() / 10;

				// 写入excel文件
				// 计算coverage
				for (int i = 0; i < 10; i++) {
					List<String> sublist = dishArrayList.subList(i * divide, divide * (i + 1));
					float sum = 0;
					System.out.println("读取第" + (i + 1) + "组");
					for (int j = 0; j < sublist.size(); j++) {
						// 计算每个子列表的覆盖度
						QueryFromDBpedia queryFromDBpedia = new QueryFromDBpedia();
						int flag = 0;
						try {
							flag = queryFromDBpedia.IsEmptyJudgement(sublist.get(j));
						}
						catch (Exception e) {
							System.err.println(sublist.get(j));
							System.out.println(e);
						}

						if (flag != 0) {
							sum++;
						}

					}
					float result = sum / sublist.size();
					System.out.println(result);
					sheet.addCell(new Label(SentenceNum - 1, i, Float.toString(result)));

				}
			}
			book.write();
			book.close();
		}
	}
}
