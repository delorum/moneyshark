package ru.moneyshark



class PeriodicMoneyJob {
    static triggers = {
      simple repeatInterval: 30*60*1000L // execute job every 30min
    }
	
    def execute() {
        
    }
}
