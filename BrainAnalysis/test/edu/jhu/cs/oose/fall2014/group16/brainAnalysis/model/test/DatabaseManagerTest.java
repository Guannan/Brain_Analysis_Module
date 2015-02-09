package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.test;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.DatabaseManager;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ImageDataOOSE;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessType;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.RawImage;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.Report;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.Subject;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.User;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.XMLImageData;

/**
 * Tests the actions that the database management requires.  This includes creating 
 * and querying database entries, as well as error handling.
 */
public class DatabaseManagerTest {

	private static DatabaseManager manager = DatabaseManager.getDatabaseManager();
	
	/**
	 * Setup the environment for testing. Create our database manager.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		manager.initializeDatabase("OOSE", "Group16");
	}
	
	/**
	 * User class tests for database storage and retrieval
	 */
	@Test
	public void userTest() {
		User user2 = new User("Amy", "abc");
		manager.databaseInsert(user2);
//		List<Integer> ids = manager.getUserID();
//		for (Integer id : ids) {
//			System.out.println(id);
//		}
		assertNotNull(manager.getUserID());
		assertEquals("Amy's passoword is abc", "abc", manager.getPassword(user2.getUserID()));   // manager takes id=1
		assertEquals("Amy", manager.getUserByPassword("abc").getUsername());
		
		User user3 = new User("Barney", "def");
		manager.databaseInsert(user3);
		assertNotNull(manager.getUserID());
		assertEquals("Barney's passworkd is def", "def", manager.getPassword(user3.getUserID()));
		assertEquals("Amy", manager.getUserByPassword("abc").getUsername());
		assertFalse(user3.equals(manager.getUserByPassword("abc")));
		assertEquals("Barney", manager.getUserByPassword("def").getUsername());
		
		User user4 = new User("Carl", "ghi");
		manager.databaseInsert(user4);
		assertNotNull(manager.getUserID());
		assertEquals("Carl's password is ghi", "ghi", manager.getPassword(user4.getUserID()));
		//assertEquals("Size of table after multiple insertion", 4, manager.getUserID().size());
		assertEquals("Carl", manager.getUserByPassword("ghi").getUsername());
		
		assertNull(manager.getUserByPassword("missingPassword"));
		
		manager.databaseDelete(user3);
//		assertEquals("Size of table after deletion", 3, manager.getUserID().size());

		manager.databaseUpdate(user3, user4);
//		assertEquals("Size of table after update", 3, manager.getUserID().size());
//		assertEquals("User with id 4 is now id 3", "def", manager.getPassword(4));
		
	}
	
	/**
	 * ProcessType class tests for data storage and retrieval
	 */
	@Test
	public void processTypeTest() {
		ProcessType pt1 = new ProcessType("Denoise", "denoise.class");
		manager.databaseInsert(pt1);
		assertTrue(manager.getProcessType().size() > 0);
		assertEquals("denoise.class", manager.getProcessTypePath("Denoise"));
		
		ProcessType pt2 = new ProcessType("GaussianBlur", "blur.class");
		manager.databaseInsert(pt2);
		assertTrue(manager.getProcessType().size() > 1);
		assertEquals("blur.class", manager.getProcessTypePath("GaussianBlur"));
		
		ProcessType pt3 = new ProcessType("SkullStrip", "skullstrip.class");
		manager.databaseInsert(pt3);
		assertTrue(manager.getProcessType().size() > 2);
		assertEquals("skullstrip.class", manager.getProcessTypePath("SkullStrip"));
	}

	/**
	 * ProcessType class tests for data storage and retrieval
	 */
	@Test
	public void testResultsTest() {
		
	}

	/**
	 * ImageData class tests for data storage and retrieval
	 */
	@Test
	public void imageDataTest() {
		Subject subject1 = new Subject("Alex",Date.valueOf("1991-09-03"),"Male","SCA6");
		manager.databaseInsert(subject1);
		ImageDataOOSE imageData1 = new ImageDataOOSE(String.valueOf(subject1.getID()),Date.valueOf("2014-09-03"),"MRI", "image1.img");
		manager.databaseInsert(imageData1);
//		assertEquals(1,manager.getImageData().size());
		assertTrue(manager.getImageDataFromSubjectID("1").size() > 0);
//		assertEquals("Format1", manager.getImageDataFormat("testpic1.jpg"));
		
		ImageDataOOSE imageData2 = new ImageDataOOSE("2",Date.valueOf("2014-09-03"),"MRI", "imgage2.img");
		manager.databaseInsert(imageData2);
//		assertEquals(2,manager.getImageData().size());
		assertTrue(manager.getImageDataFromSubjectID("2").size() > 1);
		assertFalse(imageData2.equals(manager.getImageDataFromSubjectID("1")));
//		assertEquals("Format1", manager.getImageDataFormat("testpic2.jpg"));

	}
		
