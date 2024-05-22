/*import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię ");
        String imie = scanner.nextLine();
        System.out.println("Podaj nazwisko ");
        String nazwisko = scanner.nextLine();
        System.out.println("Podaj wiek ");
        int wiek = scanner.nextInt();

        System.out.println("Imię: " + imie + "\nNazwisko: " + nazwisko + "\nWiek: " + wiek);
        */

        /*
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj a: ");
        float a = scan.nextFloat();
        System.out.println("Podaj b: ");
        float b = scan.nextFloat();
        float dod = a + b;
        float min = a - b;
        float mno = a * b;
        System.out.println(
                "Wynik dodawania: " + dod +
                "\nWynik odejmowania: " + min +
                "\nWynik mnożenia: " + mno +
                "\nWynik dzielenia: " + (a / b)
        );
        */

        // ########## ZAOKRĄGLANIE ##########
        /*
        double a = 1.44;
        double b = 43.1;
        double c = a*b;
        double d = a*b;
        System.out.println(Math.round(d));

        String roundedNumber = String.format("%.0f",c);
        System.out.println(roundedNumber);


        String[] tab = {"ga", "ge", "baab", "fraw"};

        for(int i = 0; i< tab.length; i++){
            tab[i] = tab[i] + "faew" + (i + 1);
            System.out.println(tab[i]);
        }



    }
}*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Panel panel = new Panel();
                panel.showFrame();
            }
        });
    }
}

class Panel {
    public JFrame frame;
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

        // Load image
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/arrowss.png")); // replace with your image path
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        ImagePanel imagePanel = new ImagePanel(image);
        frame.getContentPane().add(imagePanel);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        animateImageFadeIn(imagePanel);
    }

    private void animateImageFadeIn(ImagePanel imagePanel) {
        Timer timer = new Timer(20, null);
        timer.addActionListener(new ActionListener() {
            private float opacity = 0.0f;
            private final float increment = 0.01f;
            private int phase = 0;
            private int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (phase) {
                    case 0: // Fade in
                        opacity += increment;
                        if (opacity > 1.0f) {
                            opacity = 1.0f;
                            phase = 1;
                            count = 0;
                        }
                        imagePanel.setOpacity(opacity);
                        break;
                    case 1: // Hold at full opacity
                        count++;
                        if (count >= 100) { // 2 seconds at 20 ms intervals
                            phase = 2;
                        }
                        break;
                    case 2: // Fade out
                        opacity -= increment;
                        if (opacity < 0.0f) {
                            opacity = 0.0f;
                            timer.stop();
                            frame.getContentPane().remove(imagePanel);
                            frame.getContentPane().add(startPanel);
                            frame.revalidate();
                            frame.repaint();
                        }
                        imagePanel.setOpacity(opacity);
                        break;
                }
            }
        });
        timer.start();
    }

    public JPanel createStartPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, (HEIGHT / 2)));

        JButton playButton = new JButton("NEW GAME");
        playButton.addActionListener(e -> {
            frame.getContentPane().remove(startPanel);
            frame.getContentPane().remove(optionsPanel);
            frame.getContentPane().add(gamePanel);
            frame.revalidate();
            frame.repaint();
            gamePanel.activateGamePanel();
        });

        JButton continueButton = new JButton("CONTINUE");
        continueButton.addActionListener(e -> {
            frame.getContentPane().remove(startPanel);
            frame.getContentPane().remove(optionsPanel);
            frame.getContentPane().add(gamePanel);
            frame.revalidate();
            frame.repaint();
            gamePanel.activateContinueGamePanel();
        });

        JButton optionsButton = new JButton("OPTIONS");
        optionsButton.addActionListener(e -> {
            frame.getContentPane().remove(startPanel);
            frame.getContentPane().remove(gamePanel);
            frame.getContentPane().add(optionsPanel);
            frame.revalidate();
            frame.repaint();
            optionsPanel.requestFocusInWindow();
        });

        panel.add(playButton);
        panel.add(continueButton);
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

class ImagePanel extends JPanel {
    private BufferedImage image;
    private float opacity;

    public ImagePanel(BufferedImage image) {
        this.image = image;
        this.opacity = 0.0f;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        int x = (getWidth() - image.getWidth()) / 2;
        int y = (getHeight() - image.getHeight()) / 2;
        g2d.drawImage(image, x, y, this);
    }
}

class GamePanel extends JPanel {
    private JFrame frame;
    private Panel panel;

    public GamePanel(JFrame frame, Panel panel) {
        this.frame = frame;
        this.panel = panel;
    }

    public void activateGamePanel() {
        // Activate game panel logic
    }

    public void activateContinueGamePanel() {
        // Continue game panel logic
    }
}

class OptionsPanel extends JPanel {
    private JFrame frame;
    private Panel panel;

    public OptionsPanel(JFrame frame, Panel panel) {
        this.frame = frame;
        this.panel = panel;
    }

    // Options panel specific methods
}


