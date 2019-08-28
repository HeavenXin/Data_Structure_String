package String_Sort;
/**
 * 
 * @author Heaven
 *	三分法进行字符串的排序
 *	只对等于切分符的子数组的进行排序,减少了空间的时间的重复性
 */
public class Quick3Sort {
	private static int charAt(String s, int d) {
		if (s.length() > d) {
			return s.charAt(d);
		}
		return -1;// 定义查找
	}

	public void sort(String[] a) {// 排序
		int N = a.length;
		sort(a, 0, N - 1, 0);// 传递
	}

	public void sort(String[] a, int lo, int hi, int d) {
		if (lo >= hi) {
			return;
		}
		int lt = lo;
		int gt = hi;
		int v = charAt(a[lo], d);// 获取初始值
		int i = lo + 1;// 从第二个开始比较
		while (i <= gt) {
			int t = charAt(a[i], d);
			if (t < v) {
				exch(a, lt++, i++);
			} else if (t > v) {
				exch(a, gt--, i);// 这里i不自增,是为了检测是否还需要再次交换
			} else {
				i++;
			}
		}
		sort(a, lo, lt - 1, d);
		if (v > 0) {
			sort(a, lt, gt, d + 1);
		}
		sort(a, gt + 1, gt, d);
	}

	private static void exch(String[] a, int x, int y) {
		String temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
}
