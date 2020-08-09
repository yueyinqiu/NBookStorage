package top.nololiyt.bookstorage.commands.executors;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.commands.Executor;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;
import top.nololiyt.bookstorage.entitiesandtools.MessagesSender;
import top.nololiyt.bookstorage.entitiesandtools.StringPair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
public class SaveExecutor extends Executor
{
    protected final static String layerName = "save";
    
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
            case 0:
                return new ArrayList<String>(){
                    {
                        add("BOOOOOOK1");
                        add("BOOOOOOK2");
                        add("BOOOOOOK3");
                    }
                };
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
                commandSender, null);
    
        if (!(commandSender instanceof Player))
        {
            messagesSender.setArgs(new StringPair[]{
                    StringPair.senderName(commandSender.getName()),
            });
            messagesSender.send(messageKey.append("without-a-position"));
            return true;
        }
    
        if (args.length - 1 != layer)
            return false;
    
        Player player = (Player) commandSender;
    
        String markName = args[layer];
    
        messagesSender.setArgs(new StringPair[]{
                StringPair.markName(markName),
                StringPair.senderName(player.getDisplayName())
        });
        try
        {
            addMark(rootPlugin.getMarksManager(), markName,
                    player.getLocation(), messagesSender,
                    messageKey);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            messagesSender.send(messageKey.append("failed"));
        }
        return true;
    }
    
    private void addMark(MarksManager marksManager, String markName, Location location,
                         MessagesSender messagesSender, DotDividedStringBuilder messageKey)
            throws IOException
    {
        if (marksManager.getMark(markName) != null)
        {
            messagesSender.send(messageKey.append("with-occupied-name"));
            return;
        }
    
        marksManager.setMark(markName, location);
        messagesSender.send(messageKey.append("completed"));
    }
}
*/