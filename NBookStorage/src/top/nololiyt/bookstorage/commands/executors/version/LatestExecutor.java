package top.nololiyt.bookstorage.commands.executors.version;

import org.bukkit.command.CommandSender;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.VersionManager;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;
import top.nololiyt.bookstorage.commands.Executor;
import top.nololiyt.bookstorage.entitiesandtools.LatestVersion;
import top.nololiyt.bookstorage.entitiesandtools.MessagesSender;
import top.nololiyt.bookstorage.entitiesandtools.StringPair;

import java.util.ArrayList;
import java.util.List;


public class LatestExecutor extends Executor
{
    protected final static String layerName = "latest";
    
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
                StringPair.senderName(commandSender.getName())
        });
        VersionManager versionManager = rootPlugin.getVersionManager();
        if (!versionManager.isUpdateCheckerEnabled())
        {
            messagesSender.send(messageKey.append("checker-not-enabled"));
            return true;
        }
        LatestVersion latestVersion = versionManager.getLatestVersion();
        if (latestVersion == null)
        {
            messagesSender.send(messageKey.append("failed-to-check"));
            return true;
        }
        messagesSender.setArgs(new StringPair[]{
                StringPair.senderName(commandSender.getName()),
                StringPair.version(latestVersion.getVersion().toString()),
                StringPair.time(latestVersion.getCheckTime().toString())
        });
        messagesSender.send(messageKey.append("message"));
        return true;
    }
}