package org.lwt.tour.dao;

import java.util.List;

import org.lwt.tour.model.Country;

public interface CountryDao {
	
	 Country get(Integer id);//����ĳ��
	
	int delete(int id);//ɾ��
	
	void update(Country object);//����

	Integer save(Country object);//����
	
	public List<Country> getAllObject();

	public List<Country> revealObject(String keyword);
}
