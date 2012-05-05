package org.stormcat.jvbeans.sample.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stormcat.jvbeans.jvlink.JvComponentInjector;
import org.stormcat.jvbeans.jvlink.JvLinkManager;

public class JvEventWatchEngine implements Engine {
	
	private static final Logger logger = LoggerFactory.getLogger(JvEventWatchEngine.class);

	private JvLinkManager manager;
    /** 
     * 停止フラグ 
     */  
    private Boolean isStopped = Boolean.FALSE;  
  
    /** 
     * スレッドID 
     */  
    private String id = null;  
	
	public void run() {
        this.id = String.valueOf(Thread.currentThread().getId());  
        
		manager = JvComponentInjector.createJvLinkManager();
		manager.setJvLinkEventHandler(new JvLinkEventHandlerImpl());
		manager.init();
		
		manager.watchEvent();
        
        while (!isStopped()) {  
            if (logger.isInfoEnabled()) {  
                logger.info("[" + id + "]" + "hoge");  
            }  
  
            try {  
                Thread.sleep(10000);  
            } catch (InterruptedException e) {  
                isStopped = Boolean.TRUE;  
                if (logger.isErrorEnabled()) {  
                	logger.error(e.getMessage());
                }  
            }  
        }
	}

	public void stop() {
		this.isStopped = Boolean.TRUE;  
		manager.close();
	}

	public Boolean isStopped() {
		return isStopped;
	}

}
