/**
 * 
 */
package Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author mangohero1985
 * @create-time     Oct 20, 2013   2:56:50 PM   
 */
public class SearchTwitterLexicon {
	
	
   public ArrayList<String> Searching(String inputPath) throws IOException{
	   
	   ArrayList<String> arrayListName = new ArrayList<String>();
	   
	   FileReader fr = new FileReader(inputPath);
	   BufferedReader br = new BufferedReader(fr);
	   
	   String line;
	   
	   while((line = br.readLine())!=null){
		   
		   arrayListName.add(line);
	   }
		

	
	   return arrayListName;
	   
	   
   }

}
