package com.ivan.cs;

import com.ivan.cs.intf.Call;
import com.ivan.cs.intf.Employee;
import com.ivan.cs.intf.ExtStatusListener;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ivanou on 2016/12/1.
 */
public class DefaultExtensionTest {

	@Test
	public void testAssignCall() throws Exception {
		Call call = mock( Call.class );
		Employee employee = mock( Employee.class );
		when( employee.getRole() ).thenReturn( RoleType.FR );
		when( employee.getName() ).thenReturn( "ivan" );
		DefaultExtension extension = new DefaultExtension( employee );
		extension.assignCall( call );
	}

	@Test
	public void testEscalate() throws Exception {
		Employee employee = mock( Employee.class );
		when( employee.getRole() ).thenReturn( RoleType.FR );
		when( employee.getName() ).thenReturn( "ivan" );
		DefaultExtension extension = new DefaultExtension( employee );
		extension.escalate();
	}

	@Test
	public void testHangup() throws Exception {
		Employee employee = mock( Employee.class );
		when( employee.getRole() ).thenReturn( RoleType.FR );
		when( employee.getName() ).thenReturn( "ivan" );
		DefaultExtension extension = new DefaultExtension( employee );
		extension.hangup();
	}

	@Test
	public void testAddListener() throws Exception {
		Employee employee = mock( Employee.class );
		when( employee.getRole() ).thenReturn( RoleType.FR );
		when( employee.getName() ).thenReturn( "ivan" );
		DefaultExtension extension = new DefaultExtension( employee );
		ExtStatusListener listener = mock( ExtStatusListener.class );
		extension.addListener( listener );
	}

	@Test
	public void testGetCall() throws Exception {
		Call call = mock( Call.class );
		Employee employee = mock( Employee.class );
		when( employee.getRole() ).thenReturn( RoleType.FR );
		when( employee.getName() ).thenReturn( "ivan" );
		DefaultExtension extension = new DefaultExtension( employee );
		extension.assignCall( call );
		Assert.assertNotNull( extension.getCall() );
	}

	@Test
	public void testGetEmployee() throws Exception {
		Employee employee = mock( Employee.class );
		when( employee.getRole() ).thenReturn( RoleType.FR );
		when( employee.getName() ).thenReturn( "ivan" );
		DefaultExtension extension = new DefaultExtension( employee );
		Assert.assertNotNull( extension.getEmployee() );
	}
}