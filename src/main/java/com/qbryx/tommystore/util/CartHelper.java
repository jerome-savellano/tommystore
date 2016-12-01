package com.qbryx.tommystore.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.service.InventoryService;

@Component
public class CartHelper {
	
	private Map<String, CartProduct> cart;
	
	public void createCart(HttpServletRequest request){
		
		cart = new LinkedHashMap<>();
		
		if(!isCartExisting(request)){
			request.getSession().setAttribute(Constants.SESSION_ATTRIBUTE_CART, cart);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, CartProduct> getCart(HttpServletRequest request){
		return (Map<String, CartProduct>) request.getSession().getAttribute(Constants.SESSION_ATTRIBUTE_CART);
 	}
	
	public void addProductToCart(HttpServletRequest request, CartProduct cartProduct){
		
		Map<String, CartProduct> cart = getCart(request);
		
		String productId = cartProduct.getProduct().getProductId();
		
		if(cart.containsKey(productId)){
			
			updateProductInCart(cart, productId, cartProduct);
		}else{
			
			cart.put(cartProduct.getProduct().getProductId(), cartProduct);
		}
	}
	
	public void removeProductFromCart(HttpServletRequest request, CartProduct cartProduct){
		getCart(request).remove(cartProduct.getProduct().getProductId());
	}
	
	public List<CartProduct> getProductsInCart(HttpServletRequest request, InventoryService inventoryService){
		
		List<CartProduct> cartProducts = new ArrayList<>();
		
		for(CartProduct cartProduct : getCart(request).values()){
			
			int stock = getProductStock(cartProduct, inventoryService);
			
			cartProduct.setStock(stock);
			cartProducts.add(cartProduct);
		}
		
		return cartProducts;
	}
	
	private boolean isCartExisting(HttpServletRequest request){
		
		if(request.getSession().getAttribute(Constants.SESSION_ATTRIBUTE_CART) != null){
			return true;
		}
		
		return false;
	}
	
	private int getProductStock(CartProduct cartProduct, InventoryService inventoryService){
		return inventoryService.findByProduct(cartProduct.getProduct()).getStock();
	}
	
	private void updateProductInCart(Map<String, CartProduct> cart, String productId, CartProduct cartProduct){
		
		CartProduct existingCartProduct = cart.get(productId);
		
		int updateQuantity = existingCartProduct.getQuantity() + cartProduct.getQuantity();
		
		existingCartProduct.setQuantity(updateQuantity);
		
		cart.put(productId, existingCartProduct);
	}
}
