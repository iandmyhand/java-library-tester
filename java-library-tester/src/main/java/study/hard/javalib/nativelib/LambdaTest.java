package study.hard.javalib.nativelib;

public class LambdaTest {

    int counter = 0;

	public static void main(String[] args) {
        LambdaTest lt = new LambdaTest();
        Thread thread1 = lt.t1();
        Thread thread2 = lt.t1();

        thread1.start();
        thread2.start();

        Thread thread3 = lt.t2();
        Thread thread4 = lt.t2();

        thread3.start();
        thread4.start();
	}

	public Thread t1() {
        return new Thread(() -> {
            counter++;
            System.out.println(counter);
        });
    }

    public Thread t2() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                counter++;
                System.out.println(counter);
            }
        });
    }

}
