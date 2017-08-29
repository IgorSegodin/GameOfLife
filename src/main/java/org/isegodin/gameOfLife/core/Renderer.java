package org.isegodin.gameOfLife.core;

/**
 * @author isegodin
 */
public class Renderer {

    public static void render(Field field, int width, int height) {
        System.out.println();
        for (int i = 0; i < width + 2; i++) {
            System.out.print("*");
        }

        for (int y = 0; y < height; y++) {
            System.out.println();
            System.out.print("*");
            for (int x = 0; x < width; x++) {
                CellState cellType = field.get(x, y);
                Character ch;
                switch (cellType) {
                    case DEAD: ch = ' '; break;
                    case ALIVE: ch = 'o'; break;
                    default: throw new RuntimeException("Unsupported cell type: " + cellType.name());
                }
                System.out.print(ch);
            }
            System.out.print("*");
        }

        System.out.println();

        for (int i = 0; i < width + 2; i++) {
            System.out.print("*");
        }
        System.out.println();
    }


}
