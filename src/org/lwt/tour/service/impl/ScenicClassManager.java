package org.lwt.tour.service.impl;

import java.util.List;

import org.lwt.tour.dao.ScenicClassDao;
import org.lwt.tour.model.ScenicClass;

public class ScenicClassManager {
	
	private ScenicClassDao scenicClassDao;

	public ScenicClassDao getScenicClassDao() {
		return scenicClassDao;
	}

	public void setScenicClassDao(ScenicClassDao scenicClassDao) {
		this.scenicClassDao = scenicClassDao;
	}

	//�½�����
	public void saveObject(ScenicClass object) {
		scenicClassDao.save(object);
		return;
	}
	
	//�༭����
	public void updateObject(ScenicClass object) {
		scenicClassDao.update(object);
		return;
	}
	
	//ɾ������
	public void delObject(int[] ids) {
		for (int i = 0; i < ids.length; i++) {
			scenicClassDao.delete(ids[i]);
		}
	}

}
