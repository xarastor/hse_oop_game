import java.util.ArrayList;

/**
 * Created by Titaninus on 15.03.2018.
 */
public class Item extends IInventorable {

    public enum ItemType {
        Helmet,
        Weapon,
        Boots,
        Gloves,
        Armor,
        Pants
    }

    public ArrayList<Modifier> Modifiers;
    ItemType Type;
    public String Name;
    public int Id;
    public int ItemLevel;

    public Item(String name, int id, int level, ItemType type, ArrayList<Modifier> description) {
        Modifiers = description;
        Name = name;
        Id = id;
        Type = type;
        ItemLevel = level;
    }

    @Override
    public void pickup() {

    }
}
