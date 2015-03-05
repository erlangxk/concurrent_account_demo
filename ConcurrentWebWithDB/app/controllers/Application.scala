package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json

object Application extends Controller {

  def index = Action {
    Ok("Your new application is ready.")
  }

  def balance1(id: Long) = Action {
    val result = models.RaceCondition1.balance(id)
    result.fold(Ok(Json.obj("status" -> 0, "msg" -> "account not found"))) {
      r => Ok(Json.obj("status" -> 1, "id" -> id, "balance" -> r))
    }
  }

  def balance2(id: Long) = Action {
    val result = models.RaceCondition2.balance(id)
    result.fold(Ok(Json.obj("status" -> 0, "msg" -> "account not found"))) {
      r => Ok(Json.obj("status" -> 1, "id" -> id, "balance" -> r))
    }
  }

  def balance3(id: Long) = Action {
    val result = models.RaceCondition3.balance(id)
    result.fold(Ok(Json.obj("status" -> 0, "msg" -> "account not found"))) {
      r => Ok(Json.obj("status" -> 1, "id" -> id, "balance" -> r))
    }
  }

  def withdraw1(id: Long, delta: Int) = Action {
    val tn = Thread.currentThread().getName
    val result = models.RaceCondition1.withdraw(id, delta)
    result.fold(Ok(Json.obj("status" -> 0, "msg" -> "account not found", "t" -> tn))) {
      r => Ok(Json.obj("status" -> 1, "id" -> id, "balance" -> r, "t" -> tn))
    }
  }

  def withdraw2(id: Long, delta: Int) = Action {
    val tn = Thread.currentThread().getName
    val result = models.RaceCondition2.withdraw(id, delta)
    result.fold(Ok(Json.obj("status" -> 0, "msg" -> "account not found", "t" -> tn))) {
      r => Ok(Json.obj("status" -> 1, "id" -> id, "balance" -> r, "t" -> tn))
    }
  }

  def withdraw3(id: Long, delta: Int) = Action {
    val tn = Thread.currentThread().getName
    val result = models.RaceCondition3.withdraw(id, delta)
    result.fold(Ok(Json.obj("status" -> 0, "msg" -> "account not found", "t" -> tn))) {
      r => Ok(Json.obj("status" -> 1, "id" -> id, "balance" -> r, "t" -> tn))
    }
  }

}