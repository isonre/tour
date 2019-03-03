package org.lwt.tour.dao;

import java.util.List;

import org.lwt.tour.model.LineClass;

public interface LineClassDao {
	
	LineClass get(Integer id);//����ĳ��
	
	int delete(int id);//ɾ��
	
	void update(LineClass object);//����

	Integer save(LineClass object);//����
	
	public List<LineClass> getAllObject();

	public List<LineClass> revealObject(String keyword);
}
