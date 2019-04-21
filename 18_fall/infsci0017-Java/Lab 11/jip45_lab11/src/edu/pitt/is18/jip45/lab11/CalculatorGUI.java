package edu.pitt.is18.jip45.lab11;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {

    // Global variables representing SWING controls for the frame
    private JFrame mainFrame;
    private JPanel calculatorPanel;
    private JButton btnZero;
    private JButton btnOne;
    private JButton btnTwo;
    private JButton btnThree;
    private JButton btnFour;
    private JButton btnFive;
    private JButton btnSix;
    private JButton btnSeven;
    private JButton btnEight;
    private JButton btnNine;
    private JButton btnPlus;
    private JButton btnMinus;
    private JButton btnTime;
    private JButton btnDivide;
    private JButton btnPoint;
    private JButton btnAC;
    private JButton btnPercent;
    private JButton btnLeftBracket;
    private JButton btnRightBracket;
    private JButton btnEqual;
    private JTextField jtfDisplay;
    private boolean startNewValue = false;
    private double value1;
    private double value2;
    private String operator;
    private double result;

    public CalculatorGUI() {
        calculatorFrame();
    }

    public static void main(String[] args) {
        CalculatorGUI calculator = new CalculatorGUI();
    }

    private void calculatorFrame() {

        mainFrame = new JFrame("Calculator");
        mainFrame.setBounds(100, 100, 500, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);

        calculatorPanel = new JPanel(null);
        calculatorPanel.setBounds(50, 50, 300, 350);
        btnZero = new JButton("0");
        btnOne = new JButton("1");
        btnTwo = new JButton("2");
        btnThree = new JButton("3");
        btnFour = new JButton("4");
        btnFive = new JButton("5");
        btnSix = new JButton("6");
        btnSeven = new JButton("7");
        btnEight = new JButton("8");
        btnNine = new JButton("9");
        btnPlus = new JButton("+");
        btnMinus = new JButton("-");
        btnTime = new JButton("*");
        btnDivide = new JButton("/");
        btnPoint = new JButton(".");
        btnAC = new JButton("AC");
        btnPercent = new JButton("%");
        btnLeftBracket = new JButton("(");
        btnRightBracket = new JButton(")");
        btnEqual = new JButton("=");
        btnPoint = new JButton(".");
        btnEqual = new JButton("=");
        jtfDisplay = new JTextField("");

        // main frame setup
        // target outlook
        /*
        ( ) % AC
        7 8 9 /
        4 5 6 *
        1 2 3 -
        0 . = +
         */

        btnZero.setBackground(Color.white);
        btnOne.setBackground(Color.white);
        btnTwo.setBackground(Color.white);
        btnThree.setBackground(Color.white);
        btnFour.setBackground(Color.white);
        btnFive.setBackground(Color.white);
        btnSix.setBackground(Color.white);
        btnSeven.setBackground(Color.white);
        btnEight.setBackground(Color.white);
        btnNine.setBackground(Color.white);
        btnLeftBracket.setBackground(Color.gray);
        btnRightBracket.setBackground(Color.gray);
        btnPercent.setBackground(Color.gray);
        btnAC.setBackground(Color.gray);
        btnPlus.setBackground(Color.gray);
        btnMinus.setBackground(Color.gray);
        btnTime.setBackground(Color.gray);
        btnDivide.setBackground(Color.gray);
        btnEqual.setBackground(Color.blue);

        jtfDisplay.setBounds(60, 30, 200, 30);
        jtfDisplay.setEditable(false);
        jtfDisplay.setHorizontalAlignment(JTextField.RIGHT);

        btnLeftBracket.setBounds(60, 60, 50, 50);
        btnSeven.setBounds(60, 110, 50, 50);
        btnFour.setBounds(60, 160, 50, 50);
        btnOne.setBounds(60, 210, 50, 50);
        btnZero.setBounds(60, 260, 50, 50);
        btnRightBracket.setBounds(110, 60, 50, 50);
        btnEight.setBounds(110, 110, 50, 50);
        btnFive.setBounds(110, 160, 50, 50);
        btnTwo.setBounds(110, 210, 50, 50);
        btnPoint.setBounds(110, 260, 50, 50);
        btnPercent.setBounds(160, 60, 50, 50);
        btnNine.setBounds(160, 110, 50, 50);
        btnSix.setBounds(160, 160, 50, 50);
        btnThree.setBounds(160, 210, 50, 50);
        btnEqual.setBounds(160, 260, 50, 50);
        btnAC.setBounds(210, 60, 50, 50);
        btnDivide.setBounds(210, 110, 50, 50);
        btnTime.setBounds(210, 160, 50, 50);
        btnMinus.setBounds(210, 210, 50, 50);
        btnPlus.setBounds(210, 260, 50, 50);


        btnOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jtfDisplay.setText(jtfDisplay.getText() + "1");

            }
        });
        btnTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfDisplay.setText(jtfDisplay.getText() + "2");
            }
        });
        btnThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfDisplay.setText(jtfDisplay.getText() + "3");
            }
        });
        btnFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfDisplay.setText(jtfDisplay.getText() + "4");
            }
        });
        btnFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfDisplay.setText(jtfDisplay.getText() + "5");
            }
        });
        btnSix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfDisplay.setText(jtfDisplay.getText() + "6");
            }
        });
        btnSeven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfDisplay.setText(jtfDisplay.getText() + "7");
            }
        });
        btnEight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfDisplay.setText(jtfDisplay.getText() + "8");
            }
        });
        btnNine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfDisplay.setText(jtfDisplay.getText() + "9");
            }
        });
        btnZero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfDisplay.setText(jtfDisplay.getText() + "0");
            }
        });
        btnPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jtfDisplay.getText().contains("."))
                    jtfDisplay.setText(jtfDisplay.getText() + ".");
            }
        });
        btnLeftBracket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnRightBracket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnPercent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfDisplay.setText(String.valueOf(Double.parseDouble(jtfDisplay.getText()) / 100));
            }
        });
        btnPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!startNewValue) jtfDisplay.setText(jtfDisplay.getText() + "-");
            }
        });
        btnTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!startNewValue) jtfDisplay.setText(jtfDisplay.getText() + "*");
            }
        });
        btnDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!startNewValue) jtfDisplay.setText(jtfDisplay.getText() + "/");
            }
        });
        btnEqual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String out = jtfDisplay.getText();
            }
        });
        btnAC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfDisplay.setText("");
            }
        });

        calculatorPanel.add(btnZero);
        calculatorPanel.add(btnOne);
        calculatorPanel.add(btnTwo);
        calculatorPanel.add(btnThree);
        calculatorPanel.add(btnFour);
        calculatorPanel.add(btnFive);
        calculatorPanel.add(btnSix);
        calculatorPanel.add(btnSeven);
        calculatorPanel.add(btnEight);
        calculatorPanel.add(btnNine);
        calculatorPanel.add(btnAC);
        calculatorPanel.add(btnPercent);
        calculatorPanel.add(btnLeftBracket);
        calculatorPanel.add(btnRightBracket);
        calculatorPanel.add(btnPlus);
        calculatorPanel.add(btnMinus);
        calculatorPanel.add(btnTime);
        calculatorPanel.add(btnDivide);
        calculatorPanel.add(btnEqual);
        calculatorPanel.add(btnPoint);
        calculatorPanel.add(jtfDisplay);


        calculatorPanel.setBorder(new TitledBorder(new EtchedBorder(), "Calculator"));
        mainFrame.getContentPane().add(calculatorPanel);
        mainFrame.setVisible(true);
    }

    public double calculate(double value1, double value2, String operator) {
        if (operator.equals("+")) {
            return value1 + value2;
        } else if (operator.equals("-")) {
            return value1 - value2;
        } else if (operator.equals("*")) {
            return value1 * value2;
        } else if (operator.equals("/")) {
            return value1 / value2;
        } else // "="
        {
            return value2;
        }
    }


}
