package com.oleksandr.api;

import twitter4j.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Timeline {
    //private Timeline timeline = new Timeline();

    public List<Status> GetUserTimeline() {
        List<Status> statuses = new ArrayList<Status>();
        try {
            // gets Twitter instance with default credentials
            Twitter twitter = new TwitterFactory().getInstance();
            User user = twitter.verifyCredentials();
            statuses = twitter.getHomeTimeline();
            System.out.println("Returning @" + user.getScreenName() + "'s home timeline.");
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
        }
        return statuses;
    }

    public List<Status> GetStatusFromUserTimeline(String statusMsg){
        return this.GetUserTimeline().stream().filter(s -> s.getText().contains(statusMsg)).collect(Collectors.toList());
    }
}
