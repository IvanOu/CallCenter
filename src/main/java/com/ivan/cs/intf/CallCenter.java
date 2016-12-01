package com.ivan.cs.intf;

/**
 * Created by ivanou on 2016/11/30.
 */
public interface CallCenter {

	void start( int poolSize );

	void incomingCall( Call call );

	void shutDown();

}
