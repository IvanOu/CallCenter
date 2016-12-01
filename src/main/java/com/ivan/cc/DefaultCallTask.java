package com.ivan.cc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivan.cc.intf.Call;
import com.ivan.cc.intf.ExtStatusListener;
import com.ivan.cc.intf.Extension;
import com.ivan.cc.intf.ExtensionManager;

/**
 * Runnable task handle the call connect to extension,hangup call if have no extension available.
 * Created by ivanou on 2016/11/30.
 */
public class DefaultCallTask implements Runnable, ExtStatusListener {

	private Logger log = LoggerFactory.getLogger( DefaultCallTask.class );

	private ExtensionManager manager;

	private Call incomingCall;

	private DefaultCallTask() {

	}

	public DefaultCallTask( Call incomingCall, ExtensionManager manager ) {
		if ( incomingCall == null ) {
			throw new IllegalArgumentException( "DefaultCallTask" );
		} else {
			this.incomingCall = incomingCall;
			if ( manager == null ) {
				this.manager = DefaultExtensionManager.instance();
			} else {
				this.manager = manager;
			}
		}
	}

	@Override
	public void run() {
		Extension extension;
		try {
			extension = connectExt();
			if ( extension == null ) {
				log.error( "have no extension available" );
				incomingCall.hangup();
			} else {
				assign( extension );
			}
		} catch ( InterruptedException e ) {
			log.error( e.getLocalizedMessage() );
		}
	}

	@Override
	public void onHangUp( Extension ext ) {
		ext.getCall().hangup();
		manager.hangup( ext );
	}

	@Override
	public void onEscalate( Extension ext ) {
		ext.getCall().await();
		Extension extension = manager.escalate( ext );
		if ( extension != null ) {
			manager.hangup( ext );
			assign( extension );
		} else {
			manager.hangup( ext );
		}
	}

	private void assign( Extension extension ) {
		extension.addListener( this );
		extension.assignCall( incomingCall );
	}

	private Extension connectExt() throws InterruptedException {
		Extension extension;
		int expire = 0;
		do {
			Thread.sleep( 1000 );
			expire++;
			extension = manager.getAvailable();
		} while ( extension == null && expire < 5 );
		return extension;
	}
}
