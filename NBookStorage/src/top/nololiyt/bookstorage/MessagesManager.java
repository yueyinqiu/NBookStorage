package top.nololiyt.bookstorage;

import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;
import top.nololiyt.bookstorage.entitiesandtools.StringPair;

import java.io.File;

public class MessagesManager
{
    public MessagesManager(RootPlugin rootPlugin)
    {
        this.rootPlugin = rootPlugin;
        reload();
    }
    
    private YamlConfiguration configuration;
    private YamlConfiguration getConfiguration()
    {
        return configuration;
    }
    
    private RootPlugin rootPlugin;
    
    String getFileName()
    {
        return "messages.yml";
    }
    
    public @NotNull
    String getItemAndShowError(DotDividedStringBuilder node)
    {
        String key = node.toString();
        String result = getConfiguration().getString(key);
        
        if (result == null)
        {
            rootPlugin.getLogger().severe(
                    "File 'messages.yml' is corrupted and '" + key
                            + "' is missing.");
            return key;
        }
        return result;
    }
    
    public void reload()
    {
        File file = new File(rootPlugin.getDataFolder().getAbsolutePath(), getFileName());
        if (!file.exists())
            rootPlugin.saveResource(getFileName(), false);
        configuration = YamlConfiguration.loadConfiguration(file);
    }
    
    public void sendMessage(CommandSender target, DotDividedStringBuilder messageKey, StringPair[] args)
    {
        String message = getItemAndShowError(messageKey);
        sendMessage(target, message, args);
    }
    
    public void sendMessage(CommandSender target, @NotNull String message, StringPair[] args)
    {
        String result = message.trim();
        if (result.isEmpty())
            return;
        
        result = ChatColor.translateAlternateColorCodes('&', result);
        for (StringPair pair : args)
        {
            result = result.replace(pair.getKey(), pair.getValue());
        }
        
        target.sendMessage(result);
    }
}