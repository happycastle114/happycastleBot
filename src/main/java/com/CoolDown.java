package com;

import net.dv8tion.jda.api.entities.User;

import java.security.Timestamp;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CoolDown {
    public static HashMap<User, Date> user_cool = new HashMap<User, Date>();
    public static CoolDownType CheckCoolDown(User user){
        Date date = new Date();
        CoolDownType cool = new CoolDownType();

        if(!user_cool.containsKey(user)){
            user_cool.put(user, date);
            cool.result = true;
            return cool;
        } else{
            long longtime = (date.getTime() - user_cool.get(user).getTime()) / 1000 / 60;
            System.out.println(longtime);
            if((int) longtime >= 5){
                user_cool.replace(user, date);
                cool.result = true;
                return cool;
            } else{
                cool.result = false;
                cool.longtime = longtime;
                return cool;
            }
        }
    }

}
