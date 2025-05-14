package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameOver extends JFrame implements ActionListener {
    private JLabel background;
    private JButton returnMain;
    private Display d;
    
    public GameOver(Game g, Display d, boolean win) {
        (this.d) = d;
        g.dispose();
        this.setUndecorated(true);
        this.setTitle("Princess Fight");
        this.setSize(Display.WIDTH,Display.HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        returnMain = new JButton();
        returnMain.setBounds(860,620,210,90);
        returnMain.addActionListener(this);
        returnMain.setIcon(new ImageIcon("src/picture/button/PFreturn.png"));
        returnMain.setFocusable(false);
        this.add(returnMain);
        
        if (win) {
            ImageIcon bg = new ImageIcon("src/picture/background/gamewin.png");
            background = new JLabel("", bg, JLabel.CENTER);
            background.setBounds(0, 0, WIDTH, HEIGHT);
        }
        else {
            ImageIcon bg = new ImageIcon("src/picture/background/gameover.png");
            background = new JLabel("", bg, JLabel.CENTER);
            background.setBounds(0, 0, WIDTH, HEIGHT);
        }
        background.setVisible(true);
        this.add(background);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==returnMain) {
            this.dispose();
            (this.d).setVisible(true);
        }
    }
}
