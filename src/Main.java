import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Main extends JFrame implements KeyListener {

    private Tile[][] field;
    final private int FIELD_WIDTH = 5;
    final private int FIELD_HEIGHT = 5;

    public void generateMap(int w, int h) {

        Tile[][] grid = new Tile[w][h];

        for (int j = 0; j < w; j++) {
            for (int k = 0; k < h; k++) {

                int random = (int) (Math.random() * 2 - 0 + 1) + 0;
                switch (random) {
                    case 1: {
                        Ground tile = new Ground(j * 100, k * 100);

                        //tile.setBorder(null);
                        tile.setBounds(j * 100, k * 100, 100, 100);
                        tile.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                repaint();
                            }
                        });

                        grid[j][k] = tile;
                        add(tile);
                        break;
                    }

                    case 2: {
                        Water tile = new Water(j * 100, k * 100);

                        tile.setBorder(null);
                        tile.setBounds(j * 100, k * 100, 100, 100);

                        grid[j][k] = tile;
                        add(tile);
                        break;
                    }

                    default:
                        System.out.println("Uh oh");
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
                    remove((JButton) tile);
                }
            }
            repaint();
            field = null;
        }
    }

    public Main() {
        setSize(505, 519);
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