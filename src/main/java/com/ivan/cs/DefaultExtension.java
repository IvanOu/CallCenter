package com.ivan.cs;

import com.ivan.cs.intf.Call;
import com.ivan.cs.intf.Employee;
import com.ivan.cs.intf.ExtStatusListener;
import com.ivan.cs.intf.Extension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ivanou on 2016/11/30.
 */
public class DefaultExtension implements Extension {

	private Logger log = LoggerFactory.getLogger( DefaultExtension.class );

	private List<ExtStatusListener> listeners;

	private Call incomingCall;

	private Employee employee;

	public DefaultExtension( Employee employee ) {
		listeners = new ArrayList<>();
		if ( employee == null ) {
			throw new IllegalArgumentException( "DefaultExtension" );
		} else {
			this.employee = employee;
		}
	}

	@Override
	public void assignCall( Call call ) {
		log.info( employee.getName() + " pick up phone call..." );
		incomingCall = call;
		incomingCall.answer();
		pickUp();
	}

	// TODO: 2016/11/30 simulate user pick up phone
	private void pickUp() {
		try {
			log.info( employee.getName() + " speaking..." );
			Thread.sleep( 10000 );
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		} finally {
			if ( Calendar.getInstance().getTimeInMillis() % 2 == 0 )
				hangup();
			else
				escalate();
		}
	}

	@Override
	public void escalate() {
		log.info( employee.getName() + " escalate phone call..." );
		for ( ExtStatusListener listener : listeners ) {
			listener.onEscalate( this );
		}
	}

	@Override
	public void hangup() {
		log.info( employee.getName() + " hang up phone call..." );
		for ( ExtStatusListener listener : listeners ) {
			listener.onHangUp( this );
		}
	}

	@Override
	public void addListener( ExtStatusListener listener ) {
		if ( listeners == null ) {
			listeners = new ArrayList<>();
		}
		listeners.add( listener );
	}

	@Override
	public Call getCall() {
		return incomingCall;
	}

	@Override
	public Employee getEmployee() {
		return this.employee;
	}

}
