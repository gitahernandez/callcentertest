package callcenter.thread;

import callcenter.admin.CallsControl;

public class Call extends Thread {
	private int callId;
	private CallsControl callAdmin;

	public Call(int id, CallsControl callAdmin) {
		this.callId = id;
		this.callAdmin = callAdmin;
	}

	public void run() {
		callAdmin.recievingCall(callId);
	}
}