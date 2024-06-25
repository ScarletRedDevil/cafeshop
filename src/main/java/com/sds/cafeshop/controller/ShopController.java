package com.sds.cafeshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sds.cafeshop.domain.Cart;
import com.sds.cafeshop.domain.Product;
import com.sds.cafeshop.domain.TopCategory;
import com.sds.cafeshop.model.product.ProductService;
import com.sds.cafeshop.model.product.TopCategoryService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ShopController {

    @Autowired
    private TopCategoryService topCategoryService;
    
    @Autowired
    private ProductService productService;

    // 메인페이지
    @GetMapping("/main")
    public String getMainForm(Model model, HttpSession session,
    		@RequestParam(value = "topcategory_idx", defaultValue = "0") int topcategory_idx) {

        // 세션에서 카트 정보 가져오기
        List<Cart> cart = (List<Cart>) session.getAttribute("cart");

        // 세션에 카트 정보가 없으면 새로운 리스트 생성
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        List<TopCategory> topcategoryList = topCategoryService.selectAll();
        List<Product> productList = productService.selectAll();
        model.addAttribute("topcategoryList", topcategoryList);
        model.addAttribute("productList", productList);
        
        log.debug("세션 생성 및 메인화면 이동");

        return "shop/index";
    }

    
//    목록요청
    @GetMapping("/shop/drink")
    public String getList(Model model,
    		@RequestParam(value="topcategory_idx", defaultValue="0") int topcategory_idx, 
			@RequestParam(value="subcategory_idx", defaultValue="0") int subcategory_idx
    		) {
        log.debug("topcategory_idx는 : " + topcategory_idx);

        List<TopCategory> topcategoryList = topCategoryService.selectAll();
        List<Product> productList;
        
        if (topcategory_idx > 0) {
            productList = productService.selectAllByTopIdx(topcategory_idx);
        } else if (subcategory_idx > 0) {
            productList = productService.selectAllBySubIdx(subcategory_idx);
        } else {
            productList = productService.selectAll();
        }

        model.addAttribute("topcategoryList", topcategoryList);
        model.addAttribute("productList", productList);
    	
    	
    	return "shop/list";
    }
    
    @GetMapping("/shop/detail")
    public ModelAndView getDetail(@RequestParam("product_idx") int product_idx, Model model) {
        Product product = productService.select(product_idx); // 상품 상세 정보 가져오기
        ModelAndView mav = new ModelAndView();
        mav.addObject("product", product); // 결과 저장
        mav.setViewName("shop/detail"); // 상세 페이지 뷰 설정

        // 필요한 데이터 추가
        // 예: 모든 topcategory 정보를 모델에 추가하는 경우
        List<TopCategory> topcategoryList = topCategoryService.selectAll();
        model.addAttribute("topcategoryList", topcategoryList);

        return mav;
    }
}
