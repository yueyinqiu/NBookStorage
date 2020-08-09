package top.nololiyt.bookstorage.commands.executors;

import org.bukkit.command.CommandSender;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;
import top.nololiyt.bookstorage.entitiesandtools.MessagesSender;
import top.nololiyt.bookstorage.entitiesandtools.StringPair;
import top.nololiyt.bookstorage.commands.Executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveExecutor extends Executor
{
    protected final static String layerName = "remove";
    
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
    public List<String> getTabComplete(RootPlugin rootPlugin, int ordinal)
    {
        switch (ordinal)
        {
            case 0:
                return Arrays.asList(rootPlugin.getBooksManager().allBooks());
            default:
                return new ArrayList<>();
        }
    }
    
    @Override
    protected boolean run(int layer, RootPlugin rootPlugin,
                          DotDividedStringBuilder messageKey, CommandSender commandSender,
                          String[] args)
    {
        if (args.length - 1 != layer)
            return false;
    
        String bookId = args[layer];
        MessagesSender messagesSender = new MessagesSender(rootPlugin.getMessagesManager(),
                commandSender, new StringPair[]{
                StringPair.bookId(bookId),
                StringPair.senderName(commandSender.getName())
        });
    
        if (rootPlugin.getBooksManager().remove(bookId))
        {
            messagesSender.send(messageKey.append("completed"));
            return true;
        }
        messagesSender.send(messageKey.append("no-such-book"));
        return true;
    }
}