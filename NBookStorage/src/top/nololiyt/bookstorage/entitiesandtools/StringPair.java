package top.nololiyt.bookstorage.entitiesandtools;

public class StringPair
{
    private String key;
    
    public String getKey()
    {
        return key;
    }
    
    private String value;
    
    public String getValue()
    {
        return value;
    }
    
    private StringPair(String key, String value)
    {
        this.key = key;
        this.value = value;
    }
    
    public static StringPair senderName(String value)
    {
        return new StringPair("{senderName}", value);
    }
    
    public static StringPair bookId(String value)
    {
        return new StringPair("{bookId}", value);
    }
    public static StringPair version(String value)
    {
        return new StringPair("{version}", value);
    }
    
    public static StringPair time(String value)
    {
        return new StringPair("{time}", value);
    }
}