package edu.zju.reservation.service.inter;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import edu.zju.reservation.domain.ResManager;

public interface AdminServiceInter {

	/**
	 * 判断账号密码是否正确
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	ResManager checkAdmin(String account, String password)
			throws NoSuchAlgorithmException;

	/**
	 * 获取所有管理员列表
	 */
	List<ResManager> getAllManager();

	/**
	 * 新增管理员
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	void addManager(ResManager resManager) throws NoSuchAlgorithmException;

	/**
	 * 根据账号获取管理员信息
	 */
	ResManager getManagerByAccount(String account);

	/**
	 * 修改管理员
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	void updateManager(ResManager resManager) throws NoSuchAlgorithmException;

}
