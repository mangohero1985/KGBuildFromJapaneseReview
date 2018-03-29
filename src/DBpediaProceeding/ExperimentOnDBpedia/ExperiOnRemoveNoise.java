/**
 * 
 */
package DBpediaProceeding.ExperimentOnDBpedia;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import TextProceeding.ioProceeing.IOhandle;


/**
 * @author mangohero1985
 * @create-time Aug 23, 2014 5:41:09 PM
 */
// 读取词典和SubjectAugment文件
public class ExperiOnRemoveNoise {

	public void RemoveNoise(String DishName, String Num, String dir, String PercentName) throws IOException {

		String DictinaryInputPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/FinalSubjectAugment.txt";
		String subjectAugmentInputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "subjectAugment.txt";
		String OutputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "subjectAugmentWithoutNoise.txt";

		IOhandle iOhandle = new IOhandle();
		BufferedReader DictionaryBr = iOhandle.FileReader(DictinaryInputPath);
		BufferedReader subAugmentedBr = iOhandle.FileReader(subjectAugmentInputPath);
		BufferedWriter bw = iOhandle.FileWriter(OutputPath);

		ArrayList<String> dictionaryArrayList = new ArrayList<String>();
		ArrayList<String> subAugmentArrayList = new ArrayList<String>();
		ArrayList<String> resultArrayList = new ArrayList<String>();
		String ReadLine = "";
		while ((ReadLine = DictionaryBr.readLine()) != null) {
			dictionaryArrayList.add(ReadLine);
		}
		while ((ReadLine = subAugmentedBr.readLine()) != null) {
			subAugmentArrayList.add(ReadLine);
		}

		for (int i = 0; i < subAugmentArrayList.size(); i++) {
			String[] spliteSubjectAugment = subAugmentArrayList.get(i).split("=");
			String[] spliteLatter = spliteSubjectAugment[1].split("\\}");
			if(spliteLatter.length==0) continue;
			String[] spliteSubject = spliteLatter[0].split(",");
			String[] splitePrevious = spliteSubjectAugment[0].split("\\{");

			StringBuffer tempStringBuffer = new StringBuffer();
			int flag = 0;
			for (int j = 0; j < spliteSubject.length; j++) {
				if (dictionaryArrayList.contains(spliteSubject[j])) {
					tempStringBuffer.append(spliteSubject[j]+",");
					flag++;
				}
			}
			if (flag != 0) {
				resultArrayList.add(splitePrevious[1] + "=" + tempStringBuffer);
			}
		}
		for (int k = 0; k < resultArrayList.size(); k++) {
			bw.write(resultArrayList.get(k));
			bw.newLine();
			bw.flush();
		}
		System.out.println("remove Noise finished");
		
	}
}
