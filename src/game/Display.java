package game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Display extends JFrame implements ActionListener {
    public static final int WIDTH = 1920, HEIGHT = 1080;
    private JFrame window;
    private JButton start, setting, exit, settingExit;
    private JLabel background;
    private JPanel settingPanel;
    private ImageIcon icon = new ImageIcon("src/picture/button/PFicon.png");
    
    public Display() {
        this.setTitle("Princess Fight");
        this.setSize(WIDTH,HEIGHT);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setIconImage(icon.getImage());
    //Create button
        ImageIcon startIMG = new ImageIcon("src/picture/button/PFstart.png");
        start = new JButton();
        start.setBounds(860,500,210,90);
        start.addActionListener(this);
        start.setIcon(startIMG);
        start.setFocusable(false);
        this.add(start);
        
        ImageIcon settingIMG = new ImageIcon("src/picture/button/PFinfo.png");
        setting = new JButton();
        setting.setBounds(860,620,210,90);
        setting.addActionListener(this);
        setting.setIcon(settingIMG);
        setting.setFocusable(false);
        this.add(setting);
        
        ImageIcon exitIMG = new ImageIcon("src/picture/button/PFexit.png");
        exit = new JButton();
        exit.setBounds(860,740,210,90);
        exit.addActionListener(this);
        exit.setIcon(exitIMG);
        exit.setFocusable(false);
        this.add(exit);
        
        settingExit = new JButton();
        settingExit.setBounds(WIDTH/2-50,20,30,30);
        settingExit.addActionListener(this);
        settingExit.setIcon(new ImageIcon("src/picture/button/PFinfoexit.png"));
        settingExit.setVisible(false);
        this.add(settingExit);
    //Create "information panel"
        ImageIcon bgSet = new ImageIcon("src/picture/background/BGtitle_setting.png");
        JTextField setText = new JTextField(10);
        JLabel bgSet2 = new JLabel(bgSet);      
        
        settingPanel = new JPanel();
        settingPanel.setBounds(0,0,WIDTH/2,HEIGHT);
        settingPanel.setLayout(new BorderLayout());
        settingPanel.setVisible(false);
        settingPanel.add(bgSet2);
        this.add(settingPanel);
    //Set BG image
        ImageIcon bg = new ImageIcon("src/picture/background/BGtitle.png");
        background = new JLabel("", bg, JLabel.CENTER);
        background.setBounds(0, 0, WIDTH, HEIGHT);
        this.add(background);
        
        this.setLocationRelativeTo(null);    
        this.setVisible(true);
        
        playMusic();
    }
    public void playMusic() {
        try {
            File file = new File("src/BGM/bgm.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            while (true){
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (UnsupportedAudioFileException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
              System.out.println(e.toString());  
        } catch (LineUnavailableException e) {
            System.out.println(e.toString());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==start) {
            this.setVisible(false);
            Game gg = new Game(this);
        }
        else if (e.getSource()==setting) {
            settingExit.setVisible(true);
            settingPanel.setVisible(true);
            start.setVisible(false); setting.setVisible(false); exit.setVisible(false);
        }
        else if (e.getSource()==exit) {
            JFrame frame = new JFrame("EXIT");
            if (JOptionPane.showConfirmDialog(frame, "Are you really want to exit?", "EXIT", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION)
                System.exit(0);
        }
        else if (e.getSource()==settingExit) {
            settingExit.setVisible(false);
            settingPanel.setVisible(false);
            start.setVisible(true); setting.setVisible(true); exit.setVisible(true);
        }
    }

}