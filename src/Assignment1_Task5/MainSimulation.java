package Assignment1_Task5;


import java.util.*;
import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation


public class MainSimulation extends Global{

    public static void main(String[] args) throws IOException {

    	//Signallistan startas och actSignal deklareras. actSignal �r den senast utplockade signalen i huvudloopen nedan.
    	// The signal list is started and actSignal is declaree. actSignal is the latest signal that has been fetched from the 
    	// signal list in the main loop below.

    	Signal actSignal;
    	new SignalList();

    	//H�r nedan skapas de processinstanser som beh�vs och parametrar i dem ges v�rden.
    	// Here process instances are created (two queues and one generator) and their parameters are given values. 

    	QS Q1 = new QS();
    	QS Q2 = new QS();
    	QS Q3 = new QS();
    	QS Q4 = new QS();
    	QS Q5 = new QS();
    	Q1.sendTo = null;

    	Gen Generator = new Gen();
    	Generator.lambda = 9;
    	Generator.sendTo = new QS[5];
    	Generator.sendTo[0] = Q1;
    	Generator.sendTo[1] = Q2;
    	Generator.sendTo[2] = Q3;
    	Generator.sendTo[3] = Q4;
    	Generator.sendTo[4] = Q5;
    	
    	SignalList.SendSignal(DEPART, Generator, time);
    	SignalList.SendSignal(MEASURE, Q1, 1);
    	SignalList.SendSignal(MEASURE, Q2, 1);
    	SignalList.SendSignal(MEASURE, Q3, 1);
    	SignalList.SendSignal(MEASURE, Q4, 1);
    	SignalList.SendSignal(MEASURE, Q5, 1);



    	// Detta �r simuleringsloopen:
    	// This is the main loop

    	while (time < 100000){
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
    	}
    	
    	
    	double meanTime = (Q1.accTime/Q1.noDepartures + Q2.accTime/Q2.noDepartures +  Q3.accTime/Q3.noDepartures + Q4.accTime/Q4.noDepartures + Q5.accTime/Q5.noDepartures)/5  ;
    	double meancust = ((double)Q1.accumulated/Q1.noMeasurements + (double)Q2.accumulated/Q2.noMeasurements +  (double)Q3.accumulated/Q3.noMeasurements + (double)Q4.accumulated/Q4.noMeasurements + (double) Q5.accumulated/Q5.noMeasurements);  

    	//System.out.println("Mean number of customers in queuing system: " + 1.0*Q1.accumulated/Q1.noMeasurements);
    	System.out.println("Time");
    	System.out.println(meanTime);
    	System.out.println("Customers");
    	System.out.println(meancust);
    	

    	

    }
}