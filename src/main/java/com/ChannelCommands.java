package com;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.ChannelManager;

import java.util.concurrent.TimeUnit;

public class ChannelCommands {

    public void updateChannel(TextChannel channel) {
        ChannelManager manager = channel.getManager(); // get the manager
        manager.setName("testing-2").setTopic("This is a testing channel, no memes allowed"); // set the new values
        manager.queue(); // execute update, this updates both name and topic
    }
    public void setTestingChannelBlocking(TextChannel channel)
    {
        channel.getManager().setName("testing-channel").complete();
        Message m = channel.sendMessage("Update Channel").complete();
        m.delete().completeAfter(30, TimeUnit.SECONDS);
        //logChannel.sendMessage("Deleted Response in %s", channel).queue();
        // note how we used queue in the end because we don't need it sequenced anymore.
    }
    public void setTestingChannel(TextChannel channel)
    {
       /* channel.getManager().setName("testing-channel").queue( (v) ->
                channel.sendMessage("Update Channel").queue( (m) ->
                        m.delete().queueAfter(30, TimeUnit.SECONDS, (t) ->
                                //logChannel.sendMessage("Deleted Response in %s", channel).queue()
                        )
                )
        );*/
    }
    public PermissionOverride getOverride(TextChannel channel, Member member)
    {
        final PermissionOverride override = channel.getPermissionOverride(member);

        if (override == null)
            return channel.createPermissionOverride(member).complete();

        return override;
    }
}
