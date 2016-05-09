package com.vapenaysh.jace.myapplication;


import android.telephony.SmsManager;

/**
 * Created by XuanpeiEstherOuyang on 5/7/16.
 */
public class SentSMS{


    public static String number;

    //Sends a SMS consisting of a favorite location
    public String sendSms(FavoriteLocation loc){


        //SharedPreferences share = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        //number = share.getString("phone_number", "N/A");

        number = com.vapenaysh.jace.myapplication.PartnerSettings.getNumber();
        String notif = "Your partner is nearby ";
        String message = notif + loc.getName();
        //  com.vapenaysh.jace.myapplication.AddPartner.getName() +
        //        notif + "";

        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number,null,message, null, null);
        return message;
    }
}
