package TicTacToe;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public enum Seed {
    CROSS("X", "images/cat.gif"),   // Misalnya cat.gif untuk CROSS
    NOUGHT("O", "images/doggie.gif"), // Misalnya doggie.gif untuk NOUGHT
    NO_SEED(" ", null),
    EMPTY(" ", null);

    private String displayName;
    private Image img = null;

    // Constructor untuk menginisialisasi gambar
    private Seed(String name, String imageFilename) {
        this.displayName = name;

        if (imageFilename != null) {
            // Memuat gambar dengan URL
            URL imgURL = getClass().getClassLoader().getResource(imageFilename);
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                img = icon.getImage();
            } else {
                System.err.println("Couldn't find file " + imageFilename);
            }
        }
    }

    public String getDisplayName() {
        return displayName;
    }

    public Image getImage() {
        return img;
    }
}
