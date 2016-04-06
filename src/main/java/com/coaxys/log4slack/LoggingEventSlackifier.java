package com.coaxys.log4slack;

import org.apache.log4j.spi.LoggingEvent;

import java.util.Date;

public class LoggingEventSlackifier {

    public static final String LOCATION_FORMAT = "{\"text\":\"%s\",\n" +
            "\t\"attachments\": [\n" +
            "\t\t{\"title\":\"[%s] Log error\",\n" +
            "\t\t\"text\":\"%s\n" +
            "\t\tmessage : %s\"}]}";
    public static final String THROWABLE_FORMAT = "{\"text\":\"%s\",\n" +
            "\t\"attachments\": [\n" +
            "\t\t{\"title\":\"[%s] Exception catch\",\n" +
            "\t\t\"text\":\"%s\n" +
            "\t\tmessage : %s\n" +
            "\t\tall : %s\"}]}";
    public static final String OTHER_FORMAT = "{\"text\":\"%s\",\n" +
            "\t\"attachments\": [\n" +
            "\t\t{\"title\":\"%s\",\n" +
            "\t\t\"text\":\"%s\n" +
            "\t\tmessage : %s\"}]}";


    public static String slackinify(LoggingEvent event){

        String result = "";

        if(event.getLocationInformation() != null){
            result = String.format(LOCATION_FORMAT,new Date(event.getTimeStamp()),event.getLevel(),event.getLocationInformation().fullInfo,event.getMessage());
        }
        else if(event.getThrowableInformation() != null){
            result = String.format(THROWABLE_FORMAT, new Date(event.getTimeStamp()),event.getLevel(),event.getThrowableInformation().getThrowable().getLocalizedMessage(), event.getThrowableInformation().getThrowable().getMessage(), event.getThrowableInformation().getThrowable().toString());
        }
        else{
            result = String.format(OTHER_FORMAT, new Date(event.getTimeStamp()), event.getLevel(), event.getLoggerName(), event.getMessage());
        }

        return result;
    }
}
