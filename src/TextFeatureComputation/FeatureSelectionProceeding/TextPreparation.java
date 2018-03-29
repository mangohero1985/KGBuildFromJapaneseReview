/**
 * 
 */
package TextFeatureComputation.FeatureSelectionProceeding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import TestMain.CreatDir;
import TextProceeding.ioProceeing.IOhandle;
import Tools.ReservNSentence;

/**
 * @author mangohero1985
 * @create-time Aug 25, 2014 2:40:33 PM
 */
public class TextPreparation {

	public void Prepare(String DishName, String Num, String dir, String PercentName, String Symbol) throws Exception {
		try {
			int N = Integer.parseInt(Num);
			IOhandle iOhandle = new IOhandle();

			// 判断如果不存在文件夹创建文件夹
			CreatDir creatDir = new CreatDir();
			creatDir.createDir(dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "TextFeatureComputation" + "/");

			String InputPath = dir + DishName + "/" + DishName + ".txt";
			String OutputPathNSentence = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "TextFeatureComputation" + "/" + DishName + N + ".txt";
			String OutputPathWithoutNSentence = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "TextFeatureComputation" + "/" + DishName + "Without" + N
					+ ".txt";

			// 数据准备
			BufferedReader br = iOhandle.FileReader(InputPath);
			BufferedWriter bw1 = iOhandle.FileWriter(OutputPathNSentence);
			BufferedWriter bw2 = iOhandle.FileWriter(OutputPathWithoutNSentence);

			ReservNSentence reservNSentence = new ReservNSentence();
			ArrayList<String> arrayList = reservNSentence.ResearvWithoutN(br, bw1, N, DishName);

			String ReadLine = "";
			BufferedReader br2 = iOhandle.FileReader(InputPath);

			while ((ReadLine = br2.readLine()) != null) {
				String[] splite = ReadLine.split("。");
				for (int i = 0; i < splite.length; i++) {
					if (!arrayList.contains(splite[i])) {
						bw2.write(splite[i]);
						bw2.newLine();
						bw2.flush();
					}

				}
			}
			br.close();
			br2.close();
			bw1.close();
			bw2.close();
		}
		catch (Exception e) {
			System.out.println("文件预处理不分出错");
			System.out.println(e);
		}

	}

}
