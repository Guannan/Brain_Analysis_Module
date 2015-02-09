package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class DatabaseManager {

	private static final DatabaseManager MANAGER = new DatabaseManager();
	private static SessionFactory factory;
	private static AnnotationConfiguration config;

	/**
	 * Method for initializing database. Creates a single user in the user table
	 * for the database manager.
	 * 
	 * @param
	 * @param managerPassword
	 */
	public void initializeDatabase(String managerName, String managerPassword) {
		config = new AnnotationConfiguration().configure();
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(ProcessType.class);
		config.addAnnotatedClass(ProcessedImage.class);
		config.addAnnotatedClass(ImageDataOOSE.class);
		config.addAnnotatedClass(Subject.class);
//		config.addAnnotatedClass(XMLImageData.class);
		config.addAnnotatedClass(Report.class);
		config.addAnnotatedClass(TestResults.class);
		config.configure("hibernate.cfg.xml");
		factory = config.buildSessionFactory();

//		new SchemaExport(config).create(true, true);

		Session session = null;
		Transaction transaction = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			User mgr = new User(managerName, managerPassword);
			session.save(mgr);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * Get the user based on the password. This is to validate database
	 * passwords
	 * 
	 * @param password
	 *            The user's password
	 * @return The first user with password password
	 */
	public User getUserByPassword(String password) {
		Session session = null;
		Transaction transaction = null;
		List<User> user = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			user = (List<User>) session.createCriteria(User.class)
					.add(Restrictions.eq("password", password))
					.list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		if (user.size() < 1) {
			return null;
		}
		return user.get(0);
	}

	/**
	 * Gets subjects based on multiple attributes. Both parameters are assumed
	 * to be in the the same order
	 * 
	 * @param attribute
	 *            The name of the attribute
	 * @param value
	 *            The value of the attribute
	 * @return Subjects that have the attributes
	 */
	public List<Subject> querySubjects(Map<String, String> equal,
			Map<String, List<Date>> between) {

		
		Transaction transaction = null;
		Session session = null;
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			Criteria query = session.createCriteria(Subject.class);
			for (Map.Entry<String, String> entry : equal.entrySet()) {
				if(entry.getKey() == "ID") {
					query.add(Restrictions.eq(entry.getKey(), Integer.valueOf(entry.getValue())));
				}
				else {
					query.add(Restrictions.eq(entry.getKey(), entry.getValue()));
				}
			}
			if (between != null) {
				for (Entry<String, List<Date>> entry : between.entrySet()) {
					List<Date> nums = entry.getValue();
					query.add(Restrictions.ge(entry.getKey(), nums.get(0)));
					query.add(Restrictions.le(entry.getKey(), nums.get(1)));
				}
			}
			subjects = (List<Subject>) query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("ERROR" + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
				return null;
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return subjects;
	}

	/**
	 * Get image data from a subject's id
	 * 
	 * @param subjectID
	 *            The subject's id
	 * @return The image data associated with the subject
	 */
	public List<ImageDataOOSE> getImageDataFromSubjectID(String subjectID) {
		Transaction transaction = null;
		Session session = null;
		List<ImageDataOOSE> data = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			data = (List<ImageDataOOSE>) session.createCriteria(ImageDataOOSE.class)
					.add(Restrictions.eq("subjectID", subjectID)).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				return null;
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return data;
	}

	/**
	 * Get raw images from their id
	 * 
	 * @param rawID
	 *            The raw image id
	 * @return the raw images for the id
	 */
	public List<RawImage> getRawImages(int rawID) {
		Session session = null;
		Transaction transaction = null;
		List<RawImage> rawImages = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			rawImages = (List<RawImage>) session.createCriteria(RawImage.class)
					.add(Restrictions.eq("rawImageID", rawID)).list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return rawImages;
	}
	
	/**
	 * Get processed images based on the rawID of the image they were created
	 * from.
	 * 
	 * @param rawID
	 *            The rawID of the image the processed image was created from
	 * @return The images made from that raw image
	 */
	public List<ProcessedImage> getProcessedImages(int rawID) {
		// /TODO test this
		Session session = null;
		Transaction transaction = null;
		List<ProcessedImage> processedImages = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			processedImages = (List<ProcessedImage>) session
					.createCriteria(ProcessedImage.class)
					.add(Restrictions.eq("rawID", rawID)).list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return processedImages;
	}

	/**
	 * Get test results based on the rawID of the image they associated with
	 * 
	 * @param rawID
	 *            The rawID of the image they associated with
	 * @return Test results that are associated with that raw image
	 */
	public List<TestResults> getTestResults(int rawID) {
		// /TODO test this
		Session session = null;
		Transaction transaction = null;
		List<TestResults> testResult = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			testResult = (List<TestResults>) session
					.createCriteria(TestResults.class)
					.add(Restrictions.eq("rawID", rawID)).list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return testResult;
	}

	/**
	 * Getter methods for retrieving all user IDs
	 * 
	 * @return Integer list of all user IDs in the database
	 */
	public List<Integer> getUserID() {
		// new SchemaExport(config);
		Session session = null;
		Transaction transaction = null;
		// List<User> users = null;
		List<Integer> userIDs = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			// users = (List<User>) session.createCriteria(User.class).list();
			userIDs = (List<Integer>) session.createQuery(
					"select userID from User").list();
			// for (User u : users) {
			// userIDs.add(Integer.valueOf(u.getUserID()));
			// }
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return userIDs;
	}
	
	/**
	 * Getter method for retreiving a user password when given ID
	 * 
	 * @param userID
	 * @return String - user's password
	 */
	public String getPassword(int userID) {
		// new SchemaExport(config);
		Session session = null;
		Transaction transaction = null;
		User user = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			user = (User) session.createCriteria(User.class)
					.add(Restrictions.eq("userID", Integer.valueOf(userID)))
					.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return user.getPassword();
	}

	/**
	 * Getter method for retrieving all process types
	 * 
	 * @return ProcessType list
	 */
	public List<ProcessType> getProcessType() {
		Session session = null;
		Transaction transaction = null;
		List<ProcessType> pts = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			pts = (List<ProcessType>) session.createQuery(
					"select p from ProcessType p").list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return pts;
	}

	/**
	 * Getter method for retrieving a process type's filepath
	 * 
	 * @param processTypeName
	 * @return String - path for the process type
	 */
	public String getProcessTypePath(String processTypeName) {
		Session session = null;
		Transaction transaction = null;
		ProcessType pt = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			pt = (ProcessType) session.createCriteria(ProcessType.class)
					.add(Restrictions.eq("name", processTypeName))
					.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return pt.getPath();
	}

	/**
	 * Getter method for all image data
	 * 
	 * @return ImageData list
	 */
	public List<ImageDataOOSE> getImageData() {
		Session session = null;
		Transaction transaction = null;
		List<ImageDataOOSE> imageData = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			imageData = (List<ImageDataOOSE>) session.createQuery(
					"select i from ImageDataOOSE i").list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return imageData;
	}
	
	/**
	 * Getter method for all image data
	 * 
	 * @return ImageData list
	 */
	public List<ImageDataOOSE> getImageDataByType(String type, String value) {
		Session session = null;
		Transaction transaction = null;
		List<ImageDataOOSE> imageData = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			imageData = (List<ImageDataOOSE>) session.createCriteria(ImageDataOOSE.class)
					.add(Restrictions.eq(type, value))
					.list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return imageData;
	}

	/**
	 * Getter method for retrieving all raw images
	 * 
	 * @return RawImage list
	 */
	public List<RawImage> getRawImage() {
		Session session = null;
		Transaction transaction = null;
		List<RawImage> rawImage = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			rawImage = (List<RawImage>) session.createQuery(
					"select r from RawImage r").list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return rawImage;
	}

	/**
	 * Getter method for retriving test subject for xml iamge data
	 * 
	 * @param takenOn
	 *            - date image taken on
	 * @return - Subject
	 */
	public Subject getSubject(Date takenOn) {
		Session session = null;
		Transaction transaction = null;
		XMLImageData xmlImageData = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			xmlImageData = (XMLImageData) session
					.createCriteria(XMLImageData.class)
					.add(Restrictions.eq("takenOn", takenOn)).uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return xmlImageData.getSubject();
	}

	/**
	 * Getter method for retrieving all ids for reports in database
	 * @return - List of report id integers
	 */
	public List<Integer> getReportID() {
		Session session = null;
		Transaction transaction = null;
		List<Integer> reportIds = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			reportIds = (List<Integer>) session.createQuery(
					"select reportId from Report").list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return reportIds;
	}
	
	/**
	 * Getter method for the date of import for a report
	 * @param reportId - integer of the report ID
	 * @return - Date indicating the date of import for report
	 */
	public Date getImportedOn(int reportId) {
		Session session = null;
		Transaction transaction = null;
		Report report = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			report = (Report) session.createCriteria(Report.class)
					.add(Restrictions.eq("reportId", Integer.valueOf(reportId)))
					.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return report.getImportedOn();
	}
	
	
	/**
	 * Getter method for the processed image path for a report
	 * @param reportId - integer ID for report
	 * @return - String of filepath for processed image
	 */
	public String getIndependentProcessedImagePath(int reportId) {
		Session session = null;
		Transaction transaction = null;
		Report report = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			report = (Report) session.createCriteria(Report.class)
					.add(Restrictions.eq("reportId", Integer.valueOf(reportId)))
					.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return report.getIndependentProcessedImagePath();
	}
	
	/**
	 * Getter method for process type of a report
	 * @param reportId - integer ID for report
	 * @return - String of the process type name
	 */
	public String getIndependentProcessType(int reportId) {
		Session session = null;
		Transaction transaction = null;
		Report report = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			report = (Report) session.createCriteria(Report.class)
					.add(Restrictions.eq("reportId", Integer.valueOf(reportId)))
					.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return report.getIndependentProcessType();
	}
	
	
	/**
	 * Getter method for the subject ID from a report
	 * @param reportId - integer ID for the report
	 * @return - Integer for the subjectID of the report
	 */
	public Integer getReportSubjectID(int reportId) {
		Session session = null;
		Transaction transaction = null;
		Report report = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			report = (Report) session.createCriteria(Report.class)
					.add(Restrictions.eq("reportId", Integer.valueOf(reportId)))
					.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return report.getSubjectId();
	}
	
	/**
	 * Performs a database insertion
	 * 
	 * @param obj
	 *            - any valid object that belongs to a table in database
	 */
	public boolean databaseInsert(Object obj) {
		// new SchemaExport(config);
		config.addAnnotatedClass(Subject.class);
		config.configure("hibernate.cfg.xml");
		Session session = null;
		Transaction transaction = null;
		boolean insertSuccess = true;

		try {
			session = factory.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.save(obj);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			insertSuccess = false;
		} finally {
			if (session != null) {

				session.close();
			}
		}
		return insertSuccess;
	}

	/**
	 * Performs a database delete
	 * 
	 * @param obj
	 *            - any valid element in a database table
	 */
	public void databaseDelete(Object obj) {
		// new SchemaExport(config);
		Session session = null;
		Transaction transaction = null;

		try {
			session = factory.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.delete(obj);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * Performs a database update
	 * 
	 * @param oldObj
	 * @param newObj
	 */
	public void databaseUpdate(Object oldObj, Object newObj) {
		databaseDelete(oldObj);
		databaseInsert(newObj);
	}

	/**
	 * Drops a existing database table
	 * 
	 * @param tableName
	 */
	public void dropTable(String tableName) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = factory.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.createSQLQuery("DROP TABLE " + tableName).executeUpdate();
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * Getter method for our singleton database manager
	 * 
	 * @return DatabaseManager instance
	 */
	public static DatabaseManager getDatabaseManager() {
		return MANAGER;
	}

}
