package com.ivan.cc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ivan.cc.intf.Call;

/**
 * Created by ivanou on 2016/12/1.
 */
public class DefaultCallCenterTest {

	private DefaultCallCenter callCenter;

	@Before
	public void before() {
		callCenter = new DefaultCallCenter();
		callCenter.start( 5 );
	}

	@Test
	public void testIncomingCall() throws Exception {
		Call call = Mockito.mock( Call.class );
		for ( int ind = 0; ind < 20; ind++ ) {
			Thread.sleep( 1000 );
			callCenter.incomingCall( call );
		}

	}

	@After
	public void after() {
		callCenter.shutDown();
	}

}