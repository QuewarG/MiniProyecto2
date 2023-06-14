package view;

import controller.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used for ...
 * @autor Carlos Felipe Montoya carlos.felipe.montoya@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class GUI extends JFrame {
    private JLabel enterText;
    private FileManager nameUser;
    private Header headerProject;
    private JPanel squareColor;
    private Timer timer;
    private JTextField nickName;
    private Escucha escucha;
    private JButton start;
    private JPanel panel;
    private JLabel label;
    private ArrayList<String> words;
    private int counter;

    private int currentIndex;
    /**
     * Constructor of GUI class
     */
    public GUI(){

        initGUI();
        //Default JFrame configuration
        this.setTitle("I Know That Word");
        this.setSize(400,400);
        //this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {

        nameUser = new FileManager();
        escucha = new Escucha();
        counter = 0;

        panel = new JPanel();
        panel.setLayout(null);
        enterText = new JLabel("Enter a nickname");
        enterText.setBounds(50, 20, 200, 30);
        panel.add(enterText);


        headerProject = new Header("I Know That Word", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH);

        nickName = new JTextField();
        nickName.setBounds(50, 50, 200, 30);
        nickName.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(nickName);

        start = new JButton("Start");
        start.addActionListener(escucha);
        start.setBounds(50, 90, 100, 30);
        panel.add(start);

        this.add(panel, BorderLayout.CENTER);

    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });

    }

    public void WordPanel() {
        words = nameUser.getRandomWords();
        currentIndex = 0;

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        label = new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label);

        timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < words.size()) {
                    label.setText(words.get(currentIndex));
                    currentIndex++;
                } else {
                    timer.stop();
                }
            }
        });
    }

    public void startTimer() {
        currentIndex = 0;
        timer.start();
    }




    private void CheckRecord () {
        if (nickName.getText().contains(" ") || nickName.getText().isEmpty() || nickName.getText() == null) {
            JOptionPane.showMessageDialog(null,"Debes ingresar un nombre","Usuario Inválido",JOptionPane.INFORMATION_MESSAGE);
            nickName.setText("");
        } else {
           // System.out.println("Ingreso al else");
            nameUser.readUsers(nickName.getText());
            this.remove(panel);
            this.revalidate();
            this.repaint();
            this.WordPanel();
            this.startTimer();

            //nameUser.writer(nickName.getText());

        }
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start){
            CheckRecord();
        }

        }
    }
}