package Assignment1_Task6;

import java.util.Random;

public class gd {

	public static void main(String[] args) {
		
		Random slump = new Random();
		System.out.println(Math.log(1-slump.nextDouble())/(-4.0/60));
	}

}
