package SubString;

import java.math.BigInteger;
import java.util.Random;
/**
 * 
 * @author Heaven
 *	利用散列表进行查找
 */
public class RabinKarp {
	private long patHash;// 模式字符串的散列值
	private int M;// 模式字符串的长度
	private long Q;// 一个很大的素数
	private int R = 256;// 设置字母表
	private long RM;// 用于减去第一个字符串
	private String pat;

	public RabinKarp(String pat) {
		this.M = pat.length();
		this.pat = pat;
		Q = longRandomPrime();
		RM = 1;
		for (int i = 1; i <= M - 1; i++) {
			RM = (RM * R) % Q;
		}
		patHash = hash(pat, M);
	}

	private int search(String txt) {// 进行查找
		int N = txt.length();
		long txtHash = hash(txt, M);// 初识的值
		if (patHash == txtHash && check(txt, 0)) {
			return 0;// 排除上来就相等的情况
		}
		for (int i = M; i < N; i++) {
			txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;//先删除开头第一个字符
			txtHash = (txtHash * R + txt.charAt(i)) % Q;//再重新计算添加下一个字符
			int get = i - M + 1;//往前一位
			if (txtHash==patHash&& check(txt, get)) {
				return get;
			}
		}
		return -1;//返回-1
	}

	private long hash(String key, int i) {// 利用公式获取散列值
		long h = 0;
		for (int j = 0; j < M; j++)
			h = (R * h + key.charAt(j)) % Q;
		return h;
	}

	private boolean check(String key, int i) {
		for (int j = 0; j < M; j++)
			if (pat.charAt(j) != key.charAt(i + j))
				return false;
		return true;
	}

	private static long longRandomPrime() {
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();
	}
}
