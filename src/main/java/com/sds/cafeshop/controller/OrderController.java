package com.sds.cafeshop.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sds.cafeshop.domain.Cart;
import com.sds.cafeshop.domain.OrderStat;
import com.sds.cafeshop.domain.OrderSummary;
import com.sds.cafeshop.domain.Paymethod;
import com.sds.cafeshop.domain.TopCategory;
import com.sds.cafeshop.exception.OrderException;
import com.sds.cafeshop.model.order.OrderService;
import com.sds.cafeshop.model.order.OrderStatDAO;
import com.sds.cafeshop.model.order.OrderSummaryDAO;
import com.sds.cafeshop.model.order.OrderSummaryService;
import com.sds.cafeshop.model.order.PaymethodDAO;
import com.sds.cafeshop.model.product.TopCategoryService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderController {
    @Autowired
    private TopCategoryService topCategoryService;
    
    @Autowired
    private OrderStatDAO orderStatDAO;
    
    @Autowired
    private PaymethodDAO paymethodDAO;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderSummaryDAO orderSummaryDAO;
    
    @Autowired
    private OrderSummaryService orderSummaryService;
    
    @GetMapping("/shop/order/checkout")
    public String getOrderPage(HttpSession session, Model model) {
        // TopCategory 목록 가져오기
        List<TopCategory> topcategoryList = topCategoryService.selectAll();
        model.addAttribute("topcategoryList", topcategoryList);

        // 세션에서 카트 정보 가져오기
        List<Cart> cartList = (List<Cart>) session.getAttribute("cart");

        if (cartList == null || cartList.isEmpty()) {
            model.addAttribute("cartList", Collections.emptyList());
            model.addAttribute("cartTotal", 0);
        } else {
            // 총 금액 계산
            int cartTotal = cartList.stream()
                                    .mapToInt(cartItem -> cartItem.getProduct().getPrice() * cartItem.getEa())
                                    .sum();
            model.addAttribute("cartList", cartList);
            model.addAttribute("cartTotal", cartTotal);
        }

        // Paymethod 목록 가져오기
        List<Paymethod> paymethodList = paymethodDAO.selectAll();

        // 모델에 필요한 속성 추가
        model.addAttribute("paymethodList", paymethodList);

        return "order/checkout";
    }
    
	@PostMapping("/shop/order/payment/pay")
	public String regist(OrderSummary orderSummary, HttpSession session) {
		
	    if (orderSummary.getPaymethod() == null || orderSummary.getPaymethod().getPaymethod_idx() == -1) {
	        throw new IllegalArgumentException("결제 방법을 선택하세요.");
	    }


		log.debug("결제 프로세스 시작 ------ " );
		OrderStat orderStat = new OrderStat();
		orderStat.setOrderstat_idx(1);
		orderSummary.setOrderstat(orderStat);
		log.debug("주문상태 설정완료 ");
		
	    Paymethod paymethod = new Paymethod();
	    paymethod.setPaymethod_idx(orderSummary.getPaymethod().getPaymethod_idx());
	    orderSummary.setPaymethod(paymethod);
	    
	    log.debug("설정한 지불방법은"+paymethod);
		
		//3단계: 주문 등록 
		orderService.order(orderSummary, session);
		
		log.debug("--------------주문생성완료---------------");
		
		return "redirect:/shop/order/success";
	}
	
	@GetMapping("/shop/order/success")
	public String getSuccess() {
		
		return "order/success";
	}
	
    @GetMapping("/order/screen")
    public String showOrders(Model model) {
        List<OrderSummary> preparingOrders = orderSummaryService.selectByOrderStatIdx(1);
        log.debug("준비중 주문 = "+preparingOrders);
        List<OrderSummary> readyOrders = orderSummaryService.selectByOrderStatIdx(2);

        model.addAttribute("preparingOrders", preparingOrders);
        model.addAttribute("readyOrders", readyOrders);

        
        return "screen/screen";
    }

    
	@ExceptionHandler(OrderException.class)
	public ModelAndView handle(OrderException e) {
		ModelAndView mav = new ModelAndView("shop/error/result");
		mav.addObject("e", e);
		return mav;
	}

}
