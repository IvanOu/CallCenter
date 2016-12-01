package com.ivan.cs.intf;

/**
 * Extension behavior listener, co-work with Extension
 * Created by ivanou on 2016/11/30.
 */
public interface ExtStatusListener {

	void onHangUp( Extension ext );

	void onEscalate( Extension ext );

}
