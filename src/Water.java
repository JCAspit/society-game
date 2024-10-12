import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Water extends JLabel implements Tile {
    private final int x;
    private final int y;
    private final Color color = Color.BLUE;
    private int FIELD_SIZE;

    public Water() {
        this.x = 0;
        this.y = 0;
    }

    public Water(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Water(int x, int y, int FIELD_SIZE) {
        this.x = x;
        this.y = y;
        this.FIELD_SIZE = FIELD_SIZE;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Color getColor() {
        return this.color;
    }

    public String toString() {
        return "Water " + this.x + ":" + this.y;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(color);
        g2.fillRect(0, 0, FIELD_SIZE, FIELD_SIZE);
    }
}
