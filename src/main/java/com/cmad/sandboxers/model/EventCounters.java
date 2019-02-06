package com.cmad.sandboxers.model;

/**
 * 
 * @author pkrishne
 *
 */
public class EventCounters {

	private int error_count;
	private int warning_count;
	private int notification_count;
	private int debug_count;
	private int info_count;
	private int alert_count;

	public int getError_count() {
		return error_count;
	}

	/**
	 * @return the alert_count
	 */
	public int getAlert_count() {
		return alert_count;
	}

	/**
	 * @param alert_count the alert_count to set
	 */
	public void setAlert_count(int alert_count) {
		this.alert_count = alert_count;
	}

	/**
	 * @return the info_count
	 */
	public int getInfo_count() {
		return info_count;
	}

	/**
	 * @param info_count the info_count to set
	 */
	public void setInfo_count(int info_count) {
		this.info_count = info_count;
	}

	/**
	 * @return the debug_count
	 */
	public int getDebug_count() {
		return debug_count;
	}

	/**
	 * @param debug_count the debug_count to set
	 */
	public void setDebug_count(int debug_count) {
		this.debug_count = debug_count;
	}

	public void setError_count(int error_count) {
		this.error_count = error_count;
	}

	public int getWarning_count() {
		return warning_count;
	}

	public void setWarning_count(int warning_count) {
		this.warning_count = warning_count;
	}

	public int getNotification_count() {
		return notification_count;
	}

	public void setNotification_count(int notification_count) {
		this.notification_count = notification_count;
	}

	public void incError() {
		this.error_count++;
	}

	public void incWarn() {
		this.warning_count++;
	}

	public void incNotif() {
		this.notification_count++;
	}

	public void incDebug() {
		this.debug_count++;
	}

	public void incAlert() {
		this.alert_count++;

	}

	public void incInfo() {
		this.info_count++;
	}
	@Override
	public String toString() {
		return "EventCounters [error_count=" + error_count + ", warning_count=" + warning_count
				+ ", notification_count=" + notification_count + "]";
	}

}
