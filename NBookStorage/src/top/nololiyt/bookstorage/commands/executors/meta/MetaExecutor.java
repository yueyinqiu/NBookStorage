package top.nololiyt.bookstorage.commands.executors.meta;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.commands.Executor;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;
import top.nololiyt.bookstorage.entitiesandtools.MessagesSender;
import top.nololiyt.bookstorage.entitiesandtools.StringPair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class MetaExecutor extends Executor
{
    
    protected abstract String getMetaKey();
    
    @Override
    public String permissionName()
    {
        return null;
    }
    
    @Override
    public String messageKey()
    {
        return getMetaKey();
    }
    
    protected abstract List<String> getTabValues(RootPlugin rootPlugin);
    
    @Override
    public List<String> getTabComplete(RootPlugin rootPlugin, int ordinal)
    {
        switch (ordinal)
        {
            case 0:
                return rootPlugin.getBooksManager().allBooks();
            case 1:
                return getTabValues(rootPlugin);
            default:
                return new ArrayList<>();
        }
    }
    
    protected abstract BookMeta run(RootPlugin rootPlugin,
                                    BookMeta meta,
                                    String value);
    
    @Override
    protected boolean run(int layer, RootPlugin rootPlugin,
                          DotDividedStringBuilder messageKey, CommandSender commandSender,
                          String[] args)
    {
        if (args.length - 1 != layer + 1)
            return false;
    
        String bookId = args[layer];
        String metaValue = args[layer + 1];
        MessagesSender messagesSender = new MessagesSender(rootPlugin.getMessagesManager(),
                commandSender, new StringPair[]{
                StringPair.bookId(bookId),
                StringPair.senderName(commandSender.getName()),
                StringPair.metaValue(metaValue)
        });
        
        BookMeta meta = rootPlugin.getBooksManager().get(bookId);
        if (meta == null)
        {
            messagesSender.send(messageKey.append("no-such-book"));
            return true;
        }
        BookMeta r = run(rootPlugin, meta, metaValue);
        if (r == null)
            return false;
        
        try
        {
            rootPlugin.getBooksManager().override(bookId, r);
            messagesSender.send(messageKey.append("completed"));
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            messagesSender.send(messageKey.append("failed"));
        }
        return true;
    }
}