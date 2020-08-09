package top.nololiyt.bookstorage;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class RootPlugin extends JavaPlugin
{
    
    private MessagesManager messagesManager;
    
    public MessagesManager getMessagesManager()
    {
        return messagesManager;
    }
    
    private VersionManager versionManager;
    
    public VersionManager getVersionManager()
    {
        return versionManager;
    }
    private BooksManager booksManager;
    
    public BooksManager getBooksManager()
    {
        return booksManager;
    }
    private MaterialProvider materialProvider;
    
    public MaterialProvider getMaterialProvider()
    {
        return materialProvider;
    }
    
    @Override
    public void onEnable()
    {
        saveDefaultConfig();
    
        materialProvider = new MaterialProvider(this);
        booksManager = new BooksManager(this);
        messagesManager = new MessagesManager(this);
        versionManager = new VersionManager(this);
        
        getCommand("nbookstorage").setExecutor(new RootCommandExecutor(this));
        
    }
    public void Disable()
    {
        this.setEnabled(false);
    }
}