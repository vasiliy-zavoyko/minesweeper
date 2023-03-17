package ru.zavoyko.sweeper.sweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.google.common.io.Resources.getResource;

public class JavaSweeper extends JFrame {
    private static final int IMAGE_SIZE = 50;

    private Game game;

    private JPanel panel;
    private JLabel label;

    public JavaSweeper(int colsToSet, int rowsToSet, int bombsToSet) {
        game = new Game(colsToSet, rowsToSet, bombsToSet);
        game.start();
        setImages();
        initPanel();
        initLabel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel(getMsg());
        final var font = new Font("Tahoma", Font.BOLD, 20);
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
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                for (Coordinate coordinate : Ranges.getAllCoordinates()) {
                    final var image = (Image) game.getBox(coordinate).getImage();
                    graphics.drawImage(
                            image, coordinate.x() * IMAGE_SIZE, coordinate.y() * IMAGE_SIZE, this
                    );
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                int x = event.getX() / IMAGE_SIZE;
                int y = event.getY() / IMAGE_SIZE;
                final var coordinate = new Coordinate(x, y);
                switch (event.getButton()) {
                    case MouseEvent.BUTTON1 -> game.pressLeftButton(coordinate);
                    case MouseEvent.BUTTON3 -> game.pressRightButton(coordinate);
                    case MouseEvent.BUTTON2 -> game.start();
                }
                label.setText(getMsg());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(Ranges.getSize().x() * IMAGE_SIZE, Ranges.getSize().y() * IMAGE_SIZE));
        add(panel);
    }

    private Image getImage(String name) {
        final var filename = name + ".png";
        final var uri = getResource(filename);
        return new ImageIcon(uri).getImage();
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.setImage(getImage(box.name().toLowerCase()));
        }
        setIconImage(getImage("icon"));
    }

    private String getMsg() {
        return switch (game.getState()) {
            case BOMBED -> "Ba-Da-Boo!";
            case WINNER -> "Congratulations!";
            case PLAYED -> {
                if (game.getTotalFlagged() == 0) {
                    yield "Welcome!";
                } else {
                    yield "Think twice! Flagged " + game.getTotalFlagged() + " of " + game.getTotalBombs();
                }
            }
        };
    }

}
