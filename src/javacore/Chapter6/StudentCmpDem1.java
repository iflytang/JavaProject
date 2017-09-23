/*
* implement and demonstrate comparable interface
*
* */

package javacore.Chapter6;

class Student1 implements Comparable<Student1> {

    private String name;
    private int age;
    private float score;

    public Student1(String name, int age, float score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String toString()
    {
        return name+"\t"+age+"\t"+score;
    }

    @Override
    public int compareTo(Student1 other) {

        if(this.score < other.score)
            return 1;
        else if(this.score > other.score)
            return -1;
        else {
            if(this.age < other.age)
                return -1;
            else if(this.age > other.age)
                return 1;
            else
                return 0;
        }
    }
}


public class StudentCmpDem1 {

    public static void main(String[] args) {

        Student1 stu[]={new Student1("Alice",20,90.0f),
                new Student1("lisi",22,90.0f),
                new Student1("wangwu",20,99.0f),
                new Student1("sunliu",22,100.0f)};
        java.util.Arrays.sort(stu);
        for(Student1 s:stu)
        {
            System.out.println(s);
        }
    }
}
