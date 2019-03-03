package org.lwt.tour.service.impl;

import java.util.List;

import org.lwt.tour.dao.ScenicDao;
import org.lwt.tour.model.City;
import org.lwt.tour.model.Scenic;

public class ScenicManager {
	
	private ScenicDao scenicDao;

	public ScenicDao getScenicDao() {
		return scenicDao;
	}

	public void setScenicDao(ScenicDao scenicDao) {
		this.scenicDao = scenicDao;
	}

	//�½�����
	public void saveObject(Scenic object) {
		scenicDao.save(object);
		return;
	}
	
	//�༭����
	public void updateObject(Scenic object) {
		scenicDao.update(object);
		return;
	}
	
	//ɾ������
	public void delObject(int[] ids) {
		for (int i = 0; i < ids.length; i++) {
			scenicDao.delete(ids[i]);
		}
	}
	
	//���ݷ��෵�ض���
	public List<Scenic> getObjectByCtg(String ctgId){
		// TODO Auto-generated method stub
		return scenicDao.getObjectByCtg(ctgId);
	}

}
