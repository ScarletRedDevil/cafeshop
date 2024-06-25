package com.sds.cafeshop.model.order;

import java.util.List;

import com.sds.cafeshop.domain.OrderSummary;

public interface OrderSummaryService {
	public List<OrderSummary> selectByOrderStatIdx(int stat);

}
