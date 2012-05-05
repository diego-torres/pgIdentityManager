package com.dtorres.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dtorres.dto.User;
import com.dtorres.ejb.exceptions.PgIdentityException;

/**
 * Session Bean implementation class PgIdentityManager
 */
@Stateless(mappedName = "pgIdentityManager")
@WebService
public class PgIdentityManager implements PgIdentityManagerRemote {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	@WebMethod
	public String createUser(@WebParam(name = "userName") String userName,
			@WebParam(name = "password") String password)
			throws PgIdentityException {

		if (password.startsWith(":"))
			throw new PgIdentityException("Passwords are not allowed to begin with a colon symbol.");

		if (userName == null || "".equals(userName.trim()))
			throw new PgIdentityException("User Name is a required field");

		if (password == null || "".equals(password.trim()))
			throw new PgIdentityException("Password is a required field");

		if (userName.length() > 50)
			throw new PgIdentityException("User Name is too long [" + userName + "][" + userName.length() + "]");

		if (getUser(userName) != null)
			throw new PgIdentityException("The user [" + userName + "] already exists, try with another user name.");

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);

		entityManager.persist(user);
		return "Success";
	}

	@Override
	@WebMethod
	public User getUser(@WebParam(name = "userName") String userName)
			throws PgIdentityException {
		String sQuery = "SELECT u FROM User u WHERE u.userName = '" + userName + "'";
		Query query = entityManager.createQuery(sQuery);
		return (User) query.getSingleResult();
	}

	@Override
	@WebMethod
	public List<User> getAllUsers() throws PgIdentityException {
		String hquery = "select u from User u";
		Query query = entityManager.createQuery(hquery);
		return (List<User>) query.getResultList();
	}

	@Override
	@WebMethod
	public String deleteUser(@WebParam(name = "userId") Long userId)
			throws PgIdentityException {
		entityManager.remove(entityManager.merge(entityManager.find(User.class, userId)));
		return "Success";
	}

	@Override
	@WebMethod
	public String resetPassword(@WebParam(name = "userName") String userName, @WebParam(name = "newPassword") String newPassword)
			throws PgIdentityException {

		if (newPassword.startsWith(":")) {
			throw new PgIdentityException("Password must not start with a colon.");
		}

		String updateNativeQueryString = "UPDATE idm_user SET password = '"
				+ newPassword + "' WHERE user_name = '" + userName + "'";
		Query updateNativeQuery = entityManager.createNativeQuery(updateNativeQueryString);
		updateNativeQuery.executeUpdate();
		return "Success";
	}
}
