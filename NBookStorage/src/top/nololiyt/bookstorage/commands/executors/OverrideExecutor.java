package top.nololiyt.bookstorage.commands.executors;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.nololiyt.bookstorage.BooksManager;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.commands.Executor;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;
import top.nololiyt.bookstorage.entitiesandtools.MessagesSender;
import top.nololiyt.bookstorage.entitiesandtools.StringPair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OverrideExecutor extends Executor
{
    protected final static String layerName = "override";
    
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
    public List<String> getTabComplete(RootPlugin rootPlugin, int ordinal)
    {
        switch (ordinal)
        {
            case 0:
                List<String> r = Arrays.asList(rootPlugin.getBooksManager().allBooks());
                if(r.isEmpty())
                    r.add("ANewBook");
                return r;
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
    
        if (!(commandSender instanceof Player))
        {
            messagesSender.send(messageKey.append("without-a-book"));
            return true;
        }
        Player player = (Player) commandSender;
        ItemStack book = player.getInventory().getItemInMainHand();
        if(book.getType() != Material.WRITABLE_BOOK &&
                book.getType() != Material.WRITTEN_BOOK)
        {
            messagesSender.send(messageKey.append("without-a-book"));
        }
        
        try
        {
            rootPlugin.getBooksManager().override(bookId, book.getItemMeta());
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