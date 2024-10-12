import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Main extends JFrame implements KeyListener {

    private Tile[][] field;
    final private int FIELD_WIDTH = 5;
    final private int FIELD_HEIGHT = 5;
    final private int FIELD_SIZE = 100;

    public void generateMap(int w, int h) {

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
                                tile.buildStructure(new Farm());
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
                        remove((JButton) tile);
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

    public Main() {
        setSize(504, 518);
        setTitle("Grid Test");
        ImageIcon icon = new ImageIcon("assets/icon.jpg");
        setIconImage(icon.getImage());

        generateMap(FIELD_WIDTH, FIELD_HEIGHT);

        addKeyListener(this);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()) {
            case 'e':
                clearMap();
                System.out.println("Regenerating map. . .");
                generateMap(FIELD_WIDTH, FIELD_HEIGHT);
                break;
            case 'd':
                clearMap();
                break;
            case '':
                System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}