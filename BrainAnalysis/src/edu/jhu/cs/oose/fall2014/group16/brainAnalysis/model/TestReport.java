package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestReport {

	public static void main(String[] args) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(Report.class);
		config.configure("hibernate.cfg.xml");
		
		// only need to run once to create tables
		new SchemaExport(config).create(true,true);
		
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		{
			Report r1 = new Report();
			r1.setImportedOn(Date.valueOf("1991-09-03"));
//			List<ProcessType> pts1 = new ArrayList<ProcessType>();
			ProcessType pt1 = new ProcessType("Denoise", "denoise.class");
			ProcessType pt2 = new ProcessType("GaussianBlur", "blur.class");
			String processedImagePath = "image1.img";
			r1.setIndependentProcessType("Denoise");
			r1.setIndependentProcessedImagePath(processedImagePath);
			r1.setSubjectId(1);
			session.save(r1);
		}{
			Report r2 = new Report();
			r2.setImportedOn(Date.valueOf("1991-09-04"));
//			List<ProcessType> pts2 = new ArrayList<ProcessType>();
			ProcessType pt1 = new ProcessType("Blend", "blend.class");
			ProcessType pt2 = new ProcessType("SkullStrip", "skullstrip.class");
			String processedImagePath = "image2.img";
			r2.setIndependentProcessType("Blend");
			r2.setIndependentProcessedImagePath(processedImagePath);
			r2.setSubjectId(2);
			session.save(r2);

		}
		
		session.beginTransaction().commit();

	}

}


