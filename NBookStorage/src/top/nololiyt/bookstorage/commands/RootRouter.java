package top.nololiyt.bookstorage.commands;

import org.bukkit.command.CommandSender;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.commands.executors.GetExecutor;
import top.nololiyt.bookstorage.commands.executors.ListExecutor;
import top.nololiyt.bookstorage.commands.executors.OverrideExecutor;
import top.nololiyt.bookstorage.commands.executors.RemoveExecutor;
import top.nololiyt.bookstorage.commands.executors.meta.MetaRouter;
import top.nololiyt.bookstorage.commands.executors.version.VersionRouter;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RootRouter extends Router
{
    
    private RootPlugin rootPlugin;
    
    public RootRouter(RootPlugin rootPlugin)
    {
        this.rootPlugin = rootPlugin;
    }
    
    
    public void routeCommand(CommandSender commandSender, String[] args)
    {
        DotDividedStringBuilder messagesRoot = new DotDividedStringBuilder("messages");
        DotDividedStringBuilder permissionRoot = new DotDividedStringBuilder("nbookstorage");
        
        execute(0, rootPlugin, permissionRoot, messagesRoot, commandSender, args);
    }
    public List<String> doTabComplete(CommandSender commandSender, String[] args)
    {
        DotDividedStringBuilder permissionRoot = new DotDividedStringBuilder("nbookstorage");
    
        return tabComplete(0, rootPlugin, permissionRoot, commandSender, args);
    }
    
    @Override
    public String permissionName()
    {
        return null;
    }
    
    @Override
    public String messageKey()
    {
        return null;
    }
    
    private Map<String, CommandLayer> commandLayers = new LinkedHashMap<String, CommandLayer>()
    {
        {
            put("override", new OverrideExecutor());
            put("list", new ListExecutor());
            put("remove", new RemoveExecutor());
            put("get", new GetExecutor());
            
            put("meta", new MetaRouter());
            put("version", new VersionRouter());
        }
    };
    
    @Override
    protected Map<String, CommandLayer> nextLayers()
    {
        return commandLayers;
    }
}