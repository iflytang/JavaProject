package javacore.test;

public abstract class Employee {

    private double salary;

    public abstract String getDiscription();

    public double getSalary() {

        return salary;
    }

    public void setSalary(double salary) {

        this.salary = salary;
    }
}
