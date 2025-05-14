package game;

import character.Boss;
import character.Enemy;
import character.Player;
import static game.Display.HEIGHT;
import static game.Display.WIDTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static java.lang.Thread.sleep;
import javax.swing.JLayeredPane;
import java.util.ArrayList;

public class Game extends JFrame implements ActionListener {
    //JFrame gameFrame;
    private JButton exitGame, pauseGame;
    public static Display d;
    private JLabel background;
    private ImageIcon icon = new ImageIcon("src/picture/button/PFicon.png");
    public JLabel bossArea;
    private Player p;
    private JLabel iconChar;
    private Game thisgame;
    private boolean exit = false;
    
    //Emeny
    private JLayeredPane enemyMove;             //MOVE
    private ArrayList<JPanel> enemyPanelList, bossPanelList;    //List of non-null panel
    private ArrayList<JPanel> enemyPanel;       //List of NULL panel
    private ArrayList<Enemy> enemyOBJlist;       //List of enemy (Character obj)
    private ArrayList<Boss> miniBoss;            //Use for BOSS
    private Boss[] finalBoss;
    private JLabel[] areaGameOver;
    
    public static JLabel playerPower, playerHP;
    public static JLayeredPane playerInfo;
    public static int indexOfPowerText=0;
    
    public Game(Display d) {
        thisgame=this;
        this.d = d;
        (this.d).setVisible(false);
        this.setUndecorated(true);
        this.setTitle("Princess Fight");
        this.setSize(Display.WIDTH,Display.HEIGHT);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    //Set icon image    
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(this);
    
    //Player status
        playerInfo = new JLayeredPane();
        playerInfo.setBounds(125,0,800,300);
        playerInfo.setOpaque(false);
        playerInfo.setVisible(true);
        this.add(playerInfo);    
        
    //Create button    
        exitGame = new JButton();
        exitGame.setBounds(1820,20,70,70);
        exitGame.addActionListener(this);
        exitGame.setIcon(new ImageIcon("src/picture/button/PFoptionGame.png"));
        exitGame.setFocusable(false);
        this.add(exitGame);
    
    //Create path that can Moving
        pathMoving = new JLayeredPane();
        pathMoving.setBounds(0, 0, 1360*32, Display.HEIGHT);
        pathMoving.setOpaque(false);
        pathMoving.setVisible(true);
        
    //Create BOSS AREA
        bossArea = new JLabel();
        bossArea.setBounds(0,160,200,850);
        bossArea.setIcon(new ImageIcon("src/picture/character/boss/demon1.png"));
        bossArea.setVisible(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                bossArea.setIcon(new ImageIcon("src/picture/character/boss/demon2.png"));
                timer.schedule(new TimerTask() {
                    public void run() {
                        bossArea.setIcon(new ImageIcon("src/picture/character/boss/demon1.png"));
                    }
                },1000);
            }
        }, 1000,1000);
        areaGameOver = new JLabel[1];
        areaGameOver[0] = bossArea;
        pathMoving.add(bossArea,Integer.valueOf(46));
    
    //Create enemy
        enemyPanelList = new ArrayList<>();
        enemyOBJlist = new ArrayList<>();
        enemyPanel = new ArrayList<>();
        bossPanelList = new ArrayList<>();
        miniBoss = new ArrayList<>();
        enemyMove = new JLayeredPane();
        finalBoss = new Boss[1];
        enemyMove.setBounds(0, 0, Display.WIDTH*31, Display.HEIGHT);
        enemyMove.setOpaque(false);
        enemyMove.setVisible(true);
        
        pathMoving.add(enemyMove,Integer.valueOf(35));
        
    //Create path/map
        JPanel[] map = generatePath();
        
    //Create character    
        p = new Player(this, map, enemyPanelList, enemyOBJlist, bossPanelList, miniBoss, finalBoss, areaGameOver); //, areaGameOver,
        pathMoving.add(p,Integer.valueOf(45));
    
    //Create character power
        JLabel powerIcon = new JLabel();
        powerIcon.setBounds(0,20,75,75);
        powerIcon.setIcon(new ImageIcon("src/picture/button/power.png"));
        powerIcon.setVisible(true);
        powerIcon.setOpaque(false);
        playerInfo.add(powerIcon,Integer.valueOf(0));
        
        playerPower = new JLabel("  Your power : "+p.getPower(),JLabel.LEFT);
        playerPower.setBounds(75,20,400,75);
        playerPower.setFont(new Font("Layiji MaHaNiYom V1.41", Font.PLAIN, 36));
        playerPower.setForeground(Color.WHITE);
        playerPower.setBackground(Color.BLACK);
        playerPower.setVisible(true);
        playerPower.setOpaque(true);
        playerInfo.add(playerPower,Integer.valueOf(0));
        
        JLabel hpIcon = new JLabel();
        hpIcon.setBounds(0,100,75,75);
        hpIcon.setIcon(new ImageIcon("src/picture/button/hp.png"));
        hpIcon.setVisible(true);
        hpIcon.setOpaque(false);
        playerInfo.add(hpIcon,Integer.valueOf(0));
        
        playerHP = new JLabel("  Your HP : "+p.getHP(),JLabel.LEFT);
        playerHP.setBounds(75,100,400,75);
        playerHP.setFont(new Font("Layiji MaHaNiYom V1.41", Font.PLAIN, 36));
        playerHP.setForeground(Color.WHITE);
        playerHP.setBackground(Color.BLACK);
        playerHP.setVisible(true);
        playerHP.setOpaque(true);
        playerInfo.add(playerHP,Integer.valueOf(0));
        
    //Create character icon
        iconChar = new JLabel();
        iconChar.setBounds(620,0,170,170);
        iconChar.setIcon(new ImageIcon("src/picture/character/princess/picon.png"));
        iconChar.setOpaque(false);
        iconChar.setVisible(true);
        this.add(iconChar);

        JLabel fireChar = new JLabel();   
        fireChar.setBounds(790,0,170,170);
        fireChar.setOpaque(false);
        fireChar.setVisible(true);
        this.add(fireChar);
        Timer timerFire = new Timer();
        timerFire.schedule(new TimerTask() {
            public void run() {
                if (p.canFire()) {
                    fireChar.setIcon(new ImageIcon("src/picture/character/knight/kicon.png"));
                }
            }
        }, 1,1);    
        
        JLabel windChar = new JLabel();   
        windChar.setBounds(960,0,170,170);
        windChar.setOpaque(false);
        windChar.setVisible(true);
        this.add(windChar);
        Timer timerWind = new Timer();
        timerWind.schedule(new TimerTask() {
            public void run() {
                if (p.canWind()) {
                    windChar.setIcon(new ImageIcon("src/picture/character/elf/eicon.png"));
                }
            }
        }, 1,1); 
        
        JLabel waterChar = new JLabel();
        waterChar.setBounds(1130,0,170,170);
        waterChar.setOpaque(false);
        waterChar.setVisible(true);
        this.add(waterChar);
        Timer timerWater = new Timer();
        timerWater.schedule(new TimerTask() {
            public void run() {
                if (p.canWater()) {
                    waterChar.setIcon(new ImageIcon("src/picture/character/drake/dicon.png"));
                }
            }
        }, 1,1); 
    
    //Moving the path    
        movePath();
        
    //Set BG
        ImageIcon bg = new ImageIcon("src/picture/background/BGgame"+((int)(Math.random()*(4-1))+1)+".png");
        background = new JLabel("", bg, JLabel.CENTER);
        background.setBounds(0, 0, WIDTH, HEIGHT);
        this.add(background);
        
        this.setVisible(true);
    }
    public void endGame(Display d, boolean win) {
        this.setVisible(false);
        GameOver gg = new GameOver(this, d, win);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==exitGame) {
            JFrame frame = new JFrame("EXIT");
            frame.setBounds(0, 0, 1000, 1000);
            if (JOptionPane.showConfirmDialog(frame, "Are you really want to exit?\n( Your progress will be lost! )", "EXIT", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                setExitGame();
                movePath();
                this.dispose();
                (this.d).setVisible(true);
            }
        }
    }
    public void setExitGame() {
        exit = true;
    }
    private JPanel[] pathList = new JPanel[5];
    public JPanel[] pathGenerate = new JPanel[31]; //ALSO USE IN PlayerMovement ******
    private JPanel pathPanel;
    private JLabel pathBG;
    private ImageIcon pathIMG;
    public JLayeredPane pathMoving;

    public JPanel getStartPath() {
        pathPanel = new JPanel();
        pathPanel.setBounds(280,80,1360,1010);
        pathPanel.setOpaque(false);
        
        pathBG = new JLabel();
        pathBG.setIcon(new ImageIcon("src/picture/path/pstart.png"));
        pathPanel.add(pathBG);

        return pathPanel;
    }
    public JPanel getEndPath() {
        pathPanel = new JPanel();
        pathPanel.setBounds(280,80,1360,1010);
        pathPanel.setOpaque(false);
        
        pathBG = new JLabel();
        pathBG.setIcon(new ImageIcon("src/picture/path/pEnd.png"));
        pathPanel.add(pathBG);

        return pathPanel;
    }
    public void makePathList() {
        for (int i=0; i<pathList.length; i++) {
            pathPanel = new JPanel();
            pathPanel.setVisible(true);
            if (i==0) {
                pathPanel.setBounds(280,160,1360,857);
                pathPanel.setOpaque(false);
                
                pathBG = new JLabel();
                pathBG.setIcon(new ImageIcon("src/picture/path/new/p"+i+".png"));
                pathPanel.add(pathBG);
            }
            else if (i==1) {
                pathPanel.setBounds(280,160+170,1360,517);
                pathPanel.setOpaque(false);
                
                pathBG = new JLabel();
                pathBG.setIcon(new ImageIcon("src/picture/path/new/p"+i+".png"));
                pathPanel.add(pathBG);
            }
            else if (i==2) {
                pathPanel.setBounds(280,160+170,1360,687);
                pathPanel.setOpaque(false);
                
                pathBG = new JLabel();
                pathBG.setIcon(new ImageIcon("src/picture/path/new/p"+i+".png"));
                pathPanel.add(pathBG);
            }
            else if (i==3) {
                pathPanel.setBounds(280,160,1360,687);
                pathPanel.setOpaque(false);
                
                pathBG = new JLabel();
                pathBG.setIcon(new ImageIcon("src/picture/path/new/p"+i+".png"));
                pathPanel.add(pathBG);
            }
            else if (i==4) {
                pathPanel.setBounds(280,330+170,1360,177);
                pathPanel.setOpaque(false);
                
                pathBG = new JLabel();
                pathBG.setIcon(new ImageIcon("src/picture/path/new/p"+i+".png"));
                pathPanel.add(pathBG);
            }
            pathPanel.setVisible(true);
            pathList[i] = pathPanel;
        }
    }
    public JPanel getPath(int type) { makePathList(); return pathList[type]; }
    public JPanel[] generatePath() {
        pathGenerate = new JPanel[31];
        int j=0;
        for (int i=0; i<pathGenerate.length; i++) {
            if (i==0) {
                pathPanel = getStartPath();
                pathPanel.setLocation(280,80);
                pathPanel.add(pathBG);
            }
            else if (i==6 || i==12 || i==18 || i==24 || i==29) {
                pathPanel = getPath(4);
                pathPanel.setLocation(280+1360*i, 500);
                if (i==6) {
                    Boss fireBoss = new Boss(800,1);
                    fireBoss.setLocation(280+1360*i+505, 300);
                    enemyMove.add(fireBoss,Integer.valueOf(j));
                    miniBoss.add(fireBoss);
                    bossPanelList.add(fireBoss);
                }
                else if (i==12) {
                    Boss windBoss = new Boss(6000,2);
                    windBoss.setLocation(280+1360*i+505, 300);
                    enemyMove.add(windBoss,Integer.valueOf(j));
                    miniBoss.add(windBoss);
                    bossPanelList.add(windBoss);
                }
                else if (i==18) {
                    Boss waterBoss = new Boss(200000,3);
                    waterBoss.setLocation(280+1360*i+505, 300);
                    enemyMove.add(waterBoss,Integer.valueOf(j));
                    miniBoss.add(waterBoss);
                    bossPanelList.add(waterBoss);
                }
                else if (i==24) {
                    Boss randomBoss1 = new Boss(500000,(int) (Math.random()*(2))+1);
                    randomBoss1.setLocation(280+1360*i+505, 300);
                    enemyMove.add(randomBoss1,Integer.valueOf(j));
                    miniBoss.add(randomBoss1);
                    bossPanelList.add(randomBoss1);
                    
                    Boss randomBoss2 = new Boss(1000000,(int) (Math.random()*(2))+1);
                    randomBoss2.setLocation(280+1360*i+905, 300);
                    enemyMove.add(randomBoss2,Integer.valueOf(j));
                    miniBoss.add(randomBoss2);
                    bossPanelList.add(randomBoss2);
                }
                else if (i==29) {
                    Boss randomBoss1 = new Boss(2100000,(int) (Math.random()*(2))+1);
                    randomBoss1.setLocation(280+1360*i+205, 300);
                    enemyMove.add(randomBoss1,Integer.valueOf(j));
                    miniBoss.add(randomBoss1);
                    bossPanelList.add(randomBoss1);
                    
                    Boss randomBoss2 = new Boss(2200000,(int) (Math.random()*(2))+1);
                    randomBoss2.setLocation(280+1360*i+705, 300);
                    enemyMove.add(randomBoss2,Integer.valueOf(j));
                    miniBoss.add(randomBoss2);
                    bossPanelList.add(randomBoss2);
                    
                    Boss randomBoss3 = new Boss(2300000,(int) (Math.random()*(2))+1);
                    randomBoss3.setLocation(280+1360*i+1205, 300);
                    enemyMove.add(randomBoss3,Integer.valueOf(j));
                    miniBoss.add(randomBoss3);
                    bossPanelList.add(randomBoss3);
                }
            }
            else if (i==30) {
                pathPanel = getEndPath();
                pathPanel.setLocation(280+1360*i,80); 
                pathPanel.add(pathBG);
                
                Boss fBoss = new Boss(2400000,0);
                fBoss.setLocation(280+1360*i+1050, 300);
                enemyMove.add(fBoss,Integer.valueOf(j));
                finalBoss[0] = fBoss;
                //miniBoss.add(finalBoss);
            }
            else if (i==1) {
                pathPanel = getPath(0);
                pathPanel.setLocation(280+1360*i, pathPanel.getY());
                
                JPanel e1 = new JPanel();
                e1.setOpaque(false);
                e1.setVisible(true);
                e1.setBounds(280+1360*i+405, 130, 170, 240);
                enemyPanel.add(e1);
                enemyMove.add(e1,Integer.valueOf(j));
                    
                JPanel e11 = new JPanel();
                e11.setOpaque(false);
                e11.setVisible(true);
                e11.setBounds(280+1360*i+655, 130, 170, 240);
                enemyPanel.add(e11);
                enemyMove.add(e11,Integer.valueOf(j));
                    
                JPanel e2 = new JPanel();
                e2.setOpaque(false);
                e2.setVisible(true);
                e2.setBounds(280+1360*i+405, 470, 170, 240);
                enemyPanel.add(e2);
                enemyMove.add(e2,Integer.valueOf(j));
                    
                JPanel e22 = new JPanel();
                e22.setOpaque(false);
                e22.setVisible(true);
                e22.setBounds(280+1360*i+655, 470, 170, 240);
                enemyPanel.add(e22);
                enemyMove.add(e22,Integer.valueOf(j));
                    
                JPanel e3 = new JPanel();
                e3.setOpaque(false);
                e3.setVisible(true);
                e3.setBounds(280+1360*i+405, 810, 170, 240);
                enemyPanel.add(e3);
                enemyMove.add(e3,Integer.valueOf(j));
                    
                JPanel e33 = new JPanel();
                e33.setOpaque(false);
                e33.setVisible(true);
                e33.setBounds(280+1360*i+655, 810, 170, 240);
                enemyPanel.add(e33);
                enemyMove.add(e33,Integer.valueOf(j));
                    
                JPanel e4 = new JPanel();
                e4.setOpaque(false);
                e4.setVisible(true);
                e4.setBounds(280+1360*i+1100, 470, 170, 240);
                enemyPanel.add(e4);
                enemyMove.add(e4,Integer.valueOf(j));
            }
            else {
                int max = 4; int min = 0;
                int typeOfPath = (int)(Math.random()*(max-min+1));
                pathPanel = getPath(typeOfPath);
                pathPanel.setLocation(280+1360*i, pathPanel.getY());
                if (typeOfPath == 0) {
                    JPanel e1 = new JPanel();
                    e1.setOpaque(false);
                    e1.setVisible(true);
                    e1.setBounds(280+1360*i+405, 130, 170, 240);
                    enemyPanel.add(e1);
                    enemyMove.add(e1,Integer.valueOf(j));
                    
                    JPanel e11 = new JPanel();
                    e11.setOpaque(false);
                    e11.setVisible(true);
                    e11.setBounds(280+1360*i+655, 130, 170, 240);
                    enemyPanel.add(e11);
                    enemyMove.add(e11,Integer.valueOf(j));
                    
                    JPanel e2 = new JPanel();
                    e2.setOpaque(false);
                    e2.setVisible(true);
                    e2.setBounds(280+1360*i+405, 470, 170, 240);
                    enemyPanel.add(e2);
                    enemyMove.add(e2,Integer.valueOf(j));
                    
                    JPanel e22 = new JPanel();
                    e22.setOpaque(false);
                    e22.setVisible(true);
                    e22.setBounds(280+1360*i+655, 470, 170, 240);
                    enemyPanel.add(e22);
                    enemyMove.add(e22,Integer.valueOf(j));
                    
                    JPanel e3 = new JPanel();
                    e3.setOpaque(false);
                    e3.setVisible(true);
                    e3.setBounds(280+1360*i+405, 810, 170, 240);
                    enemyPanel.add(e3);
                    enemyMove.add(e3,Integer.valueOf(j));
                    
                    JPanel e33 = new JPanel();
                    e33.setOpaque(false);
                    e33.setVisible(true);
                    e33.setBounds(280+1360*i+655, 810, 170, 240);
                    enemyPanel.add(e33);
                    enemyMove.add(e33,Integer.valueOf(j));
                }
                else if (typeOfPath == 1) {
                    JPanel e1 = new JPanel();
                    e1.setOpaque(false);
                    e1.setVisible(true);
                    e1.setBounds(280+1360*i+405, 300, 170, 240);
                    enemyPanel.add(e1);
                    enemyMove.add(e1,Integer.valueOf(j));
                    
                    JPanel e11 = new JPanel();
                    e11.setOpaque(false);
                    e11.setVisible(true);
                    e11.setBounds(280+1360*i+655, 300, 170, 240);
                    enemyPanel.add(e11);
                    enemyMove.add(e11,Integer.valueOf(j));
                    
                    JPanel e2 = new JPanel();
                    e2.setOpaque(false);
                    e2.setVisible(true);
                    e2.setBounds(280+1360*i+405, 640, 170, 240);
                    enemyPanel.add(e2);
                    enemyMove.add(e2,Integer.valueOf(j));
                    
                    JPanel e22 = new JPanel();
                    e22.setOpaque(false);
                    e22.setVisible(true);
                    e22.setBounds(280+1360*i+655, 640, 170, 240);
                    enemyPanel.add(e22);
                    enemyMove.add(e22,Integer.valueOf(j));
                   
                    JPanel e3 = new JPanel();
                    e3.setOpaque(false);
                    e3.setVisible(true);
                    e3.setBounds(280+1360*i+1100, 470, 170, 240);
                    enemyPanel.add(e3);
                    enemyMove.add(e3,Integer.valueOf(j));
                }
                else if (typeOfPath == 2) {
                    JPanel e1 = new JPanel();
                    e1.setOpaque(false);
                    e1.setVisible(true);
                    e1.setBounds(280+1360*i+405, 300, 170, 240);
                    enemyPanel.add(e1);
                    enemyMove.add(e1,Integer.valueOf(j));
                    
                    JPanel e11 = new JPanel();
                    e11.setOpaque(false);
                    e11.setVisible(true);
                    e11.setBounds(280+1360*i+655, 300, 170, 240);
                    enemyPanel.add(e11);
                    enemyMove.add(e11,Integer.valueOf(j));
                    
                    JPanel e2 = new JPanel();
                    e2.setOpaque(false);
                    e2.setVisible(true);
                    e2.setBounds(280+1360*i+405, 810, 170, 240);
                    enemyPanel.add(e2);
                    enemyMove.add(e2,Integer.valueOf(j));
                    
                    JPanel e22 = new JPanel();
                    e22.setOpaque(false);
                    e22.setVisible(true);
                    e22.setBounds(280+1360*i+655, 810, 170, 240);
                    enemyPanel.add(e22);
                    enemyMove.add(e22,Integer.valueOf(j));

                    JPanel e3 = new JPanel();
                    e3.setOpaque(false);
                    e3.setVisible(true);
                    e3.setBounds(280+1360*i+1100, 470, 170, 240);
                    enemyPanel.add(e3);
                    enemyMove.add(e3,Integer.valueOf(j));
                }
                else if (typeOfPath == 3) {
                    JPanel e1 = new JPanel();
                    e1.setOpaque(false);
                    e1.setVisible(true);
                    e1.setBounds(280+1360*i+405, 130, 170, 240);
                    enemyPanel.add(e1);
                    enemyMove.add(e1,Integer.valueOf(j));

                    JPanel e11 = new JPanel();
                    e11.setOpaque(false);
                    e11.setVisible(true);
                    e11.setBounds(280+1360*i+655, 130, 170, 240);
                    enemyPanel.add(e11);
                    enemyMove.add(e11,Integer.valueOf(j));
                    
                    JPanel e2 = new JPanel();
                    e2.setOpaque(false);
                    e2.setVisible(true);
                    e2.setBounds(280+1360*i+405, 640, 170, 240);
                    enemyPanel.add(e2);
                    enemyMove.add(e2,Integer.valueOf(j));
                    
                    JPanel e22 = new JPanel();
                    e22.setOpaque(false);
                    e22.setVisible(true);
                    e22.setBounds(280+1360*i+655, 640, 170, 240);
                    enemyPanel.add(e22);
                    enemyMove.add(e22,Integer.valueOf(j));
                }
                else if (typeOfPath == 4) {
                    JPanel e1 = new JPanel();
                    e1.setOpaque(false);
                    e1.setVisible(true);
                    e1.setBounds(280+1360*i+300, 470, 170, 240);
                    enemyMove.add(e1,Integer.valueOf(j));
                    enemyPanel.add(e1);
                    
                    JPanel e2 = new JPanel();
                    e2.setOpaque(false);
                    e2.setVisible(true);
                    e2.setBounds(280+1360*i+700, 470, 170, 240);
                    enemyMove.add(e2,Integer.valueOf(j));
                    enemyPanel.add(e2);

                    JPanel e3 = new JPanel();
                    e3.setOpaque(false);
                    e3.setVisible(true);
                    e3.setBounds(280+1360*i+1100, 470, 170, 240);
                    enemyMove.add(e3,Integer.valueOf(j));
                    enemyPanel.add(e3);
                }
            }
            pathMoving.add(pathPanel,Integer.valueOf(i));
            pathGenerate[i] = pathPanel;
            j++;
        }
        this.add(pathMoving);
        return pathGenerate;
    }
    
    public void movePath() {
        Timer timeMove = new Timer();
        TimerTask timeTaskMove = new TimerTask() {
            int xMove;
            @Override
            public void run() {
                while (true) {
                    xMove -= 1;
                    pathMoving.setLocation(xMove, 0);
                    bossArea.setLocation(-xMove,160);
                    for (int i=0; i<enemyPanel.size(); i++) {
                        if (enemyPanel.get(i) != null) {
                            if (enemyPanel.get(i).getX()+xMove <= 1920) {
                                int element = (int) (Math.random()*(3))+1;
                                Enemy e = new Enemy(p, element);
                                enemyOBJlist.add(e);
                                enemyPanel.get(i).add(e);
                                enemyPanelList.add(enemyPanel.get(i));
                                enemyPanel.set(i,null);
                            }
                        }
                    }
                    if (p.getX()+xMove <= 100) {
                        GameOver go = new GameOver(thisgame,d,false);
                        break;
                    }
                    if (pathMoving.getX()== -(1360*30) || p.getHP()==0) {
                        break;
                    }
                    if (exit) {
                        break;
                    }
                    try {
                        sleep(13);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }; timeMove.schedule(timeTaskMove,3000);
    }
    
}