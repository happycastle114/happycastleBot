package com;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.managers.GuildController;

import java.security.Timestamp;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Gambang {
    public static HashMap<Member, Date> user_cool = new HashMap<Member, Date>();
    public static HashMap<Member, Integer> user_PrisonTime = new HashMap<Member, Integer>();
    public static CoolDownType CheckCoolDown(Member user, Guild guild){
        Date date = new Date();
        CoolDownType cool = new CoolDownType();
            long longtime = (date.getTime() - user_cool.get(user).getTime()) / 1000 / 60;
            System.out.println(longtime);
            if((int) longtime > user_PrisonTime.get(user)){
                user_cool.remove(user, date);
                user_PrisonTime.remove(user);
                GuildController guildController = guild.getController();
                Role role = guild.getRoleById("546225849762971650");
                Role role2 = guild.getRoleById("478200622840152064");
                guildController.addSingleRoleToMember(user, role2).queue();
                guildController.removeSingleRoleFromMember(user, role).queue();
                cool.result = true;
                WriteToFile writeToFile = new WriteToFile();
                writeToFile.write(user.getNickname() + " 님 만기출소 at " + date.toString(),"감방회고록" );
                return cool;
            } else{
                cool.result = false;
                cool.longtime = user_PrisonTime.get(user) - longtime;
                return cool;
            }

    }
    public static boolean GoJail(Member member, int time, Guild guild, String reason){
        if(!user_PrisonTime.containsKey(member)){
            Date date = new Date();

            GuildController guildController = guild.getController();
            Role role2 = guild.getRoleById("478200622840152064");
            Role role = guild.getRoleById("546225849762971650");
            guildController.removeSingleRoleFromMember(member, role2).queue();
            System.out.println(role.getName());
            guildController.addSingleRoleToMember(member,role).queue();
            WriteToFile writeToFile = new WriteToFile();
            writeToFile.write(member.getNickname() + " 감옥입소 " + "사유 " + reason + " at " + date.toString(), "감방회고록");
            user_cool.put(member, date);
            user_PrisonTime.put(member, time);
            return true;
        } else{
            return false;
        }
    }
    public static boolean InJail(Member member, Guild guild){
        return (boolean) user_cool.containsKey(member);
    }
    public static void bosuk(Member member, Guild guild){
        user_cool.remove(member);
        Date date = new Date();
        WriteToFile writeToFile = new WriteToFile();
        user_PrisonTime.remove(member);
        GuildController guildController = guild.getController();
        Role role = guild.getRoleById("546225849762971650");
        Role role2 = guild.getRoleById("478200622840152064");
        guildController.addSingleRoleToMember(member, role2).queue();
        guildController.removeSingleRoleFromMember(member, role).queue();
        writeToFile.write(member.getNickname() + " 님 특별보석으로 퇴소 at " + date.toString(),"감방회고록");
    }

}

