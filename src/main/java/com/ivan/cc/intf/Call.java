package com.ivan.cc.intf;

/**
 * Incoming call object,co-work with call center
 * Created by ivanou on 2016/11/30.
 */
public interface Call {

	void await();

	void hangup();

	void answer();
}
