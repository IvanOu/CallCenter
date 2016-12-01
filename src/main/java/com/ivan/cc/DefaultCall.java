package com.ivan.cc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivan.cc.intf.Call;

/**
 * Created by ivanou on 2016/11/30.
 */
public class DefaultCall implements Call {

	private Logger log = LoggerFactory.getLogger( DefaultCall.class );

	@Override
	public void await() {
		log.info( "call await..." );
	}

	@Override
	public void hangup() {
		log.info( "call hangup..." );
	}

	@Override
	public void answer() {
		log.info( "call answer..." );
	}
}
