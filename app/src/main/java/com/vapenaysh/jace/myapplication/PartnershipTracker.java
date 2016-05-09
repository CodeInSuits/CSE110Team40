package com.vapenaysh.jace.myapplication;

import java.util.HashMap;

/**
 * Created by XuanpeiEstherOuyang on 5/3/16.
 *
 * Class for tracking partnership
 */
public class PartnershipTracker {

    // hashmap of current user
    HashMap<User, User> tracker;

    // constructor for partnershipTracker
    public PartnershipTracker() {
        tracker = new HashMap<>();
    }

    // Check does the user have a partner
    public boolean hasPartner(User self) {

        if (tracker.get(self) == null) {
            return false;
        } else {
            return true;
        }
    }

    // getter method for getting parnter name
    public String getPartnerName(User self) {

        if(hasPartner(self)) {
            User partner = tracker.get(self);
            return partner.getUserName();
        }
        else {
            return "";
        }
    }

    // getter method for getting parnter email
    public String getPartnerEmail(User self) {

        if(hasPartner(self)) {
            User partner = tracker.get(self);
            return partner.getEmailAddress();
        }
        else {
            return "";
        }

    }

    // setter method
    public boolean setPartner(User self, User parnter){

        if (!hasPartner(self) && !hasPartner(parnter)) {
            tracker.put(self, parnter);
            tracker.put(parnter, self);
            return true;
        }
        return false;
    }

    // method for removing parnter
    public boolean removePartner(User self){

        if(hasPartner(self)){
            User partner = tracker.get(self);
            tracker.put(partner, null);
            tracker.put(self, null);
            return true;
        }
        return false;
    }
}

