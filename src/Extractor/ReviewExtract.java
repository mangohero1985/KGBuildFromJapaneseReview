/**
 * 
 */
package Extractor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Spring;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Tools.CopyFile;

import jp.uclab.io.IOhandle;

/**
 * @author mangohero1985
 * @create-time Jun 14, 2014 4:14:11 PM
 */
// 从POI文件中抽取Reviews
public class ReviewExtract {

	public void ReviewResults(BufferedReader br, BufferedWriter bw, String dataSourceFile, String filePath) throws IOException {

		try {
			// 创建一个Stringbuffer来存储读入的POI文件

			StringBuffer stringBuffer = new StringBuffer();
			String readLine = null;

			while ((readLine = br.readLine()) != null) {
				stringBuffer.append(readLine);
			}
			// 创建Json对象来存储StringBuffer中的文件

			JSONObject obj = new JSONObject(stringBuffer.toString());

			JSONArray POI = obj.getJSONArray("POI");
			for (int i = 0; i < POI.length(); i++) {
				String Review = POI.getJSONObject(i).getString("Comment");
				bw.write(Review);
				bw.newLine();
				bw.flush();
			}

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("这不是json文件,自动copy文件到文件夹下");
			File SourceFile = new File(dataSourceFile);
			File DesDir = new File(filePath);
			CopyFile copyFile = new CopyFile();
			copyFile.copyFile(SourceFile, DesDir, "ReviewExtractResult.txt");
		}

		System.out.println("评论抽取完成");
	}

	public void ReviewResultsWithKeys(BufferedReader br, BufferedWriter bw, String keys) throws Exception {

		try {
			// 创建一个Stringbuffer来存储读入的POI文件

			StringBuffer stringBuffer = new StringBuffer();
			String readLine = null;

			while ((readLine = br.readLine()) != null) {
				stringBuffer.append(readLine);
			}
			// 创建Json对象来存储StringBuffer中的文件

			JSONObject obj = new JSONObject(stringBuffer.toString());

			JSONArray POI = obj.getJSONArray("POI");
			for (int i = 0; i < POI.length(); i++) {
				String Review = POI.getJSONObject(i).getString("Comment");
				if (Review.contains(keys)) {
					bw.write(Review);
					bw.newLine();
					bw.flush();
				}
			}

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("带关键字评论抽取完成");
	}

}
