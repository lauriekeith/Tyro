package com.example.projecttyro;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

public class ProfileTest {
    Profile testingProfile = new Profile("test profile", "test@barclays.com",
            "1234", "tester", "knutsford",
            "radbroke", new Date(1999, 9, 18));

    @Test
    public void nameChangeAndGetTest(){
        assertEquals("test profile", testingProfile.getName());
        testingProfile.setName("test2");
        assertEquals("test2", testingProfile.getName());
    }

    @Test
    public void emailChangeAndGetTest(){
        assertEquals("test@barclays.com", testingProfile.getEmail());
        testingProfile.setEmail("test2@barclays.com");
        assertEquals("test2@barclays.com", testingProfile.getEmail());
    }

    @Test
    public void jobChangeAndGetTest(){
        assertEquals("tester", testingProfile.getJobTitle());
        testingProfile.setJobTitle("test2");
        assertEquals("test2", testingProfile.getJobTitle());
    }

    @Test
    public void homeChangeAndGetTest(){
        assertEquals("knutsford", testingProfile.getHomeLocation());
        testingProfile.setHomeLocation("test2");
        assertEquals("test2", testingProfile.getHomeLocation());
    }

    @Test
    public void workChangeAndGetTest(){
        assertEquals("radbroke", testingProfile.getWorkLocation());
        testingProfile.setWorkLocation("test2");
        assertEquals("test2", testingProfile.getWorkLocation());
    }

    @Test
    public void dateChangeAndGetTest(){
        assertEquals(new Date(1999, 9, 18).toString(), testingProfile.getDateOfBirth().toString());
        testingProfile.setDateOfBirth(new Date(2000, 6, 4));
        assertEquals(new Date(2000, 6, 4).toString(), testingProfile.getDateOfBirth().toString());
    }

    @Test
    public void profilePictureChangeAndGetTest(){
        assertFalse(testingProfile.hasUserSetProfilePicture());
        String newPicture = "picture.png";
        testingProfile.setProfilePicture(newPicture);
        assertEquals(newPicture, testingProfile.getProfilePicture());
        assertTrue(testingProfile.hasUserSetProfilePicture());
    }

    @Test
    public void addGroupTest(){
        int originalGroupNumber = testingProfile.getNoOfGroups();
        testingProfile.addedToGroup();
        assertEquals(originalGroupNumber + 1, testingProfile.getNoOfGroups());
    }

    @Test
    public void addConnectionTest(){
        int originalConnectionNumber = testingProfile.getNoOfConnections();
        testingProfile.increaseConnections();
        assertEquals(originalConnectionNumber + 1, testingProfile.getNoOfConnections());
    }

    @Test
    public void testCarSharingStatus(){
        testingProfile.setCarSharing(true);
        assertTrue(testingProfile.isCarSharing());
        testingProfile.setCarSharing(false);
        assertFalse(testingProfile.isCarSharing());
    }

    @Test
    public void testHidingVariables(){
        testingProfile.setCarSharingHidden(false);
        testingProfile.setHomeLocationHidden(false);
        testingProfile.setWorkLocationHidden(false);
        testingProfile.setInterestsHidden(false);
        testingProfile.setJobTitleHidden(false);

        assertFalse((testingProfile.carSharingIsHidden() || testingProfile.homeLocationIsHidden()
                || testingProfile.jobTitleIsHidden() || testingProfile.interestsAreHidden() ||
                testingProfile.workLocationIsHidden()));

        testingProfile.setCarSharingHidden(true);
        testingProfile.setHomeLocationHidden(true);
        testingProfile.setWorkLocationHidden(true);
        testingProfile.setInterestsHidden(true);
        testingProfile.setJobTitleHidden(true);

        assertTrue((testingProfile.carSharingIsHidden() && testingProfile.homeLocationIsHidden()
                && testingProfile.jobTitleIsHidden() && testingProfile.interestsAreHidden() &&
                testingProfile.workLocationIsHidden()));

    }

}
