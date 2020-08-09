package top.nololiyt.bookstorage;

import com.sun.org.apache.bcel.internal.generic.FieldGenOrMethodGen;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;

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
        if (!file.exists())
        {
            file.mkdirs();
            rootPlugin.saveResource("books/example.yml", true);
        }
        return file;
    }
    
    public BooksManager(RootPlugin rootPlugin)
    {
        this.rootPlugin = rootPlugin;
    }
    
    public String[] allBooks()
    {
        return getDirectory().list();
    }
    
    public ItemMeta get(String id)
    {
        File f = new File(getDirectory().getAbsolutePath(), id + ".yml");
        if(!f.exists())
            return null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(f);
        Object obj = configuration.get("meta");
        if(obj instanceof ItemMeta)
            return (ItemMeta)obj;
        return new ItemStack(Material.WRITABLE_BOOK).getItemMeta();
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
        configuration.set("meta", meta);
        configuration.save(f);
    }
    public boolean remove(String id)
    {
        File f = new File(getDirectory().getAbsolutePath(), id + ".yml");
        return f.delete();
    }
}
