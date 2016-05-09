package com.vapenaysh.jace.myapplication;

import java.util.HashMap;

/**
 * Created by XuanpeiEstherOuyang on 5/3/16.
 */
public class PartnershipTracker {

    // hashmap of current user
    HashMap<User, User> tracker;

    public PartnershipTracker() {
        tracker = new HashMap<>();
    }

    // Does the user have a partner?
    public boolean hasPartner(User self) {

        if (tracker.get(self) == null) {
            return false;
        } else {
            return true;
        }
    }

    public String getPartnerName(User self) {

        if(hasPartner(self)) {
            User partner = tracker.get(self);
            return partner.getUserName();
        }
        else {
            return "";
        }
    }

    public String getPartnerEmail(User self) {

        if(hasPartner(self)) {
            User partner = tracker.get(self);
            return partner.getEmailAddress();
        }
        else {
            return "";
        }

    }

    public boolean setPartner(User self, User parnter){

        if (!hasPartner(self) && !hasPartner(parnter)) {
            tracker.put(self, parnter);
            tracker.put(parnter, self);
            return true;
        }
        return false;
    }

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

