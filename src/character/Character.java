package character;

import java.util.Random;
import javax.swing.JPanel;
        
public class Character extends JPanel {
    public int power, defaultPower, element;
    private int hp = 4;
    private Random r = new Random();
    public Character(int power, int element) {
       this.power = power;
       defaultPower = power;
       this.element = element;
    }
    public int getPower() { return power; }
    public int getHP() {return hp; }
    public int getElement() { return element; }
    public int getDefaultPower() { return defaultPower; }
    public void setElement(int element) { this.element = element; }
    public void setDefaultPower(int power) { this.defaultPower = power; }
    public void upHP(int hp) { this.hp += hp; }
    public void setPower(int power) { this.power = power; }
}
