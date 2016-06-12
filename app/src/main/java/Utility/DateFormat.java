package Utility;

import java.text.SimpleDateFormat;

/**
 * Created by aticus on 11-06-2016.
 */
public class DateFormat
{
    public static String ConvertDate(String s)
    {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat toFormat = new SimpleDateFormat("dd-MMM-yyyy");

        try {
            date = fromFormat.parse(s);
        }
        catch (Exception e)
        {}
        return (toFormat.format(date));
    }
}
