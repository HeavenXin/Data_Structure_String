package String_Sort;
/**
 * 
 * @author Heaven
 *	本方法为低位优先的字符串排序算法,要求长度一致
 */

public class LSD {
	public static void sort(String[] a,int w) {
		int N = a.length;//获取数组长度,方便创建备份
		int R = 256;//ASCII码大小
		String[] aux = new String[N];//备份数组创建
		for (int d = w -1; d>= 0; d--) {//第一层遍历
			int[] count = new int[R+1];//计算出现频率
			for (int i = 0; i < N; i++) {//遍历每个字符串的相同位置
				count[a[i].charAt(d)+1]++;//获取频率
			}
			for (int r = 0; r < R; r++) {//频率转换成索引
				count[r+1]+=count[r];
			}
			for (int r = 0; r < N; r++) {//元素分类
				aux[count[a[r].charAt(d)]++] = a[r];
			}
			for (int i = 0; i < N; i++) {//回写
				a[i] = aux[i];
			}
			
		}
	}
}
