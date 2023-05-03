package wpsControl.Agent;

import BESA.Log.ReportBESA;
import org.joda.time.DateTime;

/**
 *
 * @author jairo
 */
public class wpsCurrentDateSingleton {

    private static wpsCurrentDateSingleton instance = null;
    private String currentDate = null;

    private wpsCurrentDateSingleton() {
    }

    /**
     *
     * @return
     */
    public static wpsCurrentDateSingleton getInstance() {
        if (instance == null) {
            instance = new wpsCurrentDateSingleton();
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public String getCurrentDate() {
        return this.currentDate;
    }

    /**
     *
     * @param currentDate
     */
    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    /**
     *
     * @return
     */
    public String getDatePlusOneDayAndUpdate() {
        DateTime date = DateHelper.getDateInJoda(instance.getCurrentDate());
        date = date.plusDays(1);
        String newDate = DateHelper.parseDateTimeToString(date);
        this.setCurrentDate(newDate);
        return newDate;
    }

    /**
     *
     * @return
     */
    public String getDatePlusOneWeekAndUpdate() {
        DateTime date = DateHelper.getDateInJoda(instance.getCurrentDate());
        date = date.plusWeeks(1);
        String newDate = DateHelper.parseDateTimeToString(date);
        this.setCurrentDate(newDate);
        return newDate;
    }

    /**
     *
     * @return
     */
    public String getDatePlusOneMonthAndUpdate() {
        ReportBESA.debug("----DATE---"+instance.getCurrentDate());
        DateTime date = DateHelper.getDateInJoda(instance.getCurrentDate());
        date = date.plusMonths(1);
        String newDate = DateHelper.parseDateTimeToString(date);
        this.setCurrentDate(newDate);
        return newDate;
    }
}