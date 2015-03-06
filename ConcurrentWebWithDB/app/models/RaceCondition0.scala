package models

class Account(val id: Long, val x: Int) {
  private var balance = x

  def withdraw(delta: Int) = {
    if (delta > 0 && balance >= delta) {
      balance = balance - delta
      true
    } else false
  }

  def getBalance = balance
}

object RaceCondition0 extends RC {
  val a = new Account(1L, 10000)
  val b = new Account(2L, 10000)
  val c = new Account(3L, 10000)

  val users = Map(a.id -> a, b.id -> b, c.id -> c)

  def withdraw(id: Long, delta: Int) = {
    users.get(id) map { u =>
      u.withdraw(delta)
      u.getBalance
    }
  }

  def balance(id: Long) = users.get(id) map {
    u => u.getBalance
  }
}