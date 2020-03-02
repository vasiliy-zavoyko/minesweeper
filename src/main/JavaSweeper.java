import sweeper.Box;
import sweeper.Coordinate;
import sweeper.Game;
import sweeper.Ranges;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class JavaSweeper extends JFrame {

    private Game game;

    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JPanel panel;

    private JavaSweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initPanel();
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coordinate coordinate : Ranges.getAllCoordinates()) {
                    g.drawImage((Image) game.getBox(coordinate).getImage(),
                            coordinate.x * IMAGE_SIZE,
                            coordinate.y * IMAGE_SIZE,
                            this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private Image getImage(String name) {
        String filename = "img/" + name + ".png";
        return new ImageIcon(getClass().getResource(filename)).getImage();
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.setImage(getImage(box.name().toLowerCase()));
        }
        setIconImage(getImage("icon"));
    }

}
