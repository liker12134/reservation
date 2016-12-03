package edu.zju.reservation.dao.impl;

import org.springframework.stereotype.Component;

import edu.zju.reservation.dao.basic.BasicDaoImpl;
import edu.zju.reservation.dao.inter.ConfigDaoInter;
import edu.zju.reservation.domain.ResConfig;

@Component("configDao")
public class ConfigDaoImpl extends BasicDaoImpl implements ConfigDaoInter {
	@Override
	public ResConfig getConfigByKey(String key) {
		return (ResConfig) this.uniqueQuery("from ResConfig where ckey = ?",
				new Object[] { key });
	}

	@Override
	public void updateConfigByKey(ResConfig resConfig) {
		this.executeUpdate("update ResConfig set cvalue = ? where ckey = ?",
				new Object[] { resConfig.getCvalue(), resConfig.getCkey() });
	}
}
