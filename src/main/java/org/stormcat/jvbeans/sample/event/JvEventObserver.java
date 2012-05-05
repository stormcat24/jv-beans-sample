package org.stormcat.jvbeans.sample.event;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JvEventObserver implements Daemon {  
	  
    private static Logger logger = LoggerFactory.getLogger(JvEventObserver.class);
  
    private static Engine engine = null;  
  
    private static JvEventObserver engineLauncherInstance = new JvEventObserver();  
  
    private ExecutorService executor = null;  
  
    public static void main(String[] args) {  
        engineLauncherInstance.initialize();  
  
        engineLauncherInstance.startWindowsService();  
  
        Scanner sc = new Scanner(System.in);  
        System.out.printf("Enter 'stop' to halt: ");  
  
        while (!sc.nextLine().toLowerCase().equals("stop")) {  
            ;  
        }  
  
        engineLauncherInstance.stopWindowsService();  
  
    }  
  
    public static void windowsService(String args[]) {  
        String cmd = "start";  
        if (args.length > 0) {  
            cmd = args[0];  
        }  
  
        if ("start".equals(cmd)) {  
            engineLauncherInstance.startWindowsService();  
        } else {  
            engineLauncherInstance.stopWindowsService();  
        }  
    }  
  
    public void startWindowsService() {  
        if (logger.isDebugEnabled()) {  
            logger.debug("startWindowsService called");  
        }  
  
        initialize();  
        executor.execute(engine);  
    }  
  
    public void stopWindowsService() {  
        if (logger.isDebugEnabled()) {  
            logger.debug("stopWindowsService called");  
        }  
  
        terminate();  
  
        executor.shutdown();  
    }  
  
    public void init(DaemonContext arg0) throws Exception {  
        logger.debug("Daemon init");  
    }  
  
    public void start() {  
        if (logger.isDebugEnabled()) {  
            logger.debug("Daemon start");  
        }  
        initialize();  
    }  
  
    public void stop() {  
        if (logger.isDebugEnabled()) {  
            logger.debug("Daemon stop");  
        }  
  
        terminate();  
    }  
  
    public void destroy() {  
        if (logger.isDebugEnabled()) {  
            logger.debug("Daemon destroy");  
        }  
    }  
  
    private void initialize() {  
        if (engine == null) {  
            if (logger.isInfoEnabled()) {  
                logger.info("Starting the Engine");  
            }  
            engine = new JvEventWatchEngine();
        }  
  
        executor = Executors.newSingleThreadExecutor();  
    }  
  
    public void terminate() {  
        if (engine != null) {  
            if (logger.isInfoEnabled()) {  
                logger.info("Stopping the Engine");  
            }  
  
            engine.stop();  
  
            if (logger.isInfoEnabled()) {  
                logger.info("Engine stopped");  
            }  
        }  
    }  
  
}  