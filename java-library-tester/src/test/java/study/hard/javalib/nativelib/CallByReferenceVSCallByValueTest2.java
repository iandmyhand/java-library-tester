package study.hard.javalib.nativelib;

public class CallByReferenceVSCallByValueTest2 {

	public static void main(String[] args) {
		User user = new User("James");
		System.out.println("1) An user in the main: " + user.getName());
		foo(user);
		System.out.println("4) An user in the main: " + user.getName());
		bar(user);
		System.out.println("7) An user in the main: " + user.getName());
	}

	private static void foo(User user) {
		System.out.println("2) An user in the foo: " + user.getName());
		user = new User("Tomas");
		System.out.println("3) An User in the foo: " + user.getName());
	}

	private static void bar(User user) {
		System.out.println("5) An user in the foo: " + user.getName());
		user.setName("Bill");
		System.out.println("6) An User in the foo: " + user.getName());
	}

	private static class User {
		private String name;

		public User(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
