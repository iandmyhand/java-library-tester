package study.hard.javalib.nativelib;

public class CloserTest {

	public static void main(String[] args) {
        int counter = 0;
//        counter++; // compile error!
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                counter++; // compile error!
                System.out.println(counter);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
//                counter++; // compile error!
                System.out.println(counter);
            }
        });
        thread1.start();
        thread2.start();
	}

}
