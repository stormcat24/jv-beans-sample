package org.stormcat.jvbeans.sample.event;

public interface Engine extends Runnable {  
	  
    public void stop();  
  
    public Boolean isStopped();  
  
}
