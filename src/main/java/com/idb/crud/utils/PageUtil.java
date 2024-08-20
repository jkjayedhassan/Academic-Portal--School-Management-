package com.idb.crud.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

public class PageUtil {
    public static Pageable getPageable(Integer pageNumber, Integer pageSize, String sortColumn, String sortOrder) {
        pageNumber= pageNumber == null ? 0 : pageNumber;
        pageSize = pageSize == null ? 10 : pageSize;
        sortColumn = sortColumn == null ? "id" : sortColumn;
        sortOrder = sortOrder == null ? "ASC" : sortOrder.toUpperCase();
        return PageRequest.of(pageNumber, pageSize, Direction.valueOf(sortOrder), sortColumn);
    }
}
