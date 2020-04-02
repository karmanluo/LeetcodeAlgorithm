package Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/15 17:34
 */
public class 测试当前时间 {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();//此时date为当前的时间
        System.out.println(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");//设置当前时间的格式，为年-月-日
        String today = dateFormat.format(date.getTime());
        System.out.println("当天日期"+today);
        //----------当天凌晨毫秒-----------
        //3、当天凌晨(毫秒)
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long daytime3 = c.getTimeInMillis();
        System.out.println("3、当天凌晨(毫秒)" + daytime3);


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
