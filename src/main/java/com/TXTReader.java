package com;

import net.dv8tion.jda.api.entities.MessageChannel;

import java.io.*;
import java.util.ArrayList;

public class TXTReader {
    public ArrayList<String> Reader(String filename){
        try {
            ArrayList<String> temp = new ArrayList<>();
            File file = new File(filename + ".txt");
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            String line = "";
            int counter = 0;
            while ((line = bufReader.readLine()) != null) {
                //System.out.println(str.length);
                temp.add(line);
            }
            return temp;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String ArraytoString(ArrayList<String> list){
        String re = "";
        for(int i=0;i<list.size();i++){
            re += "\n" + list.get(i);
        }
        return re;
    }
}
