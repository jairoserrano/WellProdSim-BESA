package wpsControl.Agent;

import org.joda.time.DateTime;

/**
 *
 * @author jairo
 */
public class ControlCurrentDate {

    private static ControlCurrentDate instance = null;
    private String currentDate = null;

    private ControlCurrentDate() {
    }

    /**
     *
     * @return
     */
    public synchronized static ControlCurrentDate getInstance() {
        if (instance == null) {
            instance = new ControlCurrentDate();
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

    /**
     *
     * @return
     */
    public synchronized String getDatePlusXDaysAndUpdate(int days) {
        DateTime date = DateHelper.getDateInJoda(instance.getCurrentDate());
        date = date.plusDays(days);
        String newDate = DateHelper.parseDateTimeToString(date);
        this.setCurrentDate(newDate);
        return newDate;
    }

    /**
     *
     * @return
     */
    public String getDatePlusOneDay(String remoteCurrentDate) {
        DateTime date = DateHelper.getDateInJoda(remoteCurrentDate);
        date = date.plusDays(1);
        String newDate = DateHelper.parseDateTimeToString(date);
        return newDate;
    }

    public boolean isFirstDayOfMonth(String remoteCurrentDate) {
        DateTime date = DateHelper.getDateInJoda(remoteCurrentDate);
        DateTime.Property dayOfMonth = date.dayOfMonth();
        return dayOfMonth.get() == 1;
    }

    public boolean isFirstDayOfWeek(String remoteCurrentDate) {
        DateTime date = DateHelper.getDateInJoda(remoteCurrentDate);
        DateTime.Property dayOfWeek = date.dayOfWeek();
        return dayOfWeek.get() == 1;
    }
}
