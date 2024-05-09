import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Panel {
    private JFrame frame;
    private JPanel panel;
    private JPanel startPanel;
    private GamePanel gamePanel;
    public final static int WIDTH = 1024;
    public final static int HEIGHT = 768;
    private int bodySize = 20;
    private int positionXHead = 120;
    private int positionYHead = 120;

    public void showFrame() {
        frame = new JFrame();
        frame.setTitle("Title");

        startPanel = createStartPanel();
        gamePanel = createGamePanel();
        frame.getContentPane().add(startPanel);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createStartPanel() {
        panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, (HEIGHT / 2)));

        JButton button = new JButton("PLAY");
        button.addActionListener(e -> {
            frame.getContentPane().remove(startPanel);
            frame.getContentPane().add(gamePanel);
            frame.revalidate();
            frame.repaint();
            gamePanel.requestFocusInWindow();
        });

        panel.add(button);
        return panel;
    }

    private GamePanel createGamePanel() {
        GamePanel panel = new GamePanel();
        panel.setBackground(Color.BLACK);
        panel.setFocusable(true);
        return panel;
    }

    public class GamePanel extends JPanel {
        private List<Point> body;

        public GamePanel() {
            body = new ArrayList<>();
            body.add(new Point(positionXHead - bodySize - 5, positionYHead));
            body.add(new Point(positionXHead - 2 * (bodySize + 5), positionYHead));
            body.add(new Point(positionXHead - 3 * (bodySize + 5), positionYHead));

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    switch (keyCode) {
                        case KeyEvent.VK_W:
                            moveSnake(0, -1);
                            break;
                        case KeyEvent.VK_S:
                            moveSnake(0, 1);
                            break;
                        case KeyEvent.VK_A:
                            moveSnake(-1, 0);
                            break;
                        case KeyEvent.VK_D:
                            moveSnake(1, 0);
                            break;
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawSnake(g);
        }

        private void drawSnake(Graphics g) {
            if (body != null) {
                for (Point point : body) {
                    g.setColor(Color.GREEN);
                    g.fillRect(point.x, point.y, bodySize, bodySize);
                }
                g.setColor(Color.RED);
                g.fillRect(positionXHead, positionYHead, bodySize, bodySize);
            }

        }

        private void moveSnake(int x, int y ) {
            for (int i = body.size() - 1; i > 0; i--) {
                body.get(i).setLocation(body.get(i - 1));
            }
            int posa = positionXHead += x * (bodySize + 5);
            int posb = positionYHead += y * (bodySize + 5);
            body.get(0).setLocation(posa, posb);
            collision(posa, posb);

            repaint();

        }

        private void collision(int posa, int posb){
            if(posa >= 970 || posa <= 20 ||  posb >= 695 || posb <= 20){
                int opcja = JOptionPane.showConfirmDialog(
                        frame,
                        "Wyjechałeś poza krawędź \n Czy chcesz zagrać jeszcze raz?.",
                        "GAME OVER",
                        JOptionPane.YES_NO_OPTION
                );
                if(opcja == JOptionPane.YES_OPTION){
                    positionXHead = 120;
                    positionYHead = 120;
                    //frame.dispose();
                    //showFrame();
                } else {
                    System.exit(1);
                }
                System.out.println(posa);
                System.out.println(posb);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
        }
    }
}
