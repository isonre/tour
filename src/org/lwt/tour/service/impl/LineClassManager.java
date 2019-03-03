package org.lwt.tour.service.impl;

import java.util.List;

import org.lwt.tour.dao.LineClassDao;
import org.lwt.tour.model.LineClass;

public class LineClassManager {
	
	private LineClassDao lineClassDao;

	public LineClassDao getLineClassDao() {
		return lineClassDao;
	}

	public void setLineClassDao(LineClassDao lineClassDao) {
		this.lineClassDao = lineClassDao;
	}

	//�½�����
	public void saveObject(LineClass object) {
		lineClassDao.save(object);
		return;
	}
	
	//�༭����
	public void updateObject(LineClass object) {
		lineClassDao.update(object);
		return;
	}
	
	//ɾ������
	public void delObject(int[] ids) {
		for (int i = 0; i < ids.length; i++) {
			lineClassDao.delete(ids[i]);
		}
	}

}
