package com;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.GuildController;

import java.util.Date;
import java.util.Random;

import static java.sql.JDBCType.NULL;
import static net.dv8tion.jda.api.entities.Guild.*;

public class MyListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getContentRaw();
        SoccerList soccerList = new SoccerList();
        TodaySoccer todaySoccer = new TodaySoccer();
        MessageChannel channel = event.getChannel();
        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)

        if (content.equals("!ping")) {
            //MessageChannel channel = event.getChannel();
            channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
        if (content.startsWith("!말해 ")) {
            //MessageChannel channel = event.getChannel();
            channel.sendMessage(content.substring(4) + " by " + event.getAuthor().getAsTag()).queue();
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
        }
        if(content.equals("!마크주소")){
            channel.sendMessage("서버 주소 : 49.254.121.224").queue();
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
        if(content.equals("!X") || content.equals("!x")){
            channel.sendMessage("조이를 표하셨네요.").queue();
        }
        if(content.equals("!개발자")){
            channel.sendMessage("Developed by happycastle, 오만원").queue();
        }
        if(content.equals("!섹스") || content.equals("!오만원")){
            channel.sendMessage("까악, 변태다!").queue();
        }
        if(content.equals("!ㅎㅇ") || content.equals("!호이") || content.equals("!하츠오브아이언")){
            channel.sendMessage("전략 시뮬레이션 게임 중에서도 갓-겜의 부류에 드는 엄청난 게임이죠!").queue();
        }
        if(content.equals("!오만")){
            channel.sendMessage("제가 개인적으로 좋아하는 나라예요!").queue();
        }
        if(content.equals("!ㅇㅁㅇ")){
            channel.sendMessage("오만원과 happycastle의 노가다로 탄생한 ㅇㅁㅇ 봇입니다! !도움 을 입력해 주세요!").queue();
        }
        if(content.equals("!시바스")){
            channel.sendMessage("하츠 오브 아이언과 전염병 주식회사 등 여러 게임을 주로 플레이 하시는 머기업 스트리머시죠!").queue();
        }
        if(content.equals("!드래곤")){
            channel.sendMessage("꼬리고기가 맛있다는 그 드래곤이요? 개인적으로 꼬기고기는 한번 먹어보고 싶네요!").queue();
        }
        if(content.equals("!켓냥이")){
            channel.sendMessage("이상하게 냥냥거리는 건 다른 사람인데.. 이 사람 닉네임은 켓냥이네요!").queue();
        }
        if(content.equals("!우랄스키")){
            channel.sendMessage("우랄산맥에서 스키는 잘 타고 있을까요...?").queue();
        }

        if(content.equals("!신사스키")){
            DiscordUtiles discordUtiles = new DiscordUtiles();
            channel.sendMessage("충! 성! 전역까지 D-" + discordUtiles.calcdate(2020, 7,20) + "일 남았습니다!").queue();
        }
        if(content.startsWith("!건의사항")){
            if(content.equals("!건의사항")){
                channel.sendMessage("건의사항을 입력해 주세요! !건의사항 <건의사항>").queue();
            } else{
                CoolDown coolDown = new CoolDown();
                CoolDownType cool = coolDown.CheckCoolDown(message.getAuthor());
                if(cool.result){
                    String[] word = content.split("!건의사항 ");
                    Date d = new Date();
                    WriteToFile writeToFile = new WriteToFile();
                    writeToFile.write(word[1] + " by " + message.getAuthor().getAsTag() + " at " + d.toString() , "memo");
                    channel.sendMessage("건의사항이 접수되었습니다!").queue();
                } else{
                    channel.sendMessage((10 - cool.longtime ) + "분 후 사용이 가능합니다!").queue();
                }
            }
        }
        if(content.startsWith("!단어신청")){
            if(content.equals("!단어신청")){
                channel.sendMessage("단어를 신청해 주세요! !단어신청 <단어>").queue();
            } else{
                    String[] word = content.split("!단어신청 ");
                    Date d = new Date();
                    WriteToFile writeToFile = new WriteToFile();
                    writeToFile.write(word[1] + " by " + message.getAuthor().getAsTag() + " at " + d.toString() , "단어신청");
                    channel.sendMessage("단어신청 사항이 저장되었습니다!").queue();
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
                } else if(word[1].equals("스포츠")){
                    channel.sendMessage(soccerList.helpsport).queue();
                } else if(word[1].equals("기타")){
                    channel.sendMessage(soccerList.helpguitar).queue();
                } else {
                    channel.sendMessage(soccerList.helptable).queue();
                }
            }
        }
        if(content.equals("!주사위")){
            Random random = new Random();
            channel.sendMessage(":game_die: " + (random.nextInt(5) + 1 )).queue();
        }
        if(content.equals("!happycastle")){
            channel.sendMessage("갈갈이 당하고있는 봇 개발자 입니다!").queue();
        }
        if(content.equals("!운세")){
            Random random = new Random();
            channel.sendMessage(soccerList.fortune[random.nextInt(soccerList.fortune.length - 1)]).queue();
        }
        if (content.equals("!laliga")) {
            soccerList.RANK_SAY(channel,87);
        }
        if (content.equals("!샘물청")) {
            channel.sendMessage("你吃饭了吗？").queue();
        }
        if(content.equals("!난죽택")){
            channel.sendMessage("불쌍한 렉사르...").queue();
        }
        if(content.equals("!러시안룰렛")){
            Russian rs = new Russian();
            rs.startGame(channel);
        }
        if (content.startsWith("!uefa")) {
            if (content.equals("!uefa")) {
                channel.sendMessage("!UEFA 16강'\\n'!UEFA 8강'\\n'!UEFA 4강'\\n'!UEFA 결승").queue();
            } else {
                String[] words = content.split("!uefa ");
                System.out.println(words[1] + " <------- ");
                if (words[1].equals("16강")) {
                    channel.sendMessage(soccerList.uefa16).queue();
                } else {
                    channel.sendMessage(soccerList.uefaready).queue();
                }
            }
        }
        if(content.startsWith("!유로파")){
            if(content.equals("!유로파")){
                channel.sendMessage("!유로파 32강'\\n'!유로파 16강'\\n'!유로파 8강'\\n'!유로파 4강'\\n'!유로파 결승").queue();
                return;
            }
            String[] words = content.split("!유로파 ");
            if(words[1].equals("16강")){
                channel.sendMessage(soccerList.europa32).queue();
            } else{
                channel.sendMessage(soccerList.europaready).queue();
            }
        }
        if(content.startsWith("!업데이트")){
            channel.sendMessage(soccerList.update).queue();
        }
        if(content.equals("!탈옥")){
            Gambang gambang = new Gambang();
            if(gambang.InJail(message.getMember())){
                CoolDownType cool = gambang.CheckCoolDown(message.getMember());
                if(!(cool.result)){
                    channel.sendMessage("탈옥까지 " + cool.longtime + " 분 남았습니다!").queue();
                }
            } else{
                channel.sendMessage("수감자만 사용 가능한 명령어입니다!").queue();
            }
        }
        if(message.getMember().hasPermission(Permission.ADMINISTRATOR))
        {
            Guild guild = message.getGuild();
            GuildController guildController = guild.getController();
            String msl = "!감옥 <유저맨션> <시간>";
            if(content.startsWith("!즉시탈출")){
                Gambang gambang = new Gambang();
                Member member = message.getMentionedMembers().get(0);
                if(content.equals("!즉시탈출")){
                    channel.sendMessage("!즉시탈출 <유저맨션>").queue();
                } else{
                    if(gambang.InJail(member)){
                        gambang.bosuk(member);
                        channel.sendMessage("즉시 석방처리 되었습니다!").queue();
                    } else{
                        channel.sendMessage("감옥에 수감되지 않은 유저입니다!").queue();
                    }
                }
            }
            if(content.startsWith("!감옥") && !(content.equals("!감옥"))){
                String[] str = content.split(" ");
                if(str.length == 3){
                    Member member = message.getMentionedMembers().get(0);
                    int time = Integer.parseInt(str[2]);
                    Gambang gambang = new Gambang();
                    String welcome = member.getAsMention() + "님 감옥에 오신것을 환영합니다!\n 당신의 탈옥시간은 " + time + " 분 후 입니다! \n 시간이 다 되시면 " +
                            "!탈옥 명령어를 통해 탈출하세요!\n !탈옥 명령어를 치시면 남은 시간을 확인할 수 있습니다!";
                    MessageChannel messageChannel2 = guild.getTextChannelById("546210576741826575");
                    if(!(gambang.InJail(member))){
                        gambang.GoJail(member, time);
                        messageChannel2.sendMessage(welcome).queue();
                    } else{
                        channel.sendMessage("이미 감옥에 수감되어있는 유저입니다!").queue();
                    }

                } else{
                    channel.sendMessage(msl).queue();
                }
            } else if(content.equals("!감옥")){
                channel.sendMessage(msl).queue();
            }
        }
        if(message.getAuthor().getAsTag().equals("happycastle#6206")){
            if (content.equals("!경기업데이트")){
                todaySoccer.setToday();
                try {
                    todaySoccer.UpdateTodaySoccer();
                    channel.sendMessage("Successful Update!").queue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (content.equals("!update")) {
                //SoccerList soccerList = new SoccerList();
                try {
                    soccerList.RANK_Update(2);
                    soccerList.RANK_Update(87);
                    //MessageChannel channel = event.getChannel();
                    channel.sendMessage("Successful Update!").queue();
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
            }
        }
    }
}
