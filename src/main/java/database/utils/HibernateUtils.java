package database.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {

    private static HibernateUtils oneInstance;
    private SessionFactory sessionFactory;

    private HibernateUtils () {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.xml")
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .getMetadataBuilder()
                .build();

        sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();
    }

    public static HibernateUtils oneInstance() {
        if (oneInstance ==null) {
            oneInstance = new HibernateUtils();
        }

        return oneInstance;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}


