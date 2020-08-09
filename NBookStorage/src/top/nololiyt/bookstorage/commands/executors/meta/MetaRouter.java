package top.nololiyt.bookstorage.commands.executors.meta;

import top.nololiyt.bookstorage.commands.CommandLayer;
import top.nololiyt.bookstorage.commands.Router;
import top.nololiyt.bookstorage.commands.executors.version.CurrentExecutor;
import top.nololiyt.bookstorage.commands.executors.version.LatestExecutor;

import java.util.LinkedHashMap;
import java.util.Map;

public class MetaRouter extends Router
{
    protected final static String layerName = "meta";
    
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
            put("author", new AuthorExecutor());
            put("generation", new GenerationExecutor());
            put("title", new TitleExecutor());
        }
    };
    
    @Override
    protected Map<String, CommandLayer> nextLayers()
    {
        return commandLayers;
    }
}