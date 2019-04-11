package Assignment1_Task5;
import java.util.concurrent.ThreadLocalRandom;


import java.util.*;
import java.io.*;

//Denna klass �rver Proc, det g�r att man kan anv�nda time och signalnamn utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation 

class Gen extends Proc{

	//Slumptalsgeneratorn startas:
	//The random number generator is started:
	Random slump = new Random();

	//Generatorn har tv� parametrar:
	//There are two parameters:
	public Proc[] sendTo;    //Anger till vilken process de genererade kunderna ska skickas //Where to send customers
	public double lambda;
	private int sendstate=0;

	//H�r nedan anger man vad som ska g�ras n�r en signal kommer //What to do when a signal arrives
	public void TreatSignal(Signal x){
		switch (x.signalType){
			case DEPART:{
				;
				SignalList.SendSignal(DEPART, this, time + 4*slump.nextDouble());}
				break;
		}
	}	
	private void unirandom(int e) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, e + 1);
			SignalList.SendSignal(ARRIVAL, sendTo[randomNum], time);	
	}
	
	private void roundrobin() {
		if(sendstate == 5) {
			sendstate=0;
		}
		SignalList.SendSignal(ARRIVAL, sendTo[sendstate], time);	
		sendstate++;
	}
	
	private void minQueue() {
		int prev =Integer.MAX_VALUE;
		ArrayList<QS> smallest = new ArrayList<QS>();

		for(QS b : (QS[])sendTo) {
			if(b.numberInQueue < prev) {
				smallest.clear();
				smallest.add(0,b);
				prev = b.numberInQueue;
			
			}
			else if (b.numberInQueue == prev) {
				smallest.add(smallest.size(),b);
			}
		}
		if(smallest.size() >1 ) {
			int randomNum = ThreadLocalRandom.current().nextInt(0,smallest.size() );
			SignalList.SendSignal(ARRIVAL, smallest.get(randomNum), time);
			
		} else {
			SignalList.SendSignal(ARRIVAL, smallest.get(0), time);
			
		}
	}		
}