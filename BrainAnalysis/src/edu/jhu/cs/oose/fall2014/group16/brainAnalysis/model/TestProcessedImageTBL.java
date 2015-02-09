package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author Haichong "Kai" Zhang
 *
 */

public class TestProcessedImageTBL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(ProcessedImage.class);
		config.configure("hibernate.cfg.xml");
		
		// only need to run once to create tables
		new SchemaExport(config).create(true,true);
		
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		{
			ProcessedImage processedImage1 = new ProcessedImage("rawimage1.img",Integer.valueOf("1"), "Denoise");
			//imageData1.setFileFormat("Format1");
			session.save(processedImage1);
		}
		
		session.beginTransaction().commit();
	}
}
