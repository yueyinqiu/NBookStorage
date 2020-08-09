package top.nololiyt.bookstorage.commands.executors.version;

import org.bukkit.command.CommandSender;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;
import top.nololiyt.bookstorage.commands.Executor;
import top.nololiyt.bookstorage.entitiesandtools.MessagesSender;
import top.nololiyt.bookstorage.entitiesandtools.StringPair;

import java.util.ArrayList;
import java.util.List;


public class CurrentExecutor extends Executor
{
    protected final static String layerName = "current";
    
    @Override
    public String permissionName()
    {
        return null;
    }
    
    @Override
    public String messageKey()
    {
        return layerName;
    }
    
    @Override
    public List<String> getTabComplete(RootPlugin rootPlugin,int ordinal)
    {
        switch (ordinal)
        {
            default:
                return new ArrayList<>();
        }
    }
    
    @Override
    protected boolean run(int layer, RootPlugin rootPlugin,
                          DotDividedStringBuilder messageKey, CommandSender commandSender,
                          String[] args)
    {
        MessagesSender messagesSender = new MessagesSender(rootPlugin.getMessagesManager(),
                commandSender, new StringPair[]{
                StringPair.senderName(commandSender.getName()),
                StringPair.version(rootPlugin.getVersionManager().getCurrentVersion().toString())
        });
        messagesSender.send(messageKey.append("message"));
        return true;
    }
}