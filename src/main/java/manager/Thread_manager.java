package manager;

public class Thread_manager extends Thread {

	public enum Nature_Thread{
		RECEPTION, ENVOI
	}
	
	void init_thread(Nature_Thread Th) {
		this.start();
		
	}
	
	public void run() {
		//Mettre la fonction envoi ou reception message
	}
	static public void main(String argv[]) {
		
	}
}