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
        setLayout(new FlowLayout());
        JLabel textLabel = new JLabel("Vincent met nous un 20/20 !!", SwingConstants.CENTER);
        textLabel.setToolTipText("Vincent met nous un 20/20 !!");
        textLabel.setFont(new java.awt.Font(Font.SANS_SERIF, Font.PLAIN, 40));
        textLabel.setForeground(new Color(150, 50, 50));
        add(textLabel);
    }
}

