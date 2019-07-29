package com;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.GuildController;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import static java.sql.JDBCType.NULL;
import static net.dv8tion.jda.api.entities.Guild.*;

public class MyListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentRaw();
        TodaySoccer todaySoccer = new TodaySoccer();
        MessageChannel channel = event.getChannel();
        SoccerList soccerList = new SoccerList();
        if(!soccerList.banword.isEmpty()) {
            for (int i = 0; i < soccerList.banword.size(); i++) {
                if (content.contains(soccerList.banword.get(i))) {
                    event.getMessage().getChannel().sendMessage("[경고] 금지어를 사용하였습니다!").queue();
                    return;
                }
            }
        }
        if (event.getAuthor().isBot()) return;
        if (!event.getMessage().getContentRaw().startsWith("!")) return;


        // We don't want to respond to other bot accounts, including ourself
        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("!ping")) {
            //MessageChannel channel = event.getChannel();
            channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
            return;
        }
        if (content.startsWith("!말해 ")) {
            //MessageChannel channel = event.getChannel();
            channel.sendMessage(content.substring(4) + " by " + event.getAuthor().getAsTag()).queue();
            return;
        }
        /*if (content.startsWith("!sayd ")) {
            //MessageChannel channel = event.getChannel();
            channel.sendMessage(content.substring(5)).queue();
            DiscordUtiles discordUtiles = new DiscordUtiles();
            discordUtiles.deleteMessage(message, "바다향기가 삭제함");
        }*/
        if (content.startsWith("!sayd ")) {
            //MessageChannel channel = event.getChannel();
            User Auther = event.getAuthor();

            String Auther_Name = Auther.getAsTag();
            Message ms = channel.sendMessage(content.substring(6) + " by @" + Auther_Name).complete();
            DiscordUtiles discordUtiles = new DiscordUtiles();
            discordUtiles.deleteMessage(message, "바다향기가 삭제함");
            //discordUtiles.waitForEdit(ms);
            return;
        }
        if(content.equals("!마크주소")){
            channel.sendMessage("서버 주소 : 49.254.121.224").queue();
            return;
        }
        if(content.equals("!X") || content.equals("!x")){
            channel.sendMessage("조이를 표하셨네요.").queue();
            return;
        }
        if(content.equals("!맨유")){
            channel.sendMessage("~~맹구맹구~~").queue();
            return;
        }
        if(content.equals("!아스날")){
            channel.sendMessage("어디서 7스날 지는소리 안났나요?").queue();
            return;
        }
        if(content.equals("!개발자")){
            channel.sendMessage("Developed by happycastle, 오만원").queue();
            return;
        }
        if(content.equals("!섹스") || content.equals("!오만원") || content.equals("!변태")){
            channel.sendMessage("까악, 변태다!").queue();
            return;
        }
        if(content.equals("!ㅎㅇ") || content.equals("!호이") || content.equals("!하츠오브아이언")){
            channel.sendMessage("전략 시뮬레이션 게임 중에서도 갓-겜의 부류에 드는 엄청난 게임이죠!").queue();
            return;
        }
        if(content.equals("!동지")){
            channel.sendMessage("동지여 안녕하오").queue();
            return;
        }
        if(content.equals("!오만")){
            channel.sendMessage("제가 개인적으로 좋아하는 나라예요!").queue();
            return;
        }
        if(content.equals("!HOB")){
            channel.sendMessage("오만원과 happycastle의 노가다로 탄생한 HOB 봇입니다! !도움 을 입력해 주세요!").queue();
            return;
        }
        if(content.equals("!시바스")){
            channel.sendMessage("하츠 오브 아이언과 전염병 주식회사 등 여러 게임을 주로 플레이 하시는 머기업 스트리머시죠!").queue();
            return;
        }
        if(content.equals("!드래곤")){
            channel.sendMessage(" 이서버의 2번째 보스이다. 또한 어세신크리드 같이 클로킹을 하고 다니는게 특징이다.( 언제죽을지 모르니 조심하자)\n~~꼬리고기 개꿀맛~~").queue();
            return;
        }
        if(content.equals("!켓냥이")){
            channel.sendMessage("이상하게 냥냥거리는 건 다른 사람인데.. 이 사람 닉네임은 켓냥이네요!").queue();
            return;
        }
        if(content.equals("!우랄스키")){
            channel.sendMessage("우랄산맥에서 스키는 잘 타고 있을까요...?").queue();
            return;
        }
        if(content.equals("!바다향기")){
            channel.sendMessage("우리의 애! 교! 쟁! 이!").queue();
            return;
        }
        if(content.equals("!로든")){
            channel.sendMessage("사비 찬양자 ~~리버풀만세~~ 로든이다").queue();
            return;
        }
        if(content.equals("!사비")){
            File file = new File("sabi.jpg");
            channel.sendMessage("사-비 는 GOD입니다!").queue();
            channel.sendFile(file,"신의 사진").queue();
            return;
        }
        if(content.equals("!뉴캐슬")){
            channel.sendMessage("해피캐슬이 개인적으로 좋아하는 팀이예요!").queue();
            return;
        }
        if(content.equals("!리버풀")){
            File file = new File("libul.jpg");
            channel.sendMessage("올해가 우승 30주년... 이지만 18-19시즌 우승할 팀이예요!").queue();
            channel.sendFile(file).queue();
            return;
        }
        if(content.equals("!멘시티")){
            channel.sendMessage("진정한 멘체스터 주인이 아닌 짭주인\n리버풀의 우승을 뺏어간 주적이다").queue();
            return;
        }
        if(content.equals("!한화")){
            channel.sendMessage("올해 우승 20주년 그러나 올해 우승가능성이 있어요!").queue();
            File file = new File("Eagle.jpg");
            channel.sendFile(file).queue();
            return;
        }
        if(content.equals("!아이유") || content.equals("!IU")){
            channel.sendMessage(":heart:").queue();
            File file = new File("IU.jpg");
            channel.sendFile(file).queue();
            return;
        }
        if(content.equals("!신사스키")){
            DiscordUtiles discordUtiles = new DiscordUtiles();
            channel.sendMessage("충! 성! 전역까지 D-" + discordUtiles.calcdate(2020, 7,20) + "일 남았습니다!").queue();
            return;sk
        }
        if(content.startsWith("!건의사항")){
            if(content.equals("!건의사항")){
                channel.sendMessage("건의사항을 입력해 주세요! !건의사항 <건의사항>").queue();
            } else{
                CoolDown coolDown = new CoolDown();
                CoolDownType cool = coolDown.CheckCoolDown(message.getAuthor());
                if(cool.result){
                    String[] word = content.split("!건의사항 ");
                    String[] word = content.split("!건의사항",2);
                    Date d = new Date();
                    WriteToFile writeToFile = new WriteToFile();
                    writeToFile.write(word[1] + " by " + message.getAuthor().getAsTag() + " at " + d.toString() , "memo");
                    channel.sendMessage("건의사항이 접수되었습니다!").queue();
                } else{
                    channel.sendMessage((5 - cool.longtime ) + "분 후 사용이 가능합니다!").queue();
                }
            }
        }

        if(content.startsWith("!단어신청")){
            if(content.equals("!단어신청")){
                channel.sendMessage("단어를 신청해 주세요! !단어신청 <단어>").queue();
                return;
            } else{
                    String[] word = content.split("!단어신청 ");
                    Date d = new Date();
                    WriteToFile writeToFile = new WriteToFile();
                    writeToFile.write(word[1] + " by " + message.getAuthor().getAsTag() + " at " + d.toString() , "단어신청");
                    channel.sendMessage("단어신청 사항이 저장되었습니다!").queue();
                    return;
                }
            }

        if(content.startsWith("!도움")){
            if(content.equals("!도움")){
                channel.sendMessage(soccerList.helptable).queue();
                return;
            } else{
                String[] word = content.split("!도움 ");
                if(word[1].equals("관리자")){
                    channel.sendMessage(soccerList.helpadmin).queue();
                    return;
                } else if(word[1].equals("스포츠")){
                    channel.sendMessage(soccerList.helpsport).queue();
                    return;
                } else if(word[1].equals("기타")){
                    channel.sendMessage(soccerList.helpguitar).queue();
                    return;
                } else {
                    channel.sendMessage(soccerList.helptable).queue();
                    return;
                }
            }
        }
        if(content.equals("!주사위")){
            Random random = new Random();
            channel.sendMessage(":game_die: " + (random.nextInt(5) + 1 )).queue();
            return;
        }
        if(content.equals("!happycastle") || content.equals("!해피캐슬")){
            channel.sendMessage("갈갈이 당하고있는 봇 개발자 입니다!").queue();
            return;
        }
        if(content.equals("!운세")){
            Random random = new Random();
            channel.sendMessage(soccerList.fortune[random.nextInt(soccerList.fortune.length - 1)]).queue();
            return;
        }

        if (content.equals("!샘물청")) {
            channel.sendMessage("你吃饭了吗？").queue();
            return;
        }
        if(content.equals("!난죽택")){
            channel.sendMessage("불쌍한 렉사르...").queue();
            return;
        }
        if(content.equals("!ㄹㄹㅋ")){
            channel.sendMessage("혹시... 이호님이나 해피님을 말씀하시는 겁니까..?\n~~해피캐슬 아님~~").queue();
            return;
        }
        if(content.equals("!리중딱") || content.equals("!한꼴딱") || content.equals("!리빅아")){
            channel.sendMessage("어디서 말도안되는 소리를!").queue();
            return;
        }
        if(content.equals("!러시안룰렛")){
            Russian rs = new Russian();
            rs.startGame(channel);
        }

        if(channel.getId().equals("509379021012860930")) {
            if (content.startsWith("!유로파")) {
                if (content.equals("!유로파")) {
                    channel.sendMessage("!유로파 32강'\\n'!유로파 16강'\\n'!유로파 8강'\\n'!유로파 4강'\\n'!유로파 결승").queue();
                    return;
                }
                String[] words = content.split("!유로파 ");
                if (words[1].equals("16강")) {
                    channel.sendMessage(soccerList.europa32).queue();
                } else {
                    channel.sendMessage(soccerList.europaready).queue();
                }
                return;
            }
            if (content.startsWith("!epl")) {

                //MessageChannel chaanel = event.getChannel();
                //soccerList.EPL_SAY(channel);
                String words = content.substring(3);
                if (words.equals("l")) {
                    soccerList.RANK_SAY(channel, 2);
                } else if (words.equals("l 오늘경기")) {
                    todaySoccer.PrintTodayFixture(channel, "2");
                } else {
                    channel.sendMessage("없는 명령어 입니다!").queue();
                }

            }
            if (content.equals("!laliga")) {
                soccerList.RANK_SAY(channel,87);
                return;
            }
            if (content.startsWith("!uefa")) {
                if (content.equals("!uefa")) {
                    channel.sendMessage("!UEFA 16강'\\n'!UEFA 8강'\\n'!UEFA 4강'\\n'!UEFA 결승").queue();
                } else {
                    String[] words = content.split("!uefa ");
                    if (words[1].equals("16강")) {
                        channel.sendMessage(soccerList.uefa16).queue();
                    } else {
                        channel.sendMessage(soccerList.uefaready).queue();
                    }
                }
                return;
            }
        }
        if(content.startsWith("!업데이트")){
            channel.sendMessage(soccerList.update).queue();
            return;
        }
        if(content.equals("!그치만")){
            channel.sendMessage("그..그치만 드래곤의 꼬리고기는 너무 맛있는걸!").queue();
            return;
        }
        if(content.startsWith("!자기소개")){
            String msl = "사용법 !자기소개 <설정/유저맨션> <내용>";
            if(!content.equals("!자기소개")){
                String[] str = content.split(" ");
                if(str.length >= 3){
                    IntroduceSelf introduceSelf = new IntroduceSelf();
                    if(str[1].equals("설정")){
                        if(str[1].equals("설정")){
                            String reason = content.substring(9);
                            introduceSelf.SetI(message.getAuthor(), reason);
                            channel.sendMessage("자기소개가 설정되었습니다!").queue();
                            return;
                        }
                    } else channel.sendMessage(msl).queue(); return;
                } else if(!message.getMentionedUsers().isEmpty()){
                    IntroduceSelf introduceSelf = new IntroduceSelf();
                    Guild guild = message.getGuild();
                    channel.sendMessage("```" + introduceSelf.FindI(message.getMentionedUsers().get(0)) + "```").queue();
                    return;
                }else channel.sendMessage(msl).queue(); return;
            }else channel.sendMessage(msl).queue(); return;
        }
        if(content.equals("!탈옥")){
            Gambang gambang = new Gambang();
            Guild guild = message.getGuild();
            if(gambang.InJail(message.getMember(), guild)){
                CoolDownType cool = gambang.CheckCoolDown(message.getMember(), guild);
                if(!(cool.result)){
                    channel.sendMessage("탈옥까지 " + cool.longtime + " 분 남았습니다!").queue();
                    return;
                }
            } else{
                channel.sendMessage("수감자만 사용 가능한 명령어입니다!").queue();
                return;
            }
        }
        if(content.startsWith("!나무위키")){
            String help = "!나무위키 <검색키워드> : 나무위키에서 내용을 검색 후 갠챗으로 전달합니다";
            if(content.equals("!나무위키")){
                channel.sendMessage(help).queue();
            } else{
                String[] str = content.split(" ",2);
                if(str.length == 2){
                    WebCroll webCroll = new WebCroll();

                    MessageChannel messageChannel = message.getAuthor().openPrivateChannel().complete();
                    try {
                        messageChannel.sendMessage(webCroll.croll("http://namu.wiki/w/" + URLEncoder.encode(str[1],"utf-8")) + "\n" + "Link : http://namu.wiki/w/" + str[1]).queue();
                    } catch (Exception e) {
                        e.printStackTrace();
                        channel.sendMessage("없는 문서이거나, 글자수가 너무 길거나, 에러가 발생했습니다").queue();
                        channel.sendMessage("Link : http://namu.wiki/w/" + str[1] ).queue();
                        return;
                    }
                    channel.sendMessage("검색 결과가 개인챗으로 전달되었습니다!").queue();
                }
            }
        }
        if(message.getMember().hasPermission(Permission.ADMINISTRATOR)))
        {
            Guild guild = message.getGuild();
            GuildController guildController = guild.getController();
            String msl = "!감옥 <유저맨션> <시간> <사유:띄어쓰기안됨>";
            if(content.equals("!테스트")){
                WebCroll webCroll = new WebCroll();
                try{
                    webCroll.croll("http://namu.wiki/w/");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(content.startsWith("!즉시탈출")){
                Gambang gambang = new Gambang();
                Member member = message.getMentionedMembers().get(0);
                if(content.equals("!즉시탈출")){
                    channel.sendMessage("!즉시탈출 <유저맨션>").queue();
                } else{
                    if(gambang.InJail(member,guild)){
                        gambang.bosuk(member,guild);
                        channel.sendMessage("즉시 석방처리 되었습니다!").queue();
                    } else{
                        channel.sendMessage("감옥에 수감되지 않은 유저입니다!").queue();
                    }
                }
            }
            if(content.equals("!탄핵")){
                File file = new File("hanhak.jpg");
                channel.sendFile(file).queue();
                return;
            }
            if(content.equals("!꼬리고기")){
                channel.sendMessage(soccerList.gori).queue();
                return;
            }
            if(content.startsWith("!=ban")){
                String mel = "사용법 !=ban <유저맨션> <사유:띄어쓰기가능>";
                if(!content.equals("!=ban")){
                    String[] str = content.split(" ",3);
                    if(str.length >= 3){
                        Member member = message.getMentionedMembers().get(0);
                        MessageChannel mesch = member.getUser().openPrivateChannel().complete();
                        mesch.sendMessage(member.getUser().getAsTag() + "님! \n당신은 **강철심장 서버로부터 밴당했습니다!** 관리자 : " + message.getAuthor().getAsTag() +"\n 사유 : **" + str[2] + "**").queue();
                        guildController.ban(member,1,str[2]).queue();
                        channel.sendMessage(":ok_hand: ").queue();
                    } else{
                        channel.sendMessage(mel).queue();
                    }
                } else{
                    channel.sendMessage(mel).queue();
                }
            }
            if(content.startsWith("!=kick")){
                String mel = "사용법 !=kick <유저맨션> <사유:띄어쓰기가능>";
                if(!content.equals("!=kick")){
                    String[] str = content.split(" ", 3);
                    if(str.length == 3){
                        Member member = message.getMentionedMembers().get(0);
                        MessageChannel mesch = member.getUser().openPrivateChannel().complete();
                        mesch.sendMessage(member.getUser().getAsTag() + "님! \n당신은 **강철심장 서버로부터 킥당했습니다!** 관리자 : " + message.getAuthor().getAsTag() +"\n 사유 : **" + str[2] + "**").queue();
                        guildController.kick(member,str[2]).queue();
                        channel.sendMessage(":ok_hand: ").queue();
                    } else{
                        channel.sendMessage(mel).queue();
                    }
                } else{
                    channel.sendMessage(mel).queue();
                }
            }
            if(content.startsWith("!감옥") && !(content.equals("!감옥"))){
                String[] str = content.split(" ");
                if(str.length == 4){
                    Member member = message.getMentionedMembers().get(0);
                    int time = Integer.parseInt(str[2]);
                    Gambang gambang = new Gambang();
                    String welcome = member.getNickname() + "님 감옥에 오신것을 환영합니다!\n 수감사유는 "  + str[3] + "입니다!\n 당신의 탈옥시간은 " + time + " 분 후 입니다! \n 시간이 다 되시면 " +
                            "!탈옥 명령어를 통해 탈출하세요!\n !탈옥 명령어를 치시면 남은 시간을 확인할 수 있습니다!";
                    MessageChannel messageChannel2 = guild.getTextChannelById("508569347090939924");
                    if(!(gambang.InJail(member,guild))){
                        gambang.GoJail(member, time, guild, str[3]);
                        messageChannel2.sendMessage(welcome).queue();
                        return;
                    } else{
                        channel.sendMessage("이미 감옥에 수감되어있는 유저입니다!").queue();
                        return;
                    }

                } else{
                    channel.sendMessage(msl).queue();
                    return;
                }
            } else if(content.equals("!감옥")){
                channel.sendMessage(msl).queue();
                return;
            }
        }
        if(message.getAuthor().getAsTag().equals("happycastle#6206")){
            if(content.equals("!전체업데이트")){
                soccerList.Me_Update();
            }
            if (content.equals("!경기업데이트")){
                todaySoccer.setToday();
                try {
                    todaySoccer.UpdateTodaySoccer();
                    channel.sendMessage("Successful Update!").queue();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(content.equals("!save")){
                IntroduceSelf introduceSelf = new IntroduceSelf();
                introduceSelf.Save();
                channel.sendMessage("Sucessful Backup").queue();
                return;
            }
            if(content.equals("!reload")){
                IntroduceSelf introduceSelf = new IntroduceSelf();
                introduceSelf.Update();
                channel.sendMessage("성공적인 리로드").queue();
                return;
            }
            if (content.equals("!update")) {
                //SoccerList soccerList = new SoccerList();
                try {
                    soccerList.RANK_Update(2);
                    soccerList.RANK_Update(87);
                    //MessageChannel channel = event.getChannel();
                    channel.sendMessage("Successful Update!").queue();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (content.equals("!채널수정")) {
                //MessageChannel channel = event.getChannel();
                channel.sendMessage("채널 수정함!").queue();
                ChannelCommands cs = new ChannelCommands();
                TextChannel textChannel = event.getTextChannel();
                cs.updateChannel(textChannel);
                return;
            }
        }
    }
}
