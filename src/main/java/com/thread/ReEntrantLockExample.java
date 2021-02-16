package com.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLockExample {
	
	int counter = 0;
	
	public void incrementCounter(ReentrantLock lock) {
		lock.lock();
		System.out.println("Lock in incrementCounter Method");
		try {
			for (int i=0; i<=100; i++) {
				this.counter+= i;
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println("Counter Value after incrementCounter Method =" + this.counter );
			lock.unlock();
			System.out.println("Lock released in incrementCounter Method");
			
		}
	}
	
	public void decrementCounter(ReentrantLock lock) {
		lock.lock();
		System.out.println("Lock in decrementCounter Method");
		try {
			for (int i=100; i>0; i--) {
				this.counter-= i;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println("Counter Value after decrementCounter Method =" +this.counter );
			lock.unlock();
			System.out.println("Lock released in decrementCounter Method");
			
		}
	}
	
	
	public void getCounter() {
		System.out.println("Counter Value after All Processes = " + this.counter);
	}
	

	public static void main(String[] args) throws InterruptedException {
		
		ReEntrantLockExample example = new ReEntrantLockExample();
		
		ReentrantLock lock = new ReentrantLock();
		
		Thread incrementThread = new Thread(()-> example.incrementCounter(lock));
		
		Thread decrementThread = new Thread(()-> example.decrementCounter(lock));
	
		incrementThread.start();
		decrementThread.start();
		
		incrementThread.join();
		decrementThread.join();
		example.getCounter();
		
		

	}

}
