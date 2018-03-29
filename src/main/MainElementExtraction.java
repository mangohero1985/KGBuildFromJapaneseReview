/**
 * 
 */
package main;

import java.util.ArrayList;

import main.TextProceeding.GenerateNSentence;

/**
 * @author mangohero1985
 * @create-time Aug 26, 2014 9:40:20 PM
 */
public class MainElementExtraction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String DishName = "ひつまぶし";
		String ElementName = "漬物";
		ArrayList<String> allElementsArrayList = new ArrayList<String>();
		
		ElementsIterator(DishName, ElementName);
//
//		for (int i = 0; i < allElementsArrayList.size(); i++) {
//			ElementsIterator(allElementsArrayList.get(i));
//		}

	}

	public static ArrayList<String> ElementsIterator(String DishName,String ElementsName) {
		//截取需要的子句
		GenerateNSentence generateNSentence = new GenerateNSentence();
		//generateNSentence.GenerateNSent(DishName,ElementsName);

		return null;
	}

}
