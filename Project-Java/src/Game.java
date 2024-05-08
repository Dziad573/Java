/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    private JPanel createStartPanel() {
        panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, (HEIGHT/2)));

        JButton button = new JButton("PLAY");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(startPanel);
                frame.getContentPane().add(createGamePanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        panel.add(button);
        return panel;
    }

    private JPanel createGamePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);



        return panel;
    }
}


*/
