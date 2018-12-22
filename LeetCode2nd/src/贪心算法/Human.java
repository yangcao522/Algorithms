package 贪心算法;


public class Human {
    public int age; // The Human’s age (an integer).
    public String name; // The Human’s name.
    public int calculate(int a, int b){
        return a + b;
    }
    public void introduce() { // This is a _method_definition_.

       System.out.println("I’m " + name + " and I’m " + age + " years old.");
    }

    public static void main(String[] args){
        Human xiaomeng = new Human();
        System.out.println(xiaomeng.calculate(3, 5));
        xiaomeng.introduce();
    }
}
