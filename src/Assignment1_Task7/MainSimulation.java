package Assignment1_Task7;


import java.util.*;
import java.io.*;

public class MainSimulation extends Global{

    public static void main(String[] args) throws IOException {

    	int days=0;
    	double accFinishtime=0;
    	Signal actSignal;
    	new SignalList();

    	// Initialization of Components
    	Component C1  = new Component();
    	Component C2 = new Component();
    	Component C3 = new Component();
    	Component C4 = new Component();
    	Component C5 = new Component();
    	
    	// Defenition of breakdown dependencies
    	C1.LinkedComponents = new Component[2];
    	C2.LinkedComponents=null;
    	C3.LinkedComponents = new Component[1];
    	C4.LinkedComponents=null;
    	C5.LinkedComponents =null;
    	C1.LinkedComponents[0]= C2;
    	C1.LinkedComponents[1] = C5;
    	C3.LinkedComponents[0]= C4;
    	
    	
    	// Linked compoents
    	
    	boolean allComponentsbroken= false;
    	
    	while(days<1000) {
    		C1.initComponent();
    		C2.initComponent();
    		C3.initComponent();
    		C4.initComponent();
    		C5.initComponent();
    		while (!allComponentsbroken){
    			actSignal = SignalList.FetchSignal();
    			time = actSignal.arrivalTime;
    			actSignal.destination.TreatSignal(actSignal);
    			if(C1.broken && C2.broken && C3.broken && C4.broken && C5.broken) {
    				allComponentsbroken=true;
    				accFinishtime+=time;
    			}
    		}	
    		time=0;
			days++;
			SignalList.reset();
			allComponentsbroken=false;
		
    	}

    System.out.println("Mean breakdown time 1000 runs  " + (double)accFinishtime/days);
    	
    	

    	

    }
}