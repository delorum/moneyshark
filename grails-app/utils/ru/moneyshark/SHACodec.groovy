package ru.moneyshark

import java.security.MessageDigest

class SHACodec {
	static encode = {target -> 
		MessageDigest md_sha = MessageDigest.getInstance('SHA')
		md_sha.update(target.getBytes('UTF-8'))
		String sha_hash = new String(md_sha.digest()).encodeAsBase64()
		
		/*MessageDigest md_md5 = MessageDigest.getInstance('MD5')
		md_md5.update(target.getBytes('UTF-8'))
		String md5_hash = new String(md_md5.digest()).encodeAsBase64()*/
		
		return sha_hash/* + md5_hash*/
	}
}