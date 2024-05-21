import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel{
    private JFrame frame;
    private Panel mainPanel;
    private ArrayList<Point> body;
    private int bodySize = 20;
    private Timer timer;
    private int directionX = 1;
    private int directionY = 0;
    private int positionXHead = 120;
    private int positionYHead = 120;
    private Point applePosition;
    private int positionXHeadAfterMove;
    private int positionYHeadAfterMove;
    private boolean isActive = false;

    public GamePanel(JFrame frame, Panel mainPanel) {
        this.frame = frame;
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
                        directionX = 0;
                        directionY = -1;
                        break;
                    case KeyEvent.VK_S:
                        directionX = 0;
                        directionY = 1;
                        break;
                    case KeyEvent.VK_A:
                        directionX = -1;
                        directionY = 0;
                        break;
                    case KeyEvent.VK_D:
                        directionX = 1;
                        directionY = 0;
                        break;
                    case KeyEvent.VK_ESCAPE:
                        frame.getContentPane().remove(GamePanel.this);
                        frame.getContentPane().add(mainPanel.getStartPanel());
                        frame.revalidate();
                        frame.repaint();
                        isActive = false;
                        timer.stop();
                        mainPanel.getStartPanel().requestFocusInWindow();
                        break;
                }
            }
        });
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveSnake(directionX, directionY);
            }
        });

    }

    public void activateGamePanel() {
        resetGame();
        isActive = true;
        timer.start();
        requestFocusInWindow();
    }
    public void activateContinueGamePanel() {
        isActive = true;
        timer.start();
        requestFocusInWindow();
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
        if (!isActive) return;
        if(
                positionXHeadAfterMove >= frame.getWidth() - bodySize * 2 ||
                positionXHeadAfterMove <= 5 ||
                positionYHeadAfterMove >= frame.getHeight() - bodySize * 3 ||
                positionYHeadAfterMove <= 5
        ){
            timer.stop();
            showGameOverDialog();
            return;
        }

        for (int i = 1; i < body.size() - 1; i++) {
            Point segment = body.get(i);
            if (
                    positionXHeadAfterMove == segment.x &&
                    positionYHeadAfterMove == segment.y ||
                    positionXHeadAfterMove == body.get(1).getX() &&
                    positionYHeadAfterMove == body.get(1).getY()
            ) {
                timer.stop();
                showGameOverDialog();
                return;
            }

            int firstBodyElementX = body.get(1).getLocation().x;
            int firstBodyElementY = body.get(1).getLocation().y;
            int secondBodyElementX = body.get(2).getLocation().x;
            int secondBodyElementY = body.get(2).getLocation().y;
            if (
                    positionXHeadAfterMove == firstBodyElementX &&
                    positionYHeadAfterMove == firstBodyElementY ||
                    positionXHeadAfterMove == secondBodyElementX &&
                    positionYHeadAfterMove == secondBodyElementY
            ){
                timer.stop();
                showGameOverDialog();
                return;
            }


        }
    }


    private void showGameOverDialog(){
        int opcja = JOptionPane.showConfirmDialog(
                frame,
                "Wyjechałeś poza krawędź. \n " +
                        "Czy chcesz zagrać jeszcze raz? \n " +
                        " Twój wynik to: " + (body.size() - 3),
                "GAME OVER",
                JOptionPane.YES_NO_OPTION
        );
        if(opcja == JOptionPane.YES_OPTION){
                /*positionXHead = 120;
                positionYHead = 120;
                generateApplePosition();
                frame.repaint();

                while(body.size()>3){
                    body.remove(body.size()-1);
                }*/
            resetGame();

            //frame.dispose();
            //showFrame();
        } else {
            System.exit(1);
        }
    }


    private void resetGame() {
        positionXHead = 120;
        positionYHead = 120;
        generateApplePosition();
        body.clear();
        body.add(new Point(positionXHead, positionYHead));
        body.add(new Point(positionXHead, positionYHead));
        body.add(new Point(positionXHead, positionYHead));
        moveSnake(directionX = 1, directionY = 0);
        isActive = true;
        timer.start();
        repaint();
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

        System.out.println("Apple x and y " + appleElementPositionX + " " + appleElementPositionY);
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