package structure.Framework.Utils;

import io.cucumber.datatable.DataTable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utilities {

    public static String ConvertToNumbers(String word)
    {
        return word.replaceAll("[^0-9]","");
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static String randomPhoneNumber() {
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        return "1818" + n;
    }

    public static String getDateTimeNow() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    }

    public static String getDateTimeNow(String format) {
        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
    }

    public static String GenerateRandomNumber(int charLength) {
        return String.valueOf(charLength < 1 ? 0 : new Random()
                .nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
                + (int) Math.pow(10, charLength - 1));
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static String GetTomorrowDate()
    {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = "";
        try
        {
            date = formatter.format(dt);
        }
        catch (Exception e)
        {
        }

        return date;
    }

    public static String AddOrSubstractDays(int days)
    {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, days);
        dt = c.getTime();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = "";
        try
        {
            date = formatter.format(dt);
        }
        catch (Exception e)
        {
        }

        return date;
    }

    public static String FormatPhone(String str)
    {
        return str.replaceAll("[^0-9]+", "").trim();
    }

    public static List<String> GetDatatableValues(DataTable dt, String column)
    {
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        List<String> array = new ArrayList<String>();
        for(int i=0; i<list.size(); i++) {
            String value = list.get(i).get(column);
            array.add(value);
        }
        return array;
    }

    public static String GetRandomValueFromDatatable(DataTable dt, String column)
    {
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        int size = list.size();
        return list.get(new Random().nextInt(list.size())).get(column);
    }

}
