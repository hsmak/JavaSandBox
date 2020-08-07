package com.pool.connection.main;

import com.pool.connection.legacy.ConnectionPoolLegacy;
import com.pool.connection.spring.ConnectionPoolSpring;
import com.pool.connection.legacy.RunnableConnectionLegacy;
import com.pool.connection.spring.RunnableConnectionSpring;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class TestLegacy {
	
	public static void main(String[] args) throws InterruptedException{

		ConnectionPoolLegacy pool = new ConnectionPoolLegacy(2);
		
		Thread[] threads = new Thread[4];
		for(int i=0; i<threads.length; i++){
			
			(threads[i] = new Thread(new RunnableConnectionLegacy(pool), "T"+i)).start();
			
		}				
	}
}

class TestSpring{

    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("datasource-context.xml");
        ConnectionPoolSpring pool = context.getBean("connectionPool", ConnectionPoolSpring.class);

        Thread[] threads = new Thread[4];
        for(int i=0; i<threads.length; i++){

            (threads[i] = new Thread(new RunnableConnectionSpring(pool), "T"+i)).start();

        }
    }
}