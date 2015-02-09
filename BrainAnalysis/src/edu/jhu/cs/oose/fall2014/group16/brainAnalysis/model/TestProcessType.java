package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestProcessType {

	public static void main(String[] args) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(ProcessType.class);
		config.configure("hibernate.cfg.xml");
		
		// only need to run once to create tables
		new SchemaExport(config).create(true,true);
		
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		
		session.beginTransaction();
		
		{
			ProcessType pt1 = new ProcessType("Denoise", "denoise.class");
			session.save(pt1);
		}{
			ProcessType pt2 = new ProcessType("GaussianBlur", "blur.class");
			session.save(pt2);
		}{
			ProcessType pt3 = new ProcessType("SkullStrip", "skullstrip.class");
			session.save(pt3);
		}
		
		session.getTransaction().commit();
		
		session.beginTransaction();
		List<ProcessType> processTypeList = (List<ProcessType>) session.createQuery("select p from ProcessType p").list();
		session.getTransaction().commit();
		
		session.beginTransaction();
		ProcessType pt2 = (ProcessType) session.get(ProcessType.class, "Denoise");
		session.getTransaction().commit();
		
		session.beginTransaction();
		ProcessType pt3 = (ProcessType) session.createCriteria(ProcessType.class).add(Restrictions.eq("path", "denoise.class")).uniqueResult();
		session.getTransaction().commit();
		
		session.beginTransaction();
		List<ProcessType> processTypeList2 = (List<ProcessType>) session.createCriteria(ProcessType.class).list();
		session.getTransaction().commit();
		
		session.close();
		
		for (ProcessType pt : processTypeList) {
			System.out.println(pt.getName());
		}
		System.out.println(pt2.getPath());
		System.out.println(pt3.getPath());
		for (ProcessType pt : processTypeList2) {
			System.out.println(pt.getName());
		}
		
	}
}
