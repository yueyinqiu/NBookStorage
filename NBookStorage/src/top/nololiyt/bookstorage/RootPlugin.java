package top.nololiyt.bookstorage;

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
    
    
    @Override
    public void onEnable()
    {
        saveDefaultConfig();
    
        booksManager = new BooksManager(this);
        messagesManager = new MessagesManager(this);
        versionManager = new VersionManager(this);
        
        getCommand("nbookstorage").setExecutor(new RootCommandExecutor(this));
    }
}