package com.cabinvoice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CabInVoiceTest {
    InVoiceGenerator inVoiceGenerator = null;
    @Before
    public void setUp() throws Exception {
        inVoiceGenerator = new InVoiceGenerator();
    }
    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = inVoiceGenerator.calculateFare(distance,time);
        Assert.assertEquals(25,fare,0.0);
    }
    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare () {
        double distance = 0.1;
        int time = 1;
        double fare = inVoiceGenerator.calculateFare(distance,time);
        Assert.assertEquals(5,fare,0.0);
    }
    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(2.0, 5),
                        new Ride(0.1, 1)
                        };

        InVoiceSummary summary = inVoiceGenerator.calculateFare(rides);
      InVoiceSummary expectedInVoiceSummary = new InVoiceSummary(2, 30.0);
      Assert.assertEquals(expectedInVoiceSummary,summary);
    }
    @Test
    public void givenUserId_ShouldReturnInVoiceSummary() {
        String userID = "v@j,com";
        Ride[] rides = {new Ride(2.0,5),
                        new Ride(0.1,1)};
        inVoiceGenerator.addRides(userID,rides);
        InVoiceSummary summary = inVoiceGenerator.calculateFare(rides,"normal");
        InVoiceSummary inVoiceSummary = inVoiceGenerator.getInVoiceSummary(userID);
        Assert.assertEquals(inVoiceSummary,summary);
    }
}
