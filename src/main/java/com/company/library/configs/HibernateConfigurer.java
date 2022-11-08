package com.company.library.configs;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.reflections.Reflections;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateConfigurer {
    private static SessionFactory sessionFactory;


    private static SessionFactory setUp() {
        StandardServiceRegistry registry = null;

        if (sessionFactory == null) {
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
            Properties properties = new Properties();

            try {
                properties.load(new FileReader("/home/jahongir/IdeaProjects/Library_project_with_Java_Ee/src/main/resources/datasource.properties"));

                registryBuilder.applySettings(properties);
                registry = registryBuilder.build();

                MetadataSources metadataSources = new MetadataSources(registry);

                Reflections reflections = new Reflections("com.company.library.domains");

                reflections.getTypesAnnotatedWith(Entity.class).forEach(clazz -> metadataSources.addAnnotatedClassName(clazz.getName()));

                Metadata metadata = metadataSources.getMetadataBuilder().build();

                return metadata.getSessionFactoryBuilder().build();

            } catch (IOException e) {
                if (Objects.nonNull(registry)) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }

                throw new RuntimeException("Hibernate exception %s".formatted(e.getMessage()));
            }


        }

        return sessionFactory;
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            sessionFactory = setUp();
        }
        return sessionFactory;
    }


}
