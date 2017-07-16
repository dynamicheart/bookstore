package com.dynamicheart.bookstore.core.repositories.order;

import com.dynamicheart.bookstore.core.model.statistics.Criteria;
import com.dynamicheart.bookstore.core.model.statistics.Stastistics;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface OrderRepositoryCustom {

    Stastistics getStastisticsByCriteria(Criteria criteria);
}
