package study.hard.javalib.nativelib;

class IntBag {
    int i = 0;
    public void add(int i) {
        this.i += i;
    }
    public int get(){
        return this.i;
    }
    public void set(int i) {
        this.i = i;
    }

}

interface Function1 {
    public final IntBag value = new IntBag();
    public int apply();
}

class Closure implements Function1 {
    private IntBag x = value;
    Function1 f;
    Function1 g;

    @Override
    public int apply()  {
        // print('inside foo, call to f(): ' + f()); // "2"
        // inside apply, call to f.apply()
        System.out.println("inside foo, call to f.apply(): " + f.apply());
        return 0;
    }

    public Closure() {
        f = new Function1() {
            @Override
            public int apply()  {
                x.add(1);
                return x.get();
            }
        };
        g = new Function1() {
            @Override
            public int apply()  {
                x.add(-1);
                return x.get();
            }
        };
        // x = 1;
        x.set(1);
    }
}

public class ClosureTest {
    public static void main(String[] args) {
        // foo()
        Closure foo = new Closure();
        foo.apply();
        // print('call to g(): ' + g()); // "1"
        System.out.println("call to foo.g.apply(): " + foo.g.apply());
        // print('call to f(): ' + f()); // "2"
        System.out.println("call to foo.f.apply(): " + foo.f.apply());

    }
}
