package TicTacToe;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class Cell {
    // Define named constants for drawing
    public static final int SIZE = 120; // cell width/height (square)
    // Symbols (cross/nought) are displayed inside a cell, with padding from border
    public static final int PADDING = SIZE / 5;
    public static final int SEED_SIZE = SIZE - PADDING * 2;

    // Define properties (package-visible)
    /** Content of this cell (Seed.EMPTY, Seed.CROSS, or Seed.NOUGHT) */
    Seed content;
    /** Row and column of this cell */
    int row, col;

    // Static icons for cross and nought
    private static final ImageIcon CROSS_ICON = new ImageIcon(Cell.class.getResource("/TicTacToe/cat.gif"));
    private static final ImageIcon NOUGHT_ICON = new ImageIcon(Cell.class.getResource("/TicTacToe/doggie.gif"));

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.content = Seed.NO_SEED; // default empty seed
    }

    public void newGame() {
        content = Seed.NO_SEED;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(8.0F, 1, 1));

        int cellSize = SIZE; // Size of the cell
        int padding = PADDING; // Padding around the symbol
        int x1 = this.col * cellSize + padding;
        int y1 = this.row * cellSize + padding;
        int imageSize = cellSize - 2 * padding; // Size for the image

        if (this.content == Seed.CROSS) {
            g.drawImage(CROSS_ICON.getImage(), x1, y1, imageSize, imageSize, null);
        } else if (this.content == Seed.NOUGHT) {
            g.drawImage(NOUGHT_ICON.getImage(), x1, y1, imageSize, imageSize, null);
        }
    }
}
