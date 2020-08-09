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
import java.util.*;

public class GenerationExecutor extends MetaExecutor
{
    protected final static String layerName = "generation";
    
    @Override
    protected String getMetaKey()
    {
        return layerName;
    }
    
    @Override
    protected List<String> getTabValues(RootPlugin rootPlugin)
    {
        return new ArrayList<String>()
        {
            {
                add("0");
                add("1");
                add("2");
                add("3");
            }
        };
    }
    
    @Override
    protected BookMeta run(RootPlugin rootPlugin,
                          BookMeta meta,
                          String value)
    {
        try
        {
            switch (Integer.parseInt(value))
            {
                case 0:
                    meta.setGeneration(BookMeta.Generation.ORIGINAL);
                    break;
                case 1:
                    meta.setGeneration(BookMeta.Generation.COPY_OF_ORIGINAL);
                    break;
                case 2:
                    meta.setGeneration(BookMeta.Generation.COPY_OF_COPY);
                    break;
                default:
                    meta.setGeneration(BookMeta.Generation.TATTERED);
                    break;
            }
            return meta;
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}