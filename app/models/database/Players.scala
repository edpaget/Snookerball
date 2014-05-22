package models.database

import play.api.db.slick.Config.driver.simple._
import models.Player

private[models] class Players(tag: Tag) extends Table[Player](tag, "Players") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def * = (id.?, name) <> (Player.tupled, Player.unapply _)
  def nameIdx = index("idx_name", name, unique = true)
}
