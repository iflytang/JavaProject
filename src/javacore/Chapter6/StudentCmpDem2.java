package javacore.Chapter6;

/*
* implement and demonstrate comparator interface
*
* */

import java.util.Comparator;
class Student2 {
    private String name;
    private int age;
    private float score;

    public Student2(String name, int age, float score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public float getScore() {
        return score;
    }
    public void setScore(float score) {
        this.score = score;
    }
    public String toString()
    {
        return name+"\t\t"+age+"\t\t"+score;
    }
}


class StudentComparator implements Comparator<Student2>{

    @Override
    public int compare(Student2 o1, Student2 o2) {

        if(o1.getScore()>o2.getScore())
            return -1;
        else if(o1.getScore()<o2.getScore())
            return 1;
        else{
            if(o1.getAge()>o2.getAge())
                return 1;
            else if(o1.getAge()<o2.getAge())
                return -1;
            else
                return 0;
        }
    }

}
public class StudentCmpDem2 {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Student2 stu[]={new Student2("Alice",20,90.0f),
                new Student2("lisi",22,90.0f),
                new Student2("wangwu",20,99.0f),
                new Student2("sunliu",22,100.0f)};
        java.util.Arrays.sort(stu,new StudentComparator());
        for(Student2 s:stu)
        {
            System.out.println(s);
        }
    }
}