package study.hard.javalib.nativelib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

public class ClassIsAnnotationPresentTest {

	@Retention(RetentionPolicy.RUNTIME)
	public @interface TempInterface{
		String value();

		int age();

		float salary();
	}

	public static void main(String[] args) {
		try {
			for (Method ms : Class.forName("study.hard.javalib.nativelib.ClassIsAnnotationPresentTest").getMethods()) {
				System.out.print(ms.getName() + "-");
				System.out.println(ms.isAnnotationPresent(TempInterface.class));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@TempInterface(value = "Some String", age = 30, salary = 1234.56f)
	public void doesNothing() {
		System.out.println("Really for nothing");
	}
}
