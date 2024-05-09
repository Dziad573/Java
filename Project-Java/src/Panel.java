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
    private Point applePosition;
    private int positionXHeadAfterMove;
    private int positionYHeadAfterMove;

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
        private ArrayList<Point> body;

        public GamePanel() {
            body = new ArrayList<>();
            body.add(new Point(positionXHead,positionYHead));
            body.add(new Point(positionXHead,positionYHead));
            body.add(new Point(positionXHead,positionYHead));
            generateApplePosition();

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
            drawApple(g);
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

        private void moveSnake(int x, int y) {

            for (int i = body.size() - 1; i > 0; i--) {
                body.get(i).setLocation(body.get(i-1));
                repaint();
            }
            positionXHeadAfterMove = positionXHead += x * (bodySize + 5);
            positionYHeadAfterMove = positionYHead += y * (bodySize + 5);
            body.get(0).setLocation(positionXHeadAfterMove, positionYHeadAfterMove);
            collision(positionXHeadAfterMove, positionYHeadAfterMove);
            eat();
        }

        private void collision(int positionXHeadAfterMove, int positionYHeadAfterMove){
            if(
                    positionXHeadAfterMove >= frame.getWidth() - bodySize * 2 || positionXHeadAfterMove <= 5 ||
                    positionYHeadAfterMove >= frame.getHeight() - bodySize * 3 || positionYHeadAfterMove <= 5
              ){
                int opcja = JOptionPane.showConfirmDialog(
                        frame,
                        "Wyjechałeś poza krawędź. \n Czy chcesz zagrać jeszcze raz?",
                        "GAME OVER",
                        JOptionPane.YES_NO_OPTION
                );
                if(opcja == JOptionPane.YES_OPTION){
                    positionXHead = 120;
                    positionYHead = 120;
                    generateApplePosition();
                    body.removeRange(3,body.size()-1);

                    //frame.dispose();
                    //showFrame();
                } else {
                    System.exit(1);
                }
                //System.out.println(positionXHeadAfterMove);
                //System.out.println(positionYHeadAfterMove);
            }
        }

        private void generateApplePosition() {
            int minX = bodySize + 5;
            int maxX = frame.getWidth() - bodySize * 3;
            int minY = bodySize + 5;
            int maxY = frame.getHeight() - bodySize * 4 ;
            int rangeX = (maxX - minX) / 25;
            int rangeY = (maxY - minY) / 25;
            int randomIndexX = (int) (Math.random() * rangeX);
            int randomIndexY = (int) (Math.random() * rangeY);
            int appleElementPositionX = minX + randomIndexX * 25 - 5;
            int appleElementPositionY = minY + randomIndexY * 25 - 5;
            applePosition = new Point(appleElementPositionX, appleElementPositionY);

            System.out.println(appleElementPositionX);
            System.out.println(appleElementPositionY);
        }

        private void drawApple(Graphics g) {
            while(applePosition.x < bodySize + 5 || applePosition.y < bodySize + 5){
                generateApplePosition();
            }
            g.setColor(Color.BLUE);
            g.fillRect(applePosition.x, applePosition.y, bodySize, bodySize);
        }

        private void eat(){
            int i = 4;
            while(i < 100){
                if(positionXHeadAfterMove == applePosition.x && positionYHeadAfterMove == applePosition.y){
                    generateApplePosition();
                    body.add(new Point(positionXHead, positionYHead));
                    repaint();
                }
                i++;
            }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
        }
    }
}
