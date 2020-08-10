package top.nololiyt.bookstorage;

import com.sun.org.apache.bcel.internal.generic.FieldGenOrMethodGen;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import top.nololiyt.bookstorage.entitiesandtools.StringPair;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.print.Book;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BooksManager
{
    private RootPlugin rootPlugin;
    
    protected RootPlugin getRootPlugin()
    {
        return rootPlugin;
    }
    
    private File getDirectory()
    {
        File file = new File(rootPlugin.getDataFolder().getAbsolutePath(), "books");
        if ((!file.exists()) || (!file.isDirectory()))
        {
            file.mkdirs();
            rootPlugin.saveResource("books/example.yml", true);
        }
        return file;
    }
    
    public BooksManager(RootPlugin rootPlugin)
    {
        this.rootPlugin = rootPlugin;
        getDirectory();
    }
    
    public List<String> allBooks()
    {
        List<String> result = new ArrayList<>();
        for (String f : getDirectory().list())
        {
            if (f.endsWith(".yml"))
                result.add(f.substring(0, f.length() - 4));
        }
        return result;
    }
    
    public BookMeta get(String id)
    {
        File f = new File(getDirectory().getAbsolutePath(), id + ".yml");
        if(!f.exists())
            return null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(f);
        Object obj = configuration.get("meta");
        if(obj instanceof BookMeta)
            return (BookMeta)obj;
        
        BookMeta meta = (BookMeta)(new ItemStack(rootPlugin.getMaterialProvider().WrittenBook()).getItemMeta());
        meta.setTitle("Error!");
        meta.setAuthor("NBookStorage");
        meta.addPage("Something wrong!");
        return meta;
    }
    
    public boolean add(String id, ItemMeta meta) throws IOException
    {
        File f = new File(getDirectory().getAbsolutePath(), id + ".yml");
        if (f.exists())
            return false;
        override(f, meta);
        return true;
    }
    
    public void override(String id, ItemMeta meta) throws IOException
    {
        File f = new File(getDirectory().getAbsolutePath(), id + ".yml");
        override(f, meta);
    }
    
    private void override(File f, ItemMeta meta) throws IOException
    {
        YamlConfiguration configuration = new YamlConfiguration();
        configuration.set("meta", toBookMeta(meta));
        configuration.save(f);
    }
    public boolean remove(String id)
    {
        File f = new File(getDirectory().getAbsolutePath(), id + ".yml");
        return f.delete();
    }
    
    public BookMeta toBookMeta(ItemMeta meta)
    {
        if(meta == null)
        {
            return null;
        }
        BookMeta result;
        if(meta instanceof BookMeta)
        {
            result = (BookMeta)meta.clone();
        }
        else
        {
            ItemStack book = new ItemStack(rootPlugin.getMaterialProvider().WrittenBook());
            book.setItemMeta(meta);
            result = (BookMeta) book.getItemMeta();
        }
        if(!result.hasAuthor())
        {
            result.setAuthor("NBookStorage");
        }
        if(!result.hasTitle())
        {
            result.setTitle("DefaultTitle");
        }
        if(!result.hasPages())
        {
            result.setPages("Default Content");
        }
        return result;
    }
}
