package org.lwt.tour.dao;

import java.util.List;

import org.lwt.tour.model.Province;

public interface ProvinceDao {
	
	Province get(Integer id);//����ĳ��
	
	int delete(int id);//ɾ��
	
	void update(Province object);//����

	Integer save(Province object);//����
	
	public List<Province> getAllObject();

	public List<Province> revealObject(String classword,String keyword);
	
	public List<Province> getProvinceBycountry(String cId);
	
	
}
