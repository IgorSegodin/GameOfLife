package org.isegodin.gameOfLife.core;

/**
 * @author i.segodin
 */
public class Field {

    private final CellState[] data;
    private final int width;
    private final int height;
    private final long timestamp = System.currentTimeMillis();

    public Field(CellState[] data, int width, int height) {
        this.data = data;
        this.width = width;
        this.height = height;
    }

    public CellState get(int x, int y) {
        return Array2DUtil.get(x, y, data, width);
    }

    public boolean isAlive(int x, int y) {
        return CellState.ALIVE.equals(get(x, y));
    }

    public CellState[] copyData() {
        CellState[] result = new CellState[data.length];
        System.arraycopy(data, 0, result, 0, data.length);
        return result;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
