/**
 * 
 */
package ExperimentOnDBpedia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import DBpediaProceedingToolBySparqlQuery.CheckSubject;

import jena.schemagen;

import jp.uclab.io.IOhandle;

/**
 * @author mangohero1985
 * @create-time     Aug 20, 2014   10:03:32 PM   
 */
public class ExperiOnCheckSubject {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public void CheckSubject(String DishName,String Num,String dir,String PercentName) throws Exception {
		
		String InputPath = dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"CombiResult.txt";
		String OutputPath = dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"subject.txt";
		IOhandle iOhandle = new IOhandle();
		BufferedReader br = iOhandle.FileReader(InputPath);
		BufferedWriter bw = iOhandle.FileWriter(OutputPath);
		CheckSubject checkSubject = new CheckSubject();
		ArrayList<Map<String, String>> subjResults = checkSubject.Check(br);
		for(int i =0;i<subjResults.size();i++){
			bw.write(subjResults.get(i).toString());
			bw.newLine();
			bw.flush();
		}
	}

}
