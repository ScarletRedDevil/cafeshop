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

@Controller
public class testcontroller {

//    @Autowired
//    private TopCategoryService topCategoryService;
//    
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping("/shop/order/cart")
//    public String getCartPage(HttpServletRequest request, Model model) {
//        HttpSession session = request.getSession(false);
//        List<Cart> cart = (List<Cart>) session.getAttribute("cart");
//        if (cart == null) {
//            cart = new ArrayList<>();
//        }
//        List<TopCategory> topcategoryList = topCategoryService.selectAll(); // TopCategory 목록 가져오기
//
//        model.addAttribute("cart", cart);
//        model.addAttribute("cartTotal", cart.stream().mapToInt(c -> c.getProduct().getPrice() * c.getEa()).sum());
//        model.addAttribute("topcategoryList", topcategoryList); 
//
//        return "order/cart";
//    }
//
//    @PostMapping("/shop/order/cart/add")
//    public String addToCart(@RequestParam(name = "product_idx") int product_idx, @RequestParam(name = "ea") int ea, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Product product = productService.select(product_idx);
//        if (product == null) {
//            return "redirect:/";
//        }
//
//        List<Cart> cart = (List<Cart>) session.getAttribute("cart");
//        if (cart == null) {
//            cart = new ArrayList<>();
//        }
//
//        boolean productExistsInCart = false;
//        for (Cart cartItem : cart) {
//            if (cartItem.getProduct_idx() == product_idx) {
//                cartItem.setEa(cartItem.getEa() + ea);
//                productExistsInCart = true;
//                break;
//            }
//        }
//
//        if (!productExistsInCart) {
//            Cart newCartItem = new Cart();
//            newCartItem.setProduct_idx(product_idx);
//            newCartItem.setEa(ea);
//            newCartItem.setProduct(product);
//            cart.add(newCartItem);
//        }
//
//        session.setAttribute("cart", cart);
//
//        return "redirect:/shop/order/cart";
//    }
//
//    @GetMapping("/shop/order/cart/delete")
//    public String deleteCart(@RequestParam int product_idx, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        List<Cart> cart = (List<Cart>) session.getAttribute("cart");
//        if (cart != null) {
//            cart.removeIf(c -> c.getProduct_idx() == product_idx);
//            session.setAttribute("cart", cart);
//        }
//        return "redirect:/shop/order/cart";
//    }
//
//    @GetMapping("/shop/order/checkout")
//    public String checkout(HttpServletRequest request, Model model) {
//        HttpSession session = request.getSession(false);
//        List<Cart> cart = (List<Cart>) session.getAttribute("cart");
//        if (cart == null || cart.isEmpty()) {
//            return "redirect:/shop/order/cart";
//        }
//        model.addAttribute("cart", cart);
//        return "order/checkout";
//    }
//    
//    @GetMapping("/shop/order/cart/reset")
//    public String resetCart(HttpSession session) {
//        session.invalidate();
//        return "redirect:/";
//    }
}
