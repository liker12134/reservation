package edu.zju.reservation.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.zju.reservation.dao.inter.TimequantumDaoInter;
import edu.zju.reservation.domain.ResTimequantum;
import edu.zju.reservation.service.inter.TimequantumServiceInter;

@Service("timeQuantumService")
public class TimequantumServiceImpl implements TimequantumServiceInter {

	// ====================数据访问层======================
	@Resource
	private TimequantumDaoInter timeQuantumDao;

	public TimequantumDaoInter getTimeQuantumDao() {
		return timeQuantumDao;
	}

	public void setTimeQuantumDao(TimequantumDaoInter timeQuantumDao) {
		this.timeQuantumDao = timeQuantumDao;
	}

	// ===================业务逻辑============================
	@Override
	public List<ResTimequantum> getValidTimequantum() {
		return this.timeQuantumDao.getValidTimequantum();
	}

	@Override
	public void addTimequantum(ResTimequantum timequantum) {
		this.timeQuantumDao.addTimequantum(timequantum);
	}

	@Override
	public void updateTimequantum(ResTimequantum timequantum) {
		this.timeQuantumDao.updateTimequantum(timequantum);
	}

	@Override
	public ResTimequantum getTimequantumById(Serializable tid) {
		return this.timeQuantumDao.getTimequantumById(tid);
	}

}
