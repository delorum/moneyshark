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
 
    int[] sqlTypes() { [Types.LONGVARCHAR] as int[] }
    Class returnedClass() { Integer }
    boolean equals(x, y) { x == y }
    int hashCode(x) { x.hashCode() }
    Object deepCopy(value) { value }
    boolean isMutable() { false }
    Serializable disassemble(value) { value }
    def assemble(Serializable cached, owner) { cached }
    def replace(original, target, owner) { original }
 
    Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) throws HibernateException, SQLException {
        String str = resultSet.getString(names[0])
        str ? decrypt(str) as Integer : null
    }
 
    void nullSafeSet(PreparedStatement statement, Object value, int index) {
        statement.setString(index, value ? encrypt(value) : null)
    }
    private String encrypt(Object plaintext) {
		def session = RequestContextHolder.currentRequestAttributes().getSession()
        Blowfish.encryptBase64(plaintext.toString(), session.key)
    }
 
    private String decrypt(String ciphertext) {
		def session = RequestContextHolder.currentRequestAttributes().getSession()
		Blowfish.decryptBase64(ciphertext, session.key)
    }
}
