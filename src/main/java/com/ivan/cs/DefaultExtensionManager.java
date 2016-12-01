package com.ivan.cs;

import com.google.gson.Gson;
import com.ivan.cs.intf.Employee;
import com.ivan.cs.intf.Extension;
import com.ivan.cs.intf.ExtensionManager;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Created by ivanou on 2016/11/30.
 */
public class DefaultExtensionManager implements ExtensionManager {

	private static final Logger log = LoggerFactory.getLogger( DefaultExtensionManager.class );

	private static final String DS_FILE_NAME = "extensions";

	private static DefaultExtensionManager manager;

	private static List<Employee> employeeList;

	private Lock lock = new ReentrantLock();

	private DefaultExtensionManager() {
		if ( employeeList == null ) {
			prepare();
		}
	}

	public static DefaultExtensionManager instance() {
		if ( manager == null ) {
			manager = new DefaultExtensionManager();
		}
		return manager;
	}

	@Override
	public Extension getAvailable() {
		lock.lock();
		Extension rst = null;
		List<Employee> emps;
		try {
			emps = employeeList.stream().filter( e -> e.getRole() == RoleType.FR ).collect( Collectors.toList() );
			if ( CollectionUtils.isEmpty( emps ) ) {
				emps = employeeList.stream().filter( e -> e.getRole() == RoleType.TL ).collect( Collectors.toList() );
			}
			if ( CollectionUtils.isEmpty( emps ) ) {
				emps = employeeList.stream().filter( e -> e.getRole() == RoleType.PM ).collect( Collectors.toList() );
			}
			if ( CollectionUtils.isNotEmpty( emps ) ) {
				rst = createExtension( emps );
			}
		} finally {
			lock.unlock();
		}
		log.info( "after getAvailable size = " + employeeList.size() );
		return rst;
	}

	@Override
	public Extension escalate( Extension extension ) {
		lock.lock();
		Extension rst = null;
		List<Employee> emps = null;
		try {
			switch ( extension.getEmployee().getRole() ) {
				case FR:
					emps = employeeList.stream().filter( e -> e.getRole() == RoleType.TL ).collect( Collectors.toList() );
					break;
				case TL:
					emps = employeeList.stream().filter( e -> e.getRole() == RoleType.PM ).collect( Collectors.toList() );
					break;
			}
			if ( CollectionUtils.isNotEmpty( emps ) ) {
				rst = createExtension( emps );
			}
		} finally {
			lock.unlock();
		}
		log.info( "after escalate size = " + employeeList.size() );
		return rst;
	}

	@Override
	public void hangup( Extension extension ) {
		lock.lock();
		try {
			employeeList.add( extension.getEmployee() );
		} finally {
			lock.unlock();
		}
		log.info( "after hangup size = " + employeeList.size() );
	}

	private Extension createExtension( List<Employee> emps ) {
		Collections.shuffle( emps );
		Employee p_emp = emps.get( 0 );
		employeeList.remove( p_emp );
		log.info( "manager pick " + p_emp.getName() );
		return new DefaultExtension( p_emp );
	}

	private void prepare() {
		Gson gson = new Gson();
		EmployeeImpl[] employees = gson.fromJson( loadDs(), EmployeeImpl[].class );
		employeeList = new ArrayList<>();
		employeeList.addAll( Arrays.asList( employees ) );
	}

	private String loadDs() {
		StringBuilder result = new StringBuilder( "" );
		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader.getResource( DS_FILE_NAME );
		if ( url != null ) {

			File file = new File( url.getFile() );
			try {
				try (Scanner scanner = new Scanner( file )) {

					while ( scanner.hasNextLine() ) {
						String line = scanner.nextLine();
						result.append( line ).append( "\n" );
					}
				}
			} catch ( FileNotFoundException e ) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}

}
