package wpsControl.Agent;

import org.joda.time.DateTime;

/**
 *
 * @author jairo
 */
public class wpsCurrentDate {

    private static wpsCurrentDate instance = null;
    private String currentDate = null;

    private wpsCurrentDate() {
    }

    /**
     *
     * @return
     */
    public synchronized static wpsCurrentDate getInstance() {
        if (instance == null) {
            instance = new wpsCurrentDate();
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
    public synchronized void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    /**
     *
     * @return
     */
    public synchronized String getDatePlusOneDayAndUpdate() {
        DateTime date = DateHelper.getDateInJoda(instance.getCurrentDate());        
        date = date.plusDays(1);
        String newDate = DateHelper.parseDateTimeToString(date);
        this.setCurrentDate(newDate);
        return newDate;
    }
}
