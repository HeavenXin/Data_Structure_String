package String_Sort;

/**
 * 
 * @author Heaven 高位排序字符串
 */
public class MSD {
	private static int R = 256;// 字符表数量
	private static final int M = 15;// 插入排序阀值
	private static String[] aux;// 辅助数组

	private static int charAt(String a, int d) {
		if (d < a.length()) {// 判断
			return a.charAt(d);
		}
		return -1;
	}

	public static void sort(String[] a) {// 传入数组
		int N = a.length;
		aux = new String[N];
		sort(a, 0, N - 1, 0);
	}

	public static void sort(String[] a, int lo, int hi, int d) {
		if (hi <= lo + M) {
			insertion(a, lo, hi, d);
			return;
		}
		int[] count = new int[R + 2];// 计算频率的数组
		for (int i = lo; i <= hi; i++) {// 计算频率
			count[charAt(a[i], d) + 2]++;
		}
		for (int r = 0; r < R + 1; r++) {
			count[r + 1] += count[r];// 转化索引
		}
		for (int r = 0; r < R; r++) {
			aux[count[charAt(a[r], d) + 1]++] = a[r];// 这里只加了1,让长度0的不进入下一次
		}
		for (int i = lo; i <= hi; i++) {// 同理
			a[i] = aux[i - lo];
		}
		for (int r = 0; r < R; r++) {
			sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
		}
	}

	private static void insertion(String[] a, int lo, int hi, int d) {//简单的插入排序
		for (int i = lo; i <= hi; i++)
			for (int j = i; j > lo && less(a[j], a[j - 1], d); j--)
				exch(a, j, j - 1);
	}

	private static void exch(String[] a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}


	private static boolean less(String v, String w, int d) {//字符的比较
		for (int i = d; i < Math.min(v.length(), w.length()); i++) {
			if (v.charAt(i) < w.charAt(i))
				return true;
			if (v.charAt(i) > w.charAt(i))
				return false;
		}
		return v.length() < w.length();//所有字符相同,返回长度比较
		
	}

}