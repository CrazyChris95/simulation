package Assignment1;


import java.util.*;
import java.io.*;

class State extends GlobalSimulation{
	
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	protected int  noMeasurements = 0, noRejected = 0, noArrivals=0;
	protected int Q1, Q2, accQ2;
	protected SimpleFileWriter file1, file2, file3;

	public State(SimpleFileWriter file1, SimpleFileWriter file2, SimpleFileWriter file3) {
		this.file1 = file1;
		this.file2 = file2;
		this.file3 = file3;
		
	}
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
		noArrivals++;
		
		if (Q1 < 10) {
			Q1++;
		}
		else  {
			noRejected++;
		}
		if (Q1 == 1) {
			insertEvent(DEPARTURE_1, time + Math.log(1-slump.nextDouble())/(1/-2.1));
		}
		
		insertEvent(ARRIVAL_1, time + 5);
	}
	
	private void departFrom1(){
		Q1--;
		Q2++;
		
		if(Q2 == 1) {
			insertEvent(DEPARTURE_2, time + 2);
		}
		if (Q1 > 0) {
			//System.out.println()
			insertEvent(DEPARTURE_1, time + Math.log(1-slump.nextDouble())/(1/-2.1));
		}	
	}
	
	private void departFrom2() {
		Q2--;
		if (Q2 > 0) {
			insertEvent(DEPARTURE_2, time + 2);
		}	
	}
	
	private void measure(){
		noMeasurements++;
		accQ2 += Q2;
		insertEvent(MEASURE, time + Math.log(1-slump.nextDouble())/(1/-5.0));
	}
}