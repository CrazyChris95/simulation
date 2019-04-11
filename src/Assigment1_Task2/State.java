package Assigment1_Task2;


import java.util.*;

import Assignment1_Task4.SimpleFileWriter;

import java.io.*;

class State extends GlobalSimulation{
	
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	protected int  noMeasurements = 0, noRejected = 0, noArrivals=0;
	protected double accBuffer=0;
	protected int QA=0, QB=0; // 
	private boolean processor_blocked = false;
	public SimpleFileWriter sv;
	
	Random slump = new Random(); // This is just a random number generator
	
	public State (SimpleFileWriter f) {
		this.sv = f;
	}
	
	// The following method is called by the main program each time a new event has been fetched
	// from the event list in the main loop. 
	public void treatEvent(Event x){
		switch (x.eventType){
			case ARRIVAL_A:
				arrivalA();
				break;
			case ARRIVAL_B:
				arrivalB();
				break;
			case DEPARTURE_A:
				depart("A");
				break;
			case DEPARTURE_B:
				depart("B");
				break;
			case MEASURE:
				measure();
				break;
		}
	}
	
	
	
	
	// The following methods defines what should be done when an event takes place. This could
	// have been placed in the case in treatEvent, but often it is simpler to write a method if 
	// things are getting more complicated than this.
	
	private void arrivalA(){
		noArrivals++;
		QA++;
		if(QA==1 && !processor_blocked) {
			insertEvent(DEPARTURE_A, time + 0.002);
			processor_blocked = true;
			
		}
		insertEvent(ARRIVAL_A, time + Math.log(1-slump.nextDouble())/(-150.0));
	
	}
	
	private void arrivalB() {
		QB++;
	}
	
	// Changed so A has higher priority
	private void depart(String s){
		if(s.equals("A")) {
			QA--;
			insertEvent(ARRIVAL_B, time + Math.log(1-slump.nextDouble())/(-1.0));
		}
		else {
			QB--;
		}
		
		if (QB > 0) {
			insertEvent(DEPARTURE_B, time + 0.004);
			
			processor_blocked = true;
		}else if(QA > 0) {
			insertEvent(DEPARTURE_A, time + 0.002);
			processor_blocked = true;
		
		}else {
			processor_blocked =false;
		}
	}
	
	private void measure(){
		sv.writeln(Integer.toString(QA+QB));
		System.out.println("A: " + QA + " " + "B: " +QB);
		accBuffer = accBuffer + QA + QB;
		noMeasurements++;
		insertEvent(MEASURE, time + 0.1);
	
	}
}