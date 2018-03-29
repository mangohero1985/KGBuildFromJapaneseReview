/**
 * 
 */
package DBpediaProceeding.ExperimentOnDBpedia;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.jena.atlas.io.IO;

import TextProceeding.ioProceeing.IOhandle;

import DBpediaProceeding.DBpediaProceedingToolBySparqlQuery.CheckRedirect;
import DBpediaProceeding.SparqlQuery.QueryFromDBpedia;


/**
 * @author mangohero1985
 * @create-time Aug 21, 2014 2:55:33 PM
 */
public class ExperiOnCheckRedirect {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public void CheckRedirect(String DishName,String Num,String dir,String PercentName) throws Exception {
		

		IOhandle iOhandle = new IOhandle();
		String MultiInputPath = dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"MutiNameDish.txt";
		String MonoInputPath = dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"MonoDishName.txt";
		String OutputPath = dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"CombiResult.txt";
		
		BufferedReader brMulti = iOhandle.FileReader(MultiInputPath);
		BufferedReader brMono = iOhandle.FileReader(MonoInputPath);
		BufferedWriter bw = iOhandle.FileWriter(OutputPath);
		
		CheckRedirect checkRedirect = new CheckRedirect();
		ArrayList<StringBuffer> resultArrayList = new ArrayList<StringBuffer>();
		resultArrayList = checkRedirect.Check(brMulti, brMono);

		for(int i=0;i<resultArrayList.size();i++){
			bw.write(resultArrayList.get(i).toString());
			bw.newLine();
			bw.flush();
		}
	}

}
