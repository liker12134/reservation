package edu.zju.reservation.dao.inter;

import edu.zju.reservation.domain.ResConfig;

public interface ConfigDaoInter {
	/**
	 * 根据key获取配置信息
	 */
	ResConfig getConfigByKey(String key);

	/**
	 * 修改配置信息
	 */
	void updateConfigByKey(ResConfig resConfig);

}
