package HospitalReservation;

public class Time {
	protected int hour;
	protected int minute;

	public Time() {
		this.hour = 00;
		this.minute = 00;
	}

	public int getTime() {
		return (this.hour * 100) + minute;
	}

	public String displayTime() {
		return this.hour + ":" + this.minute;
	}

	public static boolean timeClash(int XbeginTime, int XendTime, int YbeginTime, int YendTime) {
		boolean temp = false;
		for (int i = XbeginTime; i <= XendTime; i++) {
			for (int k = YbeginTime; k <= YendTime; k++) {
				if (k == i) {
					temp = true;
				}
			}
		}
		return temp;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
}
