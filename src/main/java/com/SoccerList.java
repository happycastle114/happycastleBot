package com;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import net.dv8tion.jda.api.entities.MessageChannel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.util.ArrayList;

public class SoccerList {



    public static JSONArray[] eplrank = new JSONArray[100];
    public static ArrayList<String> banword = new ArrayList<>();


    public static void RANK_Update(int league_code) throws Exception{
        HttpResponse<JsonNode> response;
        response = Unirest.get("https://api-football-v1.p.rapidapi.com/leagueTable/" + league_code).header("X-RapidAPI-Key", "eaebed5d6emsh0b42de5d003fb91p16bd36jsn01c2a4cc8942").asJson();

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject = (JSONObject) response.getBody().getObject();
        jsonObject = (JSONObject) jsonObject.getJSONObject("api");
        //jsonObject = (JSONObject) jsonArray.getJSONObject(1);
        jsonArray = (JSONArray) jsonObject.getJSONArray("standings");
        eplrank[league_code] = (JSONArray) jsonArray.getJSONArray(0);
        System.out.println(eplrank[league_code].length());
        System.out.println(eplrank[league_code].get(0).toString());
    }
    /*public static void Laliga_Update() throws Exception{

        HttpResponse<JsonNode> response2 = Unirest.get("https://api-football-v1.p.rapidapi.com/leagueTable/87").header("X-RapidAPI-Key", "eaebed5d6emsh0b42de5d003fb91p16bd36jsn01c2a4cc8942").asJson();

        laliga = (JSONObject) response2.getBody().getObject();
        laliga = (JSONObject) laliga.getJSONObject("api");
        laligarank = (JSONArray) laliga.getJSONArray("standings");
        laligarank = (JSONArray) laligarank.getJSONArray(0);
        System.out.println(laligarank.length());
        System.out.println(laligarank.get(0).toString());
    }*/





    public static void RANK_SAY(MessageChannel mc, int league_code){
        int i;
        String message = "";
        for(i = 0; i<=19; i++){

            //jsonArray = (JSONArray) jsonObject.getJSONArray("0");
            JSONObject Temp = new JSONObject();
            Temp = (JSONObject) eplrank[league_code].get(i);
            String rank = (String) Temp.get("rank");
            String teamname = (String) Temp.get("teamName");
            String matchsPlayed = (String) Temp.get("matchsPlayed");
            String win = (String) Temp.get("win");
            String draw = (String) Temp.get("draw");
            String lose = (String) Temp.get("lose");
            String points = (String) Temp.get("points");
            message += ( rank +" 위" + " 팀이름 : " + teamname + " 경기수 : " + matchsPlayed + " 승/무/패 : " + win + "/" + draw + "/" + lose + " 승점 " + points + "\n");
        }
        mc.sendMessage(message).queue();
    }
    /*public static void Laliga_SAY(MessageChannel mc){
        int i;
        String message = "";
        for(i = 0; i<=19; i++){

            //jsonArray = (JSONArray) jsonObject.getJSONArray("0");

            Temp = (JSONObject) laligarank.get(i);
            String rank = (String) Temp.get("rank");
            String teamname = (String) Temp.get("teamName");
            String matchsPlayed = (String) Temp.get("matchsPlayed");
            String win = (String) Temp.get("win");
            String draw = (String) Temp.get("draw");
            String lose = (String) Temp.get("lose");
            String points = (String) Temp.get("points");
            message += ( rank +" 위" + " 팀이름 : " + teamname + " 경기수 : " + matchsPlayed + " 승/무/패 : " + win + "/" + draw + "/" + lose + " 승점 " + points + "\n");
        }
        mc.sendMessage(message).queue();
    }*/
    public static String update = new String();

    public static void Me_Update(){
        TXTReader txtReader = new TXTReader();
        update = txtReader.ArraytoString(txtReader.Reader("update"));
        banword = txtReader.Reader("banword");
    }

