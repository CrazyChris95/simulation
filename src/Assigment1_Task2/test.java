package Assigment1_Task2;
 
import java.io.*;
import java.util.Random;
public class test {

	public static void main(String[] args) {
		Random slump = new Random();
		double a = Math.log(1-slump.nextDouble())/(-1);
		System.out.println(a);
	}

}
