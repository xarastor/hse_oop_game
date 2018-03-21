import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

    public Inventory(Player player) {
        owner = player;
        EquippedItems = new int[Item.ItemType.values().length];
        for (int i = 0; i < EquippedItems.length; i++) {
            EquippedItems[i] = -1;
        }
    }
    Player owner;
    public ArrayList<Integer> items = new ArrayList<Integer>();

    public int[] EquippedItems; // Helmet, Weapon, Boots, Gloves, Armor, Pants

    public void pickup(Integer Id) {
        if (!items.contains(Id)) {
            items.add(Id);
        }
    }

    public void removeItemModifiers(Integer Id) {
        for (Modifier modifier : ItemStorage.Items.get(Id).Modifiers) {
            owner.removeModifierExternal(modifier);
        }
    }

    public void addItemModifiers(Integer Id) {
        for (Modifier modifier : ItemStorage.Items.get(Id).Modifiers) {
            owner.addModifier(modifier);
        }
    }

    public void Unequip(Integer index) {
        if (EquippedItems[index] >= 0) {
            removeItemModifiers(EquippedItems[index]);
            EquippedItems[index] = -1;
        }
    }

    public boolean Equip(Integer Id) {
        if (items.contains(Id)) {
            Item item = ItemStorage.Items.get(Id);
            int index = item.Type.ordinal();
            Unequip(index);
            EquippedItems[index] = Id;
            addItemModifiers(Id);
            return true;
        } else {
            return false;
        }
    }
}
