/**
 * 
 */
package TextProceeding.CombineSameElement;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import TextProceeding.ioProceeing.IOhandle;


/**
 * @author mangohero1985
 * @create-time     Aug 21, 2014   11:48:21 AM   
 */
public class CombineSameNameItem {
	
	public void Combine(String HiraSortedInputPath,String MultiNameOutputPath,String TransHiraInputPath,String MonoNameOutputPath ) throws IOException{
		

	

		BufferedReader br1 = new IOhandle().FileReader(HiraSortedInputPath);
		BufferedWriter bw1 = new IOhandle().FileWriter(MultiNameOutputPath);

		BufferedReader br2 = new IOhandle().FileReader(TransHiraInputPath);
		BufferedWriter bw2 = new IOhandle().FileWriter(MonoNameOutputPath);
		// 建立arraylist存储多次出现的katakana.
		ArrayList<String> mutiNameDishArraylist = new ArrayList<String>();

		// 读取文件HiraganaSorted, 并且将重复的出现多次的菜名存Arraylist.
		String line = null;
		while ((line = br1.readLine()) != null) {

			String[] spliteKata = line.split("\\u0028");
			String spliteKata2 = spliteKata[1].substring(0, spliteKata[1].length() - 1);
			if (Integer.parseInt(spliteKata2) > 1) {
				mutiNameDishArraylist.add(spliteKata[0]);
			}
		}
		// 读取文件ThansformIntoHiragana,hiragana作为key. Dish name 作为value. 如果key
		// 未被包含在mutiNameDish中,就存进map中
		HashMap<String, String> DishNameMap = new HashMap<String, String>();
		TMap<String, String> MutiNameMap = new TMap<String, String>();
		String line2 = null;
		while ((line2 = br2.readLine()) != null) {
			String[] SpliteLine = line2.split("\t");
			if (!mutiNameDishArraylist.contains(SpliteLine[1])) {
				DishNameMap.put(SpliteLine[1], SpliteLine[0]);
			}
			else {
				MutiNameMap.put(SpliteLine[1], SpliteLine[0] + "::");
			}
		}
		// 遍历输出单一菜名的map进入文件MonoDishName.txt
		Collection<String> c = DishNameMap.values();
		Iterator it = c.iterator();
		for (; it.hasNext();) {
			bw1.write((String) it.next());
			bw1.newLine();
			bw1.flush();
		}
		// 遍历多菜名的map进入文件multNameDish.txt
		Set<String> key = MutiNameMap.keySet();
		for (Iterator it2 = key.iterator(); it2.hasNext();) {
			String s = (String) it2.next();
			for(int i =0;i< MutiNameMap.get(s).size();i++){
			bw2.write(MutiNameMap.get(s).get(i));
			}
			bw2.newLine();
			bw2.flush();
		}
	}

}
