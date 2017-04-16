package edu.zju.reservation.service.impl;

import edu.zju.reservation.dao.inter.TimequantumDaoInter;
import edu.zju.reservation.domain.ResTimequantum;
import edu.zju.reservation.service.inter.TimequantumServiceInter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

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
