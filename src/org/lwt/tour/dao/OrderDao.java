package org.lwt.tour.dao;

import java.util.List;

import org.lwt.tour.model.Order;

public interface OrderDao {
	
	Order get(Integer id);//����ĳ��
	
	int delete(int id);//ɾ��
	
	Integer save(Order object);//����
	
	public List<Order> getAllObject();
	
	public List<Order> getObjectByUser(int uId);
}
