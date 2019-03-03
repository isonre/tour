package org.lwt.tour.dao;

import java.util.List;

import org.lwt.tour.model.City;
import org.lwt.tour.model.Scenic;

public interface ScenicDao {
	
	Scenic get(Integer id);//����ĳ��
	
	int delete(int id);//ɾ��
	
	void update(Scenic object);//����

	Integer save(Scenic object);//����
	
	public List<Scenic> getAllObject();

	public List<Scenic> revealObject(String cityword,String classword);
	
	public List<Scenic> getObjectByCtg(String ctgId);
}
