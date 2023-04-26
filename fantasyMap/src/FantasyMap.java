import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class FantasyMap extends JFrame implements ActionListener {

    private static final int TILE_SIZE = 64;
    private static final int HEIGHT = 1024;
    private static final int WIDTH = 512;

    private Map<Integer, Color> terrainColors = new HashMap<>();
    private Map<Integer, String> terrainNames = new HashMap<>();

    private int[][] map;
    private int playerX;
    private int playerY;

    private JPanel mapPanel;
    private JLabel playerLabel;

    public FantasyMap() {
        super("Fantasy Map");

        terrainColors.put(0, Color.white); // plains
        terrainColors.put(1, Color.green); // forest
        terrainColors.put(2, Color.yellow); // desert
        terrainColors.put(3, Color.blue); // ocean
        terrainColors.put(4, Color.red); // mountain

        terrainNames.put(0, "Plains");
        terrainNames.put(1, "Forest");
        terrainNames.put(2, "Desert");
        terrainNames.put(3, "Ocean");
        terrainNames.put(4, "Mountain");

        map = new int[HEIGHT / TILE_SIZE][WIDTH / TILE_SIZE];
        playerX = 0;
        playerY = 0;

        mapPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (int y = 0; y < HEIGHT / TILE_SIZE; y++) {
                    for (int x = 0; x < WIDTH / TILE_SIZE; x++) {
                        g.setColor(terrainColors.get(map[y][x]));
                        g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    }
                }

                g.setColor(Color.black);
                g.drawRect(0, 0, WIDTH, HEIGHT);

                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.drawString("Player: " + playerX + ", " + playerY, 10, HEIGHT - 20);
            }
        };

        playerLabel = new JLabel("Player");

        add(mapPanel, BorderLayout.CENTER);
        add(playerLabel, BorderLayout.SOUTH);

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                switch (key) {
                    case KeyEvent.VK_UP:
                        if (playerY > 0) {
                            playerY--;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (playerY < map.length - 1) {
                            playerY++;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (playerX > 0) {
                            playerX--;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (playerX < map[0].length - 1) {
                            playerX++;
                        }
                        break;
                }

                mapPanel.repaint();

                // Added this line to fix the bug
                e.consume();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Do nothing.
    }

    public static void main(String[] args) {
        new FantasyMap();
    }
}
