package Controller;


import Impl.SGImpl;
import Interfaces.StatsGenerator;
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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.util.StringUtils;



public class StatsController {
    
    //Creating Implementation Object in order to create the right charts
    StatsGenerator statsObj = new SGImpl();
    
    //Returning static infos to the main frame
    public String getTots(Integer choice){
        String tot = null;
        switch(choice){
            case 1: tot=statsObj.totEvs();
                    break;
            case 2: tot=statsObj.totTickSold();
                    break;
            case 3: tot=statsObj.totUser();
                    break;
        }
        return tot;
    }
    
    //Controller getting the right fast chart and displaying it
    public void fastCharts(Integer choice){
        ChartFrame frame = null;
        switch(choice){
            case 1: frame=statsObj.earningsByDate("pie");
                    break;
            case 2: frame=statsObj.earningsByDate("bar");
                    break;
            case 3: frame=statsObj.earningsByDate("line");
                    break;
            case 4: frame=statsObj.tickSoldByDate("pie");
                    break;
            case 5: frame=statsObj.tickSoldByDate("bar");
                    break;
            case 6: frame=statsObj.tickSoldByDate("line");
                    break;
            case 7: frame=statsObj.tickSoldByType("pie");
                    break;
            case 8: frame=statsObj.tickSoldByType("bar");
                    break;
            case 9: frame=statsObj.earningsByType("pie");
                    break;
            case 10: frame=statsObj.earningsByType("bar");
                    break;
        }
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
    //Displaying "custom" chart
    public void resChart(Date start, Date end, String category, String comp, String tipo){
        statsObj.customResearch(start, end, category, comp, tipo);
    }
     
}

