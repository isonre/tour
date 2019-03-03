package org.lwt.tour.dao;

import java.util.List;

import org.lwt.tour.model.ScenicClass;

public interface ScenicClassDao {
	
	ScenicClass get(Integer id);//����ĳ��
	
	int delete(int id);//ɾ��
	
	void update(ScenicClass object);//����

	Integer save(ScenicClass object);//����
	
	public List<ScenicClass> getAllObject();

	public List<ScenicClass> revealObject(String keyword);
}
