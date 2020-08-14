package top.nololiyt.bookstorage.commands;

import org.bukkit.command.CommandSender;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;
import top.nololiyt.bookstorage.entitiesandtools.StringPair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class Executor implements CommandLayer
{
    protected abstract boolean run(int layer,
                                   RootPlugin rootPlugin,
                                   DotDividedStringBuilder messageKey,
                                   CommandSender commandSender,
                                   String[] args);
    
    public void execute(int layer,
                        RootPlugin rootPlugin,
                        DotDividedStringBuilder permission,
                        DotDividedStringBuilder messageKey,
                        CommandSender commandSender,
                        String[] args)
    {
        if (permissionName() != null)
        {
            permission.append(permissionName());
            if (!commandSender.hasPermission(permission.toString()))
            {
                return;
            }
        }
        DotDividedStringBuilder messageKeyCopy = new DotDividedStringBuilder(messageKey);
        
        if (messageKey() != null)
            messageKey.append(messageKey());
    
        if (!run(layer, rootPlugin, messageKey, commandSender, args))
        {
            sendHelp(messageKeyCopy, rootPlugin, commandSender);
        }
    }
    
    /**
     *
     * @param rootPlugin
     * @param ordinal 0 means the first param, 1 means the second param .....
     * @return
     */
    public abstract List<String> getTabComplete(RootPlugin rootPlugin, int ordinal);
    
    @Override
    public List<String> tabComplete(int layer, RootPlugin rootPlugin,
                                    DotDividedStringBuilder permission,
                                    CommandSender commandSender, String[] args)
    {
        if (permissionName() != null)
        {
            permission.append(permissionName());
            if (!commandSender.hasPermission(permission.toString()))
            {
                return null;
            }
        }
        List<String> result = getTabComplete(rootPlugin,args.length - (layer + 1));
    
        List<String> realResult = new ArrayList<>();
        for (String str : result)
        {
            if(str.startsWith(args[args.length - 1]))
                realResult.add(str);
        }
        return realResult;
    }
    
    protected boolean sendHelp(DotDividedStringBuilder messageKey,
                               RootPlugin rootPlugin, CommandSender commandSender)
    {
        messageKey.append("help");
        StringPair[] pairs = new StringPair[]{
                StringPair.senderName(commandSender.getName())
        };
    
        rootPlugin.getMessagesManager().sendMessage(commandSender, messageKey, pairs);
        return true;
    }
}
