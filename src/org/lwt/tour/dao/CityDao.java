package org.lwt.tour.dao;

import java.util.List;

import org.lwt.tour.model.City;
import org.lwt.tour.model.Province;

public interface CityDao {
	
	City get(Integer id);//����ĳ��
	
	int delete(int id);//ɾ��
	
	void update(City object);//����

	Integer save(City object);//����
	
	public List<City> getAllObject();

	public List<City> revealObject(String classword,String keyword);
	
	public List<City> getCityByProvince(String pId);
}
