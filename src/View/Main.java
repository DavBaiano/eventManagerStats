package View;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Main {
    public static void main(String [] args){
        //Graphic's modified
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Login frame is created and shown
        Login log = new Login();
        log.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        log.setLocationRelativeTo(null);
        log.setVisible(true);
    }
}
