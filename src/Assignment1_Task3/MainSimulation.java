package Assignment1_Task3;

import java.util.*;
import java.io.*;


public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
    	Random slump = new Random();
    	

  
    	Event actEvent;
    	State actState = new State(); // The state that shoud be used
    	// Some events must be put in the event list at the beginning
        insertEvent(ARRIVAL_1, Math.log(1-slump.nextDouble())/(-1/2.0)); 
        insertEvent(MEASURE, 1);
        // The main simulation loop
    	while (time < 6000){
    		actEvent = eventList.fetchEvent();
    		System.out.println(actEvent.eventType);
    		System.out.println(actEvent.eventTime);
    		time = actEvent.eventTime;
    		actState.treatEvent(actEvent);
    	}
    	
   
    	System.out.println("Mean Customer in system: " + (double) actState.accTotal/actState.noMeasurements);
    	System.out.println("Mean time in system: " + actState.T_total/actState.noDepartures);
    	System.out.println("No measurements: " + actState.noMeasurements);
    }
}