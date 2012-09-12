package ru.moneyshark

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

class EncryptedInteger implements UserType {
 
    int[] sqlTypes() { [Types.LONGVARCHAR, Types.INTEGER] as int[] }
    Class returnedClass() { TwoIntegers }
    boolean equals(x, y) { if (x == null || y == null) return false else return x.equals(y) }
    int hashCode(x) { if (x == null) return 0 else return x.hashCode() }
    Object deepCopy(value) { 
		if (value == null) return null;		
		TwoIntegers ti = value as TwoIntegers
		new TwoIntegers(int1: ti.int1, int2: ti.int2)
	}
    boolean isMutable() { false }
    Serializable disassemble(value) { value }
    def assemble(Serializable cached, owner) { cached }
    def replace(original, target, owner) { deepCopy(original) }
 
    Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        TwoIntegers ti = new TwoIntegers();
		Integer user_id = rs.getString(names[1]) as Integer
		ti.setInt2(user_id)
		String key = SessionKeysJob.get(user_id)
		if(key != null) {
			Integer int1 = Blowfish.decryptBase64(rs.getString(names[0]), key) as Integer
			ti.setInt1(int1)		
		}
        return ti
    }
 
    void nullSafeSet(PreparedStatement st, Object value, int index) {
        TwoIntegers ti = value as TwoIntegers
	    if (ti != null) {
			Integer user_id = ti.int2
			st.setInt(index + 1, user_id);
			String key = SessionKeysJob.get(user_id)
			if(key != null) {
				String ct = Blowfish.encryptBase64(ti.int1.toString(), key)
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
