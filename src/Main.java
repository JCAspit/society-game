import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
//import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame /*implements KeyListener*/ {

    private Tile[][] field;
    final private int FIELD_WIDTH = 10;
    final private int FIELD_HEIGHT = 10;
    final private int FIELD_SIZE = 50;
    private Map<String, Integer> currentResources = Map.of(
            "Wheat", 0
    );

    private void generateMap(int w, int h) {

        Tile[][] grid = new Tile[w][h];

        for (int j = 0; j < w; j++) {
            for (int k = 0; k < h; k++) {

                int random = (int) (Math.random() * 10 - 0 + 1) + 0;
                if(random >= 1 && random <= 5) {
                    Ground tile = new Ground(j * FIELD_SIZE, k * FIELD_SIZE, FIELD_SIZE);

                    //tile.setBorder(null);
                    tile.setBounds(j * FIELD_SIZE, k * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);
                    tile.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            if(tile.getStructure() == null) {
                                Farm farm = new Farm();
                                buildStructure(tile, farm);
                            }
                        }
                    });

                    grid[j][k] = tile;
                    add(tile);

                }else if(random >= 6 && random <= 10) {
                    Water tile = new Water(j * FIELD_SIZE, k * FIELD_SIZE, FIELD_SIZE);

                    tile.setBorder(null);
                    tile.setBounds(j * FIELD_SIZE, k * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);

                    grid[j][k] = tile;
                    add(tile);

                }else {
                    System.out.println("Uh oh " + random);
                }
            }
        }
        repaint();

        field = grid;
    }

    private void clearMap() {
        if(field == null) {
            System.out.println("Field is already clear.");
        }else {
            System.out.println("Clearing map . . .");
            for (Tile[] row : field) {
                for (Tile tile : row) {
                    try {
                        remove((JComponent) tile);
                    }
                    catch(Exception e) {
                        System.out.println(e.toString());
                    }
                }
            }
            repaint();
            field = null;
        }
    }

    private void buildStructure(Ground tile, Structure structure) {
        boolean validBuild = true;

        Map<String, Integer> buildEntries = structure.getBuildResources();
        for(Map.Entry<String, Integer> buildKey: buildEntries.entrySet()) {
            for(Map.Entry<String, Integer> currKey: currentResources.entrySet()) {
                if(currKey.getKey() == buildKey.getKey() && currKey.getValue() < buildKey.getValue()) {
                    System.out.println("Not enough of material: " + buildKey.getKey());
                    System.out.println("Required: " + buildKey.getValue() + " Have: " + currKey.getValue());
                    validBuild = false;
                    break;
                }
            }
        }

        if(validBuild) {
            // remove resources
            tile.setStructure(structure);
        }
    }

    private Runnable tick = new Runnable() {
        @Override
        public void run() {
        }
    };

    public Main() {
        setSize(504, 518);
        setTitle("Grid Test");
        ImageIcon icon = new ImageIcon("assets/icon.jpg");
        setIconImage(icon.getImage());

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(tick, 0, 500, TimeUnit.MILLISECONDS);

        generateMap(FIELD_WIDTH, FIELD_HEIGHT);

        //addKeyListener(this);

        JLabel listener = new JLabel();
        listener.getInputMap(listener.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("E"), "generateMap");
        listener.getActionMap().put("generateMap", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMap();
                System.out.println("Regenerating map. . .");
                generateMap(FIELD_WIDTH, FIELD_HEIGHT);
            }
        });
        listener.getInputMap(listener.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "clearMap");
        listener.getActionMap().put("clearMap", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMap();
            }
        });
        add(listener);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusTraversalKeysEnabled(true);
        setFocusable(true);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    //@Override
    //public void keyTyped(KeyEvent e) {}

    //@Override
    //public void keyPressed(KeyEvent e) {
    //    switch(e.getKeyChar()) {
    //        case 'e':
    //            clearMap();
    //            System.out.println("Regenerating map. . .");
    //            generateMap(FIELD_WIDTH, FIELD_HEIGHT);
    //            break;
    //        case 'd':
    //            clearMap();
    //            break;
    //        case '':
    //            System.exit(0);
    //    }
    //}

    //@Override
    //public void keyReleased(KeyEvent e) {}
}