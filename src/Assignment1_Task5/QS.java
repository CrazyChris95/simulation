package Assignment1_Task5;


import java.util.*;
import java.io.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS extends Proc{
	public int numberInQueue = 0, accumulated, noMeasurements, noDepartures;
	public double accTime=0;
	LinkedList<Double> T_C_list = new LinkedList<Double>();
	public Proc sendTo;
	Random slump = new Random();

	public void TreatSignal(Signal x){
		switch (x.signalType) {
			case ARRIVAL:{
				numberInQueue++;
				T_C_list.addLast(time);
				if (numberInQueue == 1){
					SignalList.SendSignal(DEPART,this, time + Math.log(1-slump.nextDouble())/(-2.0));
				}
			} break;

			case DEPART:{
				numberInQueue--;
				noDepartures++;
				accTime += (time-T_C_list.pollFirst());
				if (sendTo != null){
					SignalList.SendSignal(ARRIVAL, sendTo, time);
				}
				if (numberInQueue > 0){
					SignalList.SendSignal(DEPART, this, time + Math.log(1-slump.nextDouble())/(-2.0));
				}
			} break;

			case MEASURE:{
				noMeasurements++;
				accumulated = accumulated + numberInQueue;
				SignalList.SendSignal(MEASURE, this, time + 1);
			} break;
		}
	}
}