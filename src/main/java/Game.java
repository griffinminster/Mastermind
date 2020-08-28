package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Game {
    private static final int PINK = 1;
    private static final int GREEN = 2;
    private static final int YELLOW = 3;
    private static final int PURPLE = 4;
    private static final int WHITE = 5;
    private static final int ORANGE = 6;
    private static final int RED = 7;

    private final Drawing d;
    private final Board b;

    private final int[] solution;
    private int[] guess;
    private int timesCalled;
    private int turnCount;
    private int redPeg;
    private int whitePeg;
    private int filler;

    private final Color REDpeg;
    private final Color WHITEpeg;

    private int solPinkCount;
    private int solGreenCount;
    private int solYellowCount;
    private int solPurpleCount;
    private int solOrangeCount;
    private int solWhiteCount;

    private int guessPinkCount;
    private int guessGreenCount;
    private int guessYellowCount;
    private int guessPurpleCount;
    private int guessOrangeCount;
    private int guessWhiteCount;

    private boolean playAgain;

    public Game() {
        playAgain = false;

        filler = 0;
        solPinkCount = 0;
        solGreenCount = 0;
        solYellowCount = 0;
        solPurpleCount = 0;
        solOrangeCount = 0;
        solWhiteCount = 0;

        guessPinkCount = 0;
        guessGreenCount = 0;
        guessYellowCount = 0;
        guessPurpleCount = 0;
        guessOrangeCount = 0;
        guessWhiteCount = 0;

        solution = new int[5];
        guess = new int[5];
//        solution[0] = 1;
//        solution[1] = 2;
//        solution[2] = 3;
//        solution[3] = 4;
//        solution[4] = 5;
        for (int i = 0; i < solution.length; i++) {
            solution[i] = (int) (Math.random() * 6) + 1;
            if (solution[i] == PINK) {
                solPinkCount++;
            } else if (solution[i] == GREEN) {
                solGreenCount++;
            } else if (solution[i] == YELLOW) {
                solYellowCount++;
            } else if (solution[i] == PURPLE) {
                solPurpleCount++;
            } else if (solution[i] == WHITE) {
                solWhiteCount++;
            } else if (solution[i] == ORANGE) {
                solOrangeCount++;
            }
        }

        timesCalled = 0;

        d = new Drawing();
        b = new Board();
        b.getFrame().getContentPane().add(d);
        b.getFrame().pack();
        b.getFrame().setVisible(true);

        turnCount = 0;
        redPeg = 0;
        whitePeg = 0;

        REDpeg = new Color(255, 0, 0);
        WHITEpeg = new Color(255, 255, 255);

        b.resetCurCol();
        b.resetCurRow();

        addButtonReactors();
//        System.out.println(Arrays.toString(solution));
    }

    public void results() {
        for (int i = 0; i < solution.length; i++) {
            if (solution[i] == guess[i]) {
                redPegMethod(i);
            }
        }
        for(int i = 0; i < 5; i++){
            whitePegMethod();
        }
        for (int i = 0; i < redPeg; i++) {
            d.fillScore(turnCount, i, REDpeg);
            d.setSmallArr(RED, turnCount, i);
        }
        for (int i = redPeg; i < (redPeg + whitePeg); i++) {
            d.fillScore(turnCount, i, WHITEpeg);
            d.setSmallArr(WHITE, turnCount, i);
        }

        if ((!didWin()) && (turnCount == 9)) {
            String[] finalArr = new String[5];
            for (int i = 0; i < solution.length; i++) {
                if (solution[i] == 1) {
                    finalArr[i] = "PINK";
                } else if (solution[i] == 2) {
                    finalArr[i] = "GREEN";
                } else if (solution[i] == 3) {
                    finalArr[i] = "YELLOW";
                } else if (solution[i] == 4) {
                    finalArr[i] = "PURPLE";
                } else if (solution[i] == 5) {
                    finalArr[i] = "WHITE";
                } else {
                    finalArr[i] = "ORANGE";
                }
            }
            int exit = JOptionPane.showConfirmDialog(null,
                    "Sorry! You ran out of guesses!\nThe correct sequence was: " + Arrays.toString(finalArr) + "\nThanks for playing!",
                    "Sorry!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (exit == JOptionPane.YES_OPTION || exit == JOptionPane.CANCEL_OPTION || exit == -1) {
                int repeat = JOptionPane.showConfirmDialog(null,
                        "Would you like to play again?", "Play Again?",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (repeat == JOptionPane.YES_OPTION){
                    b.getFrame().dispose();
                    playAgain = true;
                }
                else{
                    System.exit(0);
                }
            }

        } else if (didWin()) {
            int exit2 = JOptionPane.showConfirmDialog(null,
                    "Congratulations! You cracked the solution!\nThanks for playing!",
                    "Congratulations!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (exit2 == JOptionPane.YES_OPTION || exit2 == JOptionPane.CANCEL_OPTION || exit2 == -1) {
                int repeat2 = JOptionPane.showConfirmDialog(null,
                        "Would you like to play again?", "Play Again?",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (repeat2 == JOptionPane.YES_OPTION){
                    b.getFrame().dispose();
                    playAgain = true;
                }
                else{
                    System.exit(0);
                }
            }
        }
    }

    public boolean didWin() {
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] != solution[i]) {
                return false;
            }
        }
        return true;
    }

    public void increaseGuess(int curGuess) {
        guess[timesCalled] = curGuess;
        timesCalled++;
        if (timesCalled > 4) {
            timesCalled = 0;
        }
    }

    public void pinkButtReact() {
        b.pinkButt().addActionListener(evt -> {
            if (guess[4] == 0) {
                buttonResponse(b.pinkColr(), PINK);
                increaseGuess(PINK);
            } else {
                JOptionPane.showMessageDialog(null, "Please press submit", "Submit", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public void greenButtReact() {
        b.greenButt().addActionListener(evt -> {
            if (guess[4] == 0) {
                buttonResponse(b.greenColr(), GREEN);
                increaseGuess(GREEN);
            } else {
                JOptionPane.showMessageDialog(null, "Please press submit", "Submit", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public void yellowButtReact() {
        b.yellowButt().addActionListener(evt -> {
            if (guess[4] == 0) {
                buttonResponse(b.yellowColr(), YELLOW);
                increaseGuess(YELLOW);
            } else {
                JOptionPane.showMessageDialog(null, "Please press submit", "Submit", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public void purpleButtReact() {
        b.purpleButt().addActionListener(evt -> {
            if (guess[4] == 0) {
                buttonResponse(b.purpleColr(), PURPLE);
                increaseGuess(PURPLE);
            } else {
                JOptionPane.showMessageDialog(null, "Please press submit", "Submit", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public void whiteButtReact() {
        b.whiteButt().addActionListener(evt -> {
            if (guess[4] == 0) {
                buttonResponse(b.whiteColr(), WHITE);
                increaseGuess(WHITE);
            } else {
                JOptionPane.showMessageDialog(null, "Please press submit", "Submit", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public void orangeButtReact() {
        b.orangeButt().addActionListener(evt -> {
            if (guess[4] == 0) {
                buttonResponse(b.orangeColr(), ORANGE);
                increaseGuess(ORANGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please press submit", "Submit", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public void submitButtReact() {
        b.submitButt().addActionListener(evt -> {
            if (guess[4] != 0) {
                b.resetCurCol();
                b.incCurRow();
                for (int value : guess) {
                    if (value == PINK) {
                        guessPinkCount++;
                    } else if (value == GREEN) {
                        guessGreenCount++;
                    } else if (value == YELLOW) {
                        guessYellowCount++;
                    } else if (value == PURPLE) {
                        guessPurpleCount++;
                    } else if (value == WHITE) {
                        guessWhiteCount++;
                    } else if (value == ORANGE) {
                        guessOrangeCount++;
                    }
                }
                results();
                turnCount++;
                resetGuess();
                resetSol();
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid guess.", "Check", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public void addButtonReactors() {
        pinkButtReact();
        greenButtReact();
        yellowButtReact();
        purpleButtReact();
        whiteButtReact();
        orangeButtReact();
        submitButtReact();
    }

    public void resetGuess() {
        for (int i = 0; i < 5; i++) {
            guess[i] = 0;
        }
        guessPinkCount = 0;
        guessGreenCount = 0;
        guessYellowCount = 0;
        guessPurpleCount = 0;
        guessWhiteCount = 0;
        guessOrangeCount = 0;

        redPeg = 0;
        whitePeg = 0;
        filler = 0;
    }

    public void redPegMethod(int i) {
        redPeg++;
        if (solution[i] == PINK) {
            guessPinkCount--;
            solPinkCount--;
        } else if (solution[i] == GREEN) {
            guessGreenCount--;
            solGreenCount--;
        } else if (solution[i] == YELLOW) {
            guessYellowCount--;
            solYellowCount--;
        } else if (solution[i] == PURPLE) {
            guessPurpleCount--;
            solPurpleCount--;
        } else if (solution[i] == ORANGE) {
            guessOrangeCount--;
            solOrangeCount--;
        } else if (solution[i] == WHITE) {
            guessWhiteCount--;
            solWhiteCount--;
        }
    }

    public void whitePegMethod() {
        if (solPinkCount > 0 && guessPinkCount > 0) {
            whitePeg++;
            guessPinkCount--;
            solPinkCount--;
        } else if (solGreenCount > 0 && guessGreenCount > 0) {
            whitePeg++;
            guessGreenCount--;
            solGreenCount--;
        } else if (solYellowCount > 0 && guessYellowCount > 0) {
            whitePeg++;
            guessYellowCount--;
            solYellowCount--;
        } else if (solPurpleCount > 0 && guessPurpleCount > 0) {
            whitePeg++;
            guessPurpleCount--;
            solPurpleCount--;
        } else if (solWhiteCount > 0 && guessWhiteCount > 0) {
            whitePeg++;
            guessWhiteCount--;
            solWhiteCount--;
        } else if (solOrangeCount > 0 && guessOrangeCount > 0) {
            whitePeg++;
            guessOrangeCount--;
            solOrangeCount--;
        } else {
            filler++;
        }
    }

    public void buttonResponse(Color colr, int num) {
        d.fillGame(b.getCurRow(), b.getCurCol(), colr);
        d.setBigArr(num, b.getCurRow(), b.getCurCol());
        if (b.getCurCol() < 5) {
            b.incCurCol();
        }
    }

    public void resetSol() {
        solPinkCount = 0;
        solGreenCount = 0;
        solYellowCount = 0;
        solPurpleCount = 0;
        solWhiteCount = 0;
        solOrangeCount = 0;

        for (int i = 0; i < 5; i++) {
            if (solution[i] == PINK) {
                solPinkCount++;
            } else if (solution[i] == GREEN) {
                solGreenCount++;
            } else if (solution[i] == YELLOW) {
                solYellowCount++;
            } else if (solution[i] == PURPLE) {
                solPurpleCount++;
            } else if (solution[i] == WHITE) {
                solWhiteCount++;
            } else if (solution[i] == ORANGE) {
                solOrangeCount++;
            }
        }
    }

    public boolean willPlayAgain(){
        return playAgain;
    }
}

