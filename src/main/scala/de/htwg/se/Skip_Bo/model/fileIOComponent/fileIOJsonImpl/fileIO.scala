
package de.htwg.se.Skip_Bo.model.fileIOComponent.fileIOJsonImpl

import de.htwg.se.Skip_Bo.model.CardComponent.{Card, Value}
import de.htwg.se.Skip_Bo.model.CardComponent.Value.Values
import de.htwg.se.Skip_Bo.model.GameComponent.GameBaseImpl.Game
import de.htwg.se.Skip_Bo.model.GameComponent.GameInterface
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerBaseImpl.Player
import de.htwg.se.Skip_Bo.model.fileIOComponent.fileIOInterface
import play.api.libs.json._

import scala.io.Source

class fileIO extends fileIOInterface {

  var stack1Size: Int = 0
  var stack2Size: Int = 0
  var stack3Size: Int = 0
  var stack4Size: Int = 0
  var p1h1: Int = 0
  var p1h2: Int = 0
  var p1h3: Int = 0
  var p1h4: Int = 0
  var p2h1: Int = 0
  var p2h2: Int = 0
  var p2h3: Int = 0
  var p2h4: Int = 0
  var p1c: Int = 0
  var p2c: Int = 0
  var p1s: Int = 0
  var p2s: Int = 0
  var cC: Int = 0

  var stack1: List[Card] = List.empty
  var stack2: List[Card] = List.empty
  var stack3: List[Card] = List.empty
  var stack4: List[Card] = List.empty
  var hs1p1: List[Card] = List.empty
  var hs2p1: List[Card] = List.empty
  var hs3p1: List[Card] = List.empty
  var hs4p1: List[Card] = List.empty
  var hs1p2: List[Card] = List.empty
  var hs2p2: List[Card] = List.empty
  var hs3p2: List[Card] = List.empty
  var hs4p2: List[Card] = List.empty
  var sp1: List[Card] = List.empty
  var sp2: List[Card] = List.empty
  var cp1: List[Card] = List.empty
  var cp2: List[Card] = List.empty
  var cover: List[Card] = List.empty



