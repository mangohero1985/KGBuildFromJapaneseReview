/**
 * 
 */
package TestMain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import Tools.ReadAllFileFromParentCatalog;

import Extractor.ReviewExtract;

import jp.uclab.io.IOhandle;

/**
 * @author mangohero1985
 * @create-time Jun 17, 2014 2:40:35 PM
 */
public class testCommentExtractionWithKeys {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String keys = "手羽先";

		String ParentsFolder = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/TextProceeding/tabelogAichitHtml-allComment-output";
		String OutputPath = "/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/ontology_porpulation/"+keys+"/"+ keys + ".txt";

		ReadAllFileFromParentCatalog readAllFileFromParentCatalog = new ReadAllFileFromParentCatalog();
		List fileList = readAllFileFromParentCatalog.ReadAllFile(ParentsFolder);

		// Iterator it = fileList.iterator();
		for (int i = 0; i < fileList.size(); i++) {

			IOhandle iOhandle = new IOhandle();
			System.out.println(fileList.get(i));
			BufferedReader br = iOhandle.FileReader(fileList.get(i).toString());
			BufferedWriter bw = iOhandle.FileWriterContinue(OutputPath);

			ReviewExtract reviewExtract = new ReviewExtract();
			reviewExtract.ReviewResultsWithKeys(br, bw, keys);
			br.close();
			bw.close();
		}
	}

}
