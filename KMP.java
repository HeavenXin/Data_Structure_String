package SubString;

/**
 * 
 * @author Heaven Kmp算法进行查找子字符串
 */
public class KMP {
	private String pat;// 查找字符串
	private int[][] dfa;// 自动机

	public KMP(String pat) {
		this.pat = pat;
		int M = pat.length();
		int R = 256;
		dfa = new int[R][M];
		dfa[pat.charAt(0)][0] = 1;// 设置0
		for (int X = 0, j = 1; j < M; j++) {
			for (int c = 0; c < R; c++) {
				dfa[c][j] = dfa[c][X];// 失败情况下的方法
			}
			dfa[pat.charAt(j)][j] = j + 1;
			/*
			 * 因为自动机是为了让 s[j+1] = dfa[(txt.charAt(i)][j]; 且相同有 dfa[(txt.charAt(j)][j] = j+1;
			 * 
			 */
			X = dfa[pat.charAt(j)][X];// 更新
		}
	}

	public int search(String txt) {
		int i, j;
		int N = txt.length();
		int M = pat.length();
		for (i = 0, j = 0; i < N && j < M; i++) {
			j = dfa[txt.charAt(i)][j];
		}
		if (j==M) {
			return i-M;
		}else {
			return -1;
		}
	}

}
