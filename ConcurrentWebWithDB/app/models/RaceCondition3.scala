package models

import play.api.db.DB
import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.api.Logger

object RaceCondition3 extends RC{

  def withdraw(id: Long, delta: Int) = DB.withTransaction { implicit conn =>
    val sql = SQL"call proc_withdraw($id,$delta)"
    sql.executeQuery().as(SqlParser.int(1).singleOpt)
  }

  def balance(id: Long) = DB.withConnection { implicit conn =>
    Logger.info("get balance in raceconditon v3")
    SQL"select balance from accounts where id=$id".as(SqlParser.int(1).singleOpt)
  }

}