package study.hard.javalib.nativelib;

public class GetClassTest {

	public static void main(String[] args) {
		GetClassTest tester = new GetClassTest();
		ExtendedClassA extendedA = tester.new ExtendedClassA();
		extendedA.printClass(); // class study.hard.javalib.nativelib.GetClassTest$ExtendedClassA

		Class<? extends ClassA> clazz = extendedA.getClass();
		System.out.println(clazz.getName());
	}

	public class ClassA {
		public void printThisClass() {
			System.out.println(getClass());
		}
	}

	public class ExtendedClassA extends ClassA {
		public void printClass() {
			printThisClass();
		}
	}

}
