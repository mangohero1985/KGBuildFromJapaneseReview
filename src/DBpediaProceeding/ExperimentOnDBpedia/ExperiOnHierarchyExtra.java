/**
 * 
 */
package DBpediaProceeding.ExperimentOnDBpedia;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import TextProceeding.ioProceeing.IOhandle;

import DBpediaProceeding.DBpediaProceedingToolBySparqlQuery.CheckHierarchy;


/**
 * @author mangohero1985
 * @create-time Aug 24, 2014 8:10:15 PM
 */
public class ExperiOnHierarchyExtra {

	public void HierarchyExtra(String DishName, String Num, String dir, String PercentName) throws Exception {

		String InputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "subjectAugmentWithoutNoise.txt";
		String OutputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "HierarchyExtraction.txt";

		IOhandle iOhandle = new IOhandle();
		BufferedReader br = iOhandle.FileReader(InputPath);
		BufferedWriter bw = iOhandle.FileWriter(OutputPath);

		CheckHierarchy checkHierarchy = new CheckHierarchy();
		ArrayList<List<StringBuffer>> resultArrayList = checkHierarchy.Hierarchy(br, DishName);

		for (int i = 0; i < resultArrayList.size(); i++) {
			for (int j = 0; j < resultArrayList.get(i).size(); j++) {
                     bw.write(resultArrayList.get(i).get(j).toString());
                     bw.newLine();
                     bw.flush();
			}
		}
      System.out.println("Hierarchy Extraction Finished");
	}

}
