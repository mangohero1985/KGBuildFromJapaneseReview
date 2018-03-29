/**
 * 
 */
package TestMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import DBpediaProceedingToolBySparqlQuery.CheckBroader;
import ExperimentOnDBpedia.ExperiOnCheckRedirect;
import ExperimentOnDBpedia.ExperiOnCheckSubject;
import ExperimentOnDBpedia.ExperiOnCombineElements;
import ExperimentOnDBpedia.ExperiOnElementsAugment;
import ExperimentOnDBpedia.ExperiOnHierarchyExtra;
import ExperimentOnDBpedia.ExperiOnRemoveNoise;

/**
 * @author mangohero1985
 * @create-time Aug 22, 2014 12:10:47 AM
 */
public class TestExperiOnDBpedia {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String DishName = "ひつまぶし";
		String Num = "1";
		String dir = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/";
		String PercentName = "CoveragedElements10";

		// 合并具有相同surface的dishElements
//		ExperiOnCombineElements combineElements = new ExperiOnCombineElements();
//		combineElements.CombineElements(DishName, Num, dir, PercentName);

//		// 通过语义合并相同的dishElements
//		ExperiOnCheckRedirect checkRedirect = new ExperiOnCheckRedirect();
//		checkRedirect.CheckRedirect(DishName, Num, dir, PercentName);
//
//		// 查找subject
//		ExperiOnCheckSubject checkSubject = new ExperiOnCheckSubject();
//		checkSubject.CheckSubject(DishName, Num, dir, PercentName);
//
//		// surface扩充
//		ExperiOnElementsAugment experiOnElementsAugment = new ExperiOnElementsAugment();
//		experiOnElementsAugment.Augment(DishName, Num, dir, PercentName);
//
//		// subject扩充在TestSubjectAugment中完成
//
//		// 通过建立的subjectDictionary移除噪音
//		ExperiOnRemoveNoise experiOnRemoveNoise = new ExperiOnRemoveNoise();
//		experiOnRemoveNoise.RemoveNoise(DishName, Num, dir, PercentName);

		//查找从dishName开始的所有Elements的路径
		ExperiOnHierarchyExtra experiOnHierarchyExtra = new ExperiOnHierarchyExtra();
		experiOnHierarchyExtra.HierarchyExtra(DishName, Num, dir, PercentName);
	}
}
