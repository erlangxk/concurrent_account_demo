package models

import play.api.db.DB
import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.api.Logger

object RaceCondition2 extends RC {

  def withdraw(id: Long, delta: Int) = DB.withTransaction { implicit conn =>
    val optb = SQL"select balance from accounts where id=$id for update".as(SqlParser.int(1).singleOpt)
    optb map { b =>
      if (delta > 0 && b >= delta) {
        val newBalance = b - delta
        SQL"update accounts set balance=${newBalance} where id=$id".executeUpdate()
        newBalance
      } else b
    }
  }

  def balance(id: Long) = DB.withConnection { implicit conn =>
    Logger.info("get balance in raceconditon v2")
    SQL"select balance from accounts where id=$id".as(SqlParser.int(1).singleOpt)
  }
}