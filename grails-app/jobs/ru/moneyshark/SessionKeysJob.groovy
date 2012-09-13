package ru.moneyshark



class SessionKeysJob {
	private static def period = 30*60*1000l
	
    static triggers = {
      simple repeatInterval: period // execute job once in 30 minutes
    }
	
	private static def keys = [:]
	static def put(key, value) {
		def ikey = key as Integer
		keys[ikey] = [value, System.currentTimeMillis()]
	}
	static def contains(key) {
		def ikey = key as Integer
		def res = keys.containsKey(ikey)
		return res
	}
	static def get(key) {
		def ikey = key as Integer
		def res = keys[ikey][0]
		return res
	}

    def execute() {
		def iterator = keys.entrySet().iterator()		
		while (iterator.hasNext()) {
		
		  if (System.currentTimeMillis() - iterator.next().value[1] >= period) {
			iterator.remove()
		  }
		}
    }
}
