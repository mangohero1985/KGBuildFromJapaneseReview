package Tools.SortAndCount;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.TreeSet;



public class TestWord {
	public static void sort(String inputPath, String outputPath){		
		//OrderedList<WordFreq> list=new OrderedList<WordFreq>();
		//分词并且只留下"名词一般"的name
//		MenuNameSplit menuNameSplit = new MenuNameSplit();
//		String InputPath= "/Users/mangohero1985/Desktop/twitterSearchResult/SpamingFewerThanFiveOutput.txt";
//		String outputPath ="/Users/mangohero1985/Desktop/twitterSearchResult/generalNounFromSpamingFewerThanFive.txt";
//		menuNameSplit.menuSplit(InputPath, outputPath);
		
		LinkedList<WordFreq> orderedList = new LinkedList<WordFreq>();
		try {
			Scanner fileIn = new Scanner(new FileReader(inputPath));
			fileIn.useDelimiter("[\t\r\n., ]+");
			while (fileIn.hasNext()) {
				//read
				String str = fileIn.next().trim();
				WordFreq wf = new WordFreq(str);
				ListIterator<WordFreq> iter = search(orderedList, wf);
				if (iter != null) {
					//exists
					((WordFreq) iter.previous()).increment();
					//update the freq!!! 
					/* iter.set(wf);
					 iter=null;*/
				} else {
					//add it to the list
					OrderedList.insertOrder(orderedList, wf);
				}
			}
			fileIn.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//display ,order by word
		try {
			displayResults(orderedList,outputPath);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//desc order by freq
		try {
			displayOrderedResults(orderedList,outputPath);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ����Ƶ�ʸߵ�����
	 * @param <T>
	 * @param list
	 * @throws IOException 
	 */
	private static <T extends WordFreq> void displayOrderedResults(Collection<T> list, String outputPath) throws IOException {
		final TreeSet<T> set = new TreeSet<T>(
				new Comparator<T>() {
					public int compare(T o1, T o2) {
						// TODO Auto-generated method stub
						if (o1.getFreq() == o2.getFreq())
							return o1.equals2(o2);
						else
							return o2.getFreq() > o1.getFreq() ? 1 : -1;
					}
				});

		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			set.add((T) iter.next());
		}
		System.out.println("-----------order by freq begin---------------");
		displayResults(set,outputPath);
		System.out.println("-----------order by freq end-----------------");
		System.out.println("排序统计完成");
	}

	private static <T> void displayResults(Collection<T> list, String outputPath) throws IOException {
	
		FileWriter fw = new FileWriter(outputPath);
		BufferedWriter bw = new BufferedWriter(fw);
		
		Iterator<T> iter = list.iterator();
		int count = 1;
		while (iter.hasNext()) {
			T item = (T) iter.next();
		    bw.write(item.toString());
		    bw.newLine();
		    bw.flush();
			System.out.printf("%-14s", item);
			if (count % 4 == 0)
				System.out.println();
			count++;
		}
		System.out.println();
		bw.close();
	}

	/**
	 * ��ѯ��ǰ�����Ƿ��ڼ�����
	 * 
	 * @param list
	 * @param target
	 * @return
	 */
	public static <T> ListIterator<T> search(LinkedList<T> list, T target) {
		ListIterator<T> iter = list.listIterator();
		while (iter.hasNext()) {
			T item = (T) iter.next();
			if (target.equals(item)) {
				return iter;
			}
		}
		return null;
	}

}
