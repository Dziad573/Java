import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {

    //private JFrame frame;
    //private Panel mainPanel;

    public OptionsPanel(JFrame frame, Panel mainPanel) {
        //this.frame = frame;
        //this.mainPanel = mainPanel;

        setLayout(new BorderLayout());
        setBackground(Color.GRAY);

        JLabel label = new JLabel("Options", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JButton backButton = new JButton("BACK");
        backButton.addActionListener(e -> {
            frame.getContentPane().remove(this);
            frame.getContentPane().add(mainPanel.getStartPanel());
            frame.revalidate();
            frame.repaint();
            mainPanel.getStartPanel().requestFocusInWindow();
        });

        JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);

        add(label, BorderLayout.NORTH);
        add(volumeSlider, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}
