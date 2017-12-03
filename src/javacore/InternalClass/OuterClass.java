package javacore.InternalClass;

/**
 * Created by tsf on 17-12-3.
 *
 * @Description about internal class
 */


public class OuterClass {

    private String name;
    private int age;

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    /**
     * internal class
     */
    public class InternalClass {
        public InternalClass() {
            name = "iflytang";
            age = 21;
        }

        public void getDescription() {
            System.out.println("name: " + getName() + " ; age: " + getAge());
        }
    }


    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        OuterClass.InternalClass internalClass = outerClass.new InternalClass();
        internalClass.getDescription();
    }
}
