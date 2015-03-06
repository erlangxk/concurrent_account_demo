package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json

object Application extends Controller {

  def version(v: String) = v match {
    case "v0" => models.RaceCondition0
    case "v1" => models.RaceCondition1
    case "v2" => models.RaceCondition2
    case "v3" => models.RaceCondition3
    case _    => throw new IllegalArgumentException
  }

  def index = Action {
    Ok("Your new application is ready.")
  }

  def balance(v: String, id: Long) = Action {
    val result = version(v).balance(id)
    result.fold(Ok(Json.obj("status" -> 0, "msg" -> "account not found", "v" -> v))) {
      r => Ok(Json.obj("status" -> 1, "id" -> id, "balance" -> r, "v" -> v))
    }
  }

  def withdraw(v: String, id: Long, delta: Int) = Action {
    val result = version(v).withdraw(id, delta)
    val tn = Thread.currentThread().getName
    result.fold(Ok(Json.obj("status" -> 0, "msg" -> "account not found", "t" -> tn, "v" -> v))) {
      r => Ok(Json.obj("status" -> 1, "id" -> id, "balance" -> r, "t" -> tn, "v" -> v))
    }
  }

}