package study.hard.javalib.nativelib;

public class EqualsTest {

	public static void main(String[] args) {
		EqualsTest tester = new EqualsTest();

		tester.testEqualToOperator1();

		tester.testEqualToOperator2();

		tester.testEqualsNotOverrided();

		tester.testEqualsOverrided();
	}

	public void testEqualToOperator1() {
		Object a = new Object();
		Object b = a;
		System.out.println(a == b); // true
	}

	public void testEqualToOperator2() {
		Object a = new Object();
		Object b = new Object();
		System.out.println(a == b); // false
	}

	private void testEqualsNotOverrided() {
		Object a = new Object();
		Object b = new Object();
		System.out.println(a.equals(b)); // false
	}

	private void testEqualsOverrided() {
		OverridedObject a = new OverridedObject();
		a.setAge(30);
		a.setName("tester");
		OverridedObject b = new OverridedObject();
		b.setAge(30);
		b.setName("tester");
		System.out.println(a.equals(b)); // true
	}

	public class OverridedObject {

		private int age;
		private String name;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}

			if (obj == null || obj.getClass() != this.getClass()) {
				return false;
			}

			OverridedObject overrided = (OverridedObject)obj;
			return age == overrided.age
				&& (name == overrided.name || (name != null && name.equals(overrided.getName())));
		}
	}

}
