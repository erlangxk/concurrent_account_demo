package coder.simon

import java.util.UUID

class Account(x: Int) {
  var balance = x

  def withdrawWithLog(delta: Int) = {
    val yyy = balance
    val result = withdraw(delta)
    println(s"before $yyy,delta is $delta, after $balance")
    result
  }

  def withdraw(delta: Int) = synchronized {
    if (delta > 0 && balance >= delta) {
      balance = balance - delta
      true
    } else false
  }
}

object RaceCondition {

  def createThreads = {
    val account = new Account(100)
    for (i <- 0 until 10) {
      new Thread(
        new Runnable {
          def run() {
            account.withdrawWithLog(3)
            Thread.sleep(35)
            account.withdrawWithLog(7)
          }
        }, s"t$i").start()
    }
    account
  }

  def main(args: Array[String]) = {
    val a = createThreads
    try {
      Thread.sleep(1000 * 3)
    } catch {
      case e: Exception =>
        println("exception when thread sleep")
    }
    println(s"eventually the balance is ${a.balance}")
  }

}