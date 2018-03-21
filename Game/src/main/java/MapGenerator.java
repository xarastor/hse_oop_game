import java.util.Random;

public class MapGenerator {

    public static double monsterProbablity = 0.75;
    public static double artefactProbability = 0.4;
    public static double distanceScaler = 0.1;

    public static Map generated;

    public static double GetDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static double GetDistanceToPlayerStartPoint(int x, int y) {
        return GetDistance(x, y, generated.StartX, generated.StartY);
    }

    public static Cell.CellType generateCellType() {
        Random random = new Random();
        double chance = random.nextDouble();
        if (chance > monsterProbablity) {
            return Cell.CellType.Monster;
        }
        if (chance > artefactProbability) {
            return Cell.CellType.Artefact;
        }
        return Cell.CellType.Empty;
    }

    public static Integer generateMonster(double distance) {
        Random random = new Random();
        double chance = random.nextDouble();
        int level = (int)(MonsterStorage.MaxLevel * (distance / distanceScaler + chance * 0.5));
        int AmountMonstersByLevel =  MonsterStorage.MonstersByLevel.get(level).size();
        int pickMonster = random.nextInt(AmountMonstersByLevel);
        return MonsterStorage.MonstersByLevel.get(level).get(pickMonster);
    }

    public static Integer generateItem(double distance) {
        Random random = new Random();
        double chance = random.nextDouble();
        Item.ItemType pickType = Item.ItemType.values()[random.nextInt(Item.ItemType.values().length)];
        int level = (int)(ItemStorage.MaxLevel * (distance / distanceScaler + chance * 0.5));
        int AmountItemsByLevel =  ItemStorage.ItemsByCategoryAndLevel.get(pickType).get(level).size();
        int pickItem = random.nextInt(AmountItemsByLevel);
        return ItemStorage.ItemsByCategoryAndLevel.get(pickType).get(level).get(pickItem);
    }

    public static Map GenerateMap(int width, int height) {
        generated = new Map(width, height);
        distanceScaler = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                generated.Cells.get(i).add(generateCell(i, j));
            }
        }
        return generated;
    }

    public static Cell generateCell(int x, int y) {
        Cell generated = new Cell(generateCellType(), x, y);
        switch (generated.Type) {
            case Monster: {
                generated.SetMonster(generateMonster(GetDistanceToPlayerStartPoint(x, y)));
                int amountItems = new Random().nextInt(4);
                for (int i = 0; i < amountItems; ++i) {
                    generated.AddArtifact(generateItem(GetDistanceToPlayerStartPoint(x, y)));
                }
                break;
            }
            case Artefact: {
                int amountItems = new Random().nextInt(4);
                for (int i = 0; i < amountItems; ++i) {
                    generated.AddArtifact(generateItem(GetDistanceToPlayerStartPoint(x, y)));
                }
                break;
            }
            default: {
                break;
            }
        }
        return generated;
    }
}
