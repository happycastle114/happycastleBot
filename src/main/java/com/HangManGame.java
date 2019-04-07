package com;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.HashMap;
import java.util.Random;

public class HangManGame {
    public static String mesName = new String("");
    public static String[] keyword = new String[]{};
    public static String[] corrent = new String[]{};
    public static boolean InGame = false;
    public static int trycount;
    public static ArrayList<String> already = new ArrayList<>();
    public static ArrayList<String> words = new ArrayList<>();
    public static HashMap<String, String> keys = new HashMap<String, String>();
    public static String realword = new String();
    public static HashMap<String,String> hint = new HashMap<String, String>();
    public static String hint2 = new String();
    public static void SetWords(MessageChannel messageChannel){
        try {
            keys.clear();
            words.clear();
            hint.clear();
            File file = new File("wordbases.txt");
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            String line = "";
            int counter = 0;
            while ((line = bufReader.readLine()) != null) {
                String[] str = line.split("-");
                //System.out.println(str.length);
                words.add(str[0]);
                keys.put(str[0], str[1]);
                hint.put(str[0], str[2]);
            }
            messageChannel.sendMessage("단어 업데이트 완료!").queue();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    //public static String[] words = {"ㅎㅏㄴㄱㅡㄹ", "ㅋㅗㄲㅣㄹㅣ", "ㅇㅗㅁㅏㄴㅇㅜㅓㄴ","ㄹㅗㄹㅣㅋㅗㄴ","ㅅㅣㅂㅏㅅㅡ","ㅇㅑㄷㅗㅇ","ㄷㅡㄹㅐㄱㅗㄴ","ㅇㅛㅇ","ㅅㅏㄱㅗㅏ","ㅌㅏㄴㅎㅐㄱ","ㅅㅣㅂㅏㄹ","ㅇㅠㄹㅗㅍㅏ","ㅎㅏㅊㅡㅇㅗㅂㅡㅇㅏㅇㅣㅇㅓㄴ","ㅍㅏㄹㅣ","ㄱㅗㅇㅅㅏㄴㄷㅏㅇ","ㅇㅏㅇㅣㅇㅠ","ㅅㅜㅂㅏㄱ","ㄴㅏㄱㅈㅣ"};
    public static String key = new String();
    public static String[] mis = {"---------------\n l                    l\n l\n l\n/\\","---------------\n l                    l\n l                 :astonished:\n l\n/\\","---------------\n l                    l\n l                 :astonished:\n l                   ㅣ\n/\\"
            ,"l                    l\n l                 :astonished:\n l                /ㅣ\n/\\"
            ,"---------------\n l                    l\n l                 :astonished:\n l                /ㅣ\\ \n/\\"
            ,"---------------\n l                    l\n l                 :astonished:\n l                /ㅣ\\ \n/\\               /"
            ,"---------------\n l                    l\n l                 :astonished:\n l                /ㅣ\\ \n/\\               / \\"
            ,"---------------\n l                    l\n l                 :tired_face:\n l                /ㅣ\\ \n/\\               / \\ "
            ,"행맨이 죽었습니다!","9"};
    public static int count;
    public static int correntcounter;
    public static void GameStart(MessageChannel messageChannel){
        InGame = true;
        Random random = new Random();
        key = words.get(random.nextInt(words.size() - 1) + 1);
        System.out.println(key);
        realword = keys.get(key);
        System.out.println(realword);
        keyword = new String[]{" "};
        already.clear();
        hint2 = hint.get(key);
        trycount = 0;
        keyword = key.split("");
        corrent = new String[keyword.length];
        count = 0;
        mesName= messageChannel.getName();
        correntcounter = 0;
        for(int i = 0; i < keyword.length ; i++){
            corrent[i] = "O";
        }
        messageChannel.sendMessage("행맨 게임이 시작되었습니다!").queue();
        PrintCounter(messageChannel);

    }
    public static void isEndGame(MessageChannel ms){
        if(count == 8){
            InGame = false;
            ms.sendMessage("행맨이 죽었습니다! \n 게임을 종료합니다 \n 정답은 " + realword + "  였습니다!").queue();
        }
        if(correntcounter == keyword.length){
            InGame = false;
            ms.sendMessage("모든 글자를 맞추셨습니다! \n 정답은 " + realword + " 였습니다! 게임을 종료합니다!").queue();
        }
    }
    public static void isWords(Message message){
        String str = message.getContentRaw();
        if(str.equals(realword)){
            InGame = false;
            message.getChannel().sendMessage("정답을 맞추셨습니다! \n 정답은 " + realword + " 였습니다! \n 승자는 " + message.getAuthor().getAsMention() + "입니다! 축하합니다! \n Deveoped by happycastle, 오만원").queue();
        }
    }
    public static void PrintCounter(MessageChannel ms){
        ms.sendMessage(mis[count] + "\n" + "```" + Arrays.toString(corrent) + "\n" + already.toString() + "```").queue();
        if(count == 6){
            ms.sendMessage("힌트 : " + hint2).queue();

        }
    }
    public static boolean isCorrent(MessageChannel ms, String str){
        if(Arrays.asList(keyword).contains(str) && !Arrays.asList(corrent).contains(str) && !already.contains(str)){
            for(int i = 0; i < keyword.length;i++){
                if(keyword[i].equals(str)){
                    corrent[i] = str;
                    correntcounter++;
                }
            }
            ms.sendMessage("해당하는 단어가 있습니다!").queue();
            already.add(str);
            PrintCounter(ms);
            isEndGame(ms);
            return true;
        } else if(already.contains(str)){
            ms.sendMessage("이미 시도한 단어입니다!").queue();
            PrintCounter(ms);
            return true;
        } else{
            ms.sendMessage("틀리셨습니다!").queue();
            count++;
            already.add(str);
            PrintCounter(ms);
            isEndGame(ms);

            return false;
        }
    }

}
