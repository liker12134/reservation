package edu.zju.reservation.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.zju.reservation.dao.inter.ConfigDaoInter;
import edu.zju.reservation.domain.ResConfig;
import edu.zju.reservation.service.inter.ConfigServiceInter;

@Service("configService")
public class ConfigServiceImpl implements ConfigServiceInter {

	Logger log = Logger.getLogger(ConfigServiceImpl.class);
	// ========================数据访问层====================
	@Resource
	private ConfigDaoInter configDao;

	public ConfigDaoInter getConfigDao() {
		return configDao;
	}

	public void setConfigDao(ConfigDaoInter configDao) {
		this.configDao = configDao;
	}

	@Override
	public ResConfig getConfigByKey(String key) {
		return this.configDao.getConfigByKey(key);
	}

	@Override
	public void updateConfigByKey(ResConfig resConfig) {
		this.configDao.updateConfigByKey(resConfig);

	}

}
