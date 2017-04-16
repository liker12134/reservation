package edu.zju.reservation.dao.inter;

import edu.zju.reservation.domain.ResTimequantum;

import java.io.Serializable;
import java.util.List;

public interface TimequantumDaoInter {
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
     * 根据ID获取时间段
     */
    ResTimequantum getTimequantumById(Serializable tid);
}
