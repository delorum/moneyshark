package ru.moneyshark

class IntervalUtils {
	static def presentDayStart() {
		def present_day_start = new GregorianCalendar()
		present_day_start.set(Calendar.HOUR_OF_DAY, 0)
		present_day_start.set(Calendar.MINUTE, 0)
		present_day_start.set(Calendar.SECOND, 0)
		present_day_start.time
	}
	
	static def nightEndMorningStart() {
		def present_day_start = new GregorianCalendar()
		present_day_start.set(Calendar.HOUR_OF_DAY, 6)
		present_day_start.set(Calendar.MINUTE, 0)
		present_day_start.set(Calendar.SECOND, 0)
		present_day_start.time
	}
	
	static def morningEndDayStart() {
		def present_day_start = new GregorianCalendar()
		present_day_start.set(Calendar.HOUR_OF_DAY, 12)
		present_day_start.set(Calendar.MINUTE, 0)
		present_day_start.set(Calendar.SECOND, 0)
		present_day_start.time
	}
	
	static def dayEndEveningStart() {
		def present_day_start = new GregorianCalendar()
		present_day_start.set(Calendar.HOUR_OF_DAY, 18)
		present_day_start.set(Calendar.MINUTE, 0)
		present_day_start.set(Calendar.SECOND, 0)
		present_day_start.time
	}
	
	static def eveningEndNightStart() {
		def present_day_start = new GregorianCalendar()
		present_day_start.set(Calendar.HOUR_OF_DAY, 23)
		present_day_start.set(Calendar.MINUTE, 0)
		present_day_start.set(Calendar.SECOND, 0)
		present_day_start.time
	}
	  
	static def tomorrowDayStart() {
		presentDayStart() + 1
	}
	
	static def tomorrowDayEnd() {
		def tomorrow_day_end = new GregorianCalendar()
		tomorrow_day_end.add(Calendar.DAY_OF_YEAR, 1)
		tomorrow_day_end.set(Calendar.HOUR_OF_DAY, 23)
		tomorrow_day_end.set(Calendar.MINUTE, 59)
		tomorrow_day_end.set(Calendar.SECOND, 59)
		tomorrow_day_end.time
	}
	
	static def flat(list, flat_closure) {
		def pew = []
		list.each {pew.addAll(flat_closure(it))}
		return pew
	}
	
	static def intersectIntervals(l1, l2) {		
		if(l1.size() == 0 || l2.size() == 0) return []
		else {
			def fl1 = flat(l1, {it -> [[it[0], "l1_start"], [it[1], "l1_end"]]})
			def fl2 = flat(l2, {it -> [[it[0], "l2_start"], [it[1], "l2_end"]]})
			def l = (fl1 + fl2).sort {a1, a2 -> a1[0] - a2[0]}
			
			def buf = []
			def is_l1_start = false
			def is_l2_start = false
			def cur = [-1, ""]
			
			for(elem in l) {
				def cur_number  = cur[0]
				def cur_owner   = cur[1]
				
				def elem_number = elem[0]
				def elem_owner  = elem[1]
				
				switch(cur_owner) {
					case "l1_start":
						is_l1_start = true
						switch(elem_owner) {
							case "l1_end":
								if(is_l2_start) buf.push([cur_number, elem_number])
								break
							case "l2_end":
								buf.push([cur_number, elem_number])
								break
						}
						break
					case "l2_start":
						is_l2_start = true
						switch(elem_owner) {
							case "l2_end":
								if(is_l1_start) buf.push([cur_number, elem_number])
								break
							case "l1_end":
								buf.push([cur_number, elem_number])
								break
						}
						break
					case "l1_end":
						is_l1_start = false
						break
					case "l2_end":
						is_l2_start = false
						break
				}
				cur = elem
			}
			return buf
		}
	}
	  
	static def periodicity(periodicity_code) {
		switch(periodicity_code) {
		  case 0:  return 0;			  // once
		  case 1:  return 60*60*1000      // one hour
		  case 2:  return 24*60*60*1000   // one day
		  case 3:  return 7*24*60*60*1000 // one week
		  default: return 60*60*1000
		}
	}
	
	/**
	 * Возвращает массив интервалов ([[0, 1], [2, 3], [4, 5]]), начиная с момента from и до начала следующего дня 
	 * 
	 * @param from             - начало первого интервала
	 * @param to               - конец первого интервала
	 * @param periodicity_code - периодичность
	 * @return                 - массив интервалов
	 */
	static def intervals(from, to, periodicity_code) {
		def to_return = []
		
		def tomorrow = tomorrowDayStart().getTime()
		def period = periodicity(periodicity_code)
		
		if(period > 0) {
			def pewpew = (tomorrow - from)/period as Long
			def last_from = from + period*pewpew
			def last_to   = to   + period*pewpew
			def next_from = from
			def next_to   = to
			while(next_from <= last_from) {
				to_return.push([next_from, next_to])
				next_from += period
				next_to   += period
			}
		} else to_return.push([from, to])
		return to_return
	}
	
	/**
	 * Заполняет переданную аргументом intervals_data структуру интервалами, начиная с начала сегодняшнего по конец следующего дня
	 * 
	 * @param from             - начало первого интервала
	 * @param to               - конец первого интервала
	 * @param periodicity_code - периодичность
	 * @param intervals_data   - json array, который требуется заполнить
	 * @return
	 */
	static def intervalsJsonTodayTomorrow(from, to, periodicity_code, intervals_data) {
		def today = presentDayStart().getTime()
		def tomorrow = tomorrowDayEnd().getTime()
		def period = periodicity(periodicity_code)
		
		if(period > 0) {
			def pewpew = (tomorrow - from)/period as Integer
			def last_from = from + period*pewpew
			def last_to   = to   + period*pewpew
			def next_from = from
			def next_to   = to
			while(next_from <= last_from) {
				if(next_from >= today && next_from < tomorrow) {
					intervals_data.push(["start": next_from, "stop":  next_to])
				}
				next_from += period
				next_to   += period
			}
		} else if(from >= today && from < tomorrow) {
			intervals_data.push(["start": from, "stop":  to])
		}
	}
}
