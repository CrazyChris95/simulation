package Assignment1_Task6;


import java.util.*;
import java.io.*;

class State extends GlobalSimulation{
	

	protected int noDepartures=0, accTotal=0, noMeasurements=0, accQ1=0;
	protected int Q1;
	protected boolean isWorkerFinished = false;
	protected double finishtime = 0 , accTime=0;
	LinkedList<Double> T_C_list = new LinkedList<Double>();
	
	Random slump = new Random(); 
	
	public void treatEvent(Event x){
		switch (x.eventType){
			case ARRIVAL_1:
				arrival();
				break;
			case DEPARTURE_1:
				departFrom1();
				break;
			case MEASURE:
				measure();
				break;	
		}
	}
	
	public void reset() {
		noDepartures=0;
		accTotal=0;
		noMeasurements=0;
		accQ1=0;
		accTime=0;
		finishtime=0;
		
		
	}
	private void arrival(){
		Q1++;
		T_C_list.addLast(time);
		
		if (Q1 == 1) {
			insertEvent(DEPARTURE_1, time + 20-10*slump.nextDouble());
		}
		
		// Makes sure that there is no arrrival after 17pm
		double a = Math.log(1-slump.nextDouble())/(-4.0/60);
		
		if ((time+a) <= 480) {
			insertEvent(ARRIVAL_1, time + a);
		
		}
	}
	

	private void departFrom1(){
		noDepartures++;
		accTime += (time-T_C_list.pollFirst());
		Q1--;
		if (Q1 > 0) 
		{	
		insertEvent(DEPARTURE_1, time + 20-10*slump.nextDouble());
		}else if (time > 480 && Q1 == 0)  //  
		{
			finishtime = time ;
		}
	}
	
	

	private void measure(){
			noMeasurements++;
			accQ1 += Q1;
			if (!(time > 480 && Q1 == 0)) {
					insertEvent(MEASURE, time + 5);
			}
	}
	
	
}