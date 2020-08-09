package top.nololiyt.bookstorage.commands.executors.version;

import top.nololiyt.bookstorage.commands.CommandLayer;
import top.nololiyt.bookstorage.commands.Router;

import java.util.LinkedHashMap;
import java.util.Map;

public class VersionRouter extends Router
{
    protected final static String layerName = "version";
    
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
    
    private Map<String, CommandLayer> commandLayers = new LinkedHashMap<String, CommandLayer>()
    {
        {
            put("latest", new LatestExecutor());
            put("current", new CurrentExecutor());
        }
    };
    
    @Override
    protected Map<String, CommandLayer> nextLayers()
    {
        return commandLayers;
    }
}