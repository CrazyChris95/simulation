package Assignment1;

import java.util.*;
import java.io.*;


public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
    	Random slump = new Random();
    	
    	//Made in order to save data from simulation
    	SimpleFileWriter file1 = new SimpleFileWriter("/Users/c/eclipse-workspace/Simulering/src/Assignment1/T1_Q1.txt", true);
    	file1.writeln("Sim1");
    	SimpleFileWriter file2 = new SimpleFileWriter("/Users/c/eclipse-workspace/Simulering/src/Assignment1/T1_Q2.txt", true);
    	file2.writeln("Sim1");
    	SimpleFileWriter file3 = new SimpleFileWriter("/Users/c/eclipse-workspace/Simulering/src/Assignment1/T1_Rj.txt", true);
    	file3.writeln("Sim1");
    	
    	Event actEvent;
    	State actState = new State(file1, file2, file3); // The state that shoud be used
    	// Some events must be put in the event list at the beginning
        insertEvent(ARRIVAL_1, 5); 
        insertEvent(MEASURE, Math.log(1-slump.nextDouble())/(1/-5.0));
        // The main simulation loop
    	while (time < 6000){
    		actEvent = eventList.fetchEvent();
    		System.out.println(actEvent.eventType);
    		System.out.println(actEvent.eventTime);
    		time = actEvent.eventTime;
    		actState.treatEvent(actEvent);
    	}
    	file3.writeln(Integer.toString(actState.noRejected));
    	
    	file1.close();
    	file2.close();
    	file3.close();
   
    	System.out.println("MeanQ2: " + (double) actState.accQ2/actState.noMeasurements);
    	System.out.println("Probability of Rejections Q1: " + (double)actState.noRejected/actState.noArrivals);
    	System.out.println("No measurements: " + actState.noMeasurements);
    }
}