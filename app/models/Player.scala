package models

import models.database.Players
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

case class Player(id: Option[Long], name: String)

object Players {

  lazy val players = TableQuery[Players]

  def list: List[Player] =  DB withSession { implicit session =>
    players.list
  }

  def byId(id: Long): Option[Player] = DB withSession { implicit session =>
    players.filter{ _.id === id }.firstOption
  }

  def create(name: String) = DB withSession { implicit session =>
    val p = new Player(None, name)
    players.insert(p)
  }

  def delete(id: Long) = DB withSession { implicit session =>
    players.filter{ _.id === id}.delete
  }

  def update(id: Long, name: String) = DB withSession { implicit session =>
    players.filter{ _.id === id }.update(new Player(new Some(id), name))
  }
}
