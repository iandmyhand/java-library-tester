package study.hard.javalib.nativelib;

import java.util.ArrayList;

import org.junit.Test;

public class ThreadTest {

	@Test
	public void testThread() {
		ArrayList<Thread> threadList = new ArrayList<Thread>();

		for (int i = 0; i < 10; i++) {
			Thread thread = new ThreadTester(i);
			thread.start();
			threadList.add(thread);
		}

		for (int i = 0; i < threadList.size(); i++) {
			Thread thread = threadList.get(i);
			try {
				thread.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("End Thread test.");
	}

}
