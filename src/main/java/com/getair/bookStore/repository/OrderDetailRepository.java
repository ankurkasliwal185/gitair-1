package com.getair.bookStore.repository;

import com.getair.bookStore.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    List<OrderDetail> findByCustomerId(Long customerId);

//    @Aggregation(pipeline = {
////            "{ $match: { 'customerId' : ?0 } } ," +
//            "{ $group: {"
//                    + " _id: { month: { $month: $createdDate } }, "
//                    + " totalOrder: {$sum: 1},"
//                    + " customerId: {$first: '$customerId'},"
//                    + " month: {$first: { $month: $createdDate }},"
//                    + " totalPurchaseBookCount: {$sum: { $size:'$books' }},"
//                    + " totalAmount:{ $sum: {$toDecimal:'$amount'} }"
//                    + "}}," +
//                    "{ $project: {"
//                    + " month: '$month',"
//                    + " _id: 0,"
//                    + " customerId: '$customerId',"
//                    + " totalOrder: '$totalOrder',"
//                    + " totalPurchaseBookCount: '$totalPurchaseBookCount',"
//                    + " totalAmount: '$totalAmount'"
//                    + "}}," + "{ $sort : { 'createdDate' : 1}} "
//
//    })
//    AggregationResults<StatisticDomain> groupMonthlyByCustomerId(String customerId);
}
