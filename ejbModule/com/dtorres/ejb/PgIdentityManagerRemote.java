package com.dtorres.ejb;

import java.util.List;
import javax.ejb.Remote;
import com.dtorres.dto.User;
import com.dtorres.ejb.exceptions.PgIdentityException;

@Remote
public interface PgIdentityManagerRemote {
	String createUser(String userName, String password) throws PgIdentityException;
	User getUser(String userName) throws PgIdentityException;
	List<User> getAllUsers() throws PgIdentityException;
	String deleteUser(Long userId) throws PgIdentityException;
	String resetPassword(String userName, String newPassword) throws PgIdentityException;
}
