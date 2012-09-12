package ru.moneyshark

import java.io.Serializable;
import java.sql.*

import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.hibernate.*
import org.hibernate.usertype.UserType
import grails.converters.JSON
import java.security.MessageDigest
import javax.crypto.*
import javax.crypto.spec.*
import cr.co.arquetipos.crypto.Blowfish
import org.springframework.web.context.request.RequestContextHolder

class EncryptedString implements UserType {
 
    int[] sqlTypes() { [Types.LONGVARCHAR, Types.INTEGER] as int[] }
    Class returnedClass() { StringInteger }
    boolean equals(x, y) { if (x == null || y == null) return false else return x.equals(y) }
    int hashCode(x) { if (x == null) return 0 else return x.hashCode() }
    Object deepCopy(value) {
		if (value == null) return null;		
		StringInteger si = value as StringInteger
		new StringInteger(s: si.s, i: si.i)
	}
    boolean isMutable() { false }
    Serializable disassemble(value) { value }
    def assemble(Serializable cached, owner) { cached }
    def replace(original, target, owner) { deepCopy(original) }
 
    Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        StringInteger si = new StringInteger();
		Integer user_id = rs.getString(names[1]) as Integer
		si.setI(user_id)
		String key = SessionKeysJob.get(user_id)
		if(key != null) {
			String s = Blowfish.decryptBase64(rs.getString(names[0]), key)
			si.setS(s)		
		}
        return si
    }
 
    void nullSafeSet(PreparedStatement st, Object value, int index) {
        StringInteger si = value as StringInteger
	    if (si != null) {
			Integer user_id = si.i
			st.setInt(index + 1, user_id);
			String key = SessionKeysJob.get(user_id)
			if(key != null) {
				String ct = Blowfish.encryptBase64(si.s, key)
				st.setString(index + 0, ct);				
			} else {			 
	        	st.setString(index + 0, "");
			}
	    } else {
	        st.setNull(index + 0, Types.LONGVARCHAR);
	        st.setNull(index + 1, Types.INTEGER);
	    }
    }
}
