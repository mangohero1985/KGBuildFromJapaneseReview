/**
 * 
 */
package TextProceeding.Segmentation;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import org.chasen.mecab.Node;
import org.chasen.mecab.Tagger;

import TextProceeding.ioProceeing.IOhandle;


/**
 * @author mangohero1985
 * @create-time May 23, 2014 12:43:00 AM
 */
public class FilePreparing {

	/**
	 * @param args
	 */
	static {
		try {
			// File f = new
			// File("/Users/mangohero1985/Documents/japanese-morphology/mecab-java-0.995/libMeCab.so");
			// System.load(f.toString());
			System.out.println("java.library.path: " + System.getProperty("java.library.path"));
			System.loadLibrary("MeCab");
		}
		catch (UnsatisfiedLinkError e) {
			System.err.println("Cannot load the example native code.\nMake sure your LD_LIBRARY_PATH contains \'.\'\n" + e);
			System.exit(1);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String FileName = "4-5";
		// 读取文件
		IOhandle io = new IOhandle();
		BufferedReader br1 = io.FileReader("/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/scaled_feature/classify_by_score_review/" + FileName + ".txt");
		BufferedWriter bw1 = io.FileWriter("/Users/mangohero1985/Documents/japanese-morphology/sentiment_extraction/scaled_feature/" + FileName + "temp.txt");
		String readline = null;
		// 先整理文件,只选取需要的词性
		while ((readline = br1.readLine()) != null) {
			if (!readline.equals("*****")) {
				Tagger tagger = new Tagger();
				Node node = tagger.parseToNode(readline);
				for (; node != null; node = node.getNext()) {
					if (node.getFeature().contains("名詞") || node.getFeature().contains("形容詞") || node.getFeature().contains("副詞") || node.getFeature().contains("動詞,自立")) {
						bw1.write(node.getSurface()+"*");
					}
				}
			}else{
				continue;
			}
			bw1.newLine();
			bw1.flush();
		}
	}

}
