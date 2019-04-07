package com;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HangMan extends ListenerAdapter {

    public static MessageChannel hangman;
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        MessageChannel messageChannel = event.getChannel();
        String content = message.getContentRaw();
        HangManGame hangManGame = new HangManGame();
        if(content.equals("!단어업데이트")){
            hangManGame.SetWords(messageChannel);
        }
        if(content.equals("!행맨시작")){
            if(!hangManGame.InGame){
                hangManGame.GameStart(messageChannel);
            } else{
                messageChannel.sendMessage("게임이 이미 시작되었습니다!").queue();
            }
        }
        if(content.equals("!행맨종료") && hangManGame.InGame){
            messageChannel.sendMessage("게임을 강제 종료합니다!").queue();
            hangManGame.InGame = false;
        }
        if(hangManGame.InGame && (messageChannel.getName().equals(hangManGame.mesName))){
            System.out.println("행맨게임 시작됨");
            String[] words = content.split("");
            if(words.length == 1){
                boolean result = hangManGame.isCorrent(messageChannel, words[0]);
            } else if(content.length() == hangManGame.realword.length()){
                hangManGame.isWords(message);
            }
        }

    }
}
