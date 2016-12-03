package edu.zju.reservation.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.zju.reservation.dao.inter.ClassDaoInter;
import edu.zju.reservation.domain.ResClass;
import edu.zju.reservation.service.inter.ClassServiceInter;

@Service("classService")
public class ClassServiceImpl implements ClassServiceInter {
	Logger log = Logger.getLogger(ClassServiceImpl.class);
	// ========================数据访问层====================
	@Resource
	private ClassDaoInter classDao;

	public ClassDaoInter getClassDao() {
		return classDao;
	}

	public void setClassDao(ClassDaoInter classDao) {
		this.classDao = classDao;
	}

	// ===================业务逻辑============================
	@Override
	public List<ResClass> getClasses() {
		return this.classDao.getClasses();
	}

	@Override
	public List<ResClass> getAllClasses() {
		return this.classDao.getAllClasses();
	}

	@Override
	public void addClass(ResClass rclass) {
		String status = rclass.getCstatus();
		rclass.setCstatus("on".equals(status) ? "1" : "0");
		rclass.setCmodifiedtime(new Date());
		this.classDao.addClass(rclass);
	}

	@Override
	public void updateClass(ResClass rclass) {
		String status = rclass.getCstatus();
		rclass.setCstatus("on".equals(status) ? "1" : "0");
		rclass.setCmodifiedtime(new Date());
		this.classDao.updateClass(rclass);
	}

	@Override
	public ResClass getClassById(Serializable cid) {
		return this.classDao.getClassById(cid);
	}

	/**
	 * 根据教室名称查询教室是否存在
	 */
	@Override
	public boolean isExistByName(String cname) {		
		return this.classDao.selectClassByName(cname) == null ? false : true;
	}

}
