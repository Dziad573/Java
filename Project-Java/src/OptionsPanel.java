import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {

    private JRadioButton setToWSAD;
    private JRadioButton setToArrows;

    public OptionsPanel(JFrame frame, Panel mainPanel) {
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

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
        settingsPanel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        settingsPanel.add(volumeSlider, gbc);

        Icon radioIcon = new RadioIcon(Color.WHITE, false, 30);
        Icon radioSelectedIcon = new RadioIcon(Color.RED, true, 30);

        ButtonGroup buttonGroup = new ButtonGroup();

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JRadioButton setToWSAD = new JRadioButton("", true);
        setToWSAD.setIcon(radioIcon);
        setToWSAD.setSelectedIcon(radioSelectedIcon);
        setToWSAD.setOpaque(false);
        buttonGroup.add(setToWSAD);
        settingsPanel.add(setToWSAD, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel imageWSAD = new JLabel(new ImageIcon("src/img/wsadd.png"));
        settingsPanel.add(imageWSAD, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JRadioButton setToArrows = new JRadioButton("");
        setToArrows.setIcon(radioIcon);
        setToArrows.setSelectedIcon(radioSelectedIcon);
        setToArrows.setOpaque(false);
        buttonGroup.add(setToArrows);
        settingsPanel.add(setToArrows, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JLabel imageArrows = new JLabel(new ImageIcon("src/img/arrowss.png"));
        settingsPanel.add(imageArrows, gbc);

        add(label, BorderLayout.NORTH);
        add(settingsPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
    public JRadioButton getSelectedRadioButton() {
        if (setToWSAD.isSelected()) {
            return setToWSAD;
        } else {
            return setToArrows;
        }
    }
}
