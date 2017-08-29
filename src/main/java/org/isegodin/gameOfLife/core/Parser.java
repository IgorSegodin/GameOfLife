package org.isegodin.gameOfLife.core;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author isegodin
 */
public class Parser {

    public static Field parse(InputStream inputStream, int width, int height) {
        Scanner scanner = new Scanner(inputStream);

        CellState[] fieldData = new CellState[width * height];

        int y = -1;
        while (scanner.hasNextLine()) {
            y++;
            String line = scanner.nextLine();
            parseLine(line, y, fieldData, width);
        }
        while (y < height - 1) {
            y++;
            parseLine("", y, fieldData, width);
        }

        return new Field(fieldData, width, height);
    }

    private static void parseLine(String line, int y, CellState[] fieldData, int width) {
        int x = -1;
        for (char c : line.toCharArray()) {
            x++;
            CellState type;
            switch (c) {
                case ' ':
                    type = CellState.DEAD;
                    break;
                case '1':
                    type = CellState.ALIVE;
                    break;
                default:
                    throw new RuntimeException("Unexpected char: " + c);
            }
            Array2DUtil.set(type, x, y, fieldData, width);
        }
        while (x < width - 1) {
            x++;
            Array2DUtil.set(CellState.DEAD, x, y, fieldData, width);
        }
    }

}
