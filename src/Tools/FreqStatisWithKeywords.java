/**
 * 
 */
package Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author mangohero1985
 * @create-time Jun 20, 2014 12:50:37 PM
 */
public class FreqStatisWithKeywords {

	public int Statistic(ArrayList<String> arraylist, String Keyword) throws Exception {

		int Frequency = 0;
		for(int i= 0; i<arraylist.size();i++) {
			if (arraylist.get(i).contains(Keyword)) {
				Frequency++;
			}
		}

		System.out.println("单词词频统计完成");
		return Frequency;

	}

	public int Statistic(ArrayList<String> arraylist, String Keyword1, String Keyword2) throws Exception {

		int Frequency = 0;

		for(int i= 0; i<arraylist.size();i++) {
			if (arraylist.get(i).contains(Keyword1) && arraylist.get(i).contains(Keyword2)) {
				Frequency++;
			}
		}

		System.out.println("多词词频统计完成");
		return Frequency;

	}
	public int StatisticFalse(ArrayList<String> arrayList, String Keyword){
		int Frequency = 0;
		for(int i= 0; i<arrayList.size();i++) {
			if (!arrayList.get(i).contains(Keyword)) {
				Frequency++;
			}
		}
		System.out.println("FN词频统计完成");
		return Frequency;
	}

}
