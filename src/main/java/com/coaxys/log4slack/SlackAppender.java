package com.coaxys.log4slack;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Calendar;
import java.util.Date;

public class SlackAppender extends AppenderSkeleton {

    private String url = "";

    @Override
    protected void append(LoggingEvent event) {
        if (url != null && url.length() > 0 && event.getLevel() == Level.ERROR) {
            try {
                HttpResponse<String> response = Unirest.post(url)
                        .header("accept", "application/json")
                        .body(LoggingEventSlackifier.slackinify(event))
                        .asString();
                String statusText = response.getStatusText();
                System.out.println(statusText);
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
    }

    public boolean requiresLayout() {
        return false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}