/**
 * 
 */
package TestMain;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import jp.uclab.io.IOhandle;

import DBpediaProceedingToolBySparqlQuery.CheckBroader;

/**
 * @author mangohero1985
 * @create-time Aug 23, 2014 4:22:31 AM
 */
public class TestSubjectAugment {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		IOhandle iOhandle = new IOhandle();

		String OutputPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/FinalSubjectAugment.txt";
		BufferedWriter bw = iOhandle.FileWriter(OutputPath);

		String[] DishDictionary = { "調味料", "香辛料", "甘味料", "香料","食品","ラーメン", "根菜", "果菜", "葉菜", "山菜", "キノコ", "イモ類","いも類", "豆腐", "豆加工品", "大豆", "豆類", "米料理", "芋料理", "豆料理", "野菜料理", "魚介料理", "卵料理", "麺料理",
				"牛肉料理", "豚肉料理", "鰻料理", "鶏料理", "肉料理", "精進料理", "串料理", "鍋料理", "餅料理", "粉物料理", "佃煮", "チーズ料理", "洋菓子", "ケーキ", "和菓子", "飴", "冷菓", "氷菓", "菓子", "ドライフルーツ", "ベリー", "果物", "アンズ",
				"ぶどう品種", "ミカン属", "発酵食品", "保存食", "漬物", "スープ", "汁物", "水産物", "水産加工品", "食用甲殻類", "食用貝", "食用海藻", "食肉", "赤身魚", "白身魚", "魚卵", "青魚", "食用川魚", "乾物", "乳製品", "パスタ", "パン", "うどん",
				"サラダ", "アイスクリーム", "サンドイッチ", "コーヒー", "日本茶", "茶","ソフトドリンク", "飲料", "中国茶", "牛乳", "台湾茶", "ワイン","砂糖","ネギ" };

		// 使用种子扩张 subject 的concept
		// String[] Seeds={"調味料","野菜","料理",
		// "菓子","果物","発酵食品","水産物","スープ","パン","サンドイッチ","乾物","乳製品","飲料"};
		ArrayList<String> dictionaryArrayList = new ArrayList<String>();
		ArrayList<String> depositeDictionaryArrayList = new ArrayList<String>();
		for (String seed : DishDictionary) {
			depositeDictionaryArrayList.add(seed);
		}
		for (String seed : DishDictionary) {
			dictionaryArrayList.add(seed);
		}
		CheckBroader checkBroader = new CheckBroader();
		int n = 2;
		for (int i = 0; i < n; i++) {
			dictionaryArrayList = checkBroader.Check(dictionaryArrayList);
			System.out.println(dictionaryArrayList);
		}
		// 接受返回关系结果的map
		Map<String, Double> resultMap = new TreeMap<String, Double>();
		resultMap = checkBroader.ComputeSemanticDistance(dictionaryArrayList, depositeDictionaryArrayList);

		ArrayList<String> augmentSubjectArrayList = new ArrayList<String>();
		// 遍历resultMap
		for (Iterator it = resultMap.keySet().iterator(); it.hasNext();) {
			String Key = it.next().toString();

			try {
				System.out.println(Key);
				if (!Key.equals("Null")) {
					if (resultMap.get(Key) > 0.6) {
						augmentSubjectArrayList.add(Key);

					}
					// bw.write(Key + " " + resultMap.get(Key));
					// bw.newLine();
					// bw.flush();
				}
			}
			catch (Exception e) {

				System.out.println(e);

			}

		}
		for (int k = 0; k < depositeDictionaryArrayList.size(); k++) {
			if (!augmentSubjectArrayList.contains(depositeDictionaryArrayList.get(k))) {
				augmentSubjectArrayList.add(depositeDictionaryArrayList.get(k));
			}
		}
		for (int k = 0; k < augmentSubjectArrayList.size(); k++) {
			bw.write(augmentSubjectArrayList.get(k));
			bw.newLine();
			bw.flush();
		}
	}

}