	@Test
	public void subjectTest() {
		
		Subject subject1 = new Subject("Alex",Date.valueOf("1991-09-03"),"Male","SCA6");
		manager.databaseInsert(subject1);
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("diagnosis", "SCA6");
		assertTrue(manager.querySubjects(params, null).size() > 0);
		
		params.put("ID", String.valueOf(subject1.getID()));
		assertEquals(subject1, manager.querySubjects(params, null).get(0));
		
		Map<String, Integer> age = new HashMap<String, Integer>();
		age.put("minAge", 2);
		age.put("maxAge", 10);
		
		
		
	}
	
	@Test
	public void reportTest() {
		
		Report r1 = new Report();
		r1.setImportedOn(Date.valueOf("1991-09-03"));
//		ProcessType pt1 = new ProcessType("Denoise", "denoise.class");
//		ProcessType pt2 = new ProcessType("GaussianBlur", "blur.class");
		String processedImagePath = "image1.img";
		r1.setIndependentProcessType("Denoise");
		r1.setIndependentProcessedImagePath(processedImagePath);
		r1.setSubjectId(1);
		manager.databaseInsert(r1);
		assertTrue(manager.getReportID().size() > 0);
		assertEquals(Date.valueOf("1991-09-03"), manager.getImportedOn(manager.getReportID().get(0)));
		assertEquals("Denoise", manager.getIndependentProcessType(manager.getReportID().get(0)));
		assertEquals(processedImagePath, manager.getIndependentProcessedImagePath(manager.getReportID().get(0)));
		assertEquals(new Integer(1), manager.getReportSubjectID(manager.getReportID().get(0)));
		
		Report r2 = new Report();
		r2.setImportedOn(Date.valueOf("1991-09-04"));
//		pt1 = new ProcessType("Blend", "blend.class");
//		pt2 = new ProcessType("SkullStrip", "skullstrip.class");
		processedImagePath = "image2.img";
		r2.setIndependentProcessType("Blend");
		r2.setIndependentProcessedImagePath(processedImagePath);
		r2.setSubjectId(2);
		manager.databaseInsert(r2);
		assertTrue(manager.getReportID().size() > 1);
		assertEquals(Date.valueOf("1991-09-04"), manager.getImportedOn(manager.getReportID().get(1)));
		assertEquals("Blend", manager.getIndependentProcessType(manager.getReportID().get(1)));
		assertEquals(processedImagePath, manager.getIndependentProcessedImagePath(manager.getReportID().get(1)));
		assertEquals(new Integer(2), manager.getReportSubjectID(manager.getReportID().get(1)));
	}
	
	/**
	 * XMLImageData class tests for data storage and retrieval
	 */
	@Test
	public void xmlImageDataTest() {
		
//		XMLImageData imageData1 = new XMLImageData();
//		imageData1.setSubject(new Subject("Alex","123",Date.valueOf("1991-09-03")));
//		imageData1.setTakenOn(Date.valueOf("2014-09-02"));
//		imageData1.setImageType("JPEG");
//		manager.databaseInsert(imageData1);
//		assertEquals(1,manager.getXMLImageData().size());
//		assertEquals("JPEG", manager.getImageType(Date.valueOf("2014-09-02")));
//		assertEquals("123", manager.getSubject(Date.valueOf("2014-09-02")).getID());
//		assertEquals("Alex", manager.getSubject(Date.valueOf("2014-09-02")).getName());
//		assertEquals(Date.valueOf("1991-09-03"), manager.getSubject(Date.valueOf("2014-09-02")).getDateOfBirth());
//		
//		XMLImageData imageData2 = new XMLImageData();
//		imageData2.setSubject(new Subject("Bob","234",Date.valueOf("1991-09-02")));			
//		imageData2.setTakenOn(Date.valueOf("2014-09-03"));
//		imageData2.setImageType("PNG");
//		manager.databaseInsert(imageData2);
//		assertEquals(2,manager.getXMLImageData().size());
//		assertEquals("PNG", manager.getImageType(Date.valueOf("2014-09-03")));
//		assertEquals("234", manager.getSubject(Date.valueOf("2014-09-03")).getID());
//		assertEquals("Bob", manager.getSubject(Date.valueOf("2014-09-03")).getName());
//		assertEquals(Date.valueOf("1991-09-02"), manager.getSubject(Date.valueOf("2014-09-03")).getDateOfBirth());
//		
//		XMLImageData imageData3 = new XMLImageData();
//		imageData3.setSubject(new Subject("Chad","345",Date.valueOf("1991-09-01")));
//		imageData3.setTakenOn(Date.valueOf("2014-09-04"));
//		imageData3.setImageType("TIF");
//		manager.databaseInsert(imageData3);
//		assertEquals(3,manager.getXMLImageData().size());
//		assertEquals("TIF", manager.getImageType(Date.valueOf("2014-09-04")));
//		assertEquals("345", manager.getSubject(Date.valueOf("2014-09-04")).getID());
//		assertEquals("Chad", manager.getSubject(Date.valueOf("2014-09-04")).getName());
//		assertEquals(Date.valueOf("1991-09-01"), manager.getSubject(Date.valueOf("2014-09-04")).getDateOfBirth());

	}
	
}
