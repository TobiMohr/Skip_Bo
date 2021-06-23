package controller

import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.controller.GameState.{GameState, IDLE, NEXT, PLACEHS, PLACES, PLACESS, START, WIN}
import de.htwg.se.Skip_Bo.model.{Card, Game, InvalidHandCard, InvalidMove, Player, Value}
import de.htwg.se.Skip_Bo.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.swing.MenuBar.NoMenuBar.listenTo

class ControllerSpec extends AnyWordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {

      val player1 = new Player("A",
        List(Card(Value.Seven), Card(Value.Eleven), Card(Value.Five), Card(Value.Twelve), Card(Value.Two)),
        List(List(Card(Value.Twelve)), List(Card(Value.Eleven)), List(Card(Value.Three)), List(Card(Value.Five))),
        List(Card(Value.Twelve), Card(Value.Joker), Card(Value.Twelve), Card(Value.Two), Card(Value.Four)))
      val player2 = new Player("B",
        List(Card(Value.Four), Card(Value.Ten), Card(Value.Eight), Card(Value.Seven), Card(Value.Seven)),
        List(List(Card(Value.Four), Card(Value.Six)), List(Card(Value.Eleven)), List(Card(Value.Ten)), List(Card(Value.Eight))),
        List(Card(Value.Two), Card(Value.Joker), Card(Value.Joker), Card(Value.Two), Card(Value.Seven)))

      val game = Game(List(List(Card(Value.Three), Card(Value.Two), Card(Value.One)),
        List(Card(Value.Two), Card(Value.Joker)),
        List(Card(Value.One)),
        List(Card(Value.Eleven), Card(Value.Ten), Card(Value.Nine), Card(Value.Eight), Card(Value.Seven), Card(Value.Six)
          , Card(Value.Five), Card(Value.Four), Card(Value.Joker), Card(Value.Two), Card(Value.One))),
        List(player1, player2),
        List(Card(Value.Three), Card(Value.Seven), Card(Value.Eight), Card(Value.Twelve), Card(Value.Joker),
          Card(Value.Five), Card(Value.Six), Card(Value.Nine), Card(Value.Eight)))
      val controller = new Controller(game)


      listenTo(controller)

      "pushCardHand and handle its undo/redo" in {
        controller.pushCardHand(0, 0, 1, false)
        controller.oldGameState should be(IDLE)
        controller.gameState should be(PLACES: GameState)
        controller.game.player(1).cards.size should be(4)
        controller.game.stack(0).head.toString should be("4")
        controller.undo
        controller.oldGameState should be(IDLE)
        controller.newGameState should be(PLACES)
        controller.gameState should be(IDLE: GameState)
        controller.game.player(1).cards.size should be(5)
        controller.game.stack(0).head.toString should be("3")
        controller.redo
        controller.oldGameState should be(IDLE)
        controller.newGameState should be(PLACES)
        controller.gameState should be(PLACES: GameState)
        controller.game.player(1).cards.size should be(4)
        controller.game.stack(0).head.toString should be("4")
      }
      "have a status text" in {
        controller.statusText should be("platziert Karte von Hand auf Ablagestapel")
      }
      "pushCardHand on helpstack and handle its undo/redo" in {
        controller.undo
        controller.oldGameState should be(IDLE)
        controller.gameState should be(IDLE: GameState)
        controller.pushCardHand(0, 0, 1, true)
        controller.oldGameState should be(IDLE)
        controller.gameState should be(NEXT: GameState)
        controller.game.player(1).helpstack(0).size should be(3)
        controller.game.player(1).cards.size should be(4)
        controller.undo
        controller.oldGameState should be(IDLE)
        controller.gameState should be(IDLE: GameState)
        controller.game.player(1).helpstack(0).size should be(2)
        controller.game.player(1).cards.size should be(5)
        controller.redo
        controller.oldGameState should be(IDLE)
        controller.gameState should be(NEXT: GameState)
        controller.game.player(1).helpstack(0).size should be(3)
        controller.game.player(1).cards.size should be(4)
      }
      "pushCardHelp and handle its undo/redo" in {
        controller.undo
        controller.oldGameState should be(IDLE)
        controller.gameState should be(IDLE: GameState)
        controller.pushCardHelp(0, 0, 1)
        controller.oldGameState should be(IDLE)
        controller.gameState should be(PLACEHS: GameState)
        controller.game.player(1).helpstack(0).size should be(1)
        controller.game.stack(0).size should be(4)
        controller.undo
        controller.oldGameState should be(IDLE)
        controller.gameState should be(IDLE: GameState)
        controller.game.player(1).helpstack(0).size should be(2)
        controller.game.stack(0).size should be(3)
        controller.redo
        controller.oldGameState should be(IDLE)
        controller.gameState should be(PLACEHS: GameState)
        controller.game.player(1).helpstack(0).size should be(1)
        controller.game.stack(0).size should be(4)
      }
      "pushCardPlayer and handle its undo/redo" in {
        controller.pushCardPlayer(2, 1)
        controller.oldGameState should be(PLACEHS)
        controller.gameState should be(PLACESS: GameState)
        controller.game.player(1).stack.size should be(4)
        controller.game.stack(2).size should be(2)
        controller.undo
        controller.oldGameState should be(PLACEHS)
        controller.gameState should be(PLACEHS: GameState)
        controller.game.player(1).stack.size should be(5)
        controller.game.stack(2).size should be(1)
        controller.redo
        controller.oldGameState should be(PLACEHS)
        controller.gameState should be(PLACESS: GameState)
        controller.game.player(1).stack.size should be(4)
        controller.game.stack(2).size should be(2)
      }
      "refill" in {
        controller.undo
        controller.pushCardPlayer(3, 0)
        game should be(game.refill(3))
        controller.game.refill(3)
        controller.game.stack(3).size should be(0)
        controller.undo
        controller.game.refill(2)
        controller.game.stack(2).size should be(1)
      }
      "have a String representation" in {
        controller.gameToString(0) should be(controller.game.toString(0))
      }


    }
    "new" should {
      val game = Game(List(List.empty, List.empty, List.empty, List.empty), List.empty, List.empty)
      val controller = new Controller(game)


      listenTo(controller)

      "get started" in {
        controller.startGame()
        controller.gameState should be(START)
      }
    }
  }
}