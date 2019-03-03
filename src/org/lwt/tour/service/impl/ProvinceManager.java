package org.lwt.tour.service.impl;

import java.util.List;

import org.lwt.tour.dao.ProvinceDao;
import org.lwt.tour.model.Province;

public class ProvinceManager {
	
	private ProvinceDao provinceDao;
	
	public ProvinceDao getProvinceDao() {
		return provinceDao;
	}

	public void setProvinceDao(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}

	//�½�����
	public void saveObject(Province object) {
		provinceDao.save(object);
		return;
	}
	
	//�༭����
	public void updateObject(Province object) {
		provinceDao.update(object);
		return;
	}
	
	//ɾ������
	public void delObject(int[] ids) {
		for (int i = 0; i < ids.length; i++) {
			provinceDao.delete(ids[i]);
		}
	}
	
	//���ݹ��ҷ���ʡ��
	public List<Province> getProvinceBycountry(String cId){
		// TODO Auto-generated method stub
		return provinceDao.getProvinceBycountry(cId);
	}

}
