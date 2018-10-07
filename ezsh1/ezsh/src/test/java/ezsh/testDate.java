package ezsh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class testDate {
   public static void main(String[] args) {
	   SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM");
       try {
           Date date = sf.parse("2015/02");
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(date);
           System.out.println(calendar.get(Calendar.YEAR));
           System.out.println(calendar.get(Calendar.MONTH) + 1);
//           System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

       } catch (ParseException e) {
           e.printStackTrace();
       }
}
}
