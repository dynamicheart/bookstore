package com.dynamicheart.bookstore.store.store.model.catalog.book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dynamicheart on 7/8/2017.
 */
public class ReadableBookList implements Serializable {


    private static final long serialVersionUID = 9186973018534284271L;

    private int totalCount;

    private List<ReadableBook> books = new ArrayList<ReadableBook>();

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ReadableBook> getBooks() {
        return books;
    }

    public void setBooks(List<ReadableBook> books) {
        this.books = books;
    }
}
