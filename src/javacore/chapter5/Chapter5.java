package javacore.chapter5;

public class Chapter5 {

    // variable arguments method 1, arguments should be variable array with initial value
    public void getArguments1(Object[] args) {

        System.out.print(args.length + " variable arguments are: ");
        for(Object e : args) {
            System.out.print(e.toString() + " ");
        }
        System.out.print("\n");
    }

    // variable arguments method 2, arguments can be any variable format
    public void getArguments2(Object... args) {

        System.out.print(args.length + " variable arguments are: ");
        for(Object e : args) {
            System.out.print(e.toString() + " ");
        }
        System.out.print("\n");
    }


    public static void main(String[] args) {

        Chapter5 chapter5 = new Chapter5();

        chapter5.getArguments1(new String[]{"I am", "a student1."});
        chapter5.getArguments2("I am", "a student2.");
    }
}
