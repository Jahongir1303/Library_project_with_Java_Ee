package com.company.library.service.page;

public interface Pageable {

    Boolean hasNext(String search, Integer offset, Integer limit);

    Boolean hasPrevious(Integer offset);

    Integer totalPage(String search, Integer limit);
}
