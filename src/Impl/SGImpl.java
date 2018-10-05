package Impl;

import Interfaces.StatsGenerator;
import Controller.StatsController;
import evtmanStats.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

//SQL Implementation of StatsGenerator Interface
public class SGImpl implements StatsGenerator{
    
    
    @Override
    public ChartFrame tickSoldByDate(String tipo){
            DBManager manager = new DBManager();
            Connection con=manager.getConnection();
            String query="SELECT ACQDATA as DATA, count(*) as QTA FROM BIGLIETTO WHERE STATO='ACQUISTATO' GROUP BY ACQDATA ORDER BY QTA";
            JFreeChart chart = null;
            ChartFrame frame = null;
        try {
            if(tipo.equals("line")){
                JDBCCategoryDataset dataset = new JDBCCategoryDataset(manager.getConnection(),query);
                chart = ChartFactory.createLineChart3D("Biglietti venduti per data", "DATA", "QTA", dataset, PlotOrientation.VERTICAL, false, true, true);
            }
            else if(tipo.equals("bar")){
                JDBCCategoryDataset dataset = new JDBCCategoryDataset(manager.getConnection(),query);
                chart = ChartFactory.createBarChart3D("Biglietti venduti per data", "DATA", "QTA", dataset);
            }
            else if(tipo.equals("pie")){
                PreparedStatement prepStat=con.prepareStatement(query);
                DefaultPieDataset dataset = new DefaultPieDataset();
                ResultSet rs=prepStat.executeQuery();
                while(rs.next()){
                    dataset.setValue(rs.getString("DATA"),rs.getInt("QTA"));
                }
                PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{1}");
                chart = ChartFactory.createPieChart("Biglietti venduti per data",   dataset, true, true, true);
                PiePlot plot=(PiePlot) chart.getPlot();
                plot.setLabelGenerator(labelGenerator);
            }
            BarRenderer render =null;
            
            render = new BarRenderer();
            frame=new ChartFrame("Grafico",chart);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
            return frame;
        
    }
    
    @Override
    public ChartFrame tickSoldByType(String tipo){
            DBManager manager = new DBManager();
            Connection con=manager.getConnection();
            String query="SELECT TIPO,count(*) as QTA FROM BIGLIETTO JOIN SETTORE ON biglietto.settoreid=settore.idsettore JOIN EVENTO ON settore.idevento=evento.id WHERE STATO='ACQUISTATO' GROUP BY TIPO ORDER BY QTA";
            JFreeChart chart = null;
            ChartFrame frame = null;
            try{
                if(tipo.equals("bar")){
                    JDBCCategoryDataset dataset = new JDBCCategoryDataset(manager.getConnection(),query);
                    chart = ChartFactory.createBarChart3D("Biglietti venduti per tipo", "TIPO", "QTA", dataset, PlotOrientation.VERTICAL, false, true, true);
                }
                else if(tipo.equals("pie")){
                    PreparedStatement prepStat=con.prepareStatement(query);
                    DefaultPieDataset dataset = new DefaultPieDataset();
                    ResultSet rs=prepStat.executeQuery();
                    while(rs.next()){
                        dataset.setValue(rs.getString("TIPO"),rs.getInt("QTA"));
                    }
                    PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{1}");
                    chart = ChartFactory.createPieChart("Biglietti venduti per tipo",   dataset, true, true, true);
                    PiePlot plot=(PiePlot) chart.getPlot();
                    plot.setLabelGenerator(labelGenerator);
            }
            frame=new ChartFrame("Grafico",chart);
            
            }
            catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return frame;
    }
    
    @Override
    public ChartFrame earningsByDate(String tipo){
            DBManager manager = new DBManager();
            Connection con=manager.getConnection();
            String query="SELECT ACQDATA as DATA, SUM(PREZZO) as TOT FROM BIGLIETTO JOIN SETTORE on biglietto.settoreid=settore.idsettore WHERE STATO='ACQUISTATO' GROUP BY ACQDATA ORDER BY TOT";
            JFreeChart chart = null;
            ChartFrame frame = null;
            try{
                if(tipo.equals("bar")){
                    JDBCCategoryDataset dataset = new JDBCCategoryDataset(manager.getConnection(),query);
                    chart = ChartFactory.createBarChart3D("Incassi per data", "DATA", "TOT", dataset, PlotOrientation.VERTICAL, false, true, true);
                }
                else if(tipo.equals("line")){
                    JDBCCategoryDataset dataset = new JDBCCategoryDataset(manager.getConnection(),query);
                    chart = ChartFactory.createLineChart3D("Incassi per data", "DATA", "TOT", dataset, PlotOrientation.VERTICAL, false, true, true);
                }
                else if(tipo.equals("pie")){
                    PreparedStatement prepStat=con.prepareStatement(query);
                    DefaultPieDataset dataset = new DefaultPieDataset();
                    ResultSet rs=prepStat.executeQuery();
                    while(rs.next()){
                        dataset.setValue(rs.getString("DATA"),rs.getDouble("TOT"));
                    }
                    PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{1}€");
                    chart = ChartFactory.createPieChart("Incassi per data", dataset, true, true, true);
                    PiePlot plot=(PiePlot) chart.getPlot();
                    plot.setLabelGenerator(labelGenerator);
            }
            frame=new ChartFrame("Grafico",chart);
            
            }
            catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return frame;
        
    }
    
    @Override
    public ChartFrame earningsByType(String tipo){
        DBManager manager = new DBManager();
            Connection con=manager.getConnection();
            String query="SELECT TIPO, SUM(PREZZO) as TOT FROM BIGLIETTO JOIN SETTORE on biglietto.settoreid=settore.idsettore JOIN EVENTO on settore.idevento=evento.id WHERE STATO='ACQUISTATO' GROUP BY TIPO ORDER BY TOT";
            JFreeChart chart = null;
            ChartFrame frame = null;
            try{
                if(tipo.equals("bar")){
                    JDBCCategoryDataset dataset = new JDBCCategoryDataset(manager.getConnection(),query);
                    chart = ChartFactory.createBarChart3D("Incassi per tipo di evento", "CATEGORIA", "TOT", dataset, PlotOrientation.VERTICAL, false, true, true);
                }
                else if(tipo.equals("line")){
                    JDBCCategoryDataset dataset = new JDBCCategoryDataset(manager.getConnection(),query);
                    chart = ChartFactory.createLineChart3D("Incassi per tipo di evento", "CATEGORIA", "TOT", dataset, PlotOrientation.VERTICAL, false, true, true);
                }
                else if(tipo.equals("pie")){
                    PreparedStatement prepStat=con.prepareStatement(query);
                    DefaultPieDataset dataset = new DefaultPieDataset();
                    ResultSet rs=prepStat.executeQuery();
                    while(rs.next()){
                        dataset.setValue(rs.getString("TIPO"),rs.getDouble("TOT"));
                    }
                    PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{1}€");
                    chart = ChartFactory.createPieChart("Incassi per tipo di evento", dataset, true, true, true);
                    PiePlot plot=(PiePlot) chart.getPlot();
                    plot.setLabelGenerator(labelGenerator);
            }
            frame=new ChartFrame("Grafico",chart);
            
            }
                catch (SQLException ex) {
                Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return frame;
    }
    
    @Override
    public String totUser(){
        Integer cnt=0;
        DBManager manager = new DBManager();
        Connection con=manager.getConnection();
        String query="SELECT COUNT(ID) FROM UTENTE";
        try{
            PreparedStatement prepStat=con.prepareStatement(query);
            ResultSet rs=prepStat.executeQuery();
            while(rs.next()){
                cnt=rs.getInt(1);
            }
        }catch (SQLException ex) {
                Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.valueOf(cnt);
    }
    
    @Override
    public String totEvs(){
        Integer cnt=0;
        DBManager manager = new DBManager();
        Connection con=manager.getConnection();
        String query="SELECT COUNT(ID) FROM EVENTO";
        try{
            PreparedStatement prepStat=con.prepareStatement(query);
            ResultSet rs=prepStat.executeQuery();
            while(rs.next()){
                cnt=rs.getInt(1);
            }
        }catch (SQLException ex) {
                Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.valueOf(cnt);
    }
    
    @Override
    public String totTickSold(){
        Integer cnt=0;
        DBManager manager = new DBManager();
        Connection con=manager.getConnection();
        String query="SELECT COUNT(ID) FROM BIGLIETTO WHERE STATO='ACQUISTATO' ";
        try{
            PreparedStatement prepStat=con.prepareStatement(query);
            ResultSet rs=prepStat.executeQuery();
            while(rs.next()){
                cnt=rs.getInt(1);
            }
        }catch (SQLException ex) {
                Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.valueOf(cnt);
    }
    
    @Override
    public void customResearch(Date start, Date end, String category, String comp, String tipo){
        DBManager manager = new DBManager();
        Connection con=manager.getConnection();
        //Creating the right query based on User's choice
        String query=QueryGenerator.resQuery(category,comp);
        int countQsMark=0;
        JFreeChart chart = null;
        boolean check=true;
        //In order to create the right preparedStatement we count how many ? there are 
        for(int i=0;i<query.length();i++){
            if(query.charAt(i)=='?'){
                countQsMark++;
            }
        }
        try{
            
            PreparedStatement prepStat=con.prepareStatement(query);
            //Prepared statement is set depending on how many ? are counted
            if(countQsMark==2){
                prepStat.setDate(1, (java.sql.Date) start);
                prepStat.setDate(2, (java.sql.Date) end);
            }
            else{
                prepStat.setString(1, category);
                prepStat.setDate(2, (java.sql.Date) start);
                prepStat.setDate(3, (java.sql.Date) end);
            }
            if(tipo.equals("bar")){
                    DefaultCategoryDataset  bdataset = new DefaultCategoryDataset ();
                    ResultSet rs=prepStat.executeQuery();
                    if(!(rs.next())){
                        check=false;
                        //Error is shown if no results got
                        JOptionPane.showMessageDialog(new JFrame(), "Il database non ha prodotto risultati, prova con altri parametri!", "Errore",
                        JOptionPane.ERROR_MESSAGE);
                    }
                    while(rs.next()){
                        bdataset.setValue(rs.getDouble(2), rs.getString(1),rs.getString(1));
                    }
                    chart = ChartFactory.createBarChart3D(comp+" Categoria evento: "+category, "DATA", "TOT", bdataset, PlotOrientation.VERTICAL, false, true, true);
                }
                else if(tipo.equals("line")){
                    DefaultCategoryDataset  ldataset = new DefaultCategoryDataset ();
                    ResultSet rs=prepStat.executeQuery();
                    if(!(rs.next())){
                        check=false;
                        //Error is shown if no results got
                        JOptionPane.showMessageDialog(new JFrame(), "Il database non ha prodotto risultati, prova con altri parametri!", "Errore",
                        JOptionPane.ERROR_MESSAGE);
                    }
                    while(rs.next()){
                        ldataset.setValue(rs.getDouble(2), rs.getString(1),rs.getString(1));
                    }
                    chart = ChartFactory.createLineChart(comp+" Categoria evento: "+category, "DATA", "TOT", ldataset, PlotOrientation.VERTICAL, true, true, false);
                }
                else if(tipo.equals("pie")){
                    DefaultPieDataset dataset = new DefaultPieDataset();
                    ResultSet rs=prepStat.executeQuery();
                    if(!(rs.next())){
                        check=false;
                        //Error is shown if no results got
                        JOptionPane.showMessageDialog(new JFrame(), "Il database non ha prodotto risultati, prova con altri parametri!", "Errore",
                        JOptionPane.ERROR_MESSAGE);
                    }
                    while(rs.next()){
                        dataset.setValue(rs.getString(1),rs.getDouble(2));
                    }
                    PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{1}");
                    chart = ChartFactory.createPieChart(comp+" Categoria evento: "+category, dataset, true, true, true);
                    PiePlot plot=(PiePlot) chart.getPlot();
                    plot.setLabelGenerator(labelGenerator);
                }
                if(check){
                    ChartFrame frame=new ChartFrame("Grafico",chart);
                    frame.setLocationRelativeTo(null);
                    frame.setSize(800, 600);
                    frame.setVisible(true);
                }
            }
                catch (SQLException ex) {
                Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
    }
    
}
