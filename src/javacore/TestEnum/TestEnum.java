package javacore.TestEnum;

/**
 * Created by tsf on 18-5-16.
 *
 * @ref https://blog.csdn.net/javazejian/article/details/71333103
 */

public class TestEnum {

    public static void main(String args[]) {
        //============== simple Enum ====================
        /* test name(), ordinal(), values() */
        for (Day day: Day.values()) {
            System.out.println("Today is " + day.name() + ", ordinal=" + day.ordinal() +
                    ", type=" + Day.values()[day.ordinal()] + ", valueOf=" + Day.valueOf(day.name()));
        }

        /* test valueOf() */
        Day d = Enum.valueOf(Day.class, "Monday");
        System.out.println("Enum.valueOf=" + d);
        System.out.println();

        // ============== complicated Enum ===============
        for (Day2 day: Day2.values()) {
            System.out.println("Today is " + day.name() + ", " + day.getDesc() + "! ordinal" + day.ordinal() +
                    ", value=" + day.getValue());
        }

        Day2 d2 = Day2.Monday;
        System.out.println(d2.toString());
        System.out.println();

        // ============== interface Enum ==================
        Demo demo = Demo.FOOD;
        demo.eat();
        demo.run();

    }
}

/* simple enum type. */
enum Day {
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday,
    Sunday,
}

/* complicated enum type, which owns self constructor and method. */
enum Day2 {
    Monday(1, "workday"),
    Tuesday(2, "workday"),
    Wednesday(3, "workday"),
    Thursday(4, "workday"),
    Friday(5, "workday"),
    Saturday(6, "restday"),
    Sunday(7, "restday");    // must end with ';'

    private int value;
    private String desc;

    private Day2(int _value, String _desc) {
        value = _value;
        desc = _desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + desc;
    }

}

/* enum can implements more interface */
interface food {
    void eat();
}

interface sports {
    void run();
}

enum Demo implements food, sports {
    FOOD,
    SPORTS;   // must end with ';'

    @Override
    public void eat() {
        System.out.println("I like eating meat.");
    }

    @Override
    public void run() {
        System.out.println("I like running.");
    }
}