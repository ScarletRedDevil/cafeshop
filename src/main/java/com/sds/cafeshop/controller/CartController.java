package com.sds.cafeshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.cafeshop.domain.Cart;
import com.sds.cafeshop.domain.Product;
import com.sds.cafeshop.domain.TopCategory;
import com.sds.cafeshop.model.product.ProductService;
import com.sds.cafeshop.model.product.TopCategoryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CartController {

    @Autowired
    private TopCategoryService topCategoryService;
    
    @Autowired
    private ProductService productService;

    @GetMapping("/shop/order/cart")
    public String getCartPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        List<Cart> cart = (List<Cart>) session.getAttribute("cart");
       
        List<TopCategory> topcategoryList = topCategoryService.selectAll(); // TopCategory 목록 가져오기
        model.addAttribute("topcategoryList", topcategoryList); 

        model.addAttribute("cart", cart);
        model.addAttribute("cartTotal", cart.stream().mapToInt(c -> c.getProduct().getPrice() * c.getEa()).sum());

        return "order/cart";
    }

    @PostMapping("/shop/order/cart/add")
    public String addCart(HttpSession session, @RequestParam("product_idx") int product_idx) {
        // 세션에서 카트 정보 가져오기
        List<Cart> originalCart = (List<Cart>) session.getAttribute("cart");

        // 새로운 리스트에 카트 정보 복사
        List<Cart> cart = new ArrayList<>(originalCart);

        // 상품 정보 가져오기
        Product product = productService.select(product_idx);

        // 카트에 동일한 상품이 있는지 확인
        for (Cart item : cart) {
            if (item.getProduct().getProduct_name().equals(product.getProduct_name()) && item.getProduct().getPrice() == product.getPrice()) {
                // 동일한 상품이 있으면 수량을 증가시키고 리턴
                item.setEa(item.getEa() + 1);
                session.setAttribute("cart", cart);
                return "redirect:/shop/order/cart";
            }
        }

        // 동일한 상품이 없으면 새 항목을 추가
        Cart newCart = new Cart();
        newCart.setProduct(product);
        newCart.setEa(1); //일단 1개 

        cart.add(newCart);

        // 세션에 카트 정보 업데이트
        session.setAttribute("cart", cart);

        return "redirect:/shop/order/cart";
    }

    
    @PostMapping("/shop/order/cart/update")
    public String updateCart(@RequestParam("product_idx") int product_idx, @RequestParam("ea") int ea, HttpSession session) {
        List<Cart> cart = (List<Cart>) session.getAttribute("cart");
        
        for (Cart cartItem : cart) {
            if (cartItem.getProduct().getProduct_idx() == product_idx) {
                cartItem.setEa(ea);
                break;
            }
        }
        
        session.setAttribute("cart", cart);
        
        return "redirect:/cart";
    }


    
  @GetMapping("/shop/order/cart/reset")
  public String resetCart(HttpSession session) {
      session.invalidate();
      log.debug("세션 초기화 및 초기화면 이동");
      return "redirect:/";
  }
    
}
