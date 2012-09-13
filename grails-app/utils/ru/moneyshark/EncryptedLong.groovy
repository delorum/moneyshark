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

class EncryptedLong implements UserType {
 
    int[] sqlTypes() { [Types.LONGVARCHAR, Types.INTEGER] as int[] }
    Class returnedClass() { LongInteger }
    boolean equals(x, y) { if (x == null || y == null) return false else return x.equals(y) }
    int hashCode(x) { if (x == null) return 0 else return x.hashCode() }
    Object deepCopy(value) { 
		if (value == null) return null;		
		LongInteger li = value as LongInteger
		new LongInteger(l: li.l, i: li.i)
	}
    boolean isMutable() { false }
    Serializable disassemble(value) { value }
    def assemble(Serializable cached, owner) { cached }
    def replace(original, target, owner) { deepCopy(original) }
 
    Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        LongInteger li = new LongInteger();
		Integer user_id = rs.getString(names[1]) as Integer
		li.setI(user_id)
		String key = SessionKeysJob.get(user_id)
		/*if(key != null) {
			Long l = Blowfish.decryptBase64(rs.getString(names[0]), key) as Long
			li.setL(l)		
		}*/
		Long l = rs.getString(names[0]) as Long
		li.setL(l)
        return li
    }
 
    void nullSafeSet(PreparedStatement st, Object value, int index) {
        LongInteger li = value as LongInteger
	    if (li != null) {
			Integer user_id = li.i
			st.setInt(index + 1, user_id);
			String key = SessionKeysJob.get(user_id)
			/*if(key != null) {
				String ct = Blowfish.encryptBase64(li.l.toString(), key)
				st.setString(index + 0, ct);				
			} else {			 
	        	st.setString(index + 0, "");
			}*/
			String ct = li.l.toString()
			st.setString(index + 0, ct)
	    } else {
	        st.setNull(index + 0, Types.LONGVARCHAR);
	        st.setNull(index + 1, Types.INTEGER);
	    }
    }
}
