package com.oleksandr.api;

import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TweetsTests {
    private String statusMsg = "Twitter test message TweetsTests";
    private Timeline timeline;
    private Tweets tweets;

    @Before
    public void prepare() throws TwitterException {
        timeline = new Timeline();
        tweets = new Tweets();
        List<Status> status = timeline.GetStatusFromUserTimeline(statusMsg);
        if (status.size() > 0) {
            tweets.DestroyStatus(status.get(0).getId());
        }
        tweets.UpdateStatus(statusMsg);
    }

    @Test
    public void destroyStatus_ShouldRemoveStatus() {
        List<Status> status = timeline.GetStatusFromUserTimeline(statusMsg);
        tweets.DestroyStatus(status.get(0).getId());
        assertTrue(timeline.GetStatusFromUserTimeline(statusMsg).size() == 0);
    }

    @Test(expected = TwitterException.class)
    public void updateStatusWithSameMsg_ShouldThrowException() throws TwitterException {
        tweets.UpdateStatus(statusMsg);
        tweets.UpdateStatus(statusMsg);
    }
}