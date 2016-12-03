package edu.zju.reservation.service.inter;

import java.io.Serializable;
import java.util.List;

import edu.zju.reservation.domain.ResTimequantum;

public interface TimequantumServiceInter {
	/**
	 * 获取当前有效时间段列表
	 */
	List<ResTimequantum> getValidTimequantum();

	/**
	 * 新增时间段
	 */
	void addTimequantum(ResTimequantum timequantum);

	/**
	 * 修改时间段
	 */
	void updateTimequantum(ResTimequantum timequantum);

	/**
	 * 根据ID获取数据
	 */
	ResTimequantum getTimequantumById(Serializable tid);
}
