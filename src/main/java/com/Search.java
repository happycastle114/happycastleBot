package com;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Random;

public class Search {

    public SearchVar FindIm(String league_code) throws Exception{

        HttpResponse<JsonNode> response;
        response = Unirest.get("https://openapi.naver.com/v1/search/image?query=" + URLEncoder.encode(league_code,"UTF-8") + "&display=5")
                .header("X-Naver-Client-Id", "")
                .header("X-Naver-Client-Secret", "")
                .asJson();
        SearchVar searchVar = new SearchVar();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject = (JSONObject) response.getBody().getObject();
       // System.out.println(jsonObject.toString());
        //jsonObject = (JSONObject) jsonObject.getJSONObject();
        //jsonObject = (JSONObject) jsonArray.getJSONObject(1);
        jsonArray = (JSONArray) jsonObject.getJSONArray("items");
        if(jsonArray.length() == 0){
            searchVar.title = "검색결과 없음";
            searchVar.url = "검색결과 없음";
            return searchVar;
        }
        int counter = 0;
        Random random = new Random();
        JSONObject temp = (JSONObject) jsonArray.getJSONObject(random.nextInt(jsonArray.length() - 1));
        while((temp.get("link").toString().contains("ilbe") || temp.get("link").toString().contains("womad")) && (counter <= 5)){
            System.out.println(temp.get("link").toString());
            temp = (JSONObject) jsonArray.getJSONObject(random.nextInt(jsonArray.length() - 1));
            counter++;
        }
        if(counter >= 5){
            searchVar.title = "검열 대상 텍스트 외에는 검색된 것이 없습니다";
            searchVar.url = "검열 대상 텍스트 외에는 검색된 것이 없습니다";
            return searchVar;
        }
        searchVar.url = (String) temp.get("link");
        searchVar.title = (String) temp.get("title");
        return searchVar;
    }
}
