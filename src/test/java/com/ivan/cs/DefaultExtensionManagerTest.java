package com.ivan.cs;

import com.ivan.cs.intf.Employee;
import com.ivan.cs.intf.Extension;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ivanou on 2016/12/1.
 */
public class DefaultExtensionManagerTest {

	@Test
	public void testInstance() throws Exception {
		DefaultExtensionManager manager = DefaultExtensionManager.instance();
		Assert.assertNotNull( manager );
	}

	@Test
	public void testGetAvailable() throws Exception {
		DefaultExtensionManager manager = DefaultExtensionManager.instance();
		Assert.assertNotNull( manager.getAvailable() );
	}

	@Test
	public void testEscalate() throws Exception {
		Employee employee = mock( Employee.class );
		when( employee.getRole() ).thenReturn( RoleType.FR );
		when( employee.getName() ).thenReturn( "ivan" );
		Extension extension = mock( Extension.class );
		when( extension.getEmployee() ).thenReturn( employee );
		DefaultExtensionManager manager = DefaultExtensionManager.instance();
		Assert.assertNotNull( manager.escalate( extension ) );
	}

	@Test
	public void testHangup() throws Exception {
		Employee employee = mock( Employee.class );
		when( employee.getRole() ).thenReturn( RoleType.FR );
		when( employee.getName() ).thenReturn( "ivan" );
		Extension extension = mock( Extension.class );
		when( extension.getEmployee() ).thenReturn( employee );
		DefaultExtensionManager manager = DefaultExtensionManager.instance();
		manager.hangup( extension );
	}
}