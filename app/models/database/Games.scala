package models.database

import play.api.db.slick.Config.driver.simple._
import models.Game

private[models] class Games(tag: Tag) extends Table[Game](tag, "Games") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def firstPlayerId = column[Long]("first_player_id")
  def secondPlayerId = column[Long]("second_player_id")
  def firstPlayer = foreignKey("first_player_fk", firstPlayerId, TableQuery[Players])(_.id)
  def secondPlayer = foreignKey("second_Player_fk", secondPlayerId, TableQuery[Players])(_.id)
  def * = (id.?, firstPlayerId, secondPlayerId) <> (Game.tupled, Game.unapply _)
}

