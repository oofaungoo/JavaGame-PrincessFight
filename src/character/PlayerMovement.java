package character;

import game.Game;
import game.GameOver;
import static game.Game.playerHP;
import static game.Game.playerPower;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerMovement implements MouseListener, MouseMotionListener{
    private Random r = new Random();
    private int x, y, xReleased, yReleased, xPrev=450, yPrev=450;
    private JPanel[] map;
    private Player p;
    private ArrayList<JPanel> enemyPanelList;    //List of non-null panel
    private ArrayList<Enemy> enemyOBJlist;      //List of enemy (Character obj)
    private ArrayList<JPanel> bossPanelList;
    private ArrayList<Boss> miniBoss;
    private Boss[] finalBoss;
    private JLabel[] areaGameOver;
    private int iForAttack;
    private Game g;
    private GameOver go;
    private boolean canMove=true;
    
    public PlayerMovement(JPanel panel, JPanel[] map, Player p, ArrayList<JPanel> enemyPanelList, ArrayList<Enemy> enemyOBJlist,
            ArrayList<JPanel> bossPanelList, ArrayList<Boss> miniBoss,Boss[] finalBoss, JLabel[] areaGameOver, Game g) {
        this.map = map;
        this.p = p;
        this.g = g;
        this.enemyPanelList = enemyPanelList;
        this.enemyOBJlist = enemyOBJlist;
        this.bossPanelList = bossPanelList;
        this.miniBoss = miniBoss;
        this.finalBoss = finalBoss;
        this.areaGameOver = areaGameOver;

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (canMove)
            e.getComponent().setLocation((e.getX()+e.getComponent().getX())-x, (e.getY()+e.getComponent().getY())-y);
    }
    @Override
    public void mousePressed(MouseEvent e) { x = e.getX(); y = e.getY(); } 
    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (canMove) {
        xReleased = (e.getX()+e.getComponent().getX())-x ;
        yReleased = (e.getY()+e.getComponent().getY())-y;
        if ((((Player)e.getComponent()).getBounds()).intersects(map[0].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[1].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[2].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[3].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[4].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[5].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[6].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[7].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[8].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[9].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[10].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[11].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[12].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[13].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[14].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[15].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[16].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[17].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[18].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[19].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[20].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[21].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[22].getBounds()) ||  
            (((Player)e.getComponent()).getBounds()).intersects(map[23].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[24].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[25].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[26].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[27].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[28].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[29].getBounds()) ||
            (((Player)e.getComponent()).getBounds()).intersects(map[30].getBounds())) {
                e.getComponent().setLocation(xReleased, yReleased);
                xPrev=xReleased; yPrev=yReleased;
                if ((((Player)e.getComponent()).getBounds()).intersects(map[6].getBounds())) {
                    p.enableFire();
                }
                else if ((((Player)e.getComponent()).getBounds()).intersects(map[12].getBounds())) {
                    p.enableWind();
                }
                else if ((((Player)e.getComponent()).getBounds()).intersects(map[18].getBounds())) {
                    p.enableWater();
                }
        }
        else 
            e.getComponent().setLocation(xPrev,yPrev);
        
        for (int i=0; i<enemyPanelList.size(); i++) {
            if ((((Player)e.getComponent()).getBounds()).intersects(enemyPanelList.get(i).getBounds())) {
                iForAttack = i;
                canMove=false;
                p.setLocation(enemyPanelList.get(i).getX()-100,enemyPanelList.get(i).getY());
                Timer timeAttack = new Timer(); 
                TimerTask timeAttackTask = new TimerTask() {
                    @Override
                    public void run() {
                        canMove=true;
                        compare(p,enemyOBJlist.get(iForAttack));
                        enemyPanelList.get(iForAttack).setBounds(0,0,0,0);
                    }
                }; timeAttack.schedule(timeAttackTask,2000);
            }
        }
        for (int j=0; j<bossPanelList.size(); j++) {
            if ((((Player)e.getComponent()).getBounds()).intersects(bossPanelList.get(j).getBounds())) {
                iForAttack = j;
                canMove=false;
                e.getComponent().setLocation(bossPanelList.get(j).getX()-100,bossPanelList.get(j).getY()+120);
                Timer timeAttack = new Timer(); 
                TimerTask timeAttackTask = new TimerTask() {
                    @Override
                    public void run() {  
                        canMove=true;
                        compare(p,miniBoss.get(iForAttack));
                        miniBoss.get(iForAttack).setBounds(0,0,0,0);
                    }
                }; timeAttack.schedule(timeAttackTask,2000);
            }
        }
        if ((((Player)e.getComponent()).getBounds()).intersects(finalBoss[0].getBounds())) {
            e.getComponent().setLocation(finalBoss[0].getX()-100,finalBoss[0].getY()+120);
            canMove=false;
            Timer timeAttack = new Timer(); 
                TimerTask timeAttackTask = new TimerTask() {
                    @Override
                    public void run() {
                        canMove = true;
                        finalBoss[0].setBounds(0,0,0,0);
                        go = new GameOver(g,Game.d,compareFinal(p,finalBoss[0]));
                    }
                }; timeAttack.schedule(timeAttackTask,2000);
        }
        if ((((Player)e.getComponent()).getBounds()).intersects(areaGameOver[0].getBounds())) {
            go = new GameOver(g,Game.d,false);
        }
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
    public void compare(Player p, Character e) {
        if (p.getElement()==0)
            switch (e.getElement()) {
                case 0: p.setDefaultPower(p.getPower() + p.getPower()*20/100);
                        break;
                default:p.setDefaultPower(p.getPower());
                        break;
            }
        if (p.getElement()==1) {
            switch (e.getElement()) {
                case 2: p.setDefaultPower(p.getPower() + p.getPower()*20/100);
                        break;
                case 3: p.setDefaultPower(p.getPower() - p.getPower()*20/100);
                        break;
                default:p.setDefaultPower(p.getPower());
                        break;
            }
        }
        if (p.getElement()==2) {
            switch (e.getElement()) {
                case 3: p.setDefaultPower(p.getPower() + p.getPower()*20/100);
                        break;
                case 1: p.setDefaultPower(p.getPower() - p.getPower()*20/100);
                        break;
                default:p.setDefaultPower(p.getPower());
                        break;
            }
        }
        if (p.getElement()==3) {
            switch (e.getElement()) {
                case 1: p.setDefaultPower(p.getPower() + p.getPower()*20/100);
                        break;
                case 2: p.setDefaultPower(p.getPower() - p.getPower()*20/100);
                        break;
                default:p.setDefaultPower(p.getPower());
                        break;
            }
        }
        if (p.getDefaultPower() >= e.getPower()) {
            if (p.getPower()<2400000)
                p.setPower(p.getPower()+e.getPower());
            else if (p.getPower()>=2400000)
                p.setPower(2400000);
            int freeHP = r.nextInt(5); if (freeHP == 3) p.upHP(1);
        }
        else {
            p.upHP(-1);
            if (p.getHP()==0)
                go = new GameOver(g,Game.d,false);
        }
        Game.indexOfPowerText+=1;
        if (p.getPower()<2400000) {
            playerPower = new JLabel("  Your power : "+p.getPower(),JLabel.LEFT);
            playerPower.setBounds(75,20,400,75);
            playerPower.setFont(new Font("Layiji MaHaNiYom V1.41", Font.PLAIN, 36));
            playerPower.setForeground(Color.WHITE);
            playerPower.setBackground(Color.BLACK);
            playerPower.setVisible(true);
            playerPower.setOpaque(true);
            Game.playerInfo.add(playerPower, Integer.valueOf(Game.indexOfPowerText));
        }
        else if (p.getPower()>=2400000) {
            playerPower = new JLabel("  Your power : 2400000 (MAX)",JLabel.LEFT);
            playerPower.setBounds(75,20,400,75);
            playerPower.setFont(new Font("Layiji MaHaNiYom V1.41", Font.PLAIN, 36));
            playerPower.setForeground(Color.WHITE);
            playerPower.setBackground(Color.BLACK);
            playerPower.setVisible(true);
            playerPower.setOpaque(true);
            Game.playerInfo.add(playerPower, Integer.valueOf(Game.indexOfPowerText));
        }
        playerHP = new JLabel("  Your HP : "+p.getHP(),JLabel.LEFT);
        playerHP.setBounds(75,100,400,75);
        playerHP.setFont(new Font("Layiji MaHaNiYom V1.41", Font.PLAIN, 36));
        playerHP.setForeground(Color.WHITE);
        playerHP.setBackground(Color.BLACK);
        playerHP.setVisible(true);
        playerHP.setOpaque(true);
        Game.playerInfo.add(playerHP, Integer.valueOf(Game.indexOfPowerText));
    }
    public boolean compareFinal(Player p, Boss finalB) {
        switch (p.getElement()){
            case 0: p.setDefaultPower(p.getPower() + p.getPower()*20/100);
                    break;
            default:p.setDefaultPower(p.getPower());
                    break;
        }
        if (p.getDefaultPower() >= finalB.getPower()) {
            return true;
        }
        else {
            return false;
        }
    }
    
}