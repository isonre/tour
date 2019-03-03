package org.lwt.tour.service.impl;

import java.util.List;

import org.lwt.tour.dao.OrderDao;
import org.lwt.tour.model.Order;

public class OrderManager {
	
	private OrderDao orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	//�½�����
	public void saveObject(Order object) {
		orderDao.save(object);
		return;
	}
	
	//ɾ������
	public void delObject(int[] ids) {
		for (int i = 0; i < ids.length; i++) {
			orderDao.delete(ids[i]);
		}
	}

	//���ݷ��෵�ض���
	public List<Order> getObjectByUser(int aId){
		// TODO Auto-generated method stub
		return orderDao.getObjectByUser(aId);
	}
}
