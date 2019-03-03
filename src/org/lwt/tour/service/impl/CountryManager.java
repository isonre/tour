package org.lwt.tour.service.impl;

import java.util.List;

import org.lwt.tour.dao.CountryDao;
import org.lwt.tour.model.Country;

public class CountryManager {
	
	private CountryDao countryDao;
	
	public CountryDao getCountryDao() {
		return countryDao;
	}

	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	//�½�����
	public void saveObject(Country object) {
		countryDao.save(object);
		return;
	}
	
	//�༭����
	public void updateObject(Country object) {
		countryDao.update(object);
		return;
	}
	
	//ɾ������
	public void delObject(int[] ids) {
		for (int i = 0; i < ids.length; i++) {
			countryDao.delete(ids[i]);
		}
	}

}
