package TicTacToe;

import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;

public class Board {
    // Define named constants
    public static final int ROWS = 3;  // ROWS x COLS cells
    public static final int COLS = 3;
    public static final int CANVAS_WIDTH = Cell.SIZE * COLS; // the drawing canvas
    public static final int CANVAS_HEIGHT = Cell.SIZE * ROWS;
    public static final int GRID_WIDTH = 8; // Grid-line's width
    public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2; // Grid-line's half-width
    public static final Color COLOR_GRID = new Color(220, 193, 190);  // grid lines
    private Image backgroundImage;

    Cell[][] cells; // Composes of 2D array of ROWS-by-COLS Cell instances

    public Board() {
        initGame();
        loadImage(); //load background pada board
    }

    public void initGame() {
        cells = new Cell[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public void loadImage() {
        // Memuat gambar background dari resource
        URL imgURL = getClass().getClassLoader().getResource("TicTacToe/background.jpg"); // Pastikan background.jpg ada di folder resources
        if (imgURL != null) {
            ImageIcon backgroundIcon = new ImageIcon(imgURL);
            backgroundImage = backgroundIcon.getImage();
        } else {
            System.err.println("Couldn't find background image.");
        }
    }

    public void newGame() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].content = Seed.NO_SEED;
            }
        }
    }

    public State stepGame(Seed player, int selectedRow, int selectedCol) {
        cells[selectedRow][selectedCol].content = player;

        if (hasWon(player, selectedRow, selectedCol)) {
            return (player == Seed.CROSS) ? State.CROSS_WON : State.NOUGHT_WON;
        }

        // Check for DRAW
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (cells[row][col].content == Seed.NO_SEED) {
                    return State.PLAYING;
                }
            }
        }
        return State.DRAW;
    }

    public boolean hasWon(Seed theSeed, int rowSelected, int colSelected) {
        // Check the row
        int count = 0;
        for (int col = 0; col < COLS; ++col) {
            if (cells[rowSelected][col].content == theSeed) {
                ++count;
                if (count == 3) return true;
            } else {
                count = 0;
            }
        }

        // Check the column
        count = 0;
        for (int row = 0; row < ROWS; ++row) {
            if (cells[row][colSelected].content == theSeed) {
                ++count;
                if (count == 3) return true;
            } else {
                count = 0;
            }
        }

        // Check diagonal (↘)
        count = 0;
        for (int offset = -3; offset <= 3; ++offset) {
            int row = rowSelected + offset;
            int col = colSelected + offset;
            if (row >= 0 && row < ROWS && col >= 0 && col < COLS &&
                    cells[row][col].content == theSeed) {
                ++count;
                if (count == 3) return true;
            } else {
                count = 0;
            }
        }

        // Check secondary diagonal (↙)
        count = 0;
        for (int offset = -3; offset <= 3; ++offset) {
            int row = rowSelected + offset;
            int col = colSelected - offset;
            if (row >= 0 && row < ROWS && col >= 0 && col < COLS &&
                    cells[row][col].content == theSeed) {
                ++count;
                if (count == 3) return true;
            } else {
                count = 0;
            }
        }

        return false;
    }

    public void paint(Graphics g) {
        // Background
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, CANVAS_WIDTH, CANVAS_HEIGHT, null);
        }

        g.setColor(COLOR_GRID);
        for (int row = 1; row < ROWS; ++row) {
            g.fillRoundRect(0, Cell.SIZE * row - GRID_WIDTH_HALF,
                    CANVAS_WIDTH - 1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
        }
        for (int col = 1; col < COLS; ++col) {
            g.fillRoundRect(Cell.SIZE * col - GRID_WIDTH_HALF, 0,
                    GRID_WIDTH, CANVAS_HEIGHT - 1, GRID_WIDTH, GRID_WIDTH);
        }

        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].paint(g);
            }
        }
    }
}
