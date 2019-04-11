package Assignment1_Task6;

import java.util.*;
import java.io.*;


public class MainSimulation extends GlobalSimulation{
	// Event scheuduling
    public static void main(String[] args) throws IOException {
    	Random slump = new Random();
    	double accTime= 0;
    	double accFinishtime =0;
    	double accCust =0;
    	int days=0;
    	
    	
  
    	Event actEvent;
    	 // The state that shoud be used
    	// Some events must be put in the event list at the beginning
    	State actState = new State();
        // The main simulation loop
        while(days<10000) {
        	insertEvent(MEASURE, time + 5);
        	insertEvent(ARRIVAL_1, Math.log(1-slump.nextDouble())/(-4.0/60));
        	while  (eventList.getNextEvent() != null){
        		actEvent = eventList.fetchEvent();
        		time = actEvent.eventTime;
        		actState.treatEvent(actEvent);
        	}
        	
        	days++;
        	time = 0;
        	accTime +=(actState.accTime/actState.noDepartures);
        	System.out.println(actState.accTime/actState.noDepartures);
        	accCust+=(double)actState.accQ1/actState.noMeasurements;
        	accFinishtime += actState.finishtime;
        	eventList.reset();
        	actState.reset();
        	//System.out.println(actState.noMeasurements);
        
        }
        System.out.println("accCust" + accCust/10000);
        System.out.println("Average time customer spent in system " + accTime/10000);
        System.out.println("Average finishtime each day " + accFinishtime/10000);
        
    	
    }
}                             
