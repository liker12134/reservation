package edu.zju.reservation.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.zju.reservation.dao.basic.BasicDaoImpl;
import edu.zju.reservation.dao.inter.StudentDaoInter;
import edu.zju.reservation.domain.ResStudent;

@Component("studentDao")
public class StudentDaoImpl extends BasicDaoImpl implements StudentDaoInter {

	@Override
	public void addStudent(ResStudent student) {
		this.add(student);

	}

	@Override
	public void updateStudent(ResStudent student) {
		// this.update(student);
		this.executeUpdate(
				"update ResStudent set spassword = ? ,sname = ? ,sremark = ? where sid = ?",
				new Object[] { student.getSpassword(), student.getSname(),
						student.getSremark(), student.getSid() });
	}

	@Override
	public ResStudent getStudentBySno(String sno) {
		return (ResStudent) this.uniqueQuery("from ResStudent where sno = ?",
				new Object[] { sno });

	}

	@Override
	public ResStudent getStudentById(Serializable sid) {
		return (ResStudent) this.findById(ResStudent.class, sid);

	}

	/**
	 * 获取所有用户
	 */
	@Override
	public List<ResStudent> getAllStudent() {
		return this.executeQuery("from ResStudent", null);
	}

	@Override
	public void updateLastLogin(Serializable sid) {
		this.executeUpdate(
				"update ResStudent set slastlogin = ?,slogincount = slogincount + 1 where sid = ?",
				new Object[] { new Date(), sid });

	}

	@Override
	public void updateLastLogout(Serializable sid) {
		this.executeUpdate(
				"update ResStudent set slastlogout = ? where sid = ?",
				new Object[] { new Date(), sid });

	}

	@Override
	public ResStudent getStudentById(Integer sid) {
		return (ResStudent) this.uniqueQuery("from ResStudent where sid = ?",
				new Object[] { sid });
	}
}
