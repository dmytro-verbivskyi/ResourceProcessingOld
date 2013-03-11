package dverbovskiy.debugTraining;

public class Test {
    public static void main(String[] args) {
        Sub a = new Sub(45);
        System.out.println(a.getI());
    }
}

class Base {
    private int i;

    Base() {this.i = 99;}

    Base(int i) { this.i = i; }

    public int getI() {
        return this.i;
    }
}

class Sub extends Base {

    public Sub(int i) {
        super(i);
    }
}


/*

public class Test {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}

class MyThread extends Thread {
    public void run() {
        System.out.print("Running");
    }
    public void start() {
        System.out.print("Starting");
    }
}
*/
