package com;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class DiscordUtiles {
    public void deleteMessage(Message message, String reason){
        message.delete().reason(reason).queue();
    }
    public void ban(Guild guild, User user, String reason){
        guild.getController().ban(user, 7, reason).queue();
    }
    public void sendAndLog(MessageChannel channel, String message){
        channel.sendMessage(message).queue(new Consumer<Message>(){
            @Override
            public void accept(Message t)
            {
                System.out.printf("Sent Message %s\n", t);
            }
        });
    }
    public void sendPrivateMessage(User user, String content)
    {
        // openPrivateChannel provides a RestAction<PrivateChannel>
        // which means it supplies you with the resulting channel
        /*user.openPrivateChannel().queue((channel) ->
        {
            // value is a parameter for the `accept(T channel)` method of our callback.
            // here we implement the body of that method, which will be called later by JDA automatically.
            channel.sendMessage(content).queue();
            // here we access the enclosing scope variable -content-
            // which was provided to sendPrivateMessage(User, String) as a parameter
        });*/
    }
    public Message waitForEdit(Message message, String str, int time)
    {
        return message.editMessage(str).completeAfter(time, TimeUnit.SECONDS);
    }
    public int calcdate(int _year, int _month, int _day){
        try{
            TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
            Calendar today = Calendar.getInstance(tz);
            Calendar dday = Calendar.getInstance(tz);
            dday.set(_year, _month-1, _day);
            long cnt_dday = dday.getTimeInMillis() / 86400000;
            long cnt_today = today.getTimeInMillis() / 86400000;
            long sub = cnt_dday - cnt_today;
            return (int) sub + 1;


        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}

