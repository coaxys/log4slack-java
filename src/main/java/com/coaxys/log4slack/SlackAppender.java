package com.coaxys.log4slack;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import java.util.concurrent.Future;

public class SlackAppender extends AppenderSkeleton {

    private String url = "";

    @Override
    protected void append(LoggingEvent event) {
        if (url != null && url.length() > 0 && event.getLevel() == Level.ERROR) {
            Future<HttpResponse<String>> future = Unirest.post(url)
                    .header("accept", "application/json")
                    .body(LoggingEventSlackifier.slackinify(event))
                    .asStringAsync(new Callback<String>() {

                        public void failed(UnirestException e) {
                            System.out.println("The slack request has failed");
                        }

                        public void completed(HttpResponse<String> response) {
                            System.out.println(response.getStatusText());
                        }

                        public void cancelled() {
                            System.out.println("The slack request has been cancelled");
                        }

                    });
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