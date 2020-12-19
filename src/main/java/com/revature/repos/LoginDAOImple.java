package com.revature.repos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.revature.models.User;
import com.revature.utils.HibernateUtility;

public class LoginDAOImple {
	
	public User findByUsername(String username) {
		
		System.out.println("test1.5");
		Session ses = HibernateUtility.getSession();
		
		
		System.out.println("test2.5");
		
		String hql = "FROM ERS_USERS WHERE ERS_USERNAME = " + username;
		Query q = ses.createQuery(hql);
		
		List<User> results = q.getResultList();
		
		User user = results.get(0);
		
		return user;
	}
}
