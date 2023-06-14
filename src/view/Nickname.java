package view;

import controller.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.*;

public class Nickname extends JPanel  implements ActionListener {
    public static FileManager nameUser;
    private JButton button;
    private JTextField textField;
    private JLabel name;
    private JPanel panel;

    Nickname(){

        nameUser = new FileManager();

        this.setLayout(new FlowLayout());

        name = new JLabel("Enter your nickname");
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        name.setFont(new Font("Consolas", Font.PLAIN,18));
        name.setForeground(new Color(255, 255, 255));


        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250,40));
        textField.setFont(new Font("Consolas", Font.PLAIN,35));
        textField.setBackground(new Color(27,228,235));
        textField.setForeground(new Color(0,0,0));

        button = new JButton("Start");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
        button.setBackground(new Color(148, 195, 219, 255));

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(0, 0, 0));
        panel.add(name);
        panel.add(textField);
        panel.add(button);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.insets = new Insets(100, 0, 20, 0); // Espacio alrededor del componente
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(name, constraints);

        constraints.gridy = 7;
        constraints.insets = new Insets(0, 0, 20, 0);
        panel.add(textField, constraints);

        constraints.gridy = 8;
        constraints.ipadx = 20; // Ajustar el tamaño del botón horizontalmente
        constraints.insets = new Insets(30, 0, 20, 0); // Mover el botón hacia abajo
        panel.add(button, constraints);

// Ajustar restricciones para mover el panel al centro de la ventana
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0; // Expansión horizontal
        constraints.weighty = 1.0; // Expansión vertical
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(panel, constraints);




        this.add(panel);
        this.setVisible(true);

    }
    private void CheckRecord () {
        if (textField.getText().contains(" ") || textField.getText().isEmpty() || textField.getText() == null) {
            JOptionPane.showMessageDialog(null,"Debes ingresar un nombre","Usuario Inválido",JOptionPane.INFORMATION_MESSAGE);
            textField.setText("");
        } else {
            // System.out.println("Ingreso al else");
            nameUser.readUsers(textField.getText());
            this.remove(panel);
            //nameUser.writer(nickName.getText());

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            textField.getText();

            CheckRecord();
        }
    }
}
