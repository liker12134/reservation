package edu.zju.reservation.dao.impl;

import edu.zju.reservation.dao.basic.BasicDaoImpl;
import edu.zju.reservation.dao.inter.TimequantumDaoInter;
import edu.zju.reservation.domain.ResTimequantum;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Component("timeQuantumDao")
public class TimeQuantumDaoImpl extends BasicDaoImpl implements
        TimequantumDaoInter {

    /**
     * 获取当前有效时间段列表
     */
    @Override
    public List<ResTimequantum> getValidTimequantum() {
        Date now = new Date();
        return this
                .executeQuery(
                        "from ResTimequantum where teffectivebegintime <= ? and teffectiveendtime >= ?",
                        new Object[]{now, now});
    }

    /**
     * 新增时间段
     */
    @Override
    public void addTimequantum(ResTimequantum timequantum) {
        this.add(timequantum);
    }

    /**
     * 修改时间段
     */
    @Override
    public void updateTimequantum(ResTimequantum timequantum) {
        this.update(timequantum);
    }

    @Override
    public ResTimequantum getTimequantumById(Serializable tid) {
        return (ResTimequantum) this.findById(ResTimequantum.class, tid);
    }

}
