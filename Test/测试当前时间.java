package Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/15 17:34
 */
public class 测试当前时间 {
    public static void main(String[] args) {
        Date date = new Date();//此时date为当前的时间
        System.out.println(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");//设置当前时间的格式，为年-月-日
        System.out.println(dateFormat.format(date));

        SimpleDateFormat dateFormat_min = new SimpleDateFormat("YYYY-MM-dd");//设置当前时间的格式，为年-月-日 时-分-秒
        System.out.println(dateFormat_min.format(date));

        //-----------------------获取一个月之前的时间--------
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        //ca.setTime(new Date()); // 设置时间为当前时间
        //ca.set(2011, 11, 17);// 月份是从0开始的，所以11表示12月
        //ca.add(Calendar.YEAR, -1); // 年份减1
        ca.add(Calendar.MONTH, -1);// 月份减1
        //ca.add(Calendar.DATE, -1);// 日期减1
        Date resultDate = ca.getTime(); // 结果
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(resultDate));
    }
}
