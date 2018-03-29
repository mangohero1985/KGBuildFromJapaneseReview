/**
 * 
 */
package TextProceeding.CombineSameElement;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import TextProceeding.ioProceeing.IOhandle;


/**
 * @author mangohero1985
 * @create-time     Aug 21, 2014   11:37:01 AM   
 */
public class ExactHiragana {
	
   public void Exact(String inputString,String outputString ) throws IOException{
	  
       BufferedReader br = new IOhandle().FileReader(inputString);
       BufferedWriter bw = new IOhandle().FileWriter(outputString);
       
       String readStr = null;
       while((readStr= br.readLine())!=null){
       	
       	String[] StrSplite = readStr.split("\t");
       	bw.write(StrSplite[1]);
       	bw.newLine();
       	bw.flush();
       }
      br.close();
      bw.close();
   }

}
