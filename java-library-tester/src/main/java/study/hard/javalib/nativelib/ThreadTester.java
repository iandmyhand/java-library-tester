package study.hard.javalib.nativelib;

/**
 * A class to test Thread.
 * @author iandmyhand@gmail.com
 */
public class ThreadTester extends Thread {

	int sequence;

	public ThreadTester(int sequence) {
		this.sequence = sequence;
	}

	public void run() {
		System.out.println("Thread [" + this.sequence + "] start.");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Thread [" + this.sequence + "] end.");
	}
}
