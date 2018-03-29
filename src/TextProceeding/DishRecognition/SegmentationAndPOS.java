/**
 * 
 */
package TextProceeding.DishRecognition;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import org.chasen.mecab.Node;
import org.chasen.mecab.Tagger;

import TextProceeding.ioProceeing.IOhandle;


/**
 * @author mangohero1985
 * @create-time Jun 14, 2014 6:35:45 PM
 */

public class SegmentationAndPOS {

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

	public void SegmentAndAnotate(BufferedReader br, BufferedWriter bw) {

		String newLine = null;
		String[] splitString = null;

		try {
			while ((newLine = br.readLine()) != null) {

				Tagger tagger = new Tagger();
				Node node = tagger.parseToNode(newLine);

				for (; node != null; node = node.getNext()) {

					splitString = node.getFeature().split(",");
					//System.out.println(node.getSurface() + "\t" + splitString[0] + splitString[1]);
					try {
						bw.write(node.getSurface() + "\t" + splitString[0] + splitString[1] + "\t" + "0");
						if (node.getFeature().contains("記号,句点")) {
							bw.write(" ");
							bw.newLine();
							bw.flush();
						}

						bw.newLine();
						bw.flush();

					}

					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("分次标注词性完成");

	}
}
