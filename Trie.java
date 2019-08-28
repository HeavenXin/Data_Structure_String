package String_Search;

import Queue.Queue;

/**
 * 
 * @author Heaven
 *
 * @param <Value>一般为数字,用于标记 此方法是字符查找树完成字符的查找
 */
public class Trie<Value> {
	private static int R = 256;// 设置字母表
	private Node root;

	private static class Node {
		private Object val;// 设置保存的值
		private Node[] next = new Node[R];// 下一个结点也是一个数组
	}

	public Value get(String key) {
		Node x = get(root, key, 0);
		if (x == null)
			return null;
		return (Value) x.val;

	}

	private Node get(Node x, String key, int id) {
		if (x == null)
			return null;// 不存在直接返回
		if (id == key.length()) {
			return x;
		}
		char temp = key.charAt(id);// 获取字符
		return get(x.next[temp], key, id + 1);// 查找下一簇
	}

	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}

	public Node put(Node x, String key, Value val, int id) {
		if (x == null) {
			x = new Node();
		}
		if (id == key.length()) {
			x.val = val;
			return x;
		}
		char temp = key.charAt(id);// 查找第id位的字符
		x.next[temp] = put(x.next[temp], key, val, id + 1);
		return x;
	}
	public void delete(String key) {
		root = delete(root, key, 0);
	}
	private Node delete(Node x,String key,int d) {
		if (x == null) {
			return null;//如果不存在
		}
		if (d==key.length()) {
			x.val = null;
		}else {
			char temp = key.charAt(d);
			delete(x.next[temp], key, d+1);
		}
		if (x.val != null) {
			return x;
		}
		for (char c = 0; c < R; c++) {
			if (x.next[c] != null) 
				return x;
		}
		return null;
		
	}
	/**
	 * 
	 * @return 利用keysWithPrefix方法,输入空字符串,得到队列
	 */
	public Iterable<String> keys() {
		return keysWithPrefix("");
	}

	public Iterable<String> keysThatMatch(String pat) {
		Queue<String> queue = new Queue<String>();
		collect(root, "", pat, queue);
		return (Iterable<String>) queue;
	}

	public Iterable<String> keysWithPrefix(String pre) {
		Queue<String> queue = new Queue<String>();
		collect(get(root, pre, 0), pre, queue);
		return (Iterable<String>) queue;
	}

	public String longestPrefixOf(String s) {
		int length = search(root, s, 0, 0);
		return s.substring(0, length);
	}

	

	/**
	 * 
	 * @param node
	 * @param pre
	 * @param queue 利用此方法,获取符合条件的字符串的键值
	 */
	private void collect(Node node, String pre, Queue<String> queue) {
		if (node == null) {
			return;
		}
		if (node.val != null) {
			queue.enqueue(pre);
		}
		for (char c = 0; c < R; c++) {
			collect(node.next[c], pre + c, queue);
		}
	}

	/**
	 * 
	 * @param node
	 * @param pre
	 * @param pat
	 * @param queue 重载此方法,获取获取匹配通配符
	 */
	private void collect(Node node, String pre, String pat, Queue<String> queue) {// 重载
		int d = pre.length();
		if (node == null) {
			return;
		}
		if (d == pat.length() && node.val != null) {
			queue.enqueue(pre);
		}
		if (d == pat.length()) {
			return;
		}
		char next = pat.charAt(d);
		for (char c = 0; c < R; c++) {
			if (next == '.' || next == c) {
				collect(node.next[c], pre + c, queue);
			}

		}
	}
	/**
	 * 
	 * @param x
	 * @param s
	 * @param d
	 * @param length
	 * @return	对给定的字符串的最长前缀进行匹配
	 * 	
	 */
	private int search(Node x, String s, int d, int length) {
		if (x == null) {
			return length;
		}
		if (x.val != null) {
			length = d;//标记上
		}
		if (d == s.length()) {
			return length;//找到结尾了
		}
		char temp = s.charAt(d);
		return search(x.next[temp], s, d+1, length);
	}
}
