package org.lwt.tour.dao;

import java.util.List;

import org.lwt.tour.model.About;


public interface IntroduceDao {
	
	About get(Integer id);//����ĳ��
		
	void update(About object);//����

	Integer save(About object);//����
	
	public Object getObject();
	
}
