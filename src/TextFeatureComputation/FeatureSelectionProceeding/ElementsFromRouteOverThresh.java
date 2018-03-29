/**
 * 
 */
package TextFeatureComputation.FeatureSelectionProceeding;

import java.io.BufferedReader;
import java.util.ArrayList;

import com.fasterxml.jackson.core.util.BufferRecycler;

import TextProceeding.ioProceeing.IOhandle;

/**
 * @author mangohero1985
 * @create-time Aug 26, 2014 8:24:19 PM
 */
public class ElementsFromRouteOverThresh {

	public void ExtractOverThresh(String DishName, String Num, String dir, String PercentName, String Symbol) {

		int N = Integer.parseInt(Num);
		String ReadLine = "";
		String routeSortedInputPath = dir + DishName + "/" + DishName + Num + "/" + PercentName + "/" + "TextFeatureComputation" + "/" + DishName + N + Symbol
				+ "RountScoreSorted.txt";
		IOhandle iOhandle = new IOhandle();
		ArrayList<String> ElementsOverThesh = new ArrayList<String>();

		try {
			BufferedReader routeScoredBr = iOhandle.FileReader(routeSortedInputPath);
			while ((ReadLine = routeScoredBr.readLine()) != null) {

				String[] spliteRoute = ReadLine.split(" ");
				String[] ElementsRoute = spliteRoute[0].split(",");
				for (int i = 0; i < ElementsRoute.length; i++) {
					String[] keyValue = ElementsRoute[i].split("=");
					if (Double.parseDouble(keyValue[1]) > 20.0 && !ElementsOverThesh.contains(keyValue[0])) {
						ElementsOverThesh.add(keyValue[0]);
					}
				}

			}
			System.out.println(ElementsOverThesh);
		}
		catch (Exception e) {
			System.out.println("从route中抽取识别的元素出错");
			System.out.println(e);
		}

	}
}
