package com.company.library.dao;

import com.company.library.domains.Uploads;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UploadDAO extends GenericDAO<Uploads, Long> {
    private static UploadDAO dao;

    public static UploadDAO getInstance() {
        if (dao == null) {
            dao = new UploadDAO();
        }
        return dao;
    }

    public Optional<Uploads> getByPath(String path) {
        Session session = getSession();
        session.beginTransaction();

        Query<Uploads> uploadsQuery = session.createQuery("select t from Uploads t where t.path =: path", Uploads.class);
        uploadsQuery.setParameter("path", path);
        Uploads singleResultOrNull = uploadsQuery.getSingleResultOrNull();
        session.close();
        session.getTransaction().commit();
        return Optional.ofNullable(singleResultOrNull);
    }
}
