package character;

import java.awt.Font;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class Enemy extends Character {
    private Random r = new Random();
//Graphic
    private final int minionWidth = 170;
    private final int minionHeight = 240;
    private JLabel minion = new JLabel();
    private JLabel powerNumber = new JLabel();
    private Timer timer;
    public Enemy (Player p, int numForElement) {
        super((int) (Math.random()*(p.getPower()*120/100-(p.getPower()*70/100)+1)+p.getPower()*70/100),numForElement);
        if (getPower()>3000000) {
            setPower((int) Math.random()*(2400000*120/100-(2400000*70/100)+1)+2400000*70/100);
        }
        this.setBounds(0, 0, minionWidth, minionHeight+50);
        this.setOpaque(false);
        minion.setBounds(0, 0, minionWidth, 185);
        minion.setVisible(true);
        randomEnemy(numForElement);
        this.add(minion);
        
        powerNumber = new JLabel(""+getPower(),JLabel.CENTER);
        powerNumber.setBounds(0,185,minionWidth-10,55);
        powerNumber.setFont(new Font("Arial", Font.BOLD, 36));
        powerNumber.setLayout(null);
        powerNumber.setVisible(true);
        
        this.add(powerNumber);
    }
    public int randomPower(Player p) {
        int i = r.nextInt(5);
        int powerReturn=0;
        switch (i) {
            case 0: powerReturn=p.getPower(); break;
            case 1: powerReturn=p.getPower()*150/100; break;
            case 2: powerReturn=p.getPower()*80/100; break;
            case 3: powerReturn=p.getPower()*110/100; break;
            case 4: powerReturn=p.getPower()*70/100; break;
        }
        return powerReturn;
    }
    public void randomEnemy(int numForElement) {
        int minionNum = (int) (Math.random()*(3))+1;
        if (numForElement == 1) {
            if (minionNum == 1) {
                minion.setIcon(new ImageIcon("src/picture/character/minion/f11.png"));
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                    minion.setIcon(new ImageIcon("src/picture/character/minion/f12.png"));
                    timer.schedule(new TimerTask() {
                        public void run() {
                            minion.setIcon(new ImageIcon("src/picture/character/minion/f11.png"));
                            }
                        },1000);
                    }
                }, 1000,1000);
            }
            else if (minionNum == 2) {
                minion.setIcon(new ImageIcon("src/picture/character/minion/f21.png"));
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                    minion.setIcon(new ImageIcon("src/picture/character/minion/f22.png"));
                    timer.schedule(new TimerTask() {
                        public void run() {
                            minion.setIcon(new ImageIcon("src/picture/character/minion/f21.png"));
                            }
                        },1000);
                    }
                }, 1000,1000);
            }
            else if (minionNum == 3) {
                minion.setIcon(new ImageIcon("src/picture/character/minion/f31.png"));
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                    minion.setIcon(new ImageIcon("src/picture/character/minion/f32.png"));
                    timer.schedule(new TimerTask() {
                        public void run() {
                            minion.setIcon(new ImageIcon("src/picture/character/minion/f31.png"));
                            }
                        },1000);
                    }
                }, 1000,1000);
            }
        }
        else if (numForElement == 2) {
            if (minionNum == 1) {
                minion.setIcon(new ImageIcon("src/picture/character/minion/n11.png"));
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                    minion.setIcon(new ImageIcon("src/picture/character/minion/n12.png"));
                    timer.schedule(new TimerTask() {
                        public void run() {
                            minion.setIcon(new ImageIcon("src/picture/character/minion/n11.png"));
                            }
                        },1000);
                    }
                }, 1000,1000);
            }
            else if (minionNum == 2) {
                minion.setIcon(new ImageIcon("src/picture/character/minion/n21.png"));
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                    minion.setIcon(new ImageIcon("src/picture/character/minion/n22.png"));
                    timer.schedule(new TimerTask() {
                        public void run() {
                            minion.setIcon(new ImageIcon("src/picture/character/minion/n21.png"));
                            }
                        },1000);
                    }
                }, 1000,1000);
            }
            else if (minionNum == 3) {
                minion.setIcon(new ImageIcon("src/picture/character/minion/n31.png"));
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                    minion.setIcon(new ImageIcon("src/picture/character/minion/n32.png"));
                    timer.schedule(new TimerTask() {
                        public void run() {
                            minion.setIcon(new ImageIcon("src/picture/character/minion/n31.png"));
                            }
                        },1000);
                    }
                }, 1000,1000);
            }
        }
        else if (numForElement == 3) {
            if (minionNum == 1) {
                minion.setIcon(new ImageIcon("src/picture/character/minion/w11.png"));
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                    minion.setIcon(new ImageIcon("src/picture/character/minion/w12.png"));
                    timer.schedule(new TimerTask() {
                        public void run() {
                            minion.setIcon(new ImageIcon("src/picture/character/minion/w11.png"));
                            }
                        },1000);
                    }
                }, 1000,1000);
            }
            else if (minionNum == 2) {
                minion.setIcon(new ImageIcon("src/picture/character/minion/w21.png"));
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                    minion.setIcon(new ImageIcon("src/picture/character/minion/w22.png"));
                    timer.schedule(new TimerTask() {
                        public void run() {
                            minion.setIcon(new ImageIcon("src/picture/character/minion/w21.png"));
                            }
                        },1000);
                    }
                }, 1000,1000);
            }
            else if (minionNum == 3) {
                minion.setIcon(new ImageIcon("src/picture/character/minion/w31.png"));
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                    minion.setIcon(new ImageIcon("src/picture/character/minion/w32.png"));
                    timer.schedule(new TimerTask() {
                        public void run() {
                            minion.setIcon(new ImageIcon("src/picture/character/minion/w31.png"));
                            }
                        },1000);
                    }
                }, 1000,1000);
            }
        }
    }
    
}
