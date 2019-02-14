package com;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class DiscordBot{
    public static void main(String[] args) throws Exception{
        JDA api = new JDABuilder("NDk4MDcyNzYwNTEwMTg1NDcy.Dwz2iQ.mr1VgyhaufZBRcQ9A4IBj6SGm-U").build();

        api.addEventListener(new MyListener());


    }


}