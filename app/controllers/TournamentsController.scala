package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.{Tournament, Tournaments}

object TournamentsController extends Controller {

  val tourneyForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "name" -> nonEmptyText,
      "displayName" -> nonEmptyText,
      "tourneyType" -> nonEmptyText
    )(Tournament.apply)(Tournament.unapply)
  )

  def index = Action {
    Ok(views.html.tournaments.index(Tournaments.list))
  }

  def show(name: String) = Action { 
    (Tournaments.byId(name)) match {
      case Some(t: Tournament) => Ok(views.html.tournaments.show(t))
      case None => NotFound("Tournament Not Found")
    }
  }

  def edit(name: String) = Action {
    (Tournaments.byId(name)) match {
      case Some(t: Tournament) => Ok(views.html.tournaments.edit(t, tourneyForm.fill(t)))
      case None => NotFound("Tournament Not Found")
    }
  }

  def newTournament = Action {
    Ok(views.html.tournaments.newTournament(tourneyForm))
  }

  def update(name: String) = Action { implicit request =>
    val t = tourneyForm.bindFromRequest.get
    Tournaments.update(name, t)
    Status(204)
  }

  def create = Action { implicit request =>
    val name = tourneyForm.bindFromRequest.get
    Tournaments.create(name)
    Status(201)
  }

  def delete(name: String) = Action {
    Tournaments.delete(name)
    Status(202)
  }
}
