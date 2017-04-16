package edu.zju.reservation.dao.impl;

import edu.zju.reservation.dao.basic.BasicDaoImpl;
import edu.zju.reservation.dao.inter.ClassDaoInter;
import edu.zju.reservation.domain.ResClass;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("classDao")
public class ClassDaoImpl extends BasicDaoImpl implements ClassDaoInter {

    /**
     * 获取所有有效的教室列表
     */
    @Override
    public List<ResClass> getClasses() {
        return this.executeQuery("from ResClass where cstatus = ?",
                new Object[]{"1"});
    }

    /**
     * 获取所有教室列表
     */
    @Override
    public List<ResClass> getAllClasses() {
        return this.executeQuery("from ResClass order by cmodifiedtime desc",
                null);
    }

    /**
     * 新增教室
     */
    @Override
    public void addClass(ResClass rclass) {
        this.add(rclass);
    }

    /**
     * 修改教室
     */
    @Override
    public void updateClass(ResClass rclass) {
        this.update(rclass);
    }

    /**
     * 根据ID获取
     */
    @Override
    public ResClass getClassById(Serializable cid) {
        return (ResClass) this.findById(ResClass.class, cid);
    }

    /**
     * 根据教室名称查询教室
     */
    @Override
    public ResClass selectClassByName(String cname) {
        return (ResClass) this.uniqueQuery("from ResClass where cname = ? and cstatus = ?",
                new Object[]{cname, "1"});
    }

}
