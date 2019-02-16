package com;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.Random;

public class Russian {
    public static int count;
    public static void startGame(MessageChannel messageChannel){
        Message message = messageChannel.sendMessage(":anguished: :gun: ").complete();
        Random random = new Random();
        DiscordUtiles discordUtiles = new DiscordUtiles();
        int i = random.nextInt(5);
        System.out.println(i);
        System.out.println(count);
        if(i == 3 || count == 5){
            count = 0;
            discordUtiles.waitForEdit(message,":skull: :gun: :boom: ",4);
        } else{
            count++;
            discordUtiles.waitForEdit(message,":slight_smile:  :gun: :ok_hand: ",4);
        }
    }
}
