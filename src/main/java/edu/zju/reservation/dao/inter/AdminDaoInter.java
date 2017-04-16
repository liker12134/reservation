package edu.zju.reservation.dao.inter;

import edu.zju.reservation.domain.ResManager;

import java.util.List;

public interface AdminDaoInter {
    /**
     * 根据账号获取管理员信息
     */
    ResManager getManagerByAccount(String account);

    /**
     * 获取所有管理员列表
     */
    List<ResManager> getAllManager();

    /**
     * 新增管理员
     */
    void addManager(ResManager resManager);

    /**
     * 修改管理员
     */
    void updateManager(ResManager resManager);

}
