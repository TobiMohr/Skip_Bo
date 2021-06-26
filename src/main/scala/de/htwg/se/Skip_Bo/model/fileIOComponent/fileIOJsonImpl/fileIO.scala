package de.htwg.se.Skip_Bo.model.fileIOComponent.fileIOJsonImpl

import de.htwg.se.Skip_Bo.model.CardComponent.Card
import de.htwg.se.Skip_Bo.model.GameComponent.GameInterface
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerBaseImpl.Player
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerInterface
import de.htwg.se.Skip_Bo.model.fileIOComponent.fileIOInterface
import play.api.libs.json._

import scala.io.Source

class fileIO extends fileIOInterface {
  override def load: GameInterface = {
    var game: GameInterface = null
    val source: String = Source.fromFile("game.json").getLines.mkString
    val json: JsValue = Json.parse(source)
  }

  override def save(game: GameInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("game.json"))
    pw.write(Json.prettyPrint(gameToJson(game)))
    pw.close
  }

  implicit val cardReads = Json.reads[Card]
  implicit val playerReads = Json.reads[PlayerInterface]

  def gameToJson(game: GameInterface) = {
    Json.obj(
      "game" -> Json.obj(
        "stacks" -> Json.arr(
          for (card <- 0 to game.stack(0).size) yield {
            Json.arr(
              "card" -> game.stack(0)(card)
            )
          }
        )

      )
    )
  }

}