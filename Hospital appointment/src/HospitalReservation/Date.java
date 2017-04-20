package HospitalReservation;

import java.util.Calendar;

public class Date {
	protected Calendar today;
	protected int date;
	protected int month;
	protected int year;

	public Date() {
		this.today = Calendar.getInstance();
		updateDateData();
	}

	public Date(int date, int month, int year) {
		this.today.set(year, month, date);
		updateDateData();
	}

	public void setDDMMYYYY(int date, int month, int year) {
		this.today.set(year, month, date);
		updateDateData();
	}

	public void updateDateData() {
		this.date = today.get(Calendar.DATE);
		this.month = today.get(Calendar.MONTH);
		this.year = today.get(Calendar.YEAR);
	}

	public String getMonthName() {

		String monthString = "";
		switch (this.getMonth()) {
		case 1:
			monthString = "January";
			break;
		case 2:
			monthString = "February";
			break;
		case 3:
			monthString = "March";
			break;
		case 4:
			monthString = "April";
			break;
		case 5:
			monthString = "May";
			break;
		case 6:
			monthString = "June";
			break;
		case 7:
			monthString = "July";
			break;
		case 8:
			monthString = "August";
			break;
		case 9:
			monthString = "September";
			break;
		case 10:
			monthString = "October";
			break;
		case 11:
			monthString = "November";
			break;
		case 12:
			monthString = "December";
			break;
		default:
			monthString = "Invalid month";
			break;
		}
		return monthString;

	}

	public Calendar getToday() {
		return today;
	}

	public void setToday(Calendar today) {
		this.today = today;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
