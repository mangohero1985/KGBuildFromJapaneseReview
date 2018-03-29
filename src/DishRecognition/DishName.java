/**
 * 
 */
package DishRecognition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author mangohero1985
 * @create-time Jun 15, 2014 9:13:18 AM
 */
public class DishName {

	public void Selection(BufferedReader br, BufferedWriter bw) {

		ArrayList<String> ArrayMenu = new ArrayList<String>();
		String readLine;
		int n = 0;
		try {
			while ((readLine = br.readLine()) != null) {
				if (!readLine.contains("0")) continue;
				else {
					// System.out.println(line);
					String NE[] = readLine.split("\t");
					if (NE[5].contains("M")) {
						ArrayMenu.add(NE[0]);
					}
					else if (NE[5].contains("0") && !ArrayMenu.isEmpty()) {
						for (int i = 0; i < ArrayMenu.size(); i++) {
							bw.write(ArrayMenu.get(i));
						}
						// System.out.println(n++);
						bw.newLine();
						bw.flush();
						ArrayMenu.clear();
					}
					else continue;
				}

			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("菜名提取完成");
	}

	public void SelectionForSentence(BufferedReader br, BufferedWriter bw, ArrayList<String> dishNameArrayList) {

		ArrayList<String> ArrayMenu = new ArrayList<String>();
		StringBuffer strConcatenate = new StringBuffer();
		String readLine;
		try {
			while ((readLine = br.readLine()) != null) {
				if (readLine.equals("")) {
					bw.newLine();
					bw.flush();
				}
				else if (!readLine.contains("0")) continue;
				else {
					// System.out.println(line);
					String NE[] = readLine.split("\t");
					if (NE[5].contains("M")) {
						ArrayMenu.add(NE[0]);
					}
					else if (NE[5].contains("0") && !ArrayMenu.isEmpty()) {
						// bw.write(DishName+" T" + ",");
						for (int i = 0; i < ArrayMenu.size(); i++) {

							strConcatenate.append(ArrayMenu.get(i));
							// bw.write(ArrayMenu.get(i));
							// bw.flush();
						}
						if (dishNameArrayList.contains(strConcatenate.toString())) {
							bw.write(strConcatenate.toString());
							bw.write(" ");
							bw.flush();
						}

						strConcatenate.setLength(0);
						// System.out.println(n++);
						// bw.newLine();
						// bw.flush();
						ArrayMenu.clear();
					}
					else continue;
				}

			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("菜名提取完成");

	}

}
