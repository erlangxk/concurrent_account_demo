package models

class Account(val x: Int) {
  private var balance = x

  def withdraw(delta: Int) = {
    if (delta > 0 && balance >= delta) {
      balance = balance - delta
      true
    } else false
  }

  def getBalance = balance
}

object RaceCondition {
  val a = new Account(10000)
  
  def withdraw(delta: Int) = {
    val r = a.withdraw(delta)
    (r, a.getBalance, Thread.currentThread().getName)
  }

  def getBalance = a.getBalance
}