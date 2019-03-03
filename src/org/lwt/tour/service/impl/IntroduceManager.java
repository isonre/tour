package org.lwt.tour.service.impl;

import org.lwt.tour.dao.IntroduceDao;
import org.lwt.tour.model.About;

public class IntroduceManager {
	
	private IntroduceDao introduceDao;

	public IntroduceDao getIntroduceDao() {
		return introduceDao;
	}

	public void setIntroduceDao(IntroduceDao introduceDao) {
		this.introduceDao = introduceDao;
	}

	//�½�����
	public void saveObject(About object) {
		introduceDao.save(object);
		return;
	}
	
	//�༭����
	public void updateObject(About object) {
		introduceDao.update(object);
		return;
	}
	

}
