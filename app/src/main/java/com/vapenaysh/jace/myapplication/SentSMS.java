package com.vapenaysh.jace.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.SmsManager;

/**
 * Created by XuanpeiEstherOuyang on 5/7/16.
 */
public class SentSMS {


    public static String number;

    public static void sendSms(String loc){


       // SharedPreferences share = getSharedPreferences("MyData", Context.MODE_PRIVATE);
       // number = share.getString("phone_number", "N/A");

        number = "5556";
        String notif = "Your partner is nearby ";
        String message = notif + loc;
              //  com.vapenaysh.jace.myapplication.AddPartner.getName() +
        //        notif + "";

        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number,null,message, null, null);
    }

}
