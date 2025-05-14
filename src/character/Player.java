package character;

import game.Game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class Player extends Character implements KeyListener {
    private ImageIcon playerIMG = new ImageIcon("src/picture/character/princess/p1.png");
    private final int playerWidth = playerIMG.getIconWidth();
    private final int playerHeight = playerIMG.getIconHeight();
    private JLabel powerNumber = new JLabel();
    private JLabel player = new JLabel(); 
    private Timer timer = new Timer();
    private boolean fire=false,wind=false,water=false;
    
    public Player(Game game, JPanel[] map,ArrayList<JPanel> enemyPanelList, ArrayList<Enemy> enemyOBJlist, 
            ArrayList<JPanel> bossPanelList, ArrayList<Boss> bossNaja, Boss[] finalBoss, JLabel[] areaGameOver) {
        super(10,0);                        //NOTE: Start status of Main Character :)
        callPrincess();
        this.setBounds(450, 450, playerWidth, playerHeight);
        this.setOpaque(false);
        player.setBounds(0,0,playerWidth,playerHeight);

        player.setOpaque(false);
        player.setLayout(null);
        player.setVisible(true);

        PlayerMovement mv = new PlayerMovement(this,map,this,enemyPanelList,enemyOBJlist,bossPanelList,bossNaja,finalBoss, areaGameOver, game);
        this.add(player);
        game.addKeyListener(this);
    }
    public boolean canFire()    { return fire; }
    public boolean canWind()    { return wind; }
    public boolean canWater()   { return water; }
    
    public void enableFire()    { fire=true; }
    public void enableWind()    { wind=true; }
    public void enableWater()   { water=true; }
    public JLabel getPlayerLabel() { return player; }
    public void callPrincess() {
        setElement(0);
        player.setIcon(new ImageIcon("src/picture/character/princess/p1.png"));
        timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                player.setIcon(new ImageIcon("src/picture/character/princess/p2.png"));
                timer.schedule(new TimerTask() {
                    public void run() {
                        player.setIcon(new ImageIcon("src/picture/character/princess/p1.png"));
                    }
                },1000);
            }
        }, 1000,1000);
    }
    public void callKnight() {
        if (fire) {
        setElement(1);
        player.setIcon(new ImageIcon("src/picture/character/knight/k1.png"));
        timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                player.setIcon(new ImageIcon("src/picture/character/knight/k2.png"));
                timer.schedule(new TimerTask() {
                    public void run() {
                        player.setIcon(new ImageIcon("src/picture/character/knight/k1.png"));
                    }
                },1000);
            }
        }, 1000,1000);
        }  
    }
    public void callElf() {
        if (wind) {
        setElement(2);
        player.setIcon(new ImageIcon("src/picture/character/elf/e1.png"));
        timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                player.setIcon(new ImageIcon("src/picture/character/elf/e2.png"));
                timer.schedule(new TimerTask() {
                    public void run() {
                        player.setIcon(new ImageIcon("src/picture/character/elf/e1.png"));
                    }
                },1000);
            }
        }, 1000,1000);
        }
    }
    public void callDrake() {
        if (water) {
        setElement(3);
        player.setIcon(new ImageIcon("src/picture/character/drake/d1.png"));
        timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                player.setIcon(new ImageIcon("src/picture/character/drake/d2.png"));
                timer.schedule(new TimerTask() {
                    public void run() {
                        player.setIcon(new ImageIcon("src/picture/character/drake/d1.png"));
                    }
                },1000);
            }
        }, 1000,1000);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'Q': case 'q': case 'ๆ': case '๐': callPrincess(); break;
            case 'W': case 'w': case 'ไ': case '"': callKnight(); break;
            case 'E': case 'e': case 'ฎ': case 'ำ': callElf(); break;
            case 'R': case 'r': case 'ฑ': case 'พ': callDrake(); break;
        }
    }
    @Override
    public void keyPressed(KeyEvent e) { }
    @Override
    public void keyReleased(KeyEvent e) { }    
}