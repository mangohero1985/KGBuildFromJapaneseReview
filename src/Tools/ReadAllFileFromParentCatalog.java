package Tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadAllFileFromParentCatalog {
	
	//读取一个文件夹下所有文件及子文件夹下的所有文件
	public List ReadAllFile(String filePath) {
		File f = null;
		f = new File(filePath);
		File[] files = f.listFiles(); // 得到f文件夹下面的所有文件。
		List<File> list = new ArrayList<File>();
		for (File file : files) {
			if(file.isDirectory()) {
				//如何当前路劲是文件夹，则循环读取这个文件夹下的所有文件
				ReadAllFile(file.getAbsolutePath());
			} else {
				list.add(file);
			}
		}
		return list;
	}
	
	//读取一个文件夹下的所有文件夹和文件
	public void ReadFile(String filePath) {
		File f = null;
		f = new File(filePath);
		File[] files = f.listFiles(); // 得到f文件夹下面的所有文件。
		List<File> list = new ArrayList<File>();
		for (File file : files) {
			list.add(file);
		}
		for(File file : files) {
			System.out.println(file.getAbsolutePath());
		}
	}
	
}