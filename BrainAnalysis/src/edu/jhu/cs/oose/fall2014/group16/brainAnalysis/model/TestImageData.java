/**
 * 
 */
package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.sql.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author Guannan
 *
 */
public class TestImageData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(ImageDataOOSE.class);
		config.configure("hibernate.cfg.xml");
		
		// only need to run once to create tables
		new SchemaExport(config).create(true,true);
		
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		{
			ImageDataOOSE imageData1 = new ImageDataOOSE("1",Date.valueOf("2014-09-03"),"MRI", "imagepath.img");
			//imageData1.setFileFormat("Format1");
			session.save(imageData1);
		}{
			ImageDataOOSE imageData1 = new ImageDataOOSE("2",Date.valueOf("2014-09-03"),"MRI", "imagepath2.img");
			//imageData1.setFileFormat("Format1");
			session.save(imageData1);
		}
		
		session.beginTransaction().commit();

	}

}
