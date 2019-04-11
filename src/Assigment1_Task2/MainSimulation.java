package Assigment1_Task2;

import java.util.*;

import Assignment1_Task4.SimpleFileWriter;

import java.io.*;


public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
    	Random slump = new Random();
    	
    	//Made in order to save data from simulation
    	SimpleFileWriter f = new SimpleFileWriter("/Users/c/Documents/Kurser_Lund/Simulering/Assignment1_Task_4.txt", false);

    	Event actEvent;
    	State actState = new State(f); // The state that shoud be used
    	
        insertEvent(ARRIVAL_A, Math.log(1-slump.nextDouble())/(-150.0)); 
        insertEvent(MEASURE, 0.1);
        
		
        // The main simulation loop
        double oldTime=0;
    	while (time < 100){
    		actEvent = eventList.fetchEvent();

    		time = actEvent.eventTime;
    		actState.treatEvent(actEvent);
    		
    	}
    	 
    	f.close();
   
    	System.out.println("MeanBUFFER: " + (double) actState.accBuffer/actState.noMeasurements);
    	System.out.println("No measurements: " + actState.noMeasurements);
    }
}