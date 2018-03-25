package Map;

import java.util.ArrayList;

public class Map {

    public ArrayList<ArrayList<Cell>> Cells;
    public int Width;
    public int Height;
    public int StartX;
    public int StartY;

    public Map(int width, int height) {
        Width = width;
        Height = height;
        Cells = new ArrayList<ArrayList<Cell>>();
        for (int i = 0; i < Height; ++i) {
            Cells.add(new ArrayList<Cell>());
        }
        StartX = width / 2;
        StartY = height / 2;
    }

}
