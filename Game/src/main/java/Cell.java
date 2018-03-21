import java.util.ArrayList;

public class Cell {
    public enum CellType {
        Empty,
        Monster,
        Artifact
    }
    public CellType Type;
    public boolean isRevealed = false;
    int MonsterId = -1;
    ArrayList<Integer> Artefacts = new ArrayList<Integer>();
    public int X;
    public int Y;

    public Cell(CellType type, int x, int y) {
        Type = type;
        X = x;
        Y = y;
    }
    public void AddArtifact(Item item) {
        Artefacts.add(item.Id);
    }

    public void SetMonster(Monster monster) {
        MonsterId = monster.Id;
    }

    public void AddArtifact(Integer itemId) {
        Artefacts.add(itemId);
    }

    public void SetMonster(Integer monsterId) {
        MonsterId = monsterId;
    }
}
