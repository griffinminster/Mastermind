package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Drawing extends JComponent {
    private final Ellipse2D.Double[][] gameArr;
    private final Ellipse2D.Double[][] scoreArr;
    private final int[][] bigArr;
    private final int[][] smallArr;
    private boolean gameCalled;
    private boolean scoreCalled;
    private int row;
    private int col;
    private Color colr;

    public Drawing() {
        bigArr = new int[10][5];
        smallArr = new int[10][5];

        gameCalled = false;
        scoreCalled = false;

        row = -1;
        col = -1;
        gameArr = new Ellipse2D.Double[10][5];
        scoreArr = new Ellipse2D.Double[10][5];
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 5; c++) {
                gameArr[r][c] = new Ellipse2D.Double(240 + c * 50, 50 + r * 50, 20, 20);
                scoreArr[r][c] = new Ellipse2D.Double(490 + c * 15, 57 + r * 50, 7, 7);
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        instructions(g2);
        scoring(g2);

        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 5; c++) {
                g2.setColor(new Color(0, 0, 0));
                g2.draw(gameArr[r][c]);
                g2.draw(scoreArr[r][c]);

                if (bigArr[r][c] == 1) {
                    g2.setColor(new Color(252, 3, 223));
                    g2.fill(gameArr[r][c]);
                } else if (bigArr[r][c] == 2) {
                    g2.setColor(new Color(18, 179, 69));
                    g2.fill(gameArr[r][c]);
                } else if (bigArr[r][c] == 3) {
                    g2.setColor(new Color(251, 255, 0));
                    g2.fill(gameArr[r][c]);
                } else if (bigArr[r][c] == 4) {
                    g2.setColor(new Color(109, 19, 189));
                    g2.fill(gameArr[r][c]);
                } else if (bigArr[r][c] == 5) {
                    g2.setColor(new Color(255, 255, 255));
                    g2.fill(gameArr[r][c]);
                } else if (bigArr[r][c] == 6) {
                    g2.setColor(new Color(235, 151, 16));
                    g2.fill(gameArr[r][c]);
                }

                if (smallArr[r][c] == 7) {
                    g2.setColor(new Color(255, 0, 0));
                    g2.fill(scoreArr[r][c]);
                } else if (smallArr[r][c] == 5) {
                    g2.setColor(new Color(255, 255, 255));
                    g2.fill(scoreArr[r][c]);
                }
            }
        }
        if (gameCalled) {
            g2.setColor(colr);
            g2.fill(gameArr[row][col]);
            gameCalled = false;
        }
        if (scoreCalled) {
            g2.setColor(colr);
            g2.fill(scoreArr[row][col]);
            scoreCalled = false;
        }
    }

    public void fillGame(int roww, int coll, Color colrr) {
        gameCalled = true;
        colr = colrr;
        row = roww;
        col = coll;

        repaint();
    }

    public void fillScore(int roww, int coll, Color colrr) {
        scoreCalled = true;
        colr = colrr;
        row = roww;
        col = coll;

        repaint();
    }

    public void setBigArr(int num, int row, int col) {
        bigArr[row][col] = num;
    }

    public void setSmallArr(int num, int row, int col) {
        smallArr[row][col] = num;
    }

    public void instructions(Graphics2D g2) {
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g2.drawString("Welcome to MASTERMIND!", 270, 25);
        g2.drawString("INSTRUCTIONS", 40, 70);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g2.drawString("The computer has come up with", 10, 110);
        g2.drawString("a code, and you must break it!", 15, 130);
        g2.drawString("Using the buttons below, you", 17, 150);
        g2.drawString("have ten chances to crack", 30, 170);
        g2.drawString("the code!", 80, 190);
    }

    public void scoring(Graphics2D g2) {
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g2.drawString("SCORING", 65, 350);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g2.drawString("RED PEG: You have a correct", 15, 390);
        g2.drawString("colored peg in the", 60, 410);
        g2.drawString("correct place!", 73, 430);
        g2.drawString("WHITE PEG: You have a", 25, 460);
        g2.drawString("correct colored peg, but in the", 15, 480);
        g2.drawString("wrong place", 70, 500);
    }
}
