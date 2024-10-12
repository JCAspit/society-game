import javax.swing.*;
import java.awt.*;

public class Farm extends JLabel implements Structure {
    private final Color color = new Color(145, 80, 0);
    private final ImageIcon image = null;

    public Farm() {}

    public Color getColor() {
        return this.color;
    }
}
