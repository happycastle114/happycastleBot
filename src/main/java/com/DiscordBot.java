package com;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class DiscordBot{
    public static void main(String[] args) throws Exception{
        JDA api = new JDABuilder("").build();
        api.getPresence().setActivity(Activity.playing("홉봇  ||  !도움"));

        api.addEventListener(new MyListener());
        api.addEventListener(new HangMan());
        api.addEventListener(new SearchListener());


    }


}
