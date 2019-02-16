package com;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import net.dv8tion.jda.api.entities.MessageChannel;
import org.json.JSONArray;
import org.json.JSONObject;

public class SoccerList {



    public static JSONArray[] eplrank = new JSONArray[100];



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


    public String epl = "1위 : 맨체스터 시티\n2위 : 리버풀\n3위 : 토트넘\n4위 : 맨체서터 유나이티드\n" +
            "5위 : 아스날\n6위 : 첼시\n7위 : 울브즈\n8위 : 왓포드\n9위 : 에버튼\n" +
            "10위 : 웨스트햄 유나이티드\n11위 : 본머스\n12위 : 레스터 시티\n"  +
            "13위 : 크리스탈 펠리스\n14위 : 브라이튼\n15위 : 번리\n16위 : 뉴캐슬 유나이티드"  +
              "\n17위 : 카디프 시티\n18위 : 사우샘프턴\n19위 : 풀럼\n20위 : 허더스필드" +
            "\n매일 오후 6시 업데이트, 순위표 출처 : 구글";
    public String epltoday = "Nothing\n" +
            "epl 오늘경기는 매일 6시 업데이트 됩니다.\n" +
            "오늘의 범위는 어제 오후 6시 ~ 오늘 오후 6시";
    public String laligatoday = "1위 : 바르셀로나\n2위 : 레알 마드리드\n3위 : AT 마드리드\n4위 : 세비야\n" +
            "5위 : 헤타페\n6위 : 알레베스\n7위 : 레알 베티스\n8위 : 발렌시아\n" +
            "9위 : 레알 소시에다드\n10위 : 에이바르\n11위 : 레가네스\n12위 : 에스파뇰\n" +
            "13위 : 아틀레틱\n14위 : 레반테\n15위 : 바야돌리드\n16위 : 셀타 비고\n" +
            "17위 : 지로나\n18위 : 라요\n19위 : 비야레알\n20위 : 우에스카\n";
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
    public String helpguitar ="'!말해\n!ㅇㅁㅇ\n!러시안룰렛\n!happycastle\n!건의사항\n!감옥\n!단어신청\n!행맨시작\n!행맨종료";
    public String update = "ㅇㅁㅇ 오픈베타 2.3.1 - 건의사항이 생겼습니다! !건의사항\n - 행맨이 생겼습니다! !행맨시작 , !행맨종료\n - 행맨 단어신청을 받습니다! !단어신청 \n - !감옥 명령어가 생겼습니다! 철컹철컹" ;



}
