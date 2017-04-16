package edu.zju.reservation.service.inter;

import edu.zju.reservation.domain.ResStudent;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

public interface StudentServiceInter {
    /**
     * 新增学生
     */
    void addStudent(ResStudent student);

    /**
     * 批量新增学生
     */
    void batchAddStudent(List<ResStudent> students);

    /**
     * 修改学生信息
     */
    void updateStudent(ResStudent student);

    /**
     * 用户登录信息验证
     */
    ResStudent checkStudent(String sno, String spassword);

    /**
     * 根据ID查询用户
     */
    ResStudent getStudentById(Serializable sid);

    /**
     * 获取所有用户
     */
    List<ResStudent> getAllStudent();

    /**
     * 根据sno判断用户是否存在
     */
    boolean isExistBySno(String sno);

    /**
     * 更新用户最近一次登入时间
     */
    void updateLastLogin(Serializable sid);

    /**
     * 更新用户最近一次登出时间
     */
    void updateLastLogout(Serializable integer);

    /**
     * 解析excel数据
     */
    List<ResStudent> parseExcel(InputStream in);
}
