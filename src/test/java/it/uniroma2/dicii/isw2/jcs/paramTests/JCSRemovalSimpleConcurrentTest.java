package it.uniroma2.dicii.isw2.jcs.paramTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 * Verify that basic removal functionality works.
 */
public class JCSRemovalSimpleConcurrentTest
{
	private static int count = 0;
	private static JCS jcs;
    /**
     * Constructor for the TestDiskCache object.
     *
     * @param testName
     */
	
	/*
    public JCSRemovalSimpleConcurrentTest( String testName )
    {
        super( testName );
    }
    */

    /**
     * Test setup
     */
    /*
	public void setUp()
        throws Exception
    {
        JCS.setConfigFilename( "/TestRemoval.ccf" );
        JCS.getInstance( "testCache1" );
    }
	*/
    /**
     * Main method passes this test to the text test runner.
     *
     * @param args
     */
   
    /*
    public static void main( String args[] )
    {
        String[] testCaseName = { JCSRemovalSimpleConcurrentTest.class.getName() };
        junit.textui.TestRunner.main( testCaseName );
    }

    */
	
	@BeforeClass
    public static void configure() throws CacheException {
   	 
		JCS.setConfigFilename( "/TestRemoval.ccf" );
        JCS.getInstance( "testCache1" );
        jcs = JCS.getInstance( "testCache1" );

        count = 500;
        
	}
    
    /**
     * Verify that 2 level deep hierchical removal works.
     *
     * @throws Exception
     */
	
	@Test
    public void testTwoDeepRemoval()
        throws Exception
    {

        System.out.println( "------------------------------------------" );
        System.out.println( "testTwoDeepRemoval" );
        
        for ( int i = 0; i <= count; i++ )
        {
            jcs.put( "key:" + i + ":anotherpart", "data" + i );
        }

        for ( int i = count; i >= 0; i-- )
        {
            String res = (String) jcs.get( "key:" + i + ":anotherpart" );
            if ( res == null )
            {
                assertNotNull( "[key:" + i + ":anotherpart] should not be null, " + jcs.getStats(), res );
            }
        }
        System.out.println( "Confirmed that " + count + " items could be found" );

        for ( int i = 0; i <= count; i++ )
        {
            jcs.remove( "key:" + i + ":" );
            assertNull( jcs.getStats(), jcs.get( "key:" + i + ":anotherpart" ) );
        }
        System.out.println( "Confirmed that " + count + " items were removed" );

        System.out.println( jcs.getStats() );

    }

    /**
     * Verify that 1 level deep hierchical removal works.
     *
     * @throws Exception
     */
	
	@Test
    public void testSingleDepthRemoval()
        throws Exception
    {

        System.out.println( "------------------------------------------" );
        System.out.println( "testSingleDepthRemoval" );

        for ( int i = 0; i <= count; i++ )
        {
            jcs.put( i + ":key", "data" + i );
        }

        for ( int i = count; i >= 0; i-- )
        {
            String res = (String) jcs.get( i + ":key" );
            if ( res == null )
            {
                assertNotNull( "[" + i + ":key] should not be null", res );
            }
        }
        System.out.println( "Confirmed that " + count + " items could be found" );

        for ( int i = 0; i <= count; i++ )
        {
            jcs.remove( i + ":" );
            assertNull( jcs.get( i + ":key" ) );
        }
        System.out.println( "Confirmed that " + count + " items were removed" );

        System.out.println( jcs.getStats() );

    }

    /**
     * Verify that clear removes everyting as it should.
     *
     * @throws Exception
     */
	
	@Test
    public void testClear()
        throws Exception
    {

        System.out.println( "------------------------------------------" );
        System.out.println( "testRemoveAll" );

        for ( int i = 0; i <= count; i++ )
        {
            jcs.put( i + ":key", "data" + i );
        }

        for ( int i = count; i >= 0; i-- )
        {
            String res = (String) jcs.get( i + ":key" );
            if ( res == null )
            {
                assertNotNull( "[" + i + ":key] should not be null", res );
            }
        }
        System.out.println( "Confirmed that " + count + " items could be found" );

        System.out.println( jcs.getStats() );

        jcs.clear();

        for ( int i = count; i >= 0; i-- )
        {
            String res = (String) jcs.get( i + ":key" );
            if ( res != null )
            {
                assertNull( "[" + i + ":key] should be null after remvoeall" + jcs.getStats(), res );
            }
        }
        System.out.println( "Confirmed that all items were removed" );

    }

    /**
     * Verify that we can clear repeatedly without error.
     *
     * @throws Exception
     */
	
	@Test
    public void testClearRepeatedlyWithoutError()
        throws Exception
    {

        System.out.println( "------------------------------------------" );
        System.out.println( "testRemoveAll" );

        jcs.clear();

        for ( int i = 0; i <= count; i++ )
        {
            jcs.put( i + ":key", "data" + i );
        }

        for ( int i = count; i >= 0; i-- )
        {
            String res = (String) jcs.get( i + ":key" );
            if ( res == null )
            {
                assertNotNull( "[" + i + ":key] should not be null", res );
            }
        }
        System.out.println( "Confirmed that " + count + " items could be found" );

        System.out.println( jcs.getStats() );

        for ( int i = count; i >= 0; i-- )
        {
            jcs.put( i + ":key", "data" + i );
            jcs.clear();
            String res = (String) jcs.get( i + ":key" );
            if ( res != null )
            {
                assertNull( "[" + i + ":key] should be null after remvoeall" + jcs.getStats(), res );
            }
        }
        System.out.println( "Confirmed that all items were removed" );

    }
}


