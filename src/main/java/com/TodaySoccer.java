package com;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.mashape.unirest.http.HttpResponse;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.json.JSONArray;
import org.json.JSONObject;

public class TodaySoccer {
    public static String strToday;
    public static JSONArray TodayFixture = new JSONArray();
    public static JSONObject Temp = new JSONObject();
    public static JSONObject jsonObject = new JSONObject();
    public static JSONArray jsonArray = new JSONArray();

    public static void setToday(){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();

        strToday = sdf1.format(c1.getTime());
    }

    public static void UpdateTodaySoccer() throws Exception {
        HttpResponse<JsonNode> response = Unirest.get("https://api-football-v1.p.rapidapi.com/fixtures/date/" + strToday).header("X-RapidAPI-Key", "eaebed5d6emsh0b42de5d003fb91p16bd36jsn01c2a4cc8942").asJson();

        jsonObject = response.getBody().getObject();
        jsonObject = (JSONObject) jsonObject.get("api");
        jsonObject = (JSONObject) jsonObject.getJSONObject("fixtures");
        TodayFixture = jsonObject.toJSONArray(jsonObject.names());
    }

    public static void PrintTodayFixture(MessageChannel mc, String league_id){
        int i=0;
        String message = "";
        for(i=0;i<TodayFixture.length();i++){
            Temp = (JSONObject) TodayFixture.get(i);

            if(Temp.get("league_id").equals(league_id)){
                message += (String) Temp.get("homeTeam") + " vs " + Temp.get("awayTeam") + " : " + Temp.get("event_date") + " " + Temp.get("final_score") + "\n";
            }
        }
        if(message == ""){
            mc.sendMessage("경기가 없습니다.\n").queue();
        } else{
            mc.sendMessage(message).queue();
        }
    }
}