    public String uefa16 = "1차전\n로마 2 : 1 포르투 / 맨체스터 유나이티드 0 : 2 PSG(2/13 수 오전 5시)\n" +
            "토트넘 3 : 0 도르트문트 / 아약스 1 : 2 레알 마드리드(2/14 목 오전 5시)\n" +
            "리옹 : 바르셀로나 / 리버풀 : 바이에른 뮌헨(2/20 수 오전 5시)\n" +
            "AT마드리드 : 유벤투스 / 샬케 04 : 맨체스터 시티(2/21 목 오전 5시)\n" +
            "2차전\n레알 마드리드 : 아약스 / 도르트문트 : 토트넘(3/6 수 오전 5시)\n" +
            "포르투 : 로마 / PSG : 맨체스터 유나이티드(3/7 목 오전 5시)\n" +
            "맨체스터 시티 : 샬케 04 / 유벤투스 : AT마드리드(3/13 수 오전 5시)\n" +
            "바이에른 뮌헨 : 리버풀 / 바르셀로나 : 리옹(3/14 목 오전 5시)";
    public String uefaready = "준비중";
    public String europa32 = "1차전\n페네르바흐체 : 제니트 / 올림피아코스 : 디나모 키예프\n" +
            "라치오 : 세비야 / 라피트 빈 : 인테르\n" +
            "슬라비아 프라하 : 헹크 / 갈라타사라이 : 벤피카\n" +
            "렌 : 레일 베티스 / BATE 보리소프 : 아스날\n" +
            "크라스노다르 : 레버쿠젠 (이상 2/15 금 오전 2시 55분)\n" +
            "빅토리아 플젠 : GNK 디나모 / 클뤼프 브뤼허 : 레드불 잘츠부르크\n" +
            "셀틱 : 발렌시아 / 말뫼 : 첼시\n" +
            "취리히 : 나폴리 / 샤흐타르 : 아인트라흐트\n" +
            "스포르팅 : 비야레알 (이상 2/15 금 오전 5시 00분)\n" +
            "2차전\n세비야 : 라치오 (2/21 목 오전 2시 00분)\n" +
            "레드불 잘츠부르크 : 클뤼프 브뤼허 / 아인트라흐트 : 샤흐타르\n" +
            "비야레알 : 스포르팅 / GNK 디나모 : 빅토리아 플젠\n" +
            "제니트 : 페네르바흐체 / 발렌시아 : 셀틱\n" +
            "나폴리 : 취리히 / 아스날 : BATE 보리소프 (이상 2/15 금 오전 2시 55분)\n" +
            "헹크 : 슬라비아 프라하 / 인테르 : 라피트 빈\n" +
            "레버쿠젠 : 크라스노다르 / 벤피카 : 갈라타사라이\n" +
            "레알 베티스 : 렌 / 첼시 : 말뫼\n" +
            "디나모 키예프 : 올림피아코스 (이상 2/15 금 오전 5시 00분)";
    public String europaready = "준비중";
    public String[] fortune = { "오늘은 최고 짱짱 좋은 하루가 예상되네요", "오늘 하루는 별로 운이 좋지 않을 것 같아요" , "오늘은 뭔가 많은 것을 배워갈 것만 같아요","오늘은 신만이 내릴 수 있을 것만 같은 행운이 따를 것 같네요"};
    public String helptable ="'!도움 관리자'\n'!도움 스포츠'\n'!도움 기타' !마크주소";
    public String helpadmin ="'!오만원'\n'!켓냥이'\n'!드래곤'\n'!우랄스키'\n'!신사스키'\n'!플랭커'\n'!샘물청'";
    public String helpsport ="'!epl'\n'!epl 오늘경기'\n'!laliga'\n'!uefa'\n'!유로파'";
    public String helpguitar ="'!말해\n!ㅇㅁㅇ\n!러시안룰렛\n!happycastle\n!건의사항\n!감옥\n!단어신청\n!행맨시작\n!행맨종료\n!자기소개";
    public String gori = "현재 디스코드에서 가장 인기 있는 식품이라 고 말하고자 하면 단연 드래곤 꼬리고기가 떠 오르는 사람이 대다수일 것이다. 이 꼬리고기 가 인기가 많은 이유를 분석, 검토하여 이 논 문을 써내렸다.\n" +
            "우선 꼬리고기의 선호도부터 알아봤다. 대다 수의 사람들은 맛있다며 호평을 하지만 일부 는 징그럽다거나 이상하다며 기피하기도 한 다. 이는 한국 음식 중 번데기에서 나타나는 특징이기도 하다. 많은 사람들이 고소한 맛에 즐기지만 그 징그러운 형태 때문에 또한 많은 사람들이 꺼려한다. 나는 이 관점에서 드래곤 꼬리고기를 관찰했다. \n" +
            "또한 살펴본 것은 그 수요와 가격이다. 현재 드래곤 꼬리고기의 가격은 암암리에 거래되고 있어 오만원권으로는 택도 없다. 만일 드래곤 꼬리고기를 요리 및 식용으로 사용하기 위한 \n" +
            "경우 적어도 문제인이나 박원숭은 되어야 한 다고 생각할 정도의 가격대가 형성되어 있기 때문에 마치 초창기의 허니 버터칩과 같은 현 상이라고 판단한다. 물론 허니 버터칩은 그 가격이 3000원대였으며, 암암리에 거래되고 있지는 않다는 특징이 있지만 말이다.\n" +
            "어찌되었든 마지막으로, 그 드래곤이 불을 뿜 어댈 때마다 꼬리고기의 맛이 좋아진다는 말 이 있는데, 이는 사실로 밝혀졌다. 드래곤이 극대로를 할 때마다 꼬리고기의 가격이 오른 현상이 관찰되었고, 이는 맛이 좋아졌다는 것 과 연결된다고 판단한다.\n" +
            "이상으로 논문을 마친다. 개소리 읽느라 고생 많았다";
    //public String update = "HOB 오픈베타 3.2.3 - 행맨 힌트가 추가되었습니다! 6회 실패시 자동으로 출력됩니다! \n - 행맨 이제 단어를 입력해도 정답으로 인식합니다! \n - ex) \"ㅎㅏㄴ\" (X) \"한\" \n - 이스터애그가 조금 추가되었습니다!";
    //public String[] banword = {"리중딱","한꼴딱","햎캐바보","시발","ㅗ"};
}
