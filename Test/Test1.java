package Test;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/11 23:32
 */
abstract class Animal
{
    abstract void eat();
}

class Cat extends Animal
{
    public void eat()
    {
        System.out.println("吃鱼");
    }
    public void catchMouse()
    {
        System.out.println("抓老鼠");
    }
}

class Dog extends Animal
{
    public void eat()
    {
        System.out.println("吃骨头");
    }
    public void kanJia()
    {
        System.out.println("看家");
    }
}

class duoTaiDemo
{
    public static void main(String[] args)
    {
        function(new Cat());
        function(new Dog());

        Animal a = new Cat();//向上转型
        a.eat();

        Cat c = (Cat)a;//向下转型
        c.catchMouse();


    }

    public static void function(Animal a)
    {
        a.eat();
        //用于子类型有限
        //或判断所属类型进而使用其特有方法
        if(a instanceof Cat)
        {
            Cat c = (Cat)a;
            c.catchMouse();
        }
        else if(a instanceof Dog)
        {
            Dog c = (Dog)a;
            c.kanJia();
        }
    }
}

