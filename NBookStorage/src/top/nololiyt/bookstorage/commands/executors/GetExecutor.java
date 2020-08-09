package top.nololiyt.bookstorage.commands.executors;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.commands.Executor;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;
import top.nololiyt.bookstorage.entitiesandtools.MessagesSender;
import top.nololiyt.bookstorage.entitiesandtools.StringPair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetExecutor extends Executor
{
    protected final static String layerName = "get";
    
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
                return rootPlugin.getBooksManager().allBooks();
            case 1:
                return new ArrayList<String>()
                {
                    {
                        add(rootPlugin.getMaterialProvider().WritableBook().toString());
                        add(rootPlugin.getMaterialProvider().WrittenBook().toString());
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
        if (args.length - 1 != layer + 1)
            return false;
        Material material = Material.getMaterial(args[layer + 1]);
        if (material == null || (material != rootPlugin.getMaterialProvider().WritableBook()
                && material != rootPlugin.getMaterialProvider().WrittenBook()))
        {
            return false;
        }
    
        String bookId = args[layer];
        MessagesSender messagesSender = new MessagesSender(rootPlugin.getMessagesManager(),
                commandSender, new StringPair[]{
                StringPair.bookId(bookId),
                StringPair.senderName(commandSender.getName()),
                StringPair.material(args[layer + 1])
        });
        if (!(commandSender instanceof Player))
        {
            messagesSender.send(messageKey.append("not-a-player"));
            return true;
        }
        
        ItemMeta meta = rootPlugin.getBooksManager().get(bookId);
        if (meta == null)
        {
            messagesSender.send(messageKey.append("not-such-book"));
            return true;
        }
    
        Player player = (Player) commandSender;
        ItemStack book = new ItemStack(material);
        book.setItemMeta(meta);
        player.getInventory().addItem(book);
        messagesSender.send(messageKey.append("completed"));
        return true;
    }
}