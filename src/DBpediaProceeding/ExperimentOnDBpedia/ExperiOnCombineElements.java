/**
 * 
 */
package DBpediaProceeding.ExperimentOnDBpedia;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import TestMain.CreatDir;
import TextProceeding.CombineSameElement.CombineSameNameItem;
import TextProceeding.CombineSameElement.ExactHiragana;
import TextProceeding.CombineSameElement.TransIntoHira;
import TextProceeding.ioProceeing.IOhandle;
import Tools.SortAndCount.TestWord;

/**
 * @author mangohero1985
 * @create-time Aug 21, 2014 11:31:06 AM
 */
public class ExperiOnCombineElements {

	/**
	 * @param args
	 * @throws IOException
	 */
	public  void CombineElements(String DishName,String Num,String dir,String PercentName) throws IOException {
	    
		//String destDirName = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/"+DishName+"/"+DishName+Num+"/CoveragedElements10";
		CreatDir creatDir = new CreatDir();
		creatDir.createDir(dir+DishName+"/"+DishName+Num+"/"+PercentName+"/");
		// 将所有的菜名标注为平假名
		String inputPath = dir+DishName+"/"+DishName+Num+"/"+PercentName+".txt";
		String outputPath = dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"TransformIntoHiragana.txt";

		IOhandle iOhandle = new IOhandle();
		BufferedReader br = iOhandle.FileReader(inputPath);
		BufferedWriter bw = iOhandle.FileWriter(outputPath);
		TransIntoHira transIntoHira = new TransIntoHira();
		transIntoHira.Transform(br, bw);

		// 提取所有的菜名的平假名
		String inputString1 =  dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"TransformIntoHiragana.txt";
		String outputString1 =  dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"Hiragana.txt";
		ExactHiragana exactHiragana = new ExactHiragana();
		exactHiragana.Exact(inputString1, outputString1);
		
		// 合并重复的平假名
		String InputPath2 =  dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"Hiragana.txt";
		String OutputPath2 =  dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"HiraganaSorted.txt";
		
		TestWord testWord = new TestWord();
		testWord.sort(InputPath2, OutputPath2);
		
		//生成文件
		String HiraSortedInputPath =  dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"HiraganaSorted.txt";
		String MultiNameOutputPath =  dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"MonoDishName.txt";

		String TransHiraInputPath =  dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"TransformIntoHiragana.txt";
		String MonoNameOutputPath =  dir+DishName+"/"+DishName+Num+"/"+PercentName+"/"+"MutiNameDish.txt";
		
		CombineSameNameItem combineSameNameItem = new CombineSameNameItem();
		combineSameNameItem.Combine(HiraSortedInputPath, MultiNameOutputPath, TransHiraInputPath, MonoNameOutputPath);
	}

}
