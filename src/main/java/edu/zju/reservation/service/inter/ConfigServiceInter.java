package edu.zju.reservation.service.inter;

import edu.zju.reservation.domain.ResConfig;

public interface ConfigServiceInter {
	ResConfig getConfigByKey(String key);

	void updateConfigByKey(ResConfig resConfig);
}
