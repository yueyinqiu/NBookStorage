package top.nololiyt.bookstorage.commands;

import org.bukkit.command.CommandSender;
import top.nololiyt.bookstorage.RootPlugin;
import top.nololiyt.bookstorage.entitiesandtools.DotDividedStringBuilder;

import java.util.List;

public interface CommandLayer
{
    String permissionName();
    
    String messageKey();
    
    void execute(int layer,
                 RootPlugin rootPlugin,
                 DotDividedStringBuilder permission,
                 DotDividedStringBuilder messageKey,
                 CommandSender commandSender,
                 String[] args);
    
    List<String> tabComplete(int layer,
                             RootPlugin rootPlugin,
                             DotDividedStringBuilder permission,
                             CommandSender commandSender,
                             String[] args);
}