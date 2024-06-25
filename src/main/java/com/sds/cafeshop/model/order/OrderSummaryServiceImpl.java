package com.sds.cafeshop.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.cafeshop.domain.OrderSummary;

@Service
public class OrderSummaryServiceImpl implements OrderSummaryService{

	@Autowired
	private OrderSummaryDAO orderSummaryDAO;
	
	public List<OrderSummary> selectByOrderStatIdx(int stat) {
		
		return orderSummaryDAO.selectByOrderStatIdx(stat);
	}

	
}
