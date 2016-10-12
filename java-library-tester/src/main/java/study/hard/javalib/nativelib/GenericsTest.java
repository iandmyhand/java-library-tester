package study.hard.javalib.nativelib;


public class GenericsTest {

	public static void main(String[] args) {
		genericArray();
	}

	public static void genericArray() {
		Integer[] is = {1, 2, 3};
		String[] ss = {"Hello", "World"};
		printArray(is);
		printArray(ss);
	}

	private static <E> void printArray(E[] arr) {
		for(E e: arr) {
			System.out.println(e.toString());
		}
	}

}
