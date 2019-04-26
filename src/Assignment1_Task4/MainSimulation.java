package Assignment1_Task4;

import java.util.*;
import java.io.*;


public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
    	Random slump = new Random();
    	
    	SimpleFileWriter file1 = new SimpleFileWriter("/Users/c/Documents/Kurser_Lund/Simulering/Assignment1_Task4_4.txt", false);
    	

  
    	Event actEvent;
    	State actState = new State(100, file1); // The state that shoud be used
    	// Some events must be put in the event list at the beginning
        insertEvent(ARRIVAL_1, Math.log(1-slump.nextDouble())/(-4),0); 
        insertEvent(MEASURE, 4,0);
        // The main simulation loop
        int i =0;
    	while (time <16000){
    		actEvent = eventList.fetchEvent();
    		time = actEvent.eventTime;
    		actState.treatEvent(actEvent);
    	}
    	file1.close();
    	System.out.println(actState.noMeasurements);
    	
    }
}                             
