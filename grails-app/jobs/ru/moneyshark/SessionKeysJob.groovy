package ru.moneyshark



class SessionKeysJob {
    static triggers = {
      simple repeatInterval: 30*60*1000l // execute job once in 30 minutes
    }
	
	private static def keys = [:]
	static def put(key, value) {keys.key = value}
	static def contains(key) {return keys.containsKey(key)}
	static def get(key) {return keys.key}

    def execute() {
        keys.clear()
    }
}
