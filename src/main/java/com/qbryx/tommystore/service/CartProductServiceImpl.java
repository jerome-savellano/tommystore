package com.qbryx.tommystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.tommystore.dao.CartProductDao;
import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.domain.User;

@Service("cartProductService")
@Transactional(readOnly=true)
public class CartProductServiceImpl implements CartProductService{
	
	@Autowired
	private CartProductDao cartProductDao;

	@Override
	public List<CartProduct> findByUser(User user) {
		return cartProductDao.findByUser(user);
	}
	
	@Override
	public CartProduct findByProduct(Product product) {
		return cartProductDao.findByProduct(product);
	}

	@Transactional(readOnly=false)
	@Override
	public void createCartProduct(CartProduct cartProductToAdd) {
		
		CartProduct cartProduct = findByProduct(cartProductToAdd.getProduct());
		
		if(cartProduct != null){
			
			int quantity = cartProduct.getQuantity() + cartProductToAdd.getQuantity();
			
			cartProduct.setQuantity(quantity);
			
			cartProductDao.updateCartProduct(cartProduct);
		}else{
			
			cartProductDao.createCartProduct(cartProductToAdd);
		}
	}

	@Transactional(readOnly=false)
	@Override
	public void deleteCartProduct(CartProduct cartProduct) {
		cartProductDao.deleteCartProduct(cartProduct);
	}
}
