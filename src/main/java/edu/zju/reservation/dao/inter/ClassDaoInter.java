package edu.zju.reservation.dao.inter;

import edu.zju.reservation.domain.ResClass;

import java.io.Serializable;
import java.util.List;

public interface ClassDaoInter {
    /**
     * 获取所有有效的教室列表
     */
    List<ResClass> getClasses();

    /**
     * 获取所有教室列表
     */
    List<ResClass> getAllClasses();

    /**
     * 新增教室
     */
    void addClass(ResClass rclass);

    /**
     * 修改教室
     */
    void updateClass(ResClass rclass);

    /**
     * 根据ID获取
     */
    ResClass getClassById(Serializable cid);

    /**
     * 根据教室名称查询教室
     */
    ResClass selectClassByName(String cname);

}
