package com.oleksandr.api;

import org.junit.*;
import twitter4j.*;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HomeTimelineTests {

    private String statusMsg = "Twitter test message HomeTimeline Tests";
    private Timeline timeline;
    private Date createdDate;
    private Tweets tweets;

    @Before
    public void prepare() throws TwitterException {
        timeline = new Timeline();
        tweets = new Tweets();
        List<Status> status = timeline.GetStatusFromUserTimeline(statusMsg);
        if (status.size() > 0) {
            tweets.DestroyStatus(status.get(0).getId());
        }
        createdDate = new Date();
        tweets.UpdateStatus(statusMsg);
    }

    @Test
    public void updateStatus_ShouldHaveCorrectDate() {
        List<Status> status = timeline.GetStatusFromUserTimeline(statusMsg);
        assertEquals(createdDate.getTime(), status.get(0).getCreatedAt().getTime(), 13);
    }

    @Test
    public void statusMessage_ShouldHaveProperText() {
        assertEquals(statusMsg, timeline.GetStatusFromUserTimeline(statusMsg).get(0).getText());
    }

    @Test
    public void retweetStatus_ShouldIncreaseRetweetCount() {
        Status status = timeline.GetStatusFromUserTimeline(statusMsg).get(0);
        int retweetCount = status.getRetweetCount();
        tweets.RetweetStatus(status.getId());
        assertEquals(retweetCount + 1, timeline.GetStatusFromUserTimeline(statusMsg).get(0).getRetweetCount());
    }
}
