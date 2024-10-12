import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ground extends JButton implements Tile {
    private final int x;
    private final int y;
    private final Color color = new Color(0, 195, 0);
    private Structure structure;
    private int FIELD_SIZE;

    public Ground() {
        this.x = 0;
        this.y = 0;
        this.FIELD_SIZE = 100;
    }

    public Ground(int x, int y) {
        this.x = x;
        this.y = y;
        this.FIELD_SIZE = 100;
    }

    public Ground(int x, int y, int FIELD_SIZE) {
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

    public Structure getStructure() {
        return this.structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public String toString() {
        return "Ground " + this.x + ":" + this.y;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(color);
        g2.fillRect(0, 0, FIELD_SIZE, FIELD_SIZE);

        if(structure != null) {
            g2.setPaint(structure.getColor());
            g2.fillRect(5, 5, getWidth()-10, getHeight()-10);
        }
    }
}
