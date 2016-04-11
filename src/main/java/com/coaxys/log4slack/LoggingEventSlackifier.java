package com.coaxys.log4slack;

import org.apache.log4j.spi.LoggingEvent;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LoggingEventSlackifier {

    public static final String LOCATION_FORMAT = "{\"text\":\"%s\",\n" +
            "\t\"attachments\": [\n" +
            "\t\t{\"title\":\"[%s] Log error\",\n" +
            "\t\t\"text\":\"%s\n" +
            "USER :%s\n" +
            "TRACKER:%s\n" +
            "MESSAGE : %s\"}]}";
    public static final String THROWABLE_FORMAT = "{\"text\":\"%s\",\n" +
            "\t\"attachments\": [\n" +
            "\t\t{\"title\":\"[%s] Exception catch\",\n" +
            "\t\t\"text\":\"%s\n" +
            "USER :%s\n" +
            "TRACKER:%s\n" +
            "MESSAGE : %s\n" +
            "all : %s\"}]}";
    public static final String OTHER_FORMAT = "{\"text\":\"%s\",\n" +
            "\t\"attachments\": [\n" +
            "\t\t{\"title\":\"%s\",\n" +
            "\t\t\"text\":\"%s\n" +
            "USER :%s\n" +
            "TRACKER:%s\n" +
            "MESSAGE : %s\"}]}";


    public static String slackinify(LoggingEvent event){

        String result = "";

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(event.getTimeStamp());

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy à HH:mm:ss", Locale.FRANCE);

        if(event.getLocationInformation() != null){
            result = String.format(LOCATION_FORMAT,sdf.format(calendar.getTime()),event.getLevel(),event.getLogger().getName(),event.getMDC("username"),event.getMDC("tracker"),event.getMessage());
        }
        else if(event.getThrowableInformation() != null){
            result = String.format(THROWABLE_FORMAT, sdf.format(calendar.getTime()),event.getLevel(),event.getThrowableInformation().getThrowable().getLocalizedMessage(), event.getThrowableInformation().getThrowable().getMessage(),event.getMDC("username"),event.getMDC("tracker"), event.getThrowableInformation().getThrowable().toString());
        }
        else{
            result = String.format(OTHER_FORMAT, sdf.format(calendar.getTime()), event.getLevel(), event.getFQNOfLoggerClass(),event.getMDC("username"),event.getMDC("tracker"), event.getMessage());
        }

        return result;
    }
}
