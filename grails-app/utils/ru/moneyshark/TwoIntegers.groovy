package ru.moneyshark

class TwoIntegers {
	Integer int1 = 0
	Integer int2 = 0
	
	def plus(Integer i) {int1 += i}
	def minus(Integer i) {int1 -= i}
	def multiply(Integer i) {int1 *= i}
	def div(Integer i) {int1 /= i}
	
	String toString() {
		int1+""
	}
}
