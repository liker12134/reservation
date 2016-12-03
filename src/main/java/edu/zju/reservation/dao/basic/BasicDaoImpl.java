package edu.zju.reservation.dao.basic;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.zju.reservation.exceptions.DataAccessException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
public abstract class BasicDaoImpl implements BasicDaoInter {

	@Resource
	SessionFactory ClientSessionFactory;
	// 统一查询方法(hql)
	public List executeQuery(String hql, Object[] parameters) {
		try {
			Session session = ClientSessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			// 注入?值
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					query.setParameter(i, parameters[i]);

				}
			}
			return query.list();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	// 提供一个统一的查询方法 sql 形式 from 类 where 条件=? ..
	public List executeSqlQuery(String sql, Object[] parameters) {
		try {
			Query query = ClientSessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			// 先判断是否有参数要绑定
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					query.setParameter(i, parameters[i]);
				}
			}
			return query.list();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	// 分页
	public List executeQueryByPage(String hql, Object[] parameters,
			int pageNow, int PagesSize) {
		try {
			Query query = ClientSessionFactory.getCurrentSession().createQuery(
					hql);
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					query.setParameter(i, parameters[i]);

				}
			}
			// 体现分页
			return query.setFirstResult((pageNow - 1) * PagesSize)
					.setMaxResults(PagesSize).list();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	// 统一的修改和删除
	public int executeUpdate(String hql, Object[] parameters) {
		try {
			Query query = ClientSessionFactory.getCurrentSession().createQuery(
					hql);
			// 先判断是否有参数要绑定
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					query.setParameter(i, parameters[i]);
				}
			}
			return query.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	public Object findById(Class clazz, Serializable id) {
		try {
			return ClientSessionFactory.getCurrentSession().get(clazz, id);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	public Object uniqueQuery(String hql, Object[] parameters) {
		try {
			Query query = ClientSessionFactory.getCurrentSession().createQuery(
					hql);
			// 给?赋值
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					query.setParameter(i, parameters[i]);
				}
			}
			return query.uniqueResult();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	public int queryPageCount(String hql, Object[] parameters, int pageSize) {
		// 获取rowCount
		/*
		 * List list=this.executeQuery(hql, parameters); Iterator
		 * iteator=list.iterator(); if(iteator.hasNext()){
		 * 
		 * }
		 */
		try {
			Object obj = this.uniqueQuery(hql, parameters);
			// System.out.println("obj value= "+ obj);//obj如果等于rowConunt
			int rowCount = Integer.parseInt(obj.toString());
			return (rowCount - 1) / pageSize + 1;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	public void delById(Class clazz, Serializable id) {
		try {
			Session session = ClientSessionFactory.getCurrentSession();
			session.delete(this.findById(clazz, id));
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	public void update(Object object) {
		try {
			ClientSessionFactory.getCurrentSession().update(object);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	public void add(Object obj) {
		try {
			ClientSessionFactory.getCurrentSession().save(obj);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	public void saveAll(List list) {
		try {
			ClientSessionFactory.getCurrentSession().saveOrUpdate(list);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
}
