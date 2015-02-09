package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestRawImage {

	public static void main(String[] args) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(RawImage.class);
		config.configure("hibernate.cfg.xml");
		
		// only need to run once to create tables
		new SchemaExport(config).create(true,true);
		
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		{
			RawImage ri1 = new RawImage("rawimage1.img");
			session.save(ri1);
		}{
			RawImage ri2 = new RawImage("rawimage2.img");
			session.save(ri2);
		}{
			RawImage ri3 = new RawImage("rawimage3.img");
			session.save(ri3);
		}
		
		session.beginTransaction().commit();
	}
}
