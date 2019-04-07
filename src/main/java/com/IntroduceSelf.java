package com;

import com.iwebpp.crypto.TweetNaclFast;
import net.dv8tion.jda.api.entities.User;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IntroduceSelf {
    public static HashMap<String, String> user_cool = new HashMap<String, String>();
    public static void Update(){
            try{
                //파일 객체 생성
                user_cool.clear();
                File file = new File("database.txt");
                //입력 스트림 생성
                FileReader filereader = new FileReader(file);
                BufferedReader bufReader = new BufferedReader(filereader);
                String line = "";
                int counter = 0;
                while((line = bufReader.readLine()) != null){
                    String[] str = line.split("-");
                    //System.out.println(str.length);
                    user_cool.put(str[0],str[1]);
                }


                filereader.close();
            }catch (FileNotFoundException e) {
                // TODO: handle exception
            }catch(IOException e){
                System.out.println(e);
            }
    }
    public static void Save(){

        File file = new File("database.txt");
        file.delete();
        WriteToFile writeToFile = new WriteToFile();
        int counter = 0;
        user_cool.forEach((k,v)-> {
            writeToFile.write( k + "-" + v , "database");

        });

    }
    public static String FindI(User user){
        if(user_cool.containsKey(user.getAsTag())){
            return user_cool.get(user.getAsTag());
        } else{
            return "설정된 자기소개가 없습니다!";
        }
    }

    public static void SetI(User user, String message){
        if(user_cool.containsKey(user.getAsTag())){
            user_cool.replace(user.getAsTag(), message);
        } else{
            user_cool.put(user.getAsTag(),message);
        }
    }


}
