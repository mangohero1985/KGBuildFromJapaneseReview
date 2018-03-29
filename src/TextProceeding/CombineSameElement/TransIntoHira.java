/**
 * 
 */
package TextProceeding.CombineSameElement;

import TextProceeding.ioProceeing.IOhandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import org.chasen.mecab.Node;
import org.chasen.mecab.Tagger;


/**
 * @author mangohero1985
 * @create-time Feb 26, 2014 10:45:37 AM
 */
public class TransIntoHira {
	// mecab静态代码块
	static {
		try {
			// File f = new
			// File("/Users/mangohero1985/Documents/japanese-morphology/mecab-java-0.995/libMeCab.so");
			// System.load(f.toString());
			System.loadLibrary("MeCab");
		}
		catch (UnsatisfiedLinkError e) {
			System.err.println("Cannot load the example native code.\nMake sure your LD_LIBRARY_PATH contains \'.\'\n" + e);
			System.exit(1);
		}
	}

	public void Transform(BufferedReader br, BufferedWriter bw) throws IOException {

		String readString = null;
		StringBuffer sbufferName = new StringBuffer();
		StringBuffer sbufferFearture = new StringBuffer();
		while ((readString = br.readLine()) != null) {
			Tagger tagger = new Tagger();
			Node node = tagger.parseToNode(readString);
			
			for (; node != null; node = node.getNext()) {
				//System.out.println(node.getSurface() + "\t" + node.getFeature());
				String[] featureSplite = node.getFeature().split(",");
				if (node.getFeature().contains("BOS/EOS")) continue;
				// System.out.println(node.getSurface() + "\t" +
				// featureSplite[7]);
				sbufferName.append(node.getSurface());
				if (featureSplite.length < 8) {
					sbufferFearture.append(node.getSurface());
				}
				else sbufferFearture.append(featureSplite[7]);
				if (node.getNext().getLength()==0) {
					bw.write(sbufferName + "\t" + sbufferFearture);
					bw.newLine();
					bw.flush();
					sbufferFearture.delete(0, sbufferFearture.length());
					sbufferName.delete(0, sbufferName.length());
				}
			}
		}
		br.close();
		bw.close();

	}

}
