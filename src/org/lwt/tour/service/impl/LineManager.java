package org.lwt.tour.service.impl;

import java.util.List;

import org.lwt.tour.dao.LineDao;
import org.lwt.tour.model.Line;
import org.lwt.tour.model.Scenic;

public class LineManager {
	
	private LineDao lineDao;

	public LineDao getLineDao() {
		return lineDao;
	}

	public void setLineDao(LineDao lineDao) {
		this.lineDao = lineDao;
	}

	//�½�����
	public void saveObject(Line object) {
		lineDao.save(object);
		return;
	}
	
	//�༭����
	public void updateObject(Line object) {
		lineDao.update(object);
		return;
	}
	
	//ɾ������
	public void delObject(int[] ids) {
		for (int i = 0; i < ids.length; i++) {
			lineDao.delete(ids[i]);
		}
	}

	//���ݷ��෵�ض���
	public List<Line> getObjectByCtg(String ctgId){
		// TODO Auto-generated method stub
		return lineDao.getObjectByCtg(ctgId);
	}
}
