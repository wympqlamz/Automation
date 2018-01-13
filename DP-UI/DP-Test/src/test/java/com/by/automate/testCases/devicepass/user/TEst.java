package com.by.automate.testCases.devicepass.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TEst {

	    public static void main(String[] args) {  
	    	final ScheduledExecutorService service = Executors  
	                .newSingleThreadScheduledExecutor();  
	        Runnable runnable = new Runnable() {  
	            public void run() {  
	                // task to run goes here  
	            	
	                System.out.println(time()+"-- Hello !!");
	                System.out.println("Shoud down");
	                service.shutdown();
	                
	            }  
	        };  
	        
	        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
	        service.scheduleAtFixedRate(runnable, 2, 1, TimeUnit.SECONDS);  
	      
	    }  
	    
	    public static String time(){
	    	Date date = new Date();
    		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss.SSS");
    		return f.format(date);
	    }

}
