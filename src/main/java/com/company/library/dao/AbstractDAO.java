package com.company.library.dao;

public abstract class AbstractDAO<D extends BaseDAO> {
    protected final D dao;

    public AbstractDAO(D dao) {
        this.dao = dao;
    }

    public abstract Boolean hasNext(String search, Integer offset, Integer limit);

    public abstract Boolean hasPrevious(Integer offset);

    public abstract Integer totalPage(String search, Integer limit);
}
