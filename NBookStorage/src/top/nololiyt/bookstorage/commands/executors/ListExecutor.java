package top.nololiyt.bookstorage.commands.executors;

import org.bukkit.command.CommandSender;
import top.nololiyt.bookstorage.MessagesManager;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.commands.Executor;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;
import top.nololiyt.bookstorage.entitiesandtools.MessagesSender;
import top.nololiyt.bookstorage.entitiesandtools.StringPair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListExecutor extends Executor
{
    protected final static String layerName = "list";
    
    @Override
    public String permissionName()
    {
        return layerName;
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
        MessagesManager messagesManager = rootPlugin.getMessagesManager();
        MessagesSender messagesSender = new MessagesSender(messagesManager,
                commandSender, new StringPair[]{
                StringPair.senderName(commandSender.getName())
        });
    
        List<String> books = rootPlugin.getBooksManager().allBooks();
        if (books.isEmpty())
        {
            messagesSender.send(messageKey.append("no-book"));
            return true;
        }
        messageKey.append("list");
    
        String beginning = getMessageItem(messagesManager, messageKey, "beginning");
        String separator = getMessageItem(messagesManager, messageKey, "separator");
        String ending = getMessageItem(messagesManager, messageKey, "ending");
        messagesSender.sendJointed(beginning, ending, separator, books);
        return true;
    }
    
    private String getMessageItem(MessagesManager messagesManager,
                                  DotDividedStringBuilder messageKey, String nodeName)
    {
        return messagesManager.getItemAndShowError(
                new DotDividedStringBuilder(messageKey).append(nodeName));
    }
}