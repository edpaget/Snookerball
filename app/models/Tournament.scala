package models

import models.database.Tournaments
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

case class Tournament(id: Option[Long], name: String, displayName: String, tourneyType: String)

object Tournaments {

  lazy val tournaments = TableQuery[Tournaments]

  def list: List[Tournament] = DB withSession { implicit session =>
    tournaments.list
  }

  def byId(name: String): Option[Tournament] = DB withSession { implicit session =>
    tournaments.filter{ _.name === name }.firstOption
  }

  def create(t: Tournament) = DB withSession { implicit session =>
    tournaments.insert(t)
  }

  def update(name: String, t: Tournament) = DB withSession { implicit session =>
    tournaments.filter{ _.name === name }.update(t)
  }

  def delete(name: String) = DB withSession { implicit session => 
    tournaments.filter{ _.name === name}.delete
  }
}
