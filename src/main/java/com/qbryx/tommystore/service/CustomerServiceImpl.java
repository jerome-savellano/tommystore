package com.qbryx.tommystore.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.tommystore.dao.CreditCardDao;
import com.qbryx.tommystore.dao.OrderDao;
import com.qbryx.tommystore.dao.ShippingAddressDao;
import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.domain.CreditCard;
import com.qbryx.tommystore.domain.Inventory;
import com.qbryx.tommystore.domain.Order;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.domain.ShippingAddress;
import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.util.DateUtil;
import com.qbryx.tommystrore.exception.InsufficientStockException;
import com.qbryx.tommystrore.exception.InvalidStockException;

@Service("customerService")
@Transactional(readOnly=true)
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private ShippingAddressDao shippingAddressdao;
	
	@Autowired
	private CreditCardDao creditCardDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private InventoryService inventoryService;

	@Override
	public List<ShippingAddress> findShippingAddresses(User user){
		return shippingAddressdao.findByUser(user);
	}
	
	@Override
	public ShippingAddress findShippingAddressById(long id) {
		return shippingAddressdao.findById(id);
	}
	
	@Override
	public CreditCard findCreditCardById(long id) {
		return creditCardDao.findById(id);
	}

	@Transactional(readOnly=false)
	@Override
	public void saveShippingAddress(ShippingAddress shippingAddress) {
		shippingAddressdao.save(shippingAddress);
	}

	@Override
	public List<CreditCard> findCreditCards(User user) {
		return creditCardDao.findByUser(user);
	}

	@Transactional(readOnly=false)
	@Override
	public void saveCreditCard(CreditCard creditCard) {
		creditCardDao.save(creditCard);
	}


	@Transactional(readOnly=false)
	@Override
	public void saveOrder(Order order) {
		orderDao.save(order);
	}

	@Transactional(readOnly=false, rollbackFor=InsufficientStockException.class)
	@Override
	public void checkOut(User user, ShippingAddress shippingAddress, Map<String, CartProduct> cart) throws InsufficientStockException, InvalidStockException {
		
		Order order = null;
			
		for(CartProduct cartProduct : cart.values()){
			
			if(inStock(cartProduct)){
				
				order = new Order();
				order.setUser(user);
				order.setShippingAddress(shippingAddress);
				order.setProduct(cartProduct.getProduct());
				order.setQuantity(cartProduct.getQuantity());
				order.setOrderDate(DateUtil.now());
				
				saveOrder(order);
				updateInventory(cartProduct);
			}else{
				
				throw new InsufficientStockException();
			}
		}
	}
	
	private void updateInventory(CartProduct cartProduct) throws InvalidStockException{
		
		Inventory inventory = getInventory(cartProduct.getProduct());
		
		int updatedStock = inventory.getStock() - cartProduct.getQuantity();
		
		inventory.setStock(updatedStock);
		inventoryService.updatePurchase(inventory);
	}
	
	private boolean inStock(CartProduct cartProduct){
		
		int stock = getStock(cartProduct.getProduct());
		
		if(stock < cartProduct.getQuantity()){
			return false;
		}
		
		return true;
	}
	
	private int getStock(Product product){
		return inventoryService.findByProduct(product).getStock();
	}
	
	private Inventory getInventory(Product product){
		return inventoryService.findByProduct(product);
	}
}
