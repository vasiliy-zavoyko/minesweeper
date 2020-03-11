import sweeper.Box;
import sweeper.Coordinate;
import sweeper.Game;
import sweeper.Ranges;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class JavaSweeper extends JFrame {

    private Game game;

    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 3;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JPanel panel;
    private JLabel label;

    private JavaSweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initPanel();
        initLabel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel(getMsg());
        Font font = new Font("Tahoma", Font.BOLD, 20);
        label.setFont(font);
        add(label, BorderLayout.SOUTH);
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
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coordinate coordinate = new Coordinate(x,y);
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1 : game.pressLeftButton(coordinate); break;
                    case MouseEvent.BUTTON3 : game.pressRightButton(coordinate); break;
                    case MouseEvent.BUTTON2 : game.start();
                }
                label.setText(getMsg());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private Image getImage(String name) {
        String filename = "img/" + name + ".png";
        return new ImageIcon(getClass().getResource(filename)).getImage();
    }

    private void setImages() {
        for (Box box : Box.values())
            box.setImage(getImage(box.name().toLowerCase()));

        setIconImage(getImage("icon"));
    }

    private String getMsg() {
        switch (game.getState()) {
            case BOMBED: return "Ba-Da-Boo!";
            case WINNER: return "Congratulations!";
            case PLAYED:
            default:
                    if (game.getTotalFlagged() == 0) {
                        return "Welcome!";
                    } else {
                        return "Think twice! Flagged " + game.getTotalFlagged() + " of " + game.getTotalBombs();
                    }
        }
    }

}
