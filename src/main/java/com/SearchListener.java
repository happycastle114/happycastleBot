package com;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

public class SearchListener extends ListenerAdapter {

    public static MessageChannel channel;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String content = event.getMessage().getContentRaw();
        channel = event.getChannel();
        if(content.startsWith("!검색")){
            SearchCoolDown searchCoolDown = new SearchCoolDown();
            if(!searchCoolDown.isCool()) {
                channel.sendMessage("과부하 방지로 연속명령어는 금지되요! 5초마다 한번씩 가능해요!").queue();
                return;
            }
            if(content.equals("!검색")) {
                channel.sendMessage("사용법 !검색 <검색키워드:띄어쓰기안됨>").queue();
                return;
            }
            String word = content.substring(4);
            Search search = new Search();
            try{
                SearchVar searchVar = search.FindIm(word);
                if(searchVar.title.equals("검색결과 없음")){
                    channel.sendMessage("검색결과가 없습니다!").queue();
                    return;
                }
                if(searchVar.title.equals("검열 대상 텍스트 외에는 검색된 것이 없습니다")){
                    channel.sendMessage(searchVar.title).queue();
                    return;
                }
                URL url = new URL(searchVar.url);
                File files = new File("Temps.jpg");
                FileUtils.copyURLToFile(url, files);
                channel.sendMessage("검색결과 : " + searchVar.title  ).queue();
                channel.sendFile(files).queue();
                files.delete();
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}