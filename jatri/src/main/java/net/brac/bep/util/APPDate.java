package net.brac.bep.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APPDate extends GregorianCalendar implements Comparable<Calendar> {

	private static final long serialVersionUID = -6729491175608853232L;
	private static final Logger log = (Logger) LoggerFactory.getLogger(APPDate.class);

	public final static int ONE_SECOND = 1000;
	public final static int ONE_MINUTE = 60 * ONE_SECOND;
	public final static int ONE_HOUR = 60 * ONE_MINUTE;
	public final static int ONE_DAY = 24 * ONE_HOUR;
	public final static int ONE_WEEK = 7 * ONE_DAY;
	public final static int ONE_YEAR = 365 * ONE_DAY;
	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SLASH_SEPARATOR = "yyyy/MM/dd HH:mm:ss";
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_DD_MM_YYYY = "dd-MM-yyyy";
	public static final String DATE_FORMAT_DD_MMM_YYYY = "dd-MMM-yyyy";
	public static final String TWELVE_HOUR_DATE_FORMAT_DD_MM_YYYY_H_MM_A = "dd-MM-yyyy h:mm a";

	public static final long MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1000;
	
	protected static final DateFormat shortFormat = new SimpleDateFormat("yyyy-MM-dd");
	protected static final DateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	public APPDate() {
		setTime(new Date());
	}

	/**
	 * 
	 * @param month the 1-based month of the year (1-12)
	 * @param day the day of the month
	 */
	public APPDate(int month, int day) {
		set(MONTH, month - 1);
		set(DAY_OF_MONTH, day);
	}

	public static APPDate now() {
		return new APPDate();
	}

	public static APPDate today() {
		return new APPDate().clearTime();
	}

	/**
	 * Creates an APDate "now" and sets the calendar's timezone to the timezone
	 * passed in.
	 */
	public static APPDate now(TimeZone zone) {
		APPDate now = new APPDate();
		now.setTimeZone(zone);
		return now;
	}

	public static APPDate beginningOfLastWeek() {
		APPDate d = beginningOfWeek().addWeeks(-1);
		return d;
	}

	/**
	 * Get the Most recent Sunday
	 * @return
	 */
	public static APPDate beginningOfWeek() {
		APPDate d = APPDate.today();
		d = d.addDays((d.get(DAY_OF_WEEK) * -1) + 1);
		return d;
	}

	public static APPDate nullableDate(Calendar cal) {
		if (cal == null) return null;
		return new APPDate(cal);
	}

	public APPDate(int year, int month, int day, TimeZone timeZone) {
		setTimeZone(timeZone);
		set(year, month, day);
	}


	public APPDate(int year, int month, int day) {
		set(year, month, day);
	}

	public APPDate(int year, int month, int day, int hourOfDay, int minute) {
		set(year, month, day, hourOfDay, minute);
	}

	public APPDate(int year, int month, int day, int hourOfDay, int minute, TimeZone timeZone) {
		setTimeZone(timeZone);
		set(year, month, day, hourOfDay, minute);
	}

	public APPDate(int year, int month, int day, int hourOfDay, int minute, int second) {
		set(year, month, day, hourOfDay, minute, second);
	}

	public APPDate(int year, int month, int day, int hourOfDay, int minute, int second, TimeZone timeZone) {
		setTimeZone(timeZone);
		set(year, month, day, hourOfDay, minute, second);
	}

	public APPDate(Date d) {
		setTime(d);
	}

	public APPDate(Date d, TimeZone timeZone) {
		setTime(d);
		setTimeZone(timeZone);
	}

	public APPDate(Calendar cal) {
		setTime(cal.getTime());
	}

	public APPDate(Calendar cal, TimeZone timeZone) {
		setTime(cal.getTime());
		setTimeZone(timeZone);
	}

	public APPDate(long timestamp) {
		setTimeInMillis(timestamp);
	}

	public APPDate(long timestamp, TimeZone timeZone) {
		super(timeZone);
		setTimeInMillis(timestamp);
	}

	public APPDate(Long timestamp) {
		setTimeInMillis(timestamp);
	}

	
	public APPDate clone() {
		return new APPDate(getTimeInMillis(), getTimeZone());
	}
	
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1; // months are zero based
	}

	public static int getMonth(Calendar cal) {
		return cal.get(Calendar.MONTH) + 1; // months are zero based
	}

	public int getMonth() {
		return get(Calendar.MONTH) + 1;
	}

	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	
	public static int getYear(Calendar cal) {
		return cal.get(Calendar.YEAR);
	}

	public static int getHourOfDay(APPDate appDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(appDate.getTime());
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public static int getDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDayOfMonth(Calendar cal) {
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public int getDayOfMonth() {
		return get(Calendar.DAY_OF_MONTH);
	}	
	
	public APPDate addField(int field, int value) {
		APPDate tmp = clone();
		tmp.add(field, value);
		return tmp;
	}
	
	public APPDate addSeconds(int seconds) {
		return addField(SECOND, seconds);
	}
	
	public APPDate addMinutes(int minutes) {
		return addField(MINUTE, minutes);
	}
	
	public APPDate addHours(int hours) {
		return addField(HOUR, hours);
	}
	
	public APPDate addDays(int days) {
		return addField(DATE, days);
	}
	
	public APPDate addMonths(int months) {
		return addField(MONTH, months);
	}
	
	public APPDate addWeeks(int weeks) {
		return addField(DATE, weeks * 7);
	}
	
	public APPDate addYears(int years) {
		return addField(YEAR, years);
	}
	
	public static boolean isToday(Date curDate) {
		return isSameDay(new APPDate(curDate), APPDate.today());
	}

	public static boolean isYesterday(Date curDate) {
		return isSameDay(new APPDate(curDate), APPDate.today().addDays(-1));
	}

	public static boolean isToday(APPDate curDate) {
		return isSameDay(curDate, APPDate.today());
	}

	public static boolean isYesterday(APPDate curDate) {
		return isSameDay(curDate, APPDate.today().addDays(-1));
	}

	public static boolean isTomorrow(APPDate curDate) {
		return isSameDay(curDate, APPDate.today().addDays(1));
	}

	public static boolean isSameDay(APPDate d1, APPDate d2) {
		if (d1 == null) {
			return false;
		}
		if (d2 == null) {
			return false;
		}
		if (d1.clearTime().getTimeInMillis() == d2.clearTime().getTimeInMillis()) {
			return true;
		}
		return false;
	}

	public static boolean isSameMonth(APPDate d1, APPDate d2) {
		if (d1 == null) {
			return false;
		}
		if (d2 == null) {
			return false;
		}
		if (d1.get(YEAR) == d2.get(YEAR) && d1.getMonth() == d2.getMonth()) {
			return true;
		}
		return false;
	}

	public static boolean isSameYear(APPDate d1, APPDate d2) {
		if (d1 == null) {
			return false;
		}
		if (d2 == null) {
			return false;
		}
		if (d1.get(YEAR) == d2.get(YEAR)) {
			return true;
		}
		return false;
	}

	public APPDate setTime(long time) {
		APPDate tmp = clone();
		tmp.setTimeInMillis(time);
		return tmp;
	}

	public static boolean datesEqual(Calendar d1, Calendar d2) {
		if (d1 == d2) {
			return true;
		}
		if (d1 == null) {
			return false;
		}
		if (d2 == null) {
			return false;
		}
		return d1.getTime().getTime() == d2.getTime().getTime();
	}

	public static double diff(Calendar thisDate, Calendar otherDate) {
		return diff(thisDate, otherDate, false);
	}

	public static double diff(Calendar thisDate, Calendar d2, boolean killTimes) {
		APPDate origDate = new APPDate(thisDate);
		APPDate otherDate = new APPDate(d2);
		if (killTimes) {
			otherDate.clearInternalTime();
			origDate.clearInternalTime();
		}
		long myTime = origDate.getTimeInMillis() + origDate.getTimeZone().getOffset(origDate.getTimeInMillis());
		long otherTime = otherDate.getTimeInMillis() + otherDate.getTimeZone().getOffset(otherDate.getTimeInMillis());
		long difference = otherTime - myTime;
		if (difference == 0)
			return 0;
		double daysDifferent = difference / (double) MILLISECONDS_PER_DAY;
		return daysDifferent;
	}

	public static int diffInDays(Calendar thisDate, Calendar thAPPDate) {
		int diffInDays = (int) diff(thisDate, thAPPDate, true);
		return (diffInDays < 0 ? (int) Math.floor(diffInDays) : diffInDays);
	}

	public static int diffInDays(Date thisDate, Date thAppDate) {
		int diffInDays = (int) diff(DateToCalendar(thisDate), DateToCalendar(thAppDate), true);
		return (diffInDays < 0 ? (int) Math.floor(diffInDays) : diffInDays);
	}

	public static Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static int diffInWeeks(Calendar thisDate, Calendar otherDate) {
		APPDate w1 = (APPDate) thisDate.clone();
		w1.set(DAY_OF_WEEK, w1.getMinimum(DAY_OF_WEEK));
		w1 = w1.clearTime();
		APPDate w2 = (APPDate) otherDate.clone();
		w2.set(DAY_OF_WEEK, w2.getMinimum(DAY_OF_WEEK));
		w2 = w2.clearTime();

		int weeks = 0;

		if (w1.before(w2)) {
			while (w1.before(w2)) {
				weeks++;
				w1.add(DATE, 7);
			}
		} else {
			while (w2.before(w1)) {
				weeks--;
				w2.add(DATE, 7);
			}
		}
		return weeks;
	}

	/** other - this **/
	public static int diffInMonths(Calendar thisDate, Calendar otherDate) {
		return ((otherDate.get(YEAR) - thisDate.get(YEAR)) * 12) - thisDate.get(MONTH) + otherDate.get(MONTH);
	}

	/** otherDate - thisDate **/
	public static int diffInMonths(Date thisDate, Date otherDate) {
		Calendar thisDateCal = Calendar.getInstance();
		thisDateCal.setTime(thisDate);
		Calendar otherDateCal = Calendar.getInstance();
		otherDateCal.setTime(otherDate);
		return ((otherDateCal.get(YEAR) - thisDateCal.get(YEAR)) * 12) - thisDateCal.get(MONTH) + otherDateCal.get(MONTH);
	}

	/** otherDate - thisDate **/
	public static int diffInYears(Calendar thisDate, Calendar otherDate) {
		return Math.abs(otherDate.get(YEAR) - thisDate.get(YEAR));
	}

	public static int diffInYears(Date thisDate, Date otherDate) {
		return Math.abs(DateToCalendar(otherDate).get(YEAR) - DateToCalendar(thisDate).get(YEAR));
	}

	public APPDate setField(int field, int value) {
		APPDate tmp = clone();
		tmp.set(field, value);
		return tmp;
	}

	public static int diffInSeconds(Calendar thisDate, Calendar otherDate) {
		return (int) Math.ceil((Math.abs(thisDate.getTime().getTime() - otherDate.getTime().getTime())) / 1000);
	}

	public static int diffInMinutes(Calendar thisDate, Calendar otherDate) {
		return (int) Math.ceil((thisDate.getTimeInMillis() - otherDate.getTimeInMillis()) / (60 * 1000));
	}

	public static int diffInHours(Calendar thisDate, Calendar otherDate) {
		return (int) Math.ceil((Math.abs(thisDate.getTime().getTime() - otherDate.getTime().getTime())) / 1000 / 60 / 60);
	}

	public int compareTo(Calendar date) {
		return getTime().compareTo(date.getTime());
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Calendar))
			return false;
		return getTimeInMillis() == ((Calendar) obj).getTimeInMillis();
	}

	public boolean hasTimeSet() {
		if (get(SECOND) > 0 || get(MINUTE) > 0 || get(HOUR) > 0)
			return true;
		return false;
	}

	private void clearInternalTime() {
		clear(HOUR_OF_DAY);
		clear(HOUR);
		clear(MINUTE);
		clear(SECOND);
		clear(MILLISECOND);
		clear(AM_PM);
	}

	public APPDate clearTime() {
		APPDate tmp = clone();
		tmp.clearInternalTime();
		return tmp;
	}

	public static int getCurrentYear() {
		return APPDate.getYear(new APPDate().getTime());
	}

	public static int getCurrentMonthNumber() {
		return APPDate.getMonth(new APPDate().getTime());
	}

	public static int getCurrentDayOfCurrentMonth() {
		return APPDate.getDayOfMonth(new APPDate().getTime());
	}

	public static APPDate getLastDateOfCurrentMonth() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		return new APPDate(calendar);
	}
	
	public static APPDate getLastDateOfMonth(APPDate appDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(appDate.getTime());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		return new APPDate(calendar);
	}
	
	/**
	 * Get a list containing the month numbers for the next X months (between 1
	 * and 12);
	 * 
	 * @param howMany
	 * @return list of int
	 */
	public static List<Integer> getNextXMonths(int howMany) {
		List<Integer> xmonths = new ArrayList<Integer>(howMany);
		int currentMont = APPDate.getCurrentMonthNumber();
		int counter = 0;
		for (int i = currentMont; i < currentMont + howMany; i++) {
			if (counter == 6)
				break;
			counter++;
			if (i == 13)
				i = 1;
			xmonths.add(i);
		}
		return xmonths;
	}

	/**
	 * Get a list containing the month numbers for the last X months (between 1
	 * and 12);
	 * 
	 * @param howMany
	 * @return list of int
	 */
	public static List<Integer> getLastXMonths(int howMany) {
		List<Integer> xmonths = new ArrayList<Integer>(howMany);
		int currentMont = APPDate.getCurrentMonthNumber();
		int counter = 0;
		xmonths.add(currentMont);
		for (int i = (currentMont - 1);; i--) {
			if (counter == howMany)
				break;
			counter++;
			if (i == 0)
				i = 12;
			xmonths.add(i);
		}
		return xmonths;
	}

	/**
	 * returns true if a month is within the next howMany months
	 * 
	 * @param month
	 *            - which month to check
	 * @param howMany
	 *            - the next howMany months
	 * @return true if yes
	 */
	public static boolean isMonthInNextXMonthsList(int month, int howMany) {
		return getNextXMonths(howMany).contains(month);
	}

	public static APPDate beginningOfMonth() {
		APPDate td = APPDate.today();
		td.set(DAY_OF_MONTH, 1);
		return td;
	}
	
	public static APPDate beginningOfMonth(APPDate d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d.getTime());
		d = d.setField(APPDate.DAY_OF_MONTH, 1);
		return d;
	}
	
	public static APPDate beginningOfNextWeek() {
		APPDate d = APPDate.today();
		int day = (d.get(DAY_OF_WEEK)-7)* -1;
		d = d.addDays(day+1);
		return d;
	}
	
	public static APPDate beginningOfQuarter(APPDate d) {
		d = d.setField(APPDate.DAY_OF_MONTH, 1);
		int mod = (d.getMonth()-1) % 3;
		return d.addMonths(-1*mod);
	}
	public static APPDate beginningOfYear() {
		APPDate td = APPDate.today().setField(DAY_OF_MONTH, 1).setField(MONTH, 0);
		return td;
	}
	
	public static String getCurrentDateTime(String format){
		String currentDate = "";
		try{
			DateFormat dateFormat = new SimpleDateFormat(format);
			Date date = new Date();
			currentDate = dateFormat.format(date);
		} catch (Throwable t) {
			log.error("Exception in APPDate getCurrentDateTime() : ", t);
		}
		return currentDate;
	}
	
	public static String getDateTimeString(String format, Date date){
		String currentDate = "";
		try{
			DateFormat dateFormat = new SimpleDateFormat(format);
			currentDate = dateFormat.format(date);
		} catch (Throwable t) {
			log.error("Exception in APPDate getDateTimeString() : ", t);
		}
		return currentDate;
	}
	
	
	public static Date getDate(String datestring, String format){
		Date date = new Date();
		try{
			DateFormat dateFormat = new SimpleDateFormat(format);
			date = dateFormat.parse(datestring);
		} catch (Throwable t) {
			log.error("Exception in APPDate getDate() : ", t);
		}
		return date;
	}
	
	public static Calendar getCalender(String datestring, String format){
		Calendar calender = Calendar.getInstance();
		try{
			DateFormat dateFormat = new SimpleDateFormat(format);
			Date date = dateFormat.parse(datestring);
			calender.setTime(date);
		} catch (Throwable t) {
			log.error("Exception in APPDate getCalender() : ", t);
		}
		return calender;
	}

	/**
	 * DB Format string yyyy-mm-dd hh:mm:ss
	 */
	public String toDBFormatString() {
		return toDBFormatString(getTimeZone());
	}

	/**
	 * DB Format string yyyy-mm-dd hh:mm:ss
	 */
	public String toDBFormatString(TimeZone timeZone) {
		DateFormat dateFormat = (DateFormat) dbFormat.clone();
		dateFormat.setTimeZone(timeZone);
		return dateFormat.format(getTime());
	}
	
	
	/**
	 * DB Format strign dd-MMM-yyyy h:mm a
	 */
	public String toBDFormat12HourString() {
		return toBDFormat12HourString(getTimeZone());
	}
	
	
	/**
	 * 12 Hour Format strign dd-MMM-yyyy h:mm a
	 */
	private String toBDFormat12HourString(TimeZone timeZone) {
		DateFormat dateFormat = new SimpleDateFormat(TWELVE_HOUR_DATE_FORMAT_DD_MM_YYYY_H_MM_A);
		dateFormat.setTimeZone(timeZone);
		return dateFormat.format(getTime());
	}
	
	/**
	 * Date format DD-MM-YYYY
	 */
	public String toDayMonthYearString() {
		return toDayMonthYearString(getTimeZone());
	}
	
	
	/**
	 * Date format DD-MM-YYYY
	 */
	private String toDayMonthYearString(TimeZone timeZone) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY);
		dateFormat.setTimeZone(timeZone);
		return dateFormat.format(getTime());
	}
	
	/**
	 * Date format YYYY-MM-DD
	 */
	public String toYearMonthDayString() {
		return toYearMonthDayString(getTimeZone());
	}
	
	
	/**
	 * Date format YYYY-MM-DD
	 */
	private String toYearMonthDayString(TimeZone timeZone) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		dateFormat.setTimeZone(timeZone);
		return dateFormat.format(getTime());
	}
		
	public static void main(String[] args) throws ParseException {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.SECOND, 0);						
			Date todayDate = c.getTime();
			
			System.out.print(todayDate.toString());
		
		
		/*System.out.println(beginningOfNextWeek());
		Date d = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		d = df.parse("2013-08-24");
		APPDate date = new APPDate(d);
		long timeNow = System.currentTimeMillis();
		long entryTime = d.getTime();
		long diff = timeNow - entryTime;
		long diffSec = diff / 1000;
		long difMin = diffSec / 60;
		long difHours = difMin / 60;
		long difDays = difHours / 24;*/

	}

}