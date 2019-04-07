package com;

import java.util.Date;

public class SearchCoolDown {
    public static Date date = new Date();
    public static boolean isCool(){
        Date now = new Date();
        if(date.getTime() == now.getTime()){
            date = now;
            return true;
        } else{
            long time = (now.getTime() - date.getTime()) / 1000;
            System.out.println(time);
            if(time >= 5){
                date = now;
                return true;
            } else{
                return false;
            }
        }
    }
}
