package callcenter.admin;

import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

public class CallsControl {
	
	private Semaphore semaphore;
	private PriorityQueue <Integer> pendingCalls;

	public CallsControl() {
		semaphore = new Semaphore(1);
		pendingCalls = new PriorityQueue <Integer>();
	}
	

	public void recievingCall(Integer callId) {
		try {
			semaphore.acquire();
			pendingCalls.add(callId);
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	public int endCall() {
		int call=0;
		try {
			if (areThereCallInQueue()) {
				semaphore.acquire();
				call = pendingCalls.poll();
				semaphore.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return call;
	}

	
	public boolean areThereCallInQueue() {
		return pendingCalls.size() > 0;
	}
	
	
	
}