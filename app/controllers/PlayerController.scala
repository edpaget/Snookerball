package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.{Players, Player}

object PlayersController extends Controller {
  val playerForm = Form(
    single(
      "name" -> nonEmptyText
    )
  )

  def index = Action {
    val players = Players.list

    Ok(views.html.players.index(players))
  }

  def show(id: Long) = Action {
    (Players.byId(id)) match {
      case Some(p: Player) => Ok(views.html.players.show(p))
      case None => NotFound("Player not Found")
    }
  }

  def edit(id: Long) = Action {
    (Players.byId(id)) match {
      case Some(p: Player) => Ok(views.html.players.edit(p, playerForm.fill(p.name)))
      case None => NotFound("Player not Found")
    }
  }

  def newPlayer = Action {
    Ok(views.html.players.newPlayer(playerForm))
  }

  def update(id: Long) = Action { implicit request =>
    val name = playerForm.bindFromRequest.get
    Players.update(id, name)
    Status(204)
  }

  def create = Action { implicit request =>
    val name = playerForm.bindFromRequest.get
    Players.create(name)
    Status(201)
  }

  def delete(id: Long) = Action {
    Players.delete(id)
    Status(202)
  }

}
