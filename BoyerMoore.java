package SubString;
/**
 * 
 * @author Heaven
 *	使用BoyerMoore来匹配子字符串
 */
public class BoyerMoore {
	private int[] right;//设置数组
	private String pat;//查找字符串
	public BoyerMoore(String pat) {
		this.pat = pat;
		int M = pat.length();
		int R = 256;
		right = new int[R];
		for (int i = 0; i < R; i++) {
			right[i] = -1;
		}
		for (int i = 0; i < M; i++) {
			right[pat.charAt(i)] = i;//设置模型字符串位置
		}
	}
	public int search(String txt) {
		int M = pat.length();
		int N = txt.length();
		int skip;//设置跳过的距离
		for (int i = 0; i <= N-M; i+=skip) {//每次增加skip位置的
			skip = 0;
			for (int j =M-1; j>=0 ; j--) {
				if (pat.charAt(j)!=txt.charAt(i+j)) {//发现不同的时候
					skip = j - right[txt.charAt(i+j)];//可能减去-1,也可能减去right数组中pat对应长度
					if (skip<1) {//强制+1
						skip = 1;
					}
					break;
				}
			}
			if (skip == 0) {
				return i ;
			}
		}
		return -1;
		
	}
}
