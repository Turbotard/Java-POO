package model;

        import java.awt.FlowLayout;
        import javax.swing.JFrame;
        import javax.swing.JLabel;
        import javax.swing.*;
        import java.awt.Font;
        import java.awt.Color;
        import javax.sound.sampled.AudioInputStream;
        import javax.sound.sampled.AudioSystem;
        import java.io.File;
        import javax.sound.sampled.Clip;

public class LabelFrame extends JFrame {

    private JLabel textLabel;

    /**
     * Cette classe, LabelFrame, est une sous-classe de JFrame et crée une interface graphique.
     *
     * La fenêtre contient 3 parties, dont deux affichent le texte "Vincent met nous un 20/20 !!" et "N'oublie pas notre +1 !!!!" respectivement.
     *
     * Le texte est centré et le texte est mis dans la police "sans serif" en rouge
     *
     * La troisième partie affiche une image.
     *
     * La fenêtre a également un titre "Vincent met nous un 20/20 !!" et une taille de 600x700.
     *
     * Un son est joué 10 fois lors de l'ouverture de la fenêtre.
     *
     * La fenêtre doit être quittée pour fermer le programme.
     */
    public LabelFrame() {

        JFrame fenetre = new JFrame();
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
        fenetre.setLayout(new FlowLayout());
        fenetre.setTitle("Vincent met nous un 20/20 !!");
        fenetre.setSize(800, 699);
        fenetre.setLocationRelativeTo(null);


        JLabel textLabel = new JLabel("Vincent met nous un 20/20 !!", JLabel.CENTER);
        textLabel.setToolTipText("Vincent met nous un 20/20 !!");
        textLabel.setFont(new java.awt.Font(Font.SANS_SERIF, Font.PLAIN, 40));
        textLabel.setForeground(new Color(20, 20, 20));

        JLabel textLabel2 = new JLabel("Et n'oublie pas notre +1 !!!!!", JLabel.CENTER);
        textLabel2.setToolTipText("ET n'oublie pas notre +1 !!!!!");
        textLabel2.setFont(new java.awt.Font(Font.SANS_SERIF, Font.PLAIN, 40));
        textLabel2.setForeground(new Color(20, 20, 20));

        JLabel textLabel3 = new JLabel("(Active le son)", JLabel.CENTER);
        textLabel3.setToolTipText("(Active le son)");
        textLabel3.setFont(new java.awt.Font(Font.SANS_SERIF, Font.PLAIN, 40));
        textLabel3.setForeground(new Color(20, 20, 20));


        Icon vincent = new ImageIcon("./src/META-INF/model.gif");
        JLabel imageLabel = new JLabel(vincent, JLabel.CENTER);
        imageLabel.setIcon(vincent);

        fenetre.add(textLabel);
        fenetre.add(textLabel2);
        fenetre.add(textLabel3);
        fenetre.add(imageLabel);
        fenetre.validate();

        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("./src/META-INF/vincent.wav"));

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            clip.loop(10);
        } catch (Exception e) {
        }
    }
}

