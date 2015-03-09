/**
 * 
 */
package com.dream.messaging.server.mina;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Frank
 *
 */
public class MinaShortConnServerTest {
	
	MinaShortConnServer minaShortConnServer = new MinaShortConnServer();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.dream.messaging.server.mina.MinaShortConnServer#start()}.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@Test
	public final void testStart() throws IOException, InterruptedException {
		minaShortConnServer.start();
		Thread.sleep(100000000);
	}

}
