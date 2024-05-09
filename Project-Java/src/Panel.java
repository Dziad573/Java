import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Panel {
    public JFrame frame;
    private JPanel panel;
    private JPanel startPanel;
    private GamePanel gamePanel;
    public final static int WIDTH = 1024;
    public final static int HEIGHT = 768;

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
        GamePanel panel = new GamePanel(frame);
        panel.setBackground(Color.BLACK);
        panel.setFocusable(true);
        return panel;
    }
}
