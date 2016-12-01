package com.ivan.cs.intf;

/**
 * ExtensionManager managed transmission path of extension.
 * Created by ivanou on 2016/11/30.
 */
public interface ExtensionManager {
	Extension getAvailable();

	Extension escalate( Extension extension );

	void hangup( Extension extension );

}
