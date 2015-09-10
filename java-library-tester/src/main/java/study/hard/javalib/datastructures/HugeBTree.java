package study.hard.javalib.datastructures;

/**
 * How to send a huge BTree data to another server and restore it used the object oriented language?
 */

public class HugeBTree implements Runnable {

	public void run() {
		BTree bTree = new BTree();

	}

	public static void main(String[] args) {
		(new Thread(new HugeBTree())).start();
	}

	public class BTree {

		public BTree() {
			System.out.println("I'm in the BTree()");
		}

	}
}