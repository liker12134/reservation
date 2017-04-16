package edu.zju.reservation.dao.inter;

import edu.zju.reservation.domain.ResStudent;

import java.io.Serializable;
import java.util.List;

public interface StudentDaoInter {
    /**
     * 新增学生
     */
    void addStudent(ResStudent student);

    /**
     * 修改学生信息
     */
    void updateStudent(ResStudent student);

    /**
     * 根据sno查询用户
     */
    ResStudent getStudentBySno(String sno);

    /**
     * 根据ID查询用户
     */
    ResStudent getStudentById(Serializable sid);

    /**
     * 获取所有用户
     */
    List<ResStudent> getAllStudent();

    /**
     * 更新用户最近一次登入时间
     */
    void updateLastLogin(Serializable sid);

    /**
     * 更新用户最近一次登出时间
     */
    void updateLastLogout(Serializable sid);

    /**
     * 根据SID获取学生信息
     */
    ResStudent getStudentById(Integer sid);

}
