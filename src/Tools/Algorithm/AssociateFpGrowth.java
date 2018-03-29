/**
 * 
 */
package Tools.Algorithm;

import java.io.BufferedWriter;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.uclab.io.IOhandle;

import weka.associations.FPGrowth;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 * @author mangohero1985
 * @create-time Jun 28, 2014 6:36:17 PM
 */
public class AssociateFpGrowth {

	/**
	 * @param args
	 * @throws Exception
	 */
	public void FPGrowth(String InputPath, BufferedWriter bw, Map map, String DishName, String[] optionsAssociat) throws Exception {

		FPGrowth fpGrowth = new FPGrowth();
		// Load data
		DataSource source = new DataSource(InputPath);
		Instances data = source.getDataSet();

		// Filter
		String[] options = weka.core.Utils.splitOptions("-R 1");
		Remove remove = new Remove();
		remove.setOptions(options);
		remove.setInputFormat(data);
		Instances newData = Filter.useFilter(data, remove);

		// Option handling
		fpGrowth.setOptions(optionsAssociat);
		fpGrowth.buildAssociations(newData);
		List list = fpGrowth.getAssociationRules().getRules();
		System.out.println("生成规则");

		ArrayList<Object> arrayListTemp = new ArrayList<Object>();
		String o = "";

		for (int i = 0; i < list.size(); i++) {

			// Iterator mapIt = map.entrySet().iterator();
			String Rule = list.get(i).toString();
			// System.out.println(Rule);
			// 截取中括号内的字符串
			Pattern p = Pattern.compile("\\[(.*?)\\]");
			Matcher m = p.matcher(Rule);
			ArrayList mTemp = new ArrayList();
			while (m.find()) {
				mTemp.add(m.group(1));
			}
			for (int k = 0; k < mTemp.size(); k++) {
				if (mTemp.get(k).toString().contains(",")) {
					Iterator mapIt = map.entrySet().iterator();
					String[] splite = mTemp.get(k).toString().split(",");
					if (splite.length == 2) {
						String[] splite0 = splite[0].split("=");
						String[] splite1 = splite[1].split("=");
						while (mapIt.hasNext()) {
							Map.Entry entry = (Map.Entry) mapIt.next();
							if (entry.getValue().toString().equals(splite0[0])) {
								o = entry.getKey().toString();
								arrayListTemp.add(o);
							}
							if (entry.getValue().toString().equals(splite1[0].substring(1, splite1[0].length()))) {
								o = entry.getKey().toString();
								arrayListTemp.add(o);
							}
						}
					}
					else {
						String[] splite0 = splite[0].split("=");
						String[] splite1 = splite[1].split("=");
						String[] splite2 = splite[2].split("=");
						while (mapIt.hasNext()) {
							Map.Entry entry = (Map.Entry) mapIt.next();
							if (entry.getValue().toString().equals(splite0[0])) {
								o = entry.getKey().toString();
								arrayListTemp.add(o);
							}
							if (entry.getValue().toString().equals(splite1[0].substring(1, splite1[0].length()))) {
								o = entry.getKey().toString();
								arrayListTemp.add(o);
							}
							if (entry.getValue().toString().equals(splite2[0].substring(1, splite2[0].length()))) {
								o = entry.getKey().toString();
								arrayListTemp.add(o);
							}
						}
					}
				}
				else {
					Iterator mapIt = map.entrySet().iterator();
					while (mapIt.hasNext()) {
						Map.Entry entry = (Map.Entry) mapIt.next();
						// System.out.println(entry);
						String[] splite = mTemp.get(k).toString().split("=");
						if (entry.getValue().toString().equals(splite[0])) {
							o = entry.getKey().toString();
							arrayListTemp.add(o);
						}
					}
				}

				// m.reset();
				// mTemp.clear();
			}
			if (arrayListTemp.size() == 2) {
				if (!arrayListTemp.get(0).equals(DishName)) {
					bw.write(Rule);
					bw.newLine();
					bw.write(arrayListTemp.get(0) + "==>" + arrayListTemp.get(1));
					bw.newLine();
					bw.flush();
				}
				else {
					bw.write(Rule);
					bw.newLine();
					bw.write(arrayListTemp.get(1) + "==>" + arrayListTemp.get(0));
					bw.newLine();
					bw.flush();
				}
			}
			else if (arrayListTemp.size() == 3) {
				if (!arrayListTemp.get(0).equals(DishName) && !arrayListTemp.get(1).equals(DishName)) {
					bw.write(Rule);
					bw.newLine();
					bw.write(arrayListTemp.get(0) + "," + arrayListTemp.get(1) + "==>" + arrayListTemp.get(2));
					bw.newLine();
					bw.flush();
				}
				else {
					if (arrayListTemp.get(0).equals(DishName)) {
						bw.write(Rule);
						bw.newLine();
						bw.write(arrayListTemp.get(1) + "," + arrayListTemp.get(2) + "==>" + arrayListTemp.get(0));
						bw.newLine();
						bw.flush();
					}
					else {
						bw.write(Rule);
						bw.newLine();
						bw.write(arrayListTemp.get(0) + "," + arrayListTemp.get(2) + "==>" + arrayListTemp.get(1));
						bw.newLine();
						bw.flush();
					}
				}
			}
			else {
				bw.write(Rule);
				bw.newLine();
				bw.write(arrayListTemp.get(0) + "," + arrayListTemp.get(1) + "," + arrayListTemp.get(2) + "==>" + arrayListTemp.get(3));
				bw.newLine();
				bw.flush();
			}
			arrayListTemp.clear();
		}

	}
}
