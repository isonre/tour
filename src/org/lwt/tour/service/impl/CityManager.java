package org.lwt.tour.service.impl;

import java.util.List;

import org.lwt.tour.dao.CityDao;
import org.lwt.tour.model.City;
import org.lwt.tour.model.Province;

public class CityManager {
	
	private CityDao cityDao;

	public CityDao getCityDao() {
		return cityDao;
	}

	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	//�½�����
	public void saveObject(City object) {
		cityDao.save(object);
		return;
	}
	
	//�༭����
	public void updateObject(City object) {
		cityDao.update(object);
		return;
	}
	
	//ɾ������
	public void delObject(int[] ids) {
		for (int i = 0; i < ids.length; i++) {
			cityDao.delete(ids[i]);
		}
	}

	//���ݹ��ҷ���ʡ��
	public List<City> getCityByProvince(String pId){
		// TODO Auto-generated method stub
		return cityDao.getCityByProvince(pId);
	}
}
