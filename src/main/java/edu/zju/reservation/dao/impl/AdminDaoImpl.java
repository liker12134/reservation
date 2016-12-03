package edu.zju.reservation.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import edu.zju.reservation.dao.basic.BasicDaoImpl;
import edu.zju.reservation.dao.inter.AdminDaoInter;
import edu.zju.reservation.domain.ResManager;

@Component("adminDao")
public class AdminDaoImpl extends BasicDaoImpl implements AdminDaoInter {

	/**
	 * 根据账号获取管理员信息
	 */
	@Override
	public ResManager getManagerByAccount(String account) {
		return (ResManager) this.uniqueQuery(
				"from ResManager where maccount = ?", new Object[] { account });
	}

	/**
	 * 获取所有管理员列表
	 */
	@Override
	public List<ResManager> getAllManager() {
		return this.executeQuery("from ResManager order by mid desc", null);
	}

	/**
	 * 新增管理员
	 */
	@Override
	public void addManager(ResManager resManager) {
		this.add(resManager);
	}

	/**
	 * 修改管理员
	 */
	@Override
	public void updateManager(ResManager resManager) {
		this.update(resManager);

	}

}
