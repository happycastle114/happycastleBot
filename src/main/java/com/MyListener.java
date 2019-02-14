package com;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MyListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getContentRaw();
        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)

       if (content.equals("!ping"))
        {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
        if (content.startsWith("!say "))
        {
            MessageChannel channel = event.getChannel();
            channel.sendMessage(content.substring(4)).queue();
        }
        if (content.equals("!채널수정")){
            MessageChannel channel = event.getChannel();
            channel.sendMessage("채널 수정함!").queue();
            ChannelCommands cs = new ChannelCommands();
            TextChannel textChannel = event.getTextChannel();
            cs.updateChannel(textChannel);
        }
        if (content.startsWith("!sayd "))
        {
            MessageChannel channel = event.getChannel();
            channel.sendMessage(content.substring(5)).queue();
            DiscordUtiles discordUtiles = new DiscordUtiles();
            discordUtiles.deleteMessage(message, "바다향기가 삭제함");
        }
        if (content.startsWith("!psayd "))
        {
            MessageChannel channel = event.getChannel();
            User Auther = event.getAuthor();
            String Auther_Name = Auther.getAsTag();
            Message ms = channel.sendMessage(content.substring(6) + " by @"+ Auther_Name).complete();
            DiscordUtiles discordUtiles = new DiscordUtiles();
            discordUtiles.deleteMessage(message, "바다향기가 삭제함");
            discordUtiles.waitForEdit(ms);
        }
        if (content.equals("!happy")){

        }

    }
}
