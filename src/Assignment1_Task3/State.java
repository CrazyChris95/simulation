package Assignment1_Task3;


import java.util.*;
import java.io.*;

class State extends GlobalSimulation{
	
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int  noMeasurements = 0, noArrivals=0, noDepartures=0, accTotal=0;
	public int Q1, Q2;
	protected double T_total;
	
	LinkedList<Double> T_C_list = new LinkedList<Double>();
	Random slump = new Random(); // This is just a random number generator
	
	
	
	// The following method is called by the main program each time a new event has been fetched
	// from the event list in the main loop. 
	public void treatEvent(Event x){
		switch (x.eventType){
			case ARRIVAL_1:
				arrivalTo1();
				break;
			case DEPARTURE_1:
				departFrom1();
				break;
			case MEASURE:
				measure();
				break;
			case DEPARTURE_2:
				departFrom2();
				break;
		}
	}
	
	
	
	
	// The following methods defines what should be done when an event takes place. This could
	// have been placed in the case in treatEvent, but often it is simpler to write a method if 
	// things are getting more complicated than this.
	
	private void arrivalTo1(){
		T_C_list.addLast(time);
		noArrivals++;
		Q1++;
		
		if (Q1 == 1) {
			insertEvent(DEPARTURE_1, time + Math.log(1-slump.nextDouble())/(-1));
		}
		
		insertEvent(ARRIVAL_1, time + Math.log(1-slump.nextDouble())/(-1/2.0));
	}
	
	private void departFrom1(){
		Q1--;
		Q2++;
		
		if(Q2 == 1) {
			insertEvent(DEPARTURE_2, time + Math.log(1-slump.nextDouble())/(-1));
		}
		if (Q1 > 0) {
			//System.out.println()
			insertEvent(DEPARTURE_1, time + Math.log(1-slump.nextDouble())/(-1));
		}	
	}
	
	private void departFrom2() {
		noDepartures++;
		T_total += (time-T_C_list.pollFirst());
		Q2--;
		if (Q2 > 0) {
			insertEvent(DEPARTURE_2, time + Math.log(1-slump.nextDouble())/(-1));
		}	
	}
	
	private void measure(){
		noMeasurements++;
		accTotal += (Q2 + Q1);
		insertEvent(MEASURE, time + 1);
	}
	
	private class Server{ 
		 boolean s;
		 
		 
	}
}