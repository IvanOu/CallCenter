package com.ivan.cs;

import com.ivan.cs.intf.Call;
import com.ivan.cs.intf.CallCenter;
import com.ivan.cs.intf.ExtensionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ivanou on 2016/11/30.
 */
public class DefaultCallCenter implements CallCenter {

	private Logger log = LoggerFactory.getLogger( DefaultCallCenter.class );

	private ExecutorService executor;

	private ExtensionManager extensionManager;

	public DefaultCallCenter() {
		this.extensionManager = DefaultExtensionManager.instance();
	}

	public DefaultCallCenter( ExtensionManager extensionManager ) {
		this.extensionManager = extensionManager;
	}

	@Override
	public void start( int poolSize ) {
		log.info( "Call center starting.." );
		this.executor = Executors.newFixedThreadPool( poolSize );
	}

	@Override
	public void incomingCall( Call call ) {
		executor.execute( new DefaultCallTask( call, extensionManager ) );
	}

	@Override
	public void shutDown() {
		log.info( "Call center shutDown.." );
		if ( executor != null ) {
			try {
				executor.awaitTermination( 5000, TimeUnit.MILLISECONDS );
				executor.shutdown();
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}

}
