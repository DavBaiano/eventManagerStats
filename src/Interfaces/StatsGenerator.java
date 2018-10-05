
package Interfaces;

import java.util.Date;
import org.jfree.chart.ChartFrame;

//Interface offering methods for chart creation
public interface StatsGenerator {
    public ChartFrame tickSoldByDate(String tipo);
    public ChartFrame tickSoldByType(String tipo);
    public ChartFrame earningsByDate(String tipo);
    public ChartFrame earningsByType(String tipo);
    public void customResearch(Date start, Date end, String category, String comp, String tipo);
    public String totTickSold();
    public String totEvs();
    public String totUser();
}
