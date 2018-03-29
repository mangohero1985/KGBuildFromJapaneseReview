/**
 * 
 */
package Tools.SortAndCount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author mangohero1985
 * @create-time     Jun 20, 2014   7:02:05 PM   
 */
public class MapSorting {

	public void Sorting(BufferedReader br, BufferedWriter bw, String SpliteSymbol) throws Exception, Exception{
		
		Map<String,Double > keyfreqs = new HashMap<String, Double>();
        String readline = "";
		while ((readline = br.readLine()) != null) {

			String[] splitReadStrings = readline.split(SpliteSymbol);
			keyfreqs.put(splitReadStrings[0], Double.parseDouble(splitReadStrings[1]));

		}
		//对map按照value进行排序
		
		ArrayList<Entry<String, Double>> l = new ArrayList<Entry<String, Double>>(keyfreqs.entrySet());

		Collections.sort(l, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				Double v1 = o1.getValue();
				Double v2 = o2.getValue();
				return v1.compareTo(v2);
			}
		});

		for (Entry<String, Double> e : l) {
			System.out.println(e.getKey() + " " + e.getValue());
		bw.write(e.getKey() + " " + e.getValue());
		bw.newLine();
		bw.flush();
		}

		System.out.println("排序处理完成");
		
	}
}
