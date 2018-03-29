package Tools.SortAndCount;

public class WordFreq implements Comparable<WordFreq> {
		private int freq = 1;
		private String word;
	
		public WordFreq(String word) {
			this.word = word;
		}
	
		public int getFreq() {
			return freq;
		}
	
		public int compareTo(WordFreq o) {
			if (this.word.compareTo(o.word) == 0) return 0;
			else if (this.word.compareTo(o.word) > 0) return 1;
			else return -1;
		}
	
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof WordFreq)) {
				return false;
			}
			WordFreq wobj = (WordFreq) obj;
			return wobj.word.equals(word);
		}
	
		@Override
		public String toString() {
	
			return word + "(" + freq + ")";
		}
	
		public void increment() {
			freq++;
		}
	
		public int equals2(WordFreq obj) {
			return word.compareTo(obj.word);
		}
	
	}
