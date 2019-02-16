package com;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class DiscordBot{
    public static void main(String[] args) throws Exception{
        JDA api = new JDABuilder("NTQzMzc3MjU3ODAxMDU2MjU2.D0k-tg.sL2_igEwsSMtIX7H48ZQPP1IYtg").build();
        api.getPresence().setActivity(Activity.playing("ㅇㅁㅇ봇 || !도움"));

        api.addEventListener(new MyListener());
        api.addEventListener(new HangMan());


    }


}