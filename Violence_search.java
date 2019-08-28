package SubString;

/**
 * 暴力法查找子字符串
 */
public class Violence_search {
	/**
	 * 
	 * @param pat 模式字符串
	 * @param txt 文本字符串
	 * @return 如果找到了返回找到处索引,不然返回文本字符串长度
	 */
	public static int search(String pat, String txt) {
		int N = txt.length();
		int M = pat.length();
		for (int i = 0; i < N; i++) {
			int j;
			for (j = 0; j < M; j++) {
				if (txt.charAt(i + j) != pat.charAt(j)) {
					break;
				}
			}
			if (j == M) {
				return i;
			}
		}
		return N;
	}
}
/**
 * 
 * @author Heaven
 *	暴力算法的显式回滚
 *
 */
class Violence_search2 {
	public static int search(String pat,String txt) {
		int i,j;
		int N = txt.length();
		int M = pat.length();
		for (i = 0,j = 0; i < N&&j < M ;i++) {
			if (txt.charAt(i) == pat.charAt(j)) 
				j++;
			else {
				i -= j ;//回滚
				j = 0;
			}
		}
		if (j==M) 
			return i-M;//返回起始位置
		else 
			return N;//,默认返回N
	}
}
