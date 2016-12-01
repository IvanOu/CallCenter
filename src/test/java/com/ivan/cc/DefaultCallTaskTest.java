package com.ivan.cc;

import com.ivan.cc.intf.Call;
import com.ivan.cc.intf.Extension;
import com.ivan.cc.intf.ExtensionManager;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ivanou on 2016/12/1.
 */
public class DefaultCallTaskTest {

	@Test
	public void testRun() throws Exception {
		Call call = mock( Call.class );
		Extension extension = mock( Extension.class );
		when(extension.getCall()).thenReturn(call);
		ExtensionManager manager = mock( ExtensionManager.class );
		when( manager.getAvailable() ).thenReturn( extension );
		when( manager.escalate( extension ) ).thenReturn( extension );
		DefaultCallTask task = new DefaultCallTask( call, manager );
		task.run();
	}

	@Test
	public void testOnHangUp() throws Exception {
		Call call = mock( Call.class );
		Extension extension = mock( Extension.class );
		when(extension.getCall()).thenReturn(call);
		ExtensionManager manager = mock( ExtensionManager.class );
		when( manager.getAvailable() ).thenReturn( extension );
		when( manager.escalate( extension ) ).thenReturn( extension );
		DefaultCallTask task = new DefaultCallTask( call, manager );
		task.onHangUp( extension );
	}

	@Test
	public void testOnEscalate() throws Exception {
		Call call = mock( Call.class );
		Extension extension = mock( Extension.class );
		when(extension.getCall()).thenReturn(call);
		ExtensionManager manager = mock( ExtensionManager.class );
		when( manager.getAvailable() ).thenReturn( extension );
		when( manager.escalate( extension ) ).thenReturn( extension );
		DefaultCallTask task = new DefaultCallTask( call, manager );
		task.onEscalate( extension );
	}
}