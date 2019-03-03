package org.lwt.tour.dao;

import java.util.List;

import org.lwt.tour.model.User;

public interface UserDao {
	
	User get(Integer id);//����ĳ��
	
	int delete(int id);//ɾ��
	
	void update(User user);//����

	Integer save(User user);//����
	
	public List findByAccount(int a_id);
	
	public List<User> findAllUsertInfo();

}
