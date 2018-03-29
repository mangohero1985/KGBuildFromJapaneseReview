/**
 * 
 */
package Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author mangohero1985
 * @create-time Jun 23, 2014 9:34:10 AM
 */
public class ReservNSentence {

	public void Researve(BufferedReader br, BufferedWriter bw, int N, String Keyword) throws IOException {

		ArrayList<String> arrayList = new ArrayList<String>();
		String ReadLine = "";
		while ((ReadLine = br.readLine()) != null) {

			String[] splite = ReadLine.split("。");
			for (int i = 0; i < splite.length; i++) {
				if (splite[i].contains(Keyword) && (i + N) < splite.length) {
					int j = 1;
					while (j <= N) {
						if (!arrayList.contains(splite[i + j])) {
							bw.write(splite[i + j]);
							bw.newLine();
							bw.flush();
							arrayList.add(splite[i + j]);
						}
						j++;
					}
				}
				else if (splite[i].contains(Keyword) && (i + N) < splite.length) {
					int j = 1;
					while (j <= splite.length - i) {
						if (!arrayList.contains(splite[i + j])) {
							bw.write(splite[i + j]);
							bw.newLine();
							bw.flush();
							arrayList.add(splite[i + j]);
						}
						j++;
					}
				}

			}
		}
		System.out.println("N-Sentence处理完成");
	}
	public ArrayList<String> ResearvWithoutN (BufferedReader br, BufferedWriter bw, int N, String Keyword) throws Exception{
		ArrayList<String> arrayList = new ArrayList<String>();
		String ReadLine = "";
		while ((ReadLine = br.readLine()) != null) {

			String[] splite = ReadLine.split("。");
			for (int i = 0; i < splite.length; i++) {
				if (splite[i].contains(Keyword) && (i + N) < splite.length) {
					int j = 1;
					while (j <= N) {
						if (!arrayList.contains(splite[i + j])) {
							bw.write(splite[i + j]);
							bw.newLine();
							bw.flush();
							arrayList.add(splite[i + j]);
						}
						j++;
					}
				}
				else if (splite[i].contains(Keyword) && (i + N) < splite.length) {
					int j = 1;
					while (j <= splite.length - i) {
						if (!arrayList.contains(splite[i + j])) {
							bw.write(splite[i + j]);
							bw.newLine();
							bw.flush();
							arrayList.add(splite[i + j]);
						}
						j++;
					}
				}

			}
		}
		System.out.println("WhitoutN-Sentence处理完成");
		return arrayList;
		
	}
}
