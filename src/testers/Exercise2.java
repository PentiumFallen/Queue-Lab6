package testers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import resourceClasses.ArrayQueue;
import resourceClasses.Job;
import resourceClasses.SLLQueue;

public class Exercise2 {

	public static void main(String[] args) throws IOException, FileNotFoundException{
		int input=0; //Hard coded to scan input1

		//select file
		Scanner in = new Scanner(System.in);
		while (input!=7) {
			System.out.println("Available input files:");
			System.out.println("1. input1");
			System.out.println("2. input2");
			System.out.println("3. input3");
			System.out.println("4. input4");
			System.out.println("5. input5");
			System.out.println("6. input6");
			System.out.println("7. EXIT");
			System.out.print("Select input data: ");
			input = Integer.parseInt(in.nextLine());
			if (input<1 || 7<input)
				System.out.println("NOT AN AVAILABLE FILE\n\n");
			if (input==7)
				System.out.print("Goodbye");
			else
				performJob(input);
		}
		in.close();
	}

	private static void performJob(int input) throws FileNotFoundException {
		int time=0;
		double sum=0;
		ArrayQueue<Job> inputQueue = new ArrayQueue<>();
		SLLQueue<Job> processingQueue = new SLLQueue<>();

		//scan file
		int id = 1;
		Scanner jobs = new Scanner(new File("fileFolder", "input"+input));
		while (jobs.hasNext()) {
			StringTokenizer token = new StringTokenizer(jobs.nextLine(), ", ");
			int at = Integer.parseInt(token.nextToken());
			int rt = Integer.parseInt(token.nextToken());
			inputQueue.enqueue(new Job(id, at, rt));
			id++;
		}
		jobs.close();
		Job[] terminatedJobs = new Job[inputQueue.size()];
		
		//job servicing
		while (!inputQueue.isEmpty() || !processingQueue.isEmpty()) {
			if (!processingQueue.isEmpty()) {
				Job service = processingQueue.first();
				service.isServed(1);
				if (service.getRemainingTime()==0) {
					service.setDepartureTime(time);
					processingQueue.dequeue();
					terminatedJobs[service.getPid()-1]=service;
				}
				else {
					processingQueue.enqueue(processingQueue.dequeue());
				}
			}
			if (!inputQueue.isEmpty())
				if (inputQueue.first().getArrivalTime()==time)
					processingQueue.enqueue(inputQueue.dequeue());
			time++;
		}
		
		//servicing results
		for (int i = 0; i < terminatedJobs.length; i++) {
			sum+=terminatedJobs[i].getDepartureTime()-terminatedJobs[i].getArrivalTime();
		}
		System.out.print("Average Time in System: ");
		System.out.printf("%.2f", (sum/terminatedJobs.length));
		System.out.println();
	}
}
