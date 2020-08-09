package top.nololiyt.bookstorage;

import org.bukkit.Material;

public class MaterialProvider
{
    public MaterialProvider(RootPlugin rootPlugin)
    {
        writableBook = Material.getMaterial("WRITABLE_BOOK");
        if (writableBook == null)
            writableBook = Material.getMaterial("BOOK_AND_QUILL");
        if (writableBook == null)
        {
            Disable(rootPlugin);
            return;
        }
    
        writtenBook = Material.getMaterial("WRITTEN_BOOK");
        if (writtenBook == null)
        {
            Disable(rootPlugin);
            return;
        }
    }
    private void Disable(RootPlugin rootPlugin)
    {
        rootPlugin.getLogger().severe("Your game version may be unsupported.");
        rootPlugin.Disable();
    }
    
    private Material writtenBook;
    
    public Material WrittenBook()
    {
        return writtenBook;
    }
    private Material writableBook;
    
    public Material WritableBook()
    {
        return writableBook;
    }
}
