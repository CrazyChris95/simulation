package Assignment1_Task4;


import java.util.*;
import java.io.*;

class State extends GlobalSimulation{
	
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int  noMeasurements = 0, noArrivals=0, noDepartures=0, accTotal=0;
	public int Q1, Q2, cust_inSystem=0;
	protected double T_total;
	public int noServers;
	public SimpleFileWriter file1;
	protected Server[] servers;
	
	Random slump = new Random(); // This is just a random number generator
	
	
	public State(int noServers, SimpleFileWriter file1) {
		this.noServers=noServers;
		this.file1 = file1;
		servers = new Server[noServers];
		
		for (int i =0; i <noServers ; i++) {
			servers[i]= new Server(i);
			
		}	
		
	}
	
	// The following method is called by the main program each time a new event has been fetched
	// from the event list in the main loop. 
	public void treatEvent(Event x){
		switch (x.eventType){
			case ARRIVAL_1:
				arrivalTo1();
				break;
			case DEPARTURE_1:
				departFrom1(x.server);
				break;
			case MEASURE:
				measure();
				break;	
		}
	}
	
	private void arrivalTo1(){
		for (Server server : servers) {
			if(server.isAvailable()) {
				server.arrive();
				insertEvent(DEPARTURE_1, time + Math.log(1-slump.nextDouble())/(-1/100.0), server.index);
				break;
			}
		}
		insertEvent(ARRIVAL_1, time + Math.log(1-slump.nextDouble())/(-10), 0);
	}
	
	private void departFrom1(int e){
	    servers[e].depart();
	}
	

	
	private void measure(){
		noMeasurements++;
		System.out.println(cust_inSystem);
		file1.writeln(Integer.toString(cust_inSystem));
		insertEvent(MEASURE, time + 4,0);
	}
	
	private class Server{ 
		 private boolean available=true;
		 public int index ;
		 
		 Server(int index) {
			 this.index =index;
		 }
		 
	
		 public void arrive() {
			 cust_inSystem++;
			 available=false;
		 }
		 
		 public void depart() {
			 cust_inSystem--;
			 available =true;
		 }
		 
		 public boolean isAvailable() {
			 return available;
		 }	 
	}
}