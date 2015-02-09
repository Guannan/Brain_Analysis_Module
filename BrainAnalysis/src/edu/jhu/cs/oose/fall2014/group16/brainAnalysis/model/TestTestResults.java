package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestTestResults {

	public static void main(String[] args) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(TestResults.class);
		config.configure("hibernate.cfg.xml");
		
		// only need to run once to create tables
		new SchemaExport(config).create(true,true);
		
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
//		System.out.println("Working Directory = " +
//	              System.getProperty("user.dir"));
//		
		session.beginTransaction();
		
		{
			TestResults tr1 = new TestResults();
//			tr1.setTakenOn(Date.valueOf("1991-09-03"));
			List<TestResults> trs = ParseText.readPatientResult("test_data.txt");
			tr1.setTestName(trs.get(0).getTestName());
			tr1.setTestResult(trs.get(0).getTestResult());
			tr1.setSubjectID(trs.get(0).getSubjectID());
			tr1.setTakenOn(trs.get(0).getTakenOn());
			session.save(tr1);
			
		}{
			
			TestResults tr2 = new TestResults();
			tr2.setTakenOn(Date.valueOf("1991-09-04"));
			tr2.setTestName(GenerateTestResult.getTestName(55));
			tr2.setTestResult(GenerateTestResult.getResult(55));
			tr2.setSubjectID("temp2");
			session.save(tr2);
			
		}
		
		session.beginTransaction().commit();
	}
}
