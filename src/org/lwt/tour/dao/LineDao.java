package org.lwt.tour.dao;

import java.util.List;

import org.lwt.tour.model.Line;
import org.lwt.tour.model.Scenic;

public interface LineDao {
	
	Line get(Integer id);//����ĳ��
	
	int delete(int id);//ɾ��
	
	void update(Line object);//����

	Integer save(Line object);//����
	
	public List<Line> getAllObject();

	public List<Line> revealObject(String classword,String keyword);
	
	public List<Line> getObjectByCtg(String ctgId);
}
