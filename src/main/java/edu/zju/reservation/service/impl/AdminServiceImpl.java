package edu.zju.reservation.service.impl;

import edu.zju.reservation.dao.inter.AdminDaoInter;
import edu.zju.reservation.dao.inter.ConfigDaoInter;
import edu.zju.reservation.dao.inter.StudentDaoInter;
import edu.zju.reservation.domain.ResManager;
import edu.zju.reservation.exceptions.ApplicationException;
import edu.zju.reservation.service.inter.AdminServiceInter;
import edu.zju.reservation.utils.MathUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminServiceInter {
    Logger log = Logger.getLogger(AdminServiceImpl.class);
    // ========================数据访问层====================
    @Resource
    private AdminDaoInter adminDao;
    @Resource
    private StudentDaoInter studentDao;
    @Resource
    private ConfigDaoInter configDao;

    public AdminDaoInter getAdminDao() {
        return adminDao;
    }

    public void setAdminDao(AdminDaoInter adminDao) {
        this.adminDao = adminDao;
    }

    public StudentDaoInter getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDaoInter studentDao) {
        this.studentDao = studentDao;
    }

    // ===================业务逻辑============================

    /**
     * 判断账号密码是否正确
     *
     * @throws NoSuchAlgorithmException
     */
    @Override
    public ResManager checkAdmin(String account, String password)
            throws NoSuchAlgorithmException {
        // 根据账号获取管理员类
        ResManager manager = this.adminDao.getManagerByAccount(account);
        if (manager != null) { // 账号存在
            // 判断密码是否正确
            if (MathUtil.toMD5(password).equals(manager.getMpassword().trim())) {
                return manager;
            } else { // 密码错误
                throw new ApplicationException("密码错误！"
                        + MathUtil.toMD5(password) + ","
                        + manager.getMpassword());
            }
        } else { // 账号不存在
            throw new ApplicationException("账号不存在！");
        }
    }

    /**
     * 获取所有管理员列表
     */
    @Override
    public List<ResManager> getAllManager() {
        return this.adminDao.getAllManager();
    }

    /**
     * 新增管理员
     *
     * @throws NoSuchAlgorithmException
     */
    @Override
    public void addManager(ResManager resManager)
            throws NoSuchAlgorithmException {
        resManager.setMpassword(MathUtil.toMD5(resManager.getMpassword()));
        this.adminDao.addManager(resManager);
    }

    /**
     * 根据账号获取管理员信息
     */
    @Override
    public ResManager getManagerByAccount(String account) {
        return this.adminDao.getManagerByAccount(account);
    }

    /**
     * 修改管理员
     *
     * @throws NoSuchAlgorithmException
     */
    @Override
    public void updateManager(ResManager resManager)
            throws NoSuchAlgorithmException {
        resManager.setMpassword(MathUtil.toMD5(resManager.getMpassword()));
        this.adminDao.updateManager(resManager);
    }

}
