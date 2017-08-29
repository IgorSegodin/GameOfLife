package org.isegodin.gameOfLife.core;

import java.util.Arrays;
import java.util.List;

/**
 * @author isegodin
 */
public class LifeSimulator {

    private static final List<Rule> rules = Arrays.asList(
            /** Any live cell with fewer than two live neighbours dies, as if caused by underpopulation. */
            new Rule() {
                @Override
                public boolean isCanApply(Cell cell) {
                    return CellState.ALIVE.equals(cell.getState()) && cell.getNeighbourCount() < 2;
                }

                @Override
                public CellState getNewState(Cell cell) {
                    return CellState.DEAD;
                }
            },
            /** Any live cell with two or three live neighbours lives on to the next generation. */
            new Rule() {
                @Override
                public boolean isCanApply(Cell cell) {
                    return CellState.ALIVE.equals(cell.getState()) &&
                            (cell.getNeighbourCount() == 2 || cell.getNeighbourCount() == 3);
                }

                @Override
                public CellState getNewState(Cell cell) {
                    return CellState.ALIVE;
                }
            },
            /** Any live cell with more than three live neighbours dies, as if by overpopulation. */
            new Rule() {
                @Override
                public boolean isCanApply(Cell cell) {
                    return CellState.ALIVE.equals(cell.getState()) && cell.getNeighbourCount() > 3;
                }

                @Override
                public CellState getNewState(Cell cell) {
                    return CellState.DEAD;
                }
            },
            /** Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction. */
            new Rule() {
                @Override
                public boolean isCanApply(Cell cell) {
                    return CellState.DEAD.equals(cell.getState()) && cell.getNeighbourCount() == 3;
                }

                @Override
                public CellState getNewState(Cell cell) {
                    return CellState.ALIVE;
                }
            }
    );

    public static Field simulate(Field input) {
        CellState[] data = input.copyData();
        boolean changed = false;
        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                Cell cell = new Cell(input.get(x, y), getCellAliveNeighbourCount(x, y, input));
                applyRules: {
                    for (Rule rule : rules) {
                        if (rule.isCanApply(cell)) {
                            CellState newState = rule.getNewState(cell);
                            if (!cell.getState().equals(newState)) {
                                Array2DUtil.set(newState, x, y, data, input.getWidth());
                                changed = true;
                            }
                            break applyRules;
                        }
                    }
                }
            }
        }

        return changed ? new Field(data, input.getWidth(), input.getHeight()) : input;
    }

    private static int getCellAliveNeighbourCount(int x, int y, Field field) {
        int count = 0;

        int x1 = x - 1 >= 0 ? x - 1 : field.getWidth() - 1;
        int x3 = x + 1 <= field.getWidth() - 1 ? x + 1 : 0;

        int y1 = y - 1 >= 0 ? y - 1 : field.getHeight() - 1;
        int y3 = y + 1 <= field.getHeight() - 1 ? y + 1 : 0;

        if (field.isAlive(x1, y1)) count++;
        if (field.isAlive(x, y1)) count++;
        if (field.isAlive(x3, y1)) count++;
        if (field.isAlive(x1, y)) count++;
        if (field.isAlive(x3, y)) count++;
        if (field.isAlive(x1, y3)) count++;
        if (field.isAlive(x, y3)) count++;
        if (field.isAlive(x3, y3)) count++;

        return count;
    }

    private static class Cell {
        private CellState state;
        private int neighbourCount;

        public Cell(CellState state, int neighbourCount) {
            this.state = state;
            this.neighbourCount = neighbourCount;
        }

        int getNeighbourCount() {
            return neighbourCount;
        }

        public CellState getState() {
            return state;
        }
    }

    private interface Rule {

        boolean isCanApply(Cell cell);

        CellState getNewState(Cell cell);
    }
}
