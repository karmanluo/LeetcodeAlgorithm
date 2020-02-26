package Java知识点.静态内部类与非静态内部类;

public class Student {

    private String name;
    private int age;

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

    public class Child{
        private String name1;
        private int age1;

        public String getName1() {
            return name1;
        }
        public void setName1(String name1) {
            this.name1 = name1;
        }
        public int getAge1() {
            System.out.println(age);
            return age1;
        }
        public void setAge1(int age1) {
            this.age1 = age1;
        }


    }

    public static void main(String[] args) {
        Student s = new Student();
        s.setAge(12);
        s.setName("yao");
        Child c = s.new Child();
        System.out.println(c.getAge1());
    }
}