package character;

import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Boss extends Character {
    private final int bossWidth = 300;
    private final int bossHeight = 450;
    private JLabel boss = new JLabel();
    private Timer timer;
    private JLabel powerNumber = new JLabel();
    public Boss(int power, int element) {
        super(power, element);
        switch (element) {
            case 0: callFinal();    break;
            case 1: callFire();     break;
            case 2: callWind();     break;
            case 3: callWater();    break;
        }
        this.setBounds(600, 300,bossWidth,bossHeight+55);
        this.setOpaque(false);
        
        powerNumber = new JLabel(""+getPower(),JLabel.CENTER);
        powerNumber.setBounds(0,bossHeight,bossWidth,55);
        powerNumber.setFont(new Font("Arial", Font.BOLD, 40));
        powerNumber.setLayout(null);
        powerNumber.setVisible(true);
        this.add(powerNumber);

        boss.setOpaque(false);
        boss.setVisible(true);
        boss.setLayout(null);
        this.add(boss);
    }
    private void callFire() {
        boss.setIcon(new ImageIcon("src/picture/character/boss/fire1.png"));
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                boss.setIcon(new ImageIcon("src/picture/character/boss/fire2.png"));
                timer.schedule(new TimerTask() {
                    public void run() {
                        boss.setIcon(new ImageIcon("src/picture/character/boss/fire1.png"));
                    }
                },1000);
            }
        }, 1000,1000);
    }
    private void callWind() {
        boss.setIcon(new ImageIcon("src/picture/character/boss/golem1.png"));
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                boss.setIcon(new ImageIcon("src/picture/character/boss/golem2.png"));
                timer.schedule(new TimerTask() {
                    public void run() {
                        boss.setIcon(new ImageIcon("src/picture/character/boss/golem1.png"));
                    }
                },1000);
            }
        }, 1000,1000);
    }
    private void callWater() {
        boss.setIcon(new ImageIcon("src/picture/character/boss/dragon1.png"));
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                boss.setIcon(new ImageIcon("src/picture/character/boss/dragon2.png"));
                timer.schedule(new TimerTask() {
                    public void run() {
                        boss.setIcon(new ImageIcon("src/picture/character/boss/dragon1.png"));
                    }
                },1000);
            }
        }, 1000,1000);
    }
    private void callFinal() {
        boss.setIcon(new ImageIcon("src/picture/character/boss/demof1.png"));
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                boss.setIcon(new ImageIcon("src/picture/character/boss/demof2.png"));
                timer.schedule(new TimerTask() {
                    public void run() {
                        boss.setIcon(new ImageIcon("src/picture/character/boss/demof1.png"));
                    }
                },1000);
            }
        }, 1000,1000);
    }

}
