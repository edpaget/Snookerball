name := "snookerball"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "0.6.0.1",
  cache
)     

play.Project.playScalaSettings
