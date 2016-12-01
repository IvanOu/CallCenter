package com.ivan.cs.intf;

/**
 * This interface show behavior of extensions.
 * Created by ivanou on 2016/11/30.
 */
public interface Extension {

	void assignCall( Call call );

	void escalate();

	void hangup();

	void addListener( ExtStatusListener listener );

	Call getCall();

	Employee getEmployee();

}
