package com.dtorres.customTypes;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.hsqldb.Types;

public class Chkpass implements UserType {

	@Override
	public Object nullSafeGet(ResultSet inResultSet, String[] names, Object o)
			throws HibernateException, SQLException {
		Object tmp = inResultSet.getObject(names[0]);
		return inResultSet.wasNull() ? null : tmp.toString();
	}

	@Override
	public void nullSafeSet(PreparedStatement inPreparedStatement, Object o,
			int i) throws HibernateException, SQLException {
		if (o == null)
			inPreparedStatement.setNull(i, Types.VARCHAR);
		else
			inPreparedStatement.setObject(i, o, Types.OTHER);
	}
	
	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	@Override
	public Object deepCopy(Object o) throws HibernateException {
		if (o == null) {
			return null;
		}
		return new String(((String) o));
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return (x == y) || (x != null && y != null && (x.equals(y)));
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}
	
	@Override
	public Object replace(Object original, Object target, Object object)
			throws HibernateException {
		return original;
	}

	@Override
	public Class<String> returnedClass() {
		return String.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.OTHER };
	}
}
