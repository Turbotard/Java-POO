package model;

        import java.awt.FlowLayout;
        import javax.swing.JFrame;
        import javax.swing.JLabel;
        import javax.swing.*;
        import java.awt.Font;
        import java.awt.Color;

public class LabelFrame extends JFrame {

    private JLabel textLabel;

    public LabelFrame() {

        JFrame fenetre = new JFrame();
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
        fenetre.setLayout(new FlowLayout());
        fenetre.setTitle("Vincent met nous un 20/20 !!");
        fenetre.setSize(600, 700);
        fenetre.setLocationRelativeTo(null);


        JLabel textLabel = new JLabel("Vincent met nous un 20/20 !!", JLabel.CENTER);
        textLabel.setToolTipText("Vincent met nous un 20/20 !!");
        textLabel.setFont(new java.awt.Font(Font.SANS_SERIF, Font.PLAIN, 40));
        textLabel.setForeground(new Color(150, 50, 50));

        JLabel textLabel2 = new JLabel("N'oublie pas notre +1 !!!!!", JLabel.CENTER);
        textLabel2.setToolTipText("N'oublie pas notre +1 !!!!!");
        textLabel2.setFont(new java.awt.Font(Font.SANS_SERIF, Font.PLAIN, 40));
        textLabel2.setForeground(new Color(150, 50, 50));




        fenetre.add(textLabel);
        fenetre.add(textLabel2);
        Icon vincent = new ImageIcon("./src/model/Vincent.png");
        System.out.println(vincent.getIconHeight());
        JLabel imageLabel = new JLabel(vincent, JLabel.CENTER);
        imageLabel.setIcon(vincent);

        fenetre.add(imageLabel);
        fenetre.validate();

    }
}

