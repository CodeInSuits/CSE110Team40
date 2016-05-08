package com.vapenaysh.jace.myapplication;


import android.telephony.SmsManager;

/**
 * Created by XuanpeiEstherOuyang on 5/7/16.
 */
public class SentSMS {

    public static void sendSms(String loc){
        String notif = "Your partner is nearby ";
        String number = "123";//com.vapenaysh.jace.myapplication.AddPartner.getNumber();
        String message = notif + loc;
              //  com.vapenaysh.jace.myapplication.AddPartner.getName() +
        //        notif + "";

        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number,null,message, null, null);
    }

}
