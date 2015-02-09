package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestUser {

	public static void main(String[] args) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(User.class);
		config.configure("hibernate.cfg.xml");
		
		// only need to run once to create tables
		new SchemaExport(config).create(true,true);
		
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		
		session.beginTransaction();
		
		{
			User user1 = new User("Amy", "abc");
			session.save(user1);
		}{
			User user2 = new User("Barney", "def");
			session.save(user2);
		}{
			User user3 = new User("Carl", "ghi");
			session.save(user3);
		}
		
		session.getTransaction().commit();
		
		session.beginTransaction();
		List<Integer> userIDs = (List<Integer>) session.createQuery("select userID from User").list();
		session.getTransaction().commit();

		session.beginTransaction();
		User user1 = (User) session.createCriteria(User.class).add(Restrictions.eq("userID", Integer.valueOf(1))).uniqueResult();
		session.getTransaction().commit();
		System.out.println(user1.getPassword());
		
		session.close();
		
		for (Integer id : userIDs) {
			System.out.println(id);
		}
	}
}
