package top.nololiyt.bookstorage.commands.executors.meta;

import org.bukkit.inventory.meta.BookMeta;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;
import top.nololiyt.bookstorage.entitiesandtools.MessagesSender;

import java.util.ArrayList;
import java.util.List;

public class AuthorExecutor extends MetaExecutor
{
    protected final static String layerName = "author";
    
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
                add("yueyinqiu5990");
                add("NBookStorage");
                add("Notch");
                add("Herobrine");
            }
        };
    }
    
    @Override
    protected BookMeta run(RootPlugin rootPlugin,
                          BookMeta meta,
                          String value)
    {
        meta.setAuthor(value);
        return meta;
    }
}