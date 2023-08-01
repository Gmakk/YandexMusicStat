package edu.API;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Класс для работы с датами
 */
public class Dates {
    /**
     * Преобразует дату, записанную в String, в объект Date
     * @param timestamp исходная строка
     * @return созданный на основе строки объект Date
     */
    public static Date timestampToDate(String timestamp){
        //example "2023-07-24T13:50:38+00:00"
        Date result = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss",new Locale("ru"));
        try {
            String formattedTimestamp = timestamp.substring(0,10)+timestamp.substring(11,19);
            result = formatter.parse(formattedTimestamp);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
