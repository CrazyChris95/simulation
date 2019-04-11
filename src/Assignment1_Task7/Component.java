package Assignment1_Task7;


import java.util.*;
import java.io.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class Component extends Proc{
	public Proc [] LinkedComponents;
	protected boolean broken;
	Random slump = new Random();

	public void TreatSignal(Signal x){
			switch (x.signalType){
				case TIME_UNTIL_BREAK:
					break;
				case  BREAK:
					breakdown();
					break;
			}
				
		}	
		public void initComponent() {
			broken =false;
			double a= 5-4*slump.nextDouble();
			System.out.println(a);
			SignalList.SendSignal(BREAK, this, time + a);
			
			
		}
		private void breakdown() {
			broken = true;
			if(LinkedComponents != null) {
				for(Proc c : LinkedComponents) {
					SignalList.SendSignal(BREAK, c, time);
				}
			}
		}

}