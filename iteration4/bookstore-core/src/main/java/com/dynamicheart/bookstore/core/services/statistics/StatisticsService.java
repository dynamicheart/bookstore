package com.dynamicheart.bookstore.core.services.statistics;

import com.dynamicheart.bookstore.core.model.statistics.Statistics;

import java.util.Date;
import java.util.List;

/**
 * Created by dynamicheart on 7/17/2017.
 */
public interface StatisticsService {

    Statistics getDateStat(Date startDate, Date endDate);

    Statistics getCustomerStat(Long customerId);

    Statistics getBookStat(String bookIsbn);
}
