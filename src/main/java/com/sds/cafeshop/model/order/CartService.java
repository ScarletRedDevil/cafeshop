package com.sds.cafeshop.model.order;

import java.util.List;

import com.sds.cafeshop.domain.Cart;

public interface CartService {
	public void regist(Cart cart); //장바구니 등록 
//	public List selectByMember(Member member);//장바구니 목록
	public Cart select(int cart_idx);
	public void update(Cart cart); //수정 
	public void updateGroup(List<Cart> cartList);//여러건 수정
	public void delete(Cart cart); //삭제
}