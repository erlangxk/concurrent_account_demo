package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json

object Application extends Controller {

  def withdraw(delta: Int) = Action {
    val (ok, balance,tn) = models.RaceCondition.withdraw(delta)
    Ok(Json.obj("status" -> ok, "balance" -> balance,"thread"->tn))
  }

  def index = Action {
    Ok(Json.obj("balance" -> models.RaceCondition.getBalance))
  }

}