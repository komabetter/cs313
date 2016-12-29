package gameCenter.model;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.mysql.fabric.xmlrpc.base.Data;

public class guest {

	private int timeToPlay;
	private boolean valid;
	private int codeID;
	private Time time;

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getGuestID() {
		return codeID;
	}

	public void setGuestID(int guestID) {
		this.codeID = guestID;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public int getTimeToPlay() {
		return timeToPlay;
	}

	public void setTimeToPlay(int timeToPlay) {
		this.timeToPlay = timeToPlay;
	}

}
