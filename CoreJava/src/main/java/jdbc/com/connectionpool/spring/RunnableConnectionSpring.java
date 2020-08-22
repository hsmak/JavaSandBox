package jdbc.com.connectionpool.spring;

import java.sql.Connection;


public class RunnableConnectionSpring implements Runnable{

	ConnectionPoolSpring pool;
	
	public RunnableConnectionSpring(ConnectionPoolSpring pool){
		this.pool = pool;
	}
	
	public void run(){
//		if(!this.pool.isEmpty()){
			Connection c = pool.pullConnection();
			try {
				
				System.out.println("Running " + Thread.currentThread().toString());
				
				/*
				 * this is for testing
				 * comment this line when not testing
				 */
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			pool.pushConnection(c);
			
			System.out.println("Push back " + Thread.currentThread().toString());
//		}
	}
}
