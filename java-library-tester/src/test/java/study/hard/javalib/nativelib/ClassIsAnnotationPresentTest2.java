package study.hard.javalib.nativelib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

public class ClassIsAnnotationPresentTest2 {

	@Retention(RetentionPolicy.RUNTIME)
	public @interface TempAnnotation{
		String value();

		int age();

		float salary();
	}

	public interface TempClass {
		void doesNothing();
	}

	@TempAnnotation(value = "Some String", age = 30, salary = 1234.56f)
	public class TempClassImpl1 implements TempClass {
		public void doesNothing() {
			System.out.println("Really for nothing");
		}
	}

	public class TempClassImpl2 implements TempClass {
		public void doesNothing() {
			System.out.println("Really for nothing");
		}
	}

	public static void main(String[] args) {
		ClassIsAnnotationPresentTest2 clazz = new ClassIsAnnotationPresentTest2();
		Map<Class<?>, Boolean> map = clazz.getCache(clazz);
		Iterator<Entry<Class<?>, Boolean>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Class<?>, Boolean> entry = iter.next();
			System.out.println("Key: " + entry.getKey());
			System.out.println("Value: " + entry.getValue());
		}
	}

	private Map<Class<?>, Boolean> getCache(ClassIsAnnotationPresentTest2 clazz) {
		Map<Class<?>, Boolean> cache = Maps.newHashMap();
		Class<? extends TempClass> tempClazz = null;
		Boolean isAnnotation = false;

		tempClazz = clazz.new TempClassImpl1().getClass();
		isAnnotation = tempClazz.isAnnotationPresent(TempAnnotation.class);
		cache.put(tempClazz, isAnnotation);

		tempClazz = clazz.new TempClassImpl2().getClass();
		isAnnotation = tempClazz.isAnnotationPresent(TempAnnotation.class);
		cache.put(tempClazz, isAnnotation);

		return cache;
	}

}
