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
    public JPanel panel;
    private JPanel startPanel;
    private GamePanel gamePanel;
    private OptionsPanel optionsPanel;
    public final static int WIDTH = 1024;
    public final static int HEIGHT = 768;

    public void showFrame() {
        frame = new JFrame();
        frame.setTitle("Title");

        startPanel = createStartPanel();
        gamePanel = createGamePanel();
        optionsPanel = createOptionsPanel();
        frame.getContentPane().add(startPanel);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JPanel createStartPanel() {
        panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, (HEIGHT / 2)));

        JButton playButton = new JButton("PLAY");
        playButton.addActionListener(e -> {
            frame.getContentPane().remove(startPanel);
            frame.getContentPane().add(gamePanel);
            frame.revalidate();
            frame.repaint();
            gamePanel.requestFocusInWindow();
        });

        JButton optionsButton = new JButton("OPTIONS");
        optionsButton.addActionListener(e -> {
            frame.getContentPane().remove(startPanel);
            frame.getContentPane().add(optionsPanel);
            frame.revalidate();
            frame.repaint();
            optionsPanel.requestFocusInWindow();
        });

        panel.add(playButton);
        panel.add(optionsButton);
        return panel;
    }

    private GamePanel createGamePanel() {
        GamePanel panel = new GamePanel(frame, this);
        panel.setBackground(Color.BLACK);
        panel.setFocusable(true);
        return panel;
    }

    private OptionsPanel createOptionsPanel() {
        OptionsPanel optionsPanel = new OptionsPanel(frame, this);
        optionsPanel.setBackground(Color.BLACK);
        optionsPanel.setFocusable(true);
        return optionsPanel;
    }

    public JPanel getStartPanel() {
        return startPanel;
    }
}
