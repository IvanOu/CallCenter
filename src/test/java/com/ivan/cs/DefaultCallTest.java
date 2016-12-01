package com.ivan.cs;

import org.junit.Test;

/**
 * Created by ivanou on 2016/12/1.
 */
public class DefaultCallTest {

	@Test
	public void testAwait() throws Exception {
		new DefaultCall().await();
	}

	@Test
	public void testHangup() throws Exception {
		new DefaultCall().hangup();
	}

	@Test
	public void testAnswer() throws Exception {
		new DefaultCall().answer();
	}
}