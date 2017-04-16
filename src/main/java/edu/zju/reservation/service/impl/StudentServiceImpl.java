package edu.zju.reservation.service.impl;

import edu.zju.reservation.dao.inter.StudentDaoInter;
import edu.zju.reservation.domain.ResStudent;
import edu.zju.reservation.exceptions.ApplicationException;
import edu.zju.reservation.service.inter.StudentServiceInter;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentServiceInter {

    // =====================数据访问层===========================
    @Resource
    private StudentDaoInter studentDao;

    public StudentDaoInter getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDaoInter studentDao) {
        this.studentDao = studentDao;
    }

    // ======================业务逻辑============================

    /**
     * 新增班级组织
     */
    @Override
    public void addStudent(ResStudent student) {
        student.setSlogincount(0);
        student.setStatus("1");
        this.studentDao.addStudent(student);

    }

    /**
     * 批量新增班级组织
     */
    @Override
    public void batchAddStudent(List<ResStudent> students) {
        for (ResStudent student : students) {
            // 数据填充
            student.setSpassword("zdrjxy"); // 默认密码
            student.setSlogincount(0);
            student.setStatus("1");
            // 数据保存
            this.studentDao.addStudent(student);
        }
    }

    /**
     * 修改班级组织信息
     */
    @Override
    public void updateStudent(ResStudent student) {
        this.studentDao.updateStudent(student);
    }

    /**
     * 用户登录信息验证
     */
    @Override
    public ResStudent checkStudent(String sno, String spassword) {
        ResStudent student = this.studentDao.getStudentBySno(sno);
        if (student == null) {
            throw new ApplicationException("输入的账号不存在！");
        } else {
            if (!"1".equals(student.getStatus())) {
                throw new ApplicationException("账号被冻结，无法使用！");
            }
            if (!student.getSpassword().equals(spassword)) {
                throw new ApplicationException("输入的密码错误！");
            }

        }
        return student;
    }

    /**
     * 根据ID查询用户
     */
    @Override
    public ResStudent getStudentById(Serializable sid) {
        return this.studentDao.getStudentById(sid);
    }

    /**
     * 获取所有用户
     */
    @Override
    public List<ResStudent> getAllStudent() {
        return this.studentDao.getAllStudent();
    }

    /**
     * 更新用户最近一次登入时间
     */
    @Override
    public void updateLastLogin(Serializable sid) {
        this.studentDao.updateLastLogin(sid);
    }

    @Override
    public boolean isExistBySno(String sno) {
        ResStudent student = this.studentDao.getStudentBySno(sno);
        if (student == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void updateLastLogout(Serializable sid) {
        // this.studentDao.updateLastLogout(sid); //作废
    }

    /**
     * 解析excel数据
     */
    @Override
    public List<ResStudent> parseExcel(InputStream in) {

        List<ResStudent> studentList = new ArrayList<ResStudent>();
        Workbook readwb = null;
        try {
            readwb = Workbook.getWorkbook(in);

            // 获取第一张Sheet表 // Sheet的下标是从0开始
            Sheet readsheet = readwb.getSheet(0);

            // 获取Sheet表中所包含的总列数
            int rsColumns = readsheet.getColumns();

            // 获取Sheet表中所包含的总行数
            int rsRows = readsheet.getRows();

            if (rsRows < 0) {
                throw new ApplicationException("文件内容不能为空！");
            }

            // 获取指定单元格的对象引用
            String data = null;
            for (int i = 1; i < rsRows; i++) {
                ResStudent student = new ResStudent();
                for (int j = 0; j < rsColumns; j++) {
                    Cell cell = readsheet.getCell(j, i);
                    data = cell.getContents();
                    // log.debug(cell.getContents() + " ");
                    switch (j) {
                        case 0: // 班级账号
                            if (data == null || data.equals("")) {
                                throw new ApplicationException("第" + i + "行第" + j
                                        + "列班级账号为空！");
                            }
                            student.setSno(data);
                            break;
                        case 1: // 班级组织名称
                            if (data == null || data.equals("")) {
                                throw new ApplicationException("第" + i + "行第" + j
                                        + "列班级组织名称为空！");
                            }
                            student.setSname(data);
                            break;

                        case 3: // 备注
                            student.setSmajor(data);
                            break;
                    }
                }
                studentList.add(student);
            }
            return studentList;
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        } finally {
            readwb.close();
        }
    }

}
