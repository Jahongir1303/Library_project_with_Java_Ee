package com.company.library.dao;

import com.company.library.domains.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDAO extends GenericDAO<User, Long> {
    private static UserDAO userDAO;

    public static UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    public Optional<User> findByEmail(String email) {
        Session session = getSession();
        session.beginTransaction();
        Query<User> query = session.createQuery("select t from User t where t.email=:email", User.class);
        User user = query.setParameter("email", email).getSingleResultOrNull();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(user);
    }
}