  override def load: GameInterface = {
    var game: GameInterface = Game(List(List.empty, List.empty, List.empty, List.empty), List.empty, List.empty)
    val source: String = Source.fromFile("game.json").getLines.mkString
    val json: JsValue = Json.parse(source)

    for (index <- 0 until stack1Size){
      val cardtmp = getCard(json, "card", index)
      stack1 = cardtmp +: stack1
    }
    stack1 = stack1.reverse

    for (index <- 0 until stack2Size){
      val cardtmp = getCard(json, "card2", index)
      stack2 = cardtmp +: stack2
    }
    stack2 = stack2.reverse

    for (index <- 0 until stack3Size){
      val cardtmp = getCard(json, "card3", index)
      stack3 = cardtmp +: stack3
    }
    stack3 = stack3.reverse

    for (index <- 0 until stack4Size){
      val cardtmp = getCard(json, "card4", index)
      stack4 = cardtmp +: stack4
    }
    stack4 = stack4.reverse

    for (index <- 0 until p1c){
      val cardtmp = getCard(json, "card5", index)
      cp1 = cardtmp +: cp1
    }
    cp1 = cp1.reverse

    for (index <- 0 until p1h1){
      val cardtmp = getCard(json, "card6", index)
      hs1p1 = cardtmp +: hs1p1
    }
    hs1p1 = hs1p1.reverse

    for (index <- 0 until p1h2){
      val cardtmp = getCard(json, "card7", index)
      hs2p1 = cardtmp +: hs2p1
    }
    hs2p1 = hs2p1.reverse

    for (index <- 0 until p1h3){
      val cardtmp = getCard(json, "card8", index)
      hs3p1 = cardtmp +: hs3p1
    }
    hs3p1 = hs3p1.reverse

    for (index <- 0 until p1h4){
      val cardtmp = getCard(json, "card9", index)
      hs4p1 = cardtmp +: hs4p1
    }
    hs4p1 = hs4p1.reverse

    for (index <- 0 until p1s){
      val cardtmp = getCard(json, "card10", index)
      sp1 = cardtmp +: sp1
    }
    sp1 = sp1.reverse

    val p1n = (json \\ "name1").toString()


    val pl1 = Player(p1n, cp1, List(hs1p1, hs2p1, hs3p1, hs4p1), sp1)

    for (index <- 0 until p2c){
      val cardtmp = getCard(json, "card11", index)
      cp2 = cardtmp +: cp2
    }
    cp2 = cp2.reverse

    for (index <- 0 until p2h1){
      val cardtmp = getCard(json, "card12", index)
      hs1p2 = cardtmp +: hs1p2
    }
    hs1p2 = hs1p2.reverse

    for (index <- 0 until p2h2){
      val cardtmp = getCard(json, "card13", index)
      hs2p2 = cardtmp +: hs2p2
    }
    hs2p2 = hs2p2.reverse

    for (index <- 0 until p2h3){
      val cardtmp = getCard(json, "card14", index)
      hs3p2 = cardtmp +: hs3p2
    }
    hs3p2 = hs3p2.reverse

    for (index <- 0 until p2h4){
      val cardtmp = getCard(json, "card15", index)
      hs4p2 = cardtmp +: hs4p2
    }
    hs4p2 = hs4p2.reverse

    for (index <- 0 until p2s){
      val cardtmp = getCard(json, "card16", index)
      sp2 = cardtmp +: sp2
    }
    sp2 = sp2.reverse

    val p2n = (json \\ "name2").toString()

    val pl2 = Player(p2n, cp2, List(hs1p2, hs2p2, hs3p2, hs4p2), sp2)

    for (index <- 0 until cC){
      val cardtmp = getCard(json, "card17", index)
      cover = cardtmp +: cover
    }
    cover = cover.reverse

    game = Game(List(stack1, stack2, stack3, stack4), List(pl1, pl2), cover)
    game
  }

  def getCard(json: JsValue, s: String, index:Int): Card = {
    var string: String = ""
    val card = (json \\ s)(index)
    string = (card \ "Type").as[String]
    val cardtmp: Card = Card(stringToValue(string))
    cardtmp
  }

  implicit val cardWrites = new Writes[String] {
    def writes(card: String) = Json.obj(
      "Type" -> card
    )
  }

  def stringToValue(s: String): Values = {
    s match {
      case "1" => Value.One
      case "2" => Value.Two
      case "3" => Value.Three
      case "4" => Value.Four
      case "5" => Value.Five
      case "6" => Value.Six
      case "7" => Value.Seven
      case "8" => Value.Eight
      case "9" => Value.Nine
      case "10" => Value.Ten
      case "11" => Value.Eleven
      case "12" => Value.Twelve
      case "J" => Value.Joker
    }
  }

  override def save(game: GameInterface): Unit = {
    import java.io._
    saveData(game)
    val pw = new PrintWriter(new File("game.json"))
    pw.write(Json.prettyPrint(gameToJson(game)))
    pw.close
  }

  def saveData(game: GameInterface): Unit = {
    stack1Size = game.stack(0).size
    stack2Size = game.stack(1).size
    stack3Size = game.stack(2).size
    stack4Size = game.stack(3).size
    p1h1 = game.player(0).helpstack(0).size
    p1h2 = game.player(0).helpstack(1).size
    p1h3 = game.player(0).helpstack(2).size
    p1h4 = game.player(0).helpstack(3).size
    p2h1 = game.player(1).helpstack(0).size
    p2h2 = game.player(1).helpstack(1).size
    p2h3 = game.player(1).helpstack(2).size
    p2h4 = game.player(1).helpstack(3).size
    p1c = game.player(0).cards.size
    p2c = game.player(1).cards.size
    p1s = game.player(0).stack.size
    p2s = game.player(1).stack.size
    cC = game.cardsCovered.size

  }

