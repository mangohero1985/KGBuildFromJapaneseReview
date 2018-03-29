/**
 * 
 */
package ExperimentOnDBpedia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import DBpediaProceedingToolBySparqlQuery.CheckAugment;

import jp.uclab.io.IOhandle;

/**
 * @author mangohero1985
 * @create-time Aug 22, 2014 1:05:46 AM
 */
public class ExperiOnElementsAugment {

	public void Augment(String DishName, String Num, String dir, String PercentName) throws Exception {

		String InputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "subject.txt";
		String OutputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "subjectAugment.txt";

		IOhandle iOhandle = new IOhandle();
		BufferedReader br = iOhandle.FileReader(InputPath);
		BufferedWriter bw = iOhandle.FileWriter(OutputPath);

		CheckAugment checkAugment = new CheckAugment();
		ArrayList<StringBuffer> result = checkAugment.Augment(br);
		for (int i = 0; i < result.size(); i++) {
			bw.write(result.get(i).toString());
			bw.newLine();
			bw.flush();
		}

	}

}
