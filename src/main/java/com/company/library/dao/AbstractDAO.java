package com.company.library.dao;

public class AbstractDAO<D extends BaseDAO> {
    protected final D dao;

    public AbstractDAO(D dao) {
        this.dao = dao;
    }
}
