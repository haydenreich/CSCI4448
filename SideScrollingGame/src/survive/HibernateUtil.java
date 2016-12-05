package survive;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory = null;
	public static SessionFactory getSessionFactory() {
		try {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metadata = new MetadataSources(standardRegistry)
					.getMetadataBuilder()
					.build();
			
			return metadata.getSessionFactoryBuilder().build();
		}catch(Exception e){}
		return null;
	}
}