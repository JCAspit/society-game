import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Farm extends JLabel implements Structure {
    private final Color color = new Color(145, 80, 0);
    private final ImageIcon image = null;
    private final double buildTime = 5; // Seconds
    private final Map<String, Integer> buildResources = Map.of(
    );

    public Farm() {}

    public Color getColor() {
        return this.color;
    }

    public Map<String, Integer> getBuildResources() {
        return this.buildResources;
    }
}
