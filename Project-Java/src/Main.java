import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        Panel panel = new Panel();
//        SwingUtilities.invokeLater(panel::showFrame);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Panel panel = new Panel();
                panel.showFrame();
            }
        });
    }
}