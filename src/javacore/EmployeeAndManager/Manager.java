package javacore.EmployeeAndManager;

import java.util.Arrays;

public class Manager extends Employee {

    private double bonus;

    @Override
    public double getSalary() {

        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }

    public void setSalary(double salary, double bonus) {
        super.setSalary(salary);
        this.bonus = bonus;
    }

    @Override
    public String getDiscription() {
        return "I am a manager, my salary is $";
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[salary = " + getSalary() +
                ", bonus = " + bonus +"]";
    }

    public void printString(Object... args) {
        System.out.print("printString: " );
        for(Object e : args) {
            System.out.print(e.toString() + " ");
        }
        System.out.println();

        for(int i=0; i<args.length; i++) {
            System.out.print(args[i].toString() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        int[][] b = {{1, 2, 3}, {4, 5, 6}};

        Manager manager = new Manager();
        manager.setSalary(1000, 2000);
        System.out.println(manager.getDiscription() + manager.getSalary());
        System.out.println(manager.toString());

        manager.printString("I am", "a student.");

        System.out.println("a:" + Arrays.deepToString(a) +"\nb:" + Arrays.deepToString(b) +
                "\na equals b?");
        System.out.println(Arrays.deepEquals(a, b));

    }
}
