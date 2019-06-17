package org.dppc.vv.common.model;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 分页查询实体
 * @param <T>
 */
@SuppressWarnings("unused")
public class QueryDTO<T> {
    private Integer page;
    private Integer limit;
    private String orderBy;
    private String orderSort;
    private T query;

    public QueryDTO() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(String orderSort) {
        this.orderSort = orderSort;
    }

    public T getQuery() {
        return query;
    }

    public void setQuery(T query) {
        this.query = query;
    }

    public Pageable gainPageable() {
        Pageable pageable = null;
        if (this.getOrderSort() != null) {
            if (this.getOrderSort().toLowerCase().equals("asc")) {
                pageable = new PageRequest(this.getPage() - 1, (int) this.getLimit(), Sort.Direction.ASC, this.getOrderBy());
            } else {
                pageable = new PageRequest(this.getPage() - 1, (int) this.getLimit(), Sort.Direction.DESC, this.getOrderBy());
            }
        } else {
            pageable = new PageRequest(this.getPage() - 1, (int) this.getLimit());
        }
        return pageable;
    }

    public Pageable gainPageable(T t) {
        Pageable pageable = gainPageable();
        this.query = t;
        return pageable;
    }
}
