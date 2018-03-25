package Map;

import java.util.ArrayList;
import Character.Monster;

public class Cell {

    public CellType Type;
    public boolean isRevealed = false;
    public int MonsterId = -1;
    public ArrayList<Integer> Artifacts = new ArrayList<Integer>();
    public int X;
    public int Y;

    public Cell(CellType type, int x, int y) {
        Type = type;
        X = x;
        Y = y;
    }
    public void AddArtifact(Item.Item item) {
        Artifacts.add(item.Id);
    }

    public void SetMonster(Monster monster) {
        MonsterId = monster.Id;
    }

    public void AddArtifact(Integer itemId) {
        Artifacts.add(itemId);
    }

    public void SetMonster(Integer monsterId) {
        MonsterId = monsterId;
    }
}
