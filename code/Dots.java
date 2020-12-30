//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Name: Vuochlang Chang                                                                                                            //
// Class: CSE223        Spring 2020                                                                                                 //
// Date: 05/28/2020                                                                                                                 //
// Assignment: PA4 - Dots game                                                                          						    //
//     This is a game that allow 2 players take turn to play the game. It will draw a line where the player clicked on. It will 	//
//		remember whose turn it is and always calculate players's score and display who will win the game. There's also a dark mode 	//
//		that will change the color for the background, text, line, ect.																//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.SwingConstants;

public class Dots extends JFrame {

  JPanel contentPane;
  JTextField player1,player2;
  JTextArea	score, txArea1;
  JLabel lblNewLabel1,lblNewLabel2,lblScore,lblInstruction;
  JButton startButton;
  JRadioButton rdbtnNewRadioButton;
  JTextArea txtrInstructionPressButton;
  int count=0;
  GamePanel panel;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Dots frame = new Dots();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public Dots() {

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 659, 572);
    contentPane = new JPanel();
    contentPane.setBackground(Color.WHITE);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    panel = new GamePanel();
    panel.setBorder(new LineBorder(new Color(0, 0, 0)));
    panel.saveParent(this);
    panel.setBackground(Color.WHITE);
    panel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {//when player clicks inside the JPanel (Dots area)
        int x=arg0.getX();
        int y=arg0.getY();
        panel.click(x,y);
        panel.repaint();
      }
    });
    panel.setBounds(50, 50, 412, 412);
    contentPane.add(panel);
    panel.setLayout(null);

    player1 = new JTextField();
    player1.setBounds(480, 92, 129, 26);
    contentPane.add(player1);
    player1.setColumns(10);

    player2 = new JTextField();
    player2.setSelectedTextColor(Color.WHITE);
    player2.setDisabledTextColor(Color.GRAY);
    player2.setBounds(480, 179, 129, 26);
    contentPane.add(player2);
    player2.setColumns(10);

    startButton = new JButton("START");
    startButton.addActionListener(new ActionListener() {//when player presses START button
      public void actionPerformed(ActionEvent arg0) {
        panel.startGame();
        panel.repaint();
      }
    });

    startButton.setBounds(480, 291, 115, 29);
    contentPane.add(startButton);

    lblNewLabel1 = new JLabel("1st Player's name");
    lblNewLabel1.setForeground(Color.BLACK);
    lblNewLabel1.setBounds(480, 50, 128, 26);
    contentPane.add(lblNewLabel1);

    lblNewLabel2 = new JLabel("2nd Player's name");
    lblNewLabel2.setBounds(480, 137, 139, 26);
    contentPane.add(lblNewLabel2);

    score = new JTextArea();
    score.setEditable(false);
    score.setFont(new Font("Tahoma", Font.BOLD, 15));
    score.setWrapStyleWord(true);
    score.setBounds(124, 16, 341, 34);
    contentPane.add(score);

    lblScore = new JLabel("SCORE :");
    lblScore.setBounds(50, 15, 68, 26);
    contentPane.add(lblScore);

    rdbtnNewRadioButton = new JRadioButton("Dark Mode");
    rdbtnNewRadioButton.setOpaque(false);
    rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {//when player clicks on the radio-button to change to DarkMode
        ++count;
        panel.changeMode(count);
        System.out.println(e+"\ncount= "+count);
      }
    });

    rdbtnNewRadioButton.setBounds(480, 236, 115, 29);
    contentPane.add(rdbtnNewRadioButton);

    txArea1 = new JTextArea();
    txArea1.setEditable(false);
    txArea1.setFont(new Font("Tahoma", Font.BOLD, 16));
    txArea1.setBounds(60, 467, 393, 33);

    contentPane.add(txArea1);

    //the following <txtrInstructionPressButton> is to display the instruction outside of JPanel
    txtrInstructionPressButton = new JTextArea("Press <START> button to : \r\n       _start the game and \r\n       _to restart the game");
    txtrInstructionPressButton.setRows(2);
    txtrInstructionPressButton.setOpaque(false);
    txtrInstructionPressButton.setWrapStyleWord(true);
    txtrInstructionPressButton.setBounds(480, 388, 157, 76);
    contentPane.add(txtrInstructionPressButton);

    lblInstruction = new JLabel("INSTRUCTION :");
    lblInstruction.setBounds(480, 368, 139, 26);
    contentPane.add(lblInstruction);
    contentPane.setVisible(true);
  }
}
