package Test;

import com.sun.jmx.snmp.Timestamp;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/8 23:04
 */
public class test {
    public static void main(String[] args) {
        Long time = System.currentTimeMillis();  //当前时间的时间戳
        long zero = time / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        System.out.println(new Timestamp(zero));
        System.out.println("今天零点时间戳" + zero);

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) - 1, 0, 0, 0);
        long tt = calendar.getTime().getTime();
        System.out.println("昨天零点的时间戳"+tt);

    }

}