  def gameToJson(game: GameInterface):JsObject = {
    val player1 = game.player(0)
    val player2 = game.player(1)
    Json.obj (
      "stacks" -> Json.obj(
        "stack1" -> Json.toJson(
          for (card <- game.stack(0).indices) yield {
            Json.obj(
              "card" -> game.stack(0)(card).toString
            )
          }
        ),
          "stack2" -> Json.toJson(
            for (card <- game.stack(1).indices) yield {
              Json.obj(
                "card2" -> game.stack(1)(card).toString
              )
            }
          ),
        "stack3" -> Json.toJson(
          for (card <- game.stack(2).indices) yield {
            Json.obj(
              "card3" -> game.stack(2)(card).toString
            )
          }
        ),
        "stack4" -> Json.toJson(
          for (card <- game.stack(3).indices) yield {
            Json.obj(
              "card4" -> game.stack(3)(card).toString
            )
          }
        )
      ), "players" -> Json.obj(
      "p1" -> Json.obj(
        "name1" -> Json.toJson( player1.name ),
        "cards" -> Json.toJson(
          for (card <- player1.cards.indices) yield {
            Json.obj(
              "card5" -> player1.cards(card).toString
            )
          }
        ),
        "helpstacks" -> Json.obj(
          "hstack1" -> Json.toJson(
            for (card <- player1.helpstack(0).indices) yield {
              Json.obj(
                "card6" -> player1.helpstack(0)(card).toString
              )
            }
          ),
          "hstack2" -> Json.toJson(
            for (card <- player1.helpstack(1).indices) yield {
              Json.obj(
                "card7" -> player1.helpstack(1)(card).toString
              )
            }
          ),
          "hstack3" -> Json.toJson(
            for (card <- player1.helpstack(2).indices) yield {
              Json.obj(
                "card8" -> player1.helpstack(2)(card).toString
              )
            }
          ),
          "hstack4" -> Json.toJson(
            for (card <- player1.helpstack(3).indices) yield {
              Json.obj(
                "card9" -> player1.helpstack(3)(card).toString
              )
            }
          )
        ),
        "pstack" -> Json.toJson(
          for (card <- player1.stack.indices) yield {
            Json.obj(
              "card10" -> player1.stack(card).toString
            )
          }
        )
      ),
        "p2" -> Json.obj(
          "name2" -> Json.toJson(player2.name),
          "cards" -> Json.toJson(
            for (card <- player2.cards.indices) yield {
              Json.obj(
                "card11" -> player2.cards(card).toString
              )
            }
          ),
          "helpstacks" -> Json.obj(
            "hstack1" -> Json.toJson(
              for (card <- player2.helpstack(0).indices) yield {
                Json.obj(
                  "card12" -> player2.helpstack(0)(card).toString
                )
              }
            ),
            "hstack2" -> Json.toJson(
              for (card <- player2.helpstack(1).indices) yield {
                Json.obj(
                  "card13" -> player2.helpstack(1)(card).toString
                )
              }
            ),
            "hstack3" -> Json.toJson(
              for (card <- player2.helpstack(2).indices) yield {
                Json.obj(
                  "card14" -> player2.helpstack(2)(card).toString
                )
              }
            ),
            "hstack4" -> Json.toJson(
              for (card <- player1.helpstack(3).indices) yield {
                Json.obj(
                  "card15" -> player1.helpstack(3)(card).toString
                )
              }
            )
          ),
          "pstack" -> Json.toJson(
            for (card <- player2.stack.indices) yield {
              Json.obj(
                "card16" -> player2.stack(card).toString
              )
            }
          )
        )
    ), "cardsCovered" -> Json.toJson(
        for (card <- game.cardsCovered.indices) yield {
          Json.obj(
            "card17" -> game.cardsCovered(card).toString
          )
        }
      )
    )
  }
}

