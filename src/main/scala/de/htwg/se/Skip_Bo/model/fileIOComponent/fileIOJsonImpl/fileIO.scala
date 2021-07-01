
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
    loadStacks(json)

    val pl1 = loadPlayer1(json)
    val pl2 = loadPlayer2(json)

    for (index <- 0 until cC){
      cover = addCard(json, "card17", index, cover)
    }
    cover = cover.reverse

    game = Game(List(stack1, stack2, stack3, stack4), List(pl1, pl2), cover)
    game
  }

    def loadStacks(json: JsValue): Unit = {
      for (index <- 0 until stack1Size){
        stack1 = addCard(json, "card", index, stack1)
      }
      stack1 = stack1.reverse

      for (index <- 0 until stack2Size){
        stack2 = addCard(json, "card2", index, stack2)
      }
      stack2 = stack2.reverse

      for (index <- 0 until stack3Size){
        stack3 = addCard(json, "card3", index, stack3)
      }
      stack3 = stack3.reverse

      for (index <- 0 until stack4Size){
        stack4 = addCard(json, "card4", index, stack4)
      }
      stack4 = stack4.reverse
    }

    def loadPlayer1(json: JsValue): Player = {
      for (index <- 0 until p1c){
        cp1 = addCard(json, "card5", index, cp1)
      }
      cp1 = cp1.reverse

      for (index <- 0 until p1h1){
        hs1p1 = addCard(json, "card6", index, hs1p1)
      }
      hs1p1 = hs1p1.reverse

      for (index <- 0 until p1h2){
        hs2p1 = addCard(json, "card7", index, hs2p1)
      }
      hs2p1 = hs2p1.reverse

      for (index <- 0 until p1h3){
        hs3p1 = addCard(json, "card8", index, hs3p1)
      }
      hs3p1 = hs3p1.reverse

      for (index <- 0 until p1h4){
        hs4p1 = addCard(json, "card9", index, hs4p1)
      }
      hs4p1 = hs4p1.reverse

      for (index <- 0 until p1s){
        sp1 = addCard(json, "card10", index, sp1)
      }
      sp1 = sp1.reverse

      val p1n = (json \\ "name1")(0)
      val p1name = (p1n \"Type").as[String]

      val pl1 = Player(p1name, cp1, List(hs1p1, hs2p1, hs3p1, hs4p1), sp1)
      pl1
    }

    def loadPlayer2(json: JsValue): Player = {
      for (index <- 0 until p2c){
        cp2 = addCard(json, "card11", index, cp2)
      }
      cp2 = cp2.reverse

      for (index <- 0 until p2h1){
        hs1p2 = addCard(json, "card12", index, hs1p2)
      }
      hs1p2 = hs1p2.reverse

      for (index <- 0 until p2h2){
        hs2p2 = addCard(json, "card13", index, hs2p2)
      }
      hs2p2 = hs2p2.reverse

      for (index <- 0 until p2h3){
        hs3p2 = addCard(json, "card14", index, hs3p2)
      }
      hs3p2 = hs3p2.reverse

      for (index <- 0 until p2h4){
        hs4p2 = addCard(json, "card15", index, hs4p2)
      }
      hs4p2 = hs4p2.reverse

      for (index <- 0 until p2s){
        sp2 = addCard(json, "card16", index, sp2)
      }
      sp2 = sp2.reverse

      val p2n = (json \\ "name2")(0)
      val p2name = (p2n \ "Type").as[String]

      val pl2 = Player(p2name, cp2, List(hs1p2, hs2p2, hs3p2, hs4p2), sp2)
      pl2
    }

  def addCard(json: JsValue, s: String, index:Int, stack: List[Card]): List[Card] = {
    var string: String = ""
    val card = (json \\ s)(index)
    string = (card \ "Card").as[String]
    val cardtmp: Card = Card(stringToValue(string))
    val newStack = cardtmp +: stack
    newStack
  }

  implicit val cardWrites = new Writes[String] {
    def writes(card: String): JsObject = Json.obj(
      "Card" -> card
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
              for (card <- player2.helpstack(3).indices) yield {
                Json.obj(
                  "card15" -> player2.helpstack(3)(card).toString
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

