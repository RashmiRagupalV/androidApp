package redflag.capstone.com.redflag.database;

import java.util.Date;

/**
 * Created by npariha on 10/20/14.
 */
public class ScreeningTest {
    private int testId;
    private int recordId;
    private int screenerId;
    private Date dateConducted;
    private double sandBag;
    private String trackingHorizontal;
    private String trackingVertical;
    private long balanceEyesOpenRightFootSec;
    private long balanceEyesOpenLeftFootSec;
    private long balanceEyesCloseRightFootSec;
    private long balanceEyesCloseLeftFootSec;
    private String teamingRound1;
    private String teamingRound2;
    private String skipping;
    private String crawling;
    private int scores;

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getScreenerId() {
        return screenerId;
    }

    public void setScreenerId(int screenerId) {
        this.screenerId = screenerId;
    }

    public Date getDateConducted() {
        return dateConducted;
    }

    public void setDateConducted(Date dateConducted) {
        this.dateConducted = dateConducted;
    }

    public double getSandBag() {
        return sandBag;
    }

    public void setSandBag(double sandBag) {
        this.sandBag = sandBag;
    }

    public String getTrackingHorizontal() {
        return trackingHorizontal;
    }

    public void setTrackingHorizontal(String trackingHorizontal) {
        this.trackingHorizontal = trackingHorizontal;
    }

    public String getTrackingVertical() {
        return trackingVertical;
    }

    public void setTrackingVertical(String trackingVertical) {
        this.trackingVertical = trackingVertical;
    }

    public long getBalanceEyesOpenRightFootSec() {
        return balanceEyesOpenRightFootSec;
    }

    public void setBalanceEyesOpenRightFootSec(long balanceEyesOpenRightFootSec) {
        this.balanceEyesOpenRightFootSec = balanceEyesOpenRightFootSec;
    }

    public long getBalanceEyesOpenLeftFootSec() {
        return balanceEyesOpenLeftFootSec;
    }

    public void setBalanceEyesOpenLeftFootSec(long balanceEyesOpenLeftFootSec) {
        this.balanceEyesOpenLeftFootSec = balanceEyesOpenLeftFootSec;
    }

    public long getBalanceEyesCloseRightFootSec() {
        return balanceEyesCloseRightFootSec;
    }

    public void setBalanceEyesCloseRightFootSec(long balanceEyesCloseRightFootSec) {
        this.balanceEyesCloseRightFootSec = balanceEyesCloseRightFootSec;
    }

    public long getBalanceEyesCloseLeftFootSec() {
        return balanceEyesCloseLeftFootSec;
    }

    public void setBalanceEyesCloseLeftFootSec(long balanceEyesCloseLeftFootSec) {
        this.balanceEyesCloseLeftFootSec = balanceEyesCloseLeftFootSec;
    }

    public String getTeamingRound1() {
        return teamingRound1;
    }

    public void setTeamingRound1(String teamingRound1) {
        this.teamingRound1 = teamingRound1;
    }

    public String getTeamingRound2() {
        return teamingRound2;
    }

    public void setTeamingRound2(String teamingRound2) {
        this.teamingRound2 = teamingRound2;
    }

    public String getSkipping() {
        return skipping;
    }

    public void setSkipping(String skipping) {
        this.skipping = skipping;
    }

    public String getCrawling() {
        return crawling;
    }

    public void setCrawling(String crawling) {
        this.crawling = crawling;
    }
}
