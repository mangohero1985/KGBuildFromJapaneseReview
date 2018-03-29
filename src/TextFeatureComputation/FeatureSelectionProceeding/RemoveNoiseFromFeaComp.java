/**
 * 
 */
package TextFeatureComputation.FeatureSelectionProceeding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import TextProceeding.ioProceeing.IOhandle;

/**
 * @author mangohero1985
 * @create-time Aug 25, 2014 4:16:42 PM
 */
public class RemoveNoiseFromFeaComp {

	public void Remove(String DishName, String Num, String dir, String PercentName, String Symbol) {
		int N = Integer.parseInt(Num);
		String ReadLine = "";
		try {
			IOhandle iOhandle = new IOhandle();
			String SubjectAugInputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "subjectAugmentWithoutNoise.txt";
			String FeatureCompInputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "TextFeatureComputation" + "/" + DishName + N + Symbol + "sorted.txt";
			String OutputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "TextFeatureComputation" + "/" + DishName + N + Symbol + "WithoutNoise.txt";

			// 把SubjectAugment文件读入
			BufferedReader SubjectAugBr = iOhandle.FileReader(SubjectAugInputPath);
			ArrayList<List<String>> subjectAugArrayList = new ArrayList<List<String>>();
			while ((ReadLine = SubjectAugBr.readLine()) != null) {
				ArrayList<String> elementArrayList = new ArrayList<String>();
				String[] spliteReadLine = ReadLine.split("=");
				String[] ElementsGroup = spliteReadLine[0].split("::");
				for (int i = 0; i < ElementsGroup.length; i++) {
					elementArrayList.add(ElementsGroup[i]);
				}
				subjectAugArrayList.add(elementArrayList);
			}

			// 把featureComputaiton文件读入
			ArrayList<String> saveResultArrayList = new ArrayList<String>();
			BufferedReader featureCompBr = iOhandle.FileReader(FeatureCompInputPath);
			while ((ReadLine = featureCompBr.readLine()) != null) {
				String[] spliteReadLine = ReadLine.split(" ");
				for (int i = 0; i < subjectAugArrayList.size(); i++) {
					if (subjectAugArrayList.get(i).contains(spliteReadLine[0])) {
						saveResultArrayList.add(ReadLine);
					}
				}
			}
			
			//结果写入文件
			BufferedWriter bw = iOhandle.FileWriter(OutputPath);
			for(int i=0;i<saveResultArrayList.size();i++){
				bw.write(saveResultArrayList.get(i));
				bw.newLine();
				bw.flush();
			}
			System.out.println("移除噪音完成");
		}
		catch (Exception e) {
			System.out.println("特征计算移除出错");
			System.out.println(e);
		}

	}
}
