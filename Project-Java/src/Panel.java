import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Panel{
    private JFrame frame;
    public void showFrame(){
        frame = new JFrame();

        frame.setTitle("Title");


        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,0, 550));


        JButton button = new JButton("PLAY");
        JButton button2 = new JButton("PLAY");
        JButton button3 = new JButton("PLAY");
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 20));


        panel.add(button);
        panel.add(button2);
        panel.add(button3);

        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setBackground(Color.BLACK);
        frame.getContentPane().add(panel);
        frame.setVisible(true);



        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
            }
        });

    }



}
