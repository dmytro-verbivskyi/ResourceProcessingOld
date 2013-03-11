package dverbovskiy.debugTraining;

public class Test {
    public int a;   //--1--
    public Obj o;   //--2--

    public int getB() {
//        int b;
//        Obj o;
//        return o.getX() + b;   //--3--
        return 1;
    }

    public static void main(String[] args) {
        Test t = new Test();
        System.out.println(t.a);        //--4--
        System.out.println(t.o);        //--5--
        System.out.println(t.getB());   //--6--
    }
}

class Obj {
    private int x;

    Obj()             { this.x = 1; }
    public int getX() { return this.x; }
}