package com.dynamicheart.bookstore.core.services.statistics;

import com.dynamicheart.bookstore.core.model.statistics.Statistics;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dynamicheart on 7/17/2017.
 */
@Service
public class StatisticsServeImpl implements StatisticsService{

    @Inject
    @Qualifier("datasource")
    DataSource dataSource;

    @Override
    public Statistics getDateStat(Date startDate, Date endDate) {
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        try {
            Connection con = dataSource.getConnection();
            String sql = "{call " + "date_stat" + "(?, ?)}";
            CallableStatement stmt = con.prepareCall(sql);
            stmt.setDate(1, sqlStartDate);
            stmt.setDate(2, sqlEndDate);

            boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    Statistics statistics = new Statistics();
                    statistics.setQuantity(rs.getInt(1));
                    statistics.setTotal(rs.getBigDecimal(2));
                    return statistics;

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    @Override
    public Statistics getCustomerStat(Long customerId) {
        try {
            Connection con = dataSource.getConnection();
            String sql = "{call " + "customer_stat" + "(?)}";
            CallableStatement stmt = con.prepareCall(sql);
            stmt.setLong(1, customerId);

            boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    Statistics statistics = new Statistics();
                    statistics.setQuantity(rs.getInt(1));
                    statistics.setTotal(rs.getBigDecimal(2));
                    return statistics;

                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {

        }

        return null;
    }

    @Override
    public Statistics getBookStat(String bookIsbn) {
        try {
            Connection con = dataSource.getConnection();
            String sql = "{call " + "book_stat" + "(?)}";
            CallableStatement stmt = con.prepareCall(sql);
            stmt.setString(1, bookIsbn);

            boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    Statistics statistics = new Statistics();
                    statistics.setQuantity(rs.getInt(1));
                    statistics.setTotal(rs.getBigDecimal(2));
                    return statistics;

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }
}
