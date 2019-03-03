package org.lwt.tour.dao;

import java.util.List;

import org.lwt.tour.model.Account;

public interface AccountDao {
	
	Account get(Integer id);//����ĳ��
	
	int delete(int id);//ɾ��
	
	void update(Account account);//����

	Integer save(Account account);//����
	
	public List findByStudentID(String name);
	
	public List findByStudentIDAndPass(String name, String password);

}
