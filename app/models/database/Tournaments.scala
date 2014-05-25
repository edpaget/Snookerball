package models.database

import play.api.db.slick.Config.driver.simple._
import models.Tournament

private[models] class Tournaments(tag: Tag) extends Table[Tournament](tag, "Tournaments") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def displayName = column[String]("display_name")
  def tourneyType = column[String]("tourney_type")
  def * = (id.?, name, displayName, tourneyType) <> (Tournament.tupled, Tournament.unapply _)
  def nameIdx = index("idx_name", name, unique = true)
}
