package org.stormcat.jvbeans.sample.event;

import org.stormcat.jvbeans.jvlink.JvLinkEventHandler;

public class JvLinkEventHandlerImpl implements JvLinkEventHandler {

	public void handlePay(String yyyymmddjjrr) {
		System.out.println("handlePay");
	}

	public void handleWeight(String yyyymmddjjrr) {
		System.out.println("handleWeight");
	}

	public void handleJockeyChange(String ttyyyymmddjjrrnnnnnnnnnnnnnn) {
		System.out.println("handleJockeyChange");
	}

	public void handleWeather(String ttyyyymmddjjrrnnnnnnnnnnnnnn) {
		System.out.println("handleWeather");
	}

	public void handleCourseChange(String ttyyyymmddjjrrnnnnnnnnnnnnnn) {
		System.out.println("handleCourseChange");
	}

	public void handleAvoid(String ttyyyymmddjjrrnnnnnnnnnnnnnn) {
		System.out.println("handleAvoid");
	}

	public void handleTimeChange(String ttyyyymmddjjrrnnnnnnnnnnnnnn) {
		System.out.println("handleTimeChange");
	}

}
