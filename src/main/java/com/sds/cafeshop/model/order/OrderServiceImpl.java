package com.sds.cafeshop.model.order;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.cafeshop.domain.Cart;
import com.sds.cafeshop.domain.OrderDetail;
import com.sds.cafeshop.domain.OrderSummary;
import com.sds.cafeshop.domain.Ordernum;
import com.sds.cafeshop.exception.OrderException;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private PaymethodDAO paymethodDAO;
	
	//주문 DAO
	@Autowired
	private OrderSummaryDAO orderSummaryDAO;
	
	@Autowired
	private OrdernumDAO ordernumDAO;
	
	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	@Override
	public List selectPaymethod() {
		return paymethodDAO.selectAll();
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
    public void order(OrderSummary orderSummary, HttpSession session) throws OrderException {
        // 세션에서 카트 정보 가져오기
        List<Cart> cartList = (List<Cart>) session.getAttribute("cart");

        // 장바구니가 비어 있으면 예외 발생
        if (cartList == null || cartList.isEmpty()) {
            throw new OrderException("장바구니가 비어 있습니다.");
        }

        // 총 구매 금액 및 총 결제 금액 계산
        int totalBuy = cartList.stream().mapToInt(cart -> cart.getProduct().getPrice() * cart.getEa()).sum();
        int totalPay = totalBuy; // 할인 등이 없는 경우 동일, 할인 적용 등 추가 로직 가능

        // 주문 요약 정보 설정
        orderSummary.setTotal_buy(totalBuy);
        orderSummary.setTotal_pay(totalPay);
        
        ordernumDAO.insertOrdernum();
        ordernumDAO.updateOrdernum();
        
        log.debug("주문번호 생성완료");
        
        orderSummary.setOrdernum(ordernumDAO.selectLastOrdernum());

        // 주문 요약 정보 삽입
        orderSummaryDAO.insert(orderSummary); // mybatis의 select key에 의해 ordersummary_idx는 채워져 있게 됨
        
        //주문번호용으로 세션에 하나 저장
        session.setAttribute("orderNumber", orderSummary.getOrdernum().getOrdernum_idx());


        // 장바구니에 담긴 수만큼 반복하여 주문 상세 등록
        for (Cart cart : cartList) {
            OrderDetail orderDetail = new OrderDetail(); // empty
            orderDetail.setOrderSummary(orderSummary);
            orderDetail.setProduct(cart.getProduct()); // 장바구니에 있는 Product를 대입
            
            log.debug("상품 대입 완료 -------------");
            
            orderDetail.setEa(cart.getEa()); // 장바구니에 있는 갯수를 대입
            
            log.debug("상품갯수 대입 완료 -------------");
            
            orderDetail.setProduct_name(cart.getProduct().getProduct_name());
            orderDetail.setPrice(cart.getProduct().getPrice());

            orderDetailDAO.insert(orderDetail);
            
            log.debug("주문의 상품명"+orderDetail.getProduct_name());
            log.debug("주문의 상태"+orderDetail.getOrderSummary().getOrderstat().getOrderstat_name());
        }

        // 회원이 사용하던 장바구니 비우기
        session.removeAttribute("cart");
    }
	
}













