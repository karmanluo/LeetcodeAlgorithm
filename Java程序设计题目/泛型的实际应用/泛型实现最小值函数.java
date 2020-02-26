package Java程序设计题目.泛型的实际应用;

//自己设计一个泛型的获取数组最小值的函数.并且这个方法只能接受Number的子类并且实现了Comparable接口。
public class 泛型实现最小值函数 {
    private static <T extends Number & Comparable<? super T>>T min(T[] values){
        if (values.length == 0 || values == null) return null;
        T min = values[0];
        for (int i = 1; i < values.length; i++) {
            if (min.compareTo(values[i]) > 0) min = values[i];
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println("minInteger: " + min(new Integer[]{4,1,2,3}));
        System.out.println("minDouble: " + min(new Double[]{4.2, 1.2, 2.0, 3.4}));
//        System.out.println("typeError: "+ min(new String[]{"1", "2", "3"}));
    }
}
