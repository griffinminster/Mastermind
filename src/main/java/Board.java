package main.java;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {
    private final Color PINK;
    private final Color GREEN;
    private final Color PURPLE;
    private final Color YELLOW;
    private final Color ORANGE;
    private final Color WHITE;
    private int curCol;
    private int curRow;

    JFrame f;

    private final JButton pink;
    private final JButton green;
    private final JButton yellow;
    private final JButton purple;
    private final JButton white;
    private final JButton orange;
    private final JButton submit;

    public Board() {
        PINK = new Color(252, 3, 223);
        GREEN = new Color(18, 179, 69);
        PURPLE = new Color(109, 19, 189);
        YELLOW = new Color(251, 255, 0);
        ORANGE = new Color(235, 151, 16);
        WHITE = new Color(255, 255, 255);

        curCol = 0;
        curRow = 0;

        f = new JFrame();
        f.setPreferredSize(new Dimension(800, 800));
        f.setTitle("MASTERMIND");
        f.setBackground(new Color(200, 200, 200));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pink = new JButton();
        buttonSetUp(pink, 100, PINK);

        green = new JButton();
        buttonSetUp(green, 200, GREEN);

        purple = new JButton();
        buttonSetUp(purple, 400, PURPLE);

        yellow = new JButton();
        buttonSetUp(yellow, 300, YELLOW);

        orange = new JButton();
        buttonSetUp(orange, 600, ORANGE);

        white = new JButton();
        buttonSetUp(white, 500, WHITE);

        submit = new JButton();
        submit.setBounds(325, 675, 100, 50);
        submit.setText("SUBMIT");
        submit.setBackground(new Color(125, 125, 125));
        f.add(submit);

        f.getRootPane().setDefaultButton(submit);
    }

    public JFrame getFrame() {
        return f;
    }

    public int getCurCol() {
        return curCol;
    }

    public void resetCurCol() {
        curCol = 0;
    }

    public void resetCurRow() { curRow = 0; }

    public void incCurRow() {
        curRow++;
    }

    public void incCurCol() {
        curCol++;
    }

    public int getCurRow() {
        return curRow;
    }

    public JButton pinkButt() {
        return pink;
    }

    public JButton greenButt() {
        return green;
    }

    public JButton yellowButt() {
        return yellow;
    }

    public JButton purpleButt() {
        return purple;
    }

    public JButton whiteButt() {
        return white;
    }

    public JButton orangeButt() {
        return orange;
    }

    public JButton submitButt() {
        return submit;
    }

    public Color pinkColr() {
        return PINK;
    }

    public Color greenColr() {
        return GREEN;
    }

    public Color yellowColr() {
        return YELLOW;
    }

    public Color purpleColr() {
        return PURPLE;
    }

    public Color whiteColr() {
        return WHITE;
    }

    public Color orangeColr() {
        return ORANGE;
    }

    public void buttonSetUp(JButton name, int x, Color colr) {
        name.setBounds(x, 600, 50, 50);
        name.setBackground(colr);
        name.setOpaque(true);
        name.setBorderPainted(false);
        f.add(name);
    }
}
