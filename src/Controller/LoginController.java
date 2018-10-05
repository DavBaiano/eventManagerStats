package Controller;


import View.MainFrame;
import evtmanStats.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Gianmarco
 */
public class LoginController {
    //Creating object in order to verify login credentials
    User utente = new User();
    public void checkLogin(String username, String password){
        boolean check;
        check=utente.verifyLogin(username, password);
        //If credentials are valid main frame is shown
        if(check){
            MainFrame mf = new MainFrame();
            mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mf.setLocationRelativeTo(null);
            mf.setVisible(true);
        }
        //else error is shown
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Le credenziali non sono valide", "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
