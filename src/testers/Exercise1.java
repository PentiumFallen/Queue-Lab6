package testers;

import resourceClasses.ArrayQueue;
import resourceClasses.Queue;
import resourceClasses.SLLQueue;

public class Exercise1 {

	public static void main(String[] args) {
		Queue<Integer> queue1 = new SLLQueue<Integer>();
		Queue<Integer> queue2 = new ArrayQueue<Integer>();
		int[] data = {1,2,3,4,5,6,9,8,7};
		for (int i = 0; i < data.length; i++) {
			queue1.enqueue(data[i]);
			queue2.enqueue(data[data.length-i-1]);
		}
		queue1.enqueue(10);
		queue2.enqueue(0);
		for (int i = 0; i < data.length/3; i++) {
			queue1.dequeue();
			queue2.dequeue();
		}

		displayQueue(queue1);
		displayQueue(queue2);
	}

	private static void displayQueue(Queue<Integer> queue) {
		System.out.print("{");
		int length=queue.size();
		for (int i = 0; i < length; i++) {
			System.out.print(queue.dequeue());
			if (!queue.isEmpty())
				System.out.print(", ");
		}
		System.out.println("}");
	}
}