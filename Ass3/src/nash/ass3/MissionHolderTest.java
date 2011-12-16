/**
 * 
 */
package nash.ass3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Nadavi, shlomi
 *
 */
public class MissionHolderTest {

	
	private MissionHolder mis;

	/**
	 * @throws java.lang.Exception
	 */
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mis = MissionHolderImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("OH HI");
		mis.clear();
	}	
	

	/**
	 * Test method for {@link nash.ass3.MissionHolderImpl#isEmpty()}.
	 * soppuse to return true if the mission holder is empty.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(mis.isEmpty());
	}

	/**
	 * Test method for {@link nash.ass3.MissionHolderImpl#insert(Mission)}.
	 * after the insertion the mission holder will not be empty.
	 */
	@Test
	public void testInsert() {
		Mission m = new MissionImpl();
		mis.insert(m);
		assertFalse(mis.isEmpty());
	}
	
	

	/**
	 * checks if the mission holder is empty after fetching the last mission.
	 */
	@Test
	public void testFetchIfEmptyAfterFetch() {
		mis.insert(new MissionImpl());
		Mission mTest = mis.fetch(new SergeantImpl());
		assertTrue(mis.isEmpty());
		
	}

	/**
	 * Test method for {@link nash.ass3.MissionHolderImpl#getMission(Sergeant)}.
	 */
	@Test
	public void testGetMission() {
		Mission mTest = mis.getMission(new SergeantImpl());
		assertNull(mTest);
	}
	
	/**
	 * returns the size of the mission holder
	 */
	@Test
	public void testSizeOfMissionHolder() {
		Mission m1 = new MissionImpl();
		Mission m2 = new MissionImpl();
		mis.insert(m1);
		mis.insert(m2);
		assertEquals(mis.getSize(),2);
	}
	
	/*
	 * soppuse to return true if the mission holder is not empty after insertion.
	 */
	
	@Test
	public void testIfNullAfterInsert() {
		Mission mTest = new MissionImpl();
		mis.insert(mTest);
		mTest = mis.getMission(new SergeantImpl());
		assertNotNull(mTest);
	}
	
	/*
	 * 
	 */
	public void testClear()
	{
		mis.insert(new MissionImpl());
		mis.clear();
		assertTrue(mis.isEmpty());
	}

}
