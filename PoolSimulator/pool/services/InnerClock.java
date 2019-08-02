package pool.services;

public class InnerClock { 
	private static double time = 0;

	public static double getTime() {
		return time;
	}
	
	public static void setTime(double time) {
		InnerClock.time = time;
	}
	
}
