/**
 * 
 */
package main.textFeatureComputaion;

import TextFeatureComputation.FeatureSelectionProceeding.ElementsFromRouteOverThresh;
import TextFeatureComputation.FeatureSelectionProceeding.FeatureComputation;
import TextFeatureComputation.FeatureSelectionProceeding.RemoveNoiseFromFeaComp;
import TextFeatureComputation.FeatureSelectionProceeding.RouteScoreComp;
import TextFeatureComputation.FeatureSelectionProceeding.TextPreparation;

/**
 * @author mangohero1985
 * @create-time Aug 25, 2014 1:59:23 PM
 */
public class TextFeatureCompuataiton {

	public static void main(String[] args) throws Exception {

		String DishName = "ひつまぶし";
		String Num = "1";
		String dir = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/";
		String PercentName = "CoveragedElements10";
		String Symbol = "CHI";

		// 文本文件准备,生成子句子集,和非子句子集
		TextPreparation textPreparation = new TextPreparation();
		textPreparation.Prepare(DishName, Num, dir, PercentName, Symbol);

		// 计算每个Elements的feature得分
		FeatureComputation featureComputation = new FeatureComputation();
		featureComputation.GetResults(DishName, Num, dir, PercentName, Symbol);

		// 移除特征计算中无用的Elements
		RemoveNoiseFromFeaComp removeNoiseFromFeaComp = new RemoveNoiseFromFeaComp();
		removeNoiseFromFeaComp.Remove(DishName, Num, dir, PercentName, Symbol);

		// 给每个路径计算分数
		RouteScoreComp routeScoreComp = new RouteScoreComp();
		routeScoreComp.Compute(DishName, Num, dir, PercentName, Symbol);

		// 读取分数排序结果,返回大于阈值的词条
		ElementsFromRouteOverThresh elementsFromRouteOverThresh = new ElementsFromRouteOverThresh();
		elementsFromRouteOverThresh.ExtractOverThresh(DishName, Num, dir, PercentName, Symbol);
	}

}
