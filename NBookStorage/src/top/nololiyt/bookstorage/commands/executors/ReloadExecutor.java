package top.nololiyt.bookstorage.commands.executors;

import org.bukkit.command.CommandSender;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.commands.Executor;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;
import top.nololiyt.bookstorage.entitiesandtools.StringPair;

import java.util.ArrayList;
import java.util.List;

public class ReloadExecutor extends Executor
{
    void run(RootPlugin rootPlugin,DotDividedStringBuilder messageKey,CommandSender commandSender)
    {
        run(-1, rootPlugin,
                new DotDividedStringBuilder(messageKey).append(messageKey()),
                commandSender, null);
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
    protected boolean run(int layer, RootPlugin rootPlugin, DotDividedStringBuilder messageKey, CommandSender commandSender, String[] args)
    {
        rootPlugin.saveDefaultConfig();
        rootPlugin.reloadConfig();
        messageKey.append("completed");
    
        StringPair[] pairs = new StringPair[]{
                StringPair.senderName(commandSender.getName())
        };
    
        rootPlugin.getMessagesManager().sendMessage(
                commandSender, messageKey, pairs);
        return true;
    }
    
    protected final static String layerName = "reload";
    
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
}
