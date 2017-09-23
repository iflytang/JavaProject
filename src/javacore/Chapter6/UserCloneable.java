package javacore.Chapter6;

/**
*  implement and test Cloneable interface
* */

import java.util.Date;
import java.util.GregorianCalendar;

class User implements Cloneable {

    private String name;
    private int salary;
    private Date birthday;

    public User(String name, int salary, Date birthday) {

        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        User clone = (User) super.clone();
        clone.birthday = (Date) birthday.clone();

        return clone;
    }

    public void setSalary(int salary) {

        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setBirthday(Date date) {

        this.birthday = date;
    }

    public Date getBirthday() {

        return birthday;
    }
}

public class UserCloneable {

    public static void main(String[] args) {

        User user1 = new User("Alice", 2000, new Date());
        System.out.println("========= Test salary [primitive type] ========");

        user1.setSalary(3000);
        System.out.println("user1's salary: " + user1.getSalary());

        try{
            User user2 = user1.clone();
            user2.setSalary(4000);
            System.out.println("user2's salary: " + user2.getSalary());
            System.out.println("user1's salary: " + user1.getSalary());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println("\n========= Test Birthday [object type] ========");
        user1.setBirthday(new GregorianCalendar(2001, 10, 1).getTime());
        System.out.println("user1's salary: " + user1.getBirthday());

        try{
            User user3 = user1.clone();
            user3.setBirthday(new GregorianCalendar(2007, 9, 18).getTime());
            System.out.println("user3's salary: " + user3.getBirthday());
            System.out.println("user1's salary: " + user1.getBirthday());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
