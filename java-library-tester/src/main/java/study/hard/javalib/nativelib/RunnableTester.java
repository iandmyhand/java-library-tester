package study.hard.javalib.nativelib;

public class RunnableTester implements Runnable {

	int sequence;

	public RunnableTester(int sequence) {
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
