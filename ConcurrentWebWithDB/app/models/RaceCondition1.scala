package models

import play.api.db.DB
import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.api.Logger

trait RC {
  def withdraw(id: Long, delta: Int): Option[Int]
  def balance(id: Long): Option[Int]
}

object RaceCondition1 extends RC {

  def withdraw(id: Long, delta: Int) = {
    balance(id) flatMap { b =>
      if (delta > 0 && b >= delta) {
        update(id, b - delta)
        balance(id)
      } else Some(b)
    }
  }

  def update(id: Long, newBalance: Int) = DB.withConnection { implicit conn =>
    SQL"update accounts set balance=$newBalance where id=$id".executeUpdate()
  }

  def balance(id: Long) = DB.withConnection { implicit conn =>
    Logger.info("get balance in raceconditon v1")
    SQL"select balance from accounts where id=$id".as(SqlParser.int(1).singleOpt)
  }
}