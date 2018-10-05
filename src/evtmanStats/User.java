/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evtmanStats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gianmarco
 */
public class User {
    public boolean verifyLogin(String username, String password){
        boolean check=false;
        DBManager db = new DBManager();
        Connection con=db.getConnection();
        String sql = "SELECT * FROM UTENTE WHERE EMAIL LIKE ? AND PASSWORD LIKE ?";
        PreparedStatement prepStat = null;
        ResultSet rs = null;
        try {
                prepStat = con.prepareStatement(sql);
                prepStat.setString(1, username);
                prepStat.setString(2, password);
                rs = prepStat.executeQuery();
                if(!(rs.next())){
                    check=false;
                }
                else{
                    check=true;
                }
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(new JFrame(), "Errore durante il login", "Dialog",
                JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            finally {
                try { rs.close(); } catch (Exception e) {}
                try { prepStat.close(); } catch (Exception e) {}
                try { con.close(); } catch (Exception e) {}
            }
        return check;
    }
}
