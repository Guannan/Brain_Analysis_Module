package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.io.File;
import java.sql.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestSubject {
	
	public static void main(String[] args) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(Subject.class);
		config.configure("hibernate.cfg.xml");
		
		// only need to run once to create tables
		//new SchemaExport(config).create(true,true);
		
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		{
			Subject sub1 = new Subject("Alex",Date.valueOf("1991-09-03"),"Male","SCA6");
			session.save(sub1);
		}{
			Subject sub2 = new Subject("Bob",Date.valueOf("1991-09-03"),"Male","SCA2");
			session.save(sub2);
		}{
			Subject sub3 = new Subject("Cathy",Date.valueOf("1991-09-03"),"Female","SCA6");
			session.save(sub3);
		}
		
		session.beginTransaction().commit();
	}

}
