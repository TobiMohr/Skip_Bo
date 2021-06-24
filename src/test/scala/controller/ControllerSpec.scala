package controller

import de.htwg.se.Skip_Bo.controller.controllerComponent.GameState.{GameState, IDLE, NEXT, PLACEHS, PLACES, PLACESS, START, WIN}
import de.htwg.se.Skip_Bo.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.Skip_Bo.model.CardComponent
import de.htwg.se.Skip_Bo.model.CardComponent.{Card, Value}
import de.htwg.se.Skip_Bo.model.GameComponent.GameBaseImpl.Game
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerBaseImpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.swing.MenuBar.NoMenuBar.listenTo

class ControllerSpec extends AnyWordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {

      val player1 = new Player("A",
        List(CardComponent.Card(Value.Seven), CardComponent.Card(Value.Eleven), CardComponent.Card(Value.Five), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two)),
        List(List(CardComponent.Card(Value.Twelve)), List(CardComponent.Card(Value.Eleven)), List(CardComponent.Card(Value.Three)), List(CardComponent.Card(Value.Five))),
        List(CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two), CardComponent.Card(Value.Four)))
      val player2 = new Player("B",
        List(CardComponent.Card(Value.Four), CardComponent.Card(Value.Ten), CardComponent.Card(Value.Eight), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Seven)),
        List(List(CardComponent.Card(Value.Four), CardComponent.Card(Value.Six)), List(CardComponent.Card(Value.Eleven)), List(CardComponent.Card(Value.Ten)), List(CardComponent.Card(Value.Eight))),
        List(CardComponent.Card(Value.Two), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Two), CardComponent.Card(Value.Seven)))

      val game = Game(List(List(CardComponent.Card(Value.Three), CardComponent.Card(Value.Two), CardComponent.Card(Value.One)),
        List(CardComponent.Card(Value.Two), CardComponent.Card(Value.Joker)),
        List(CardComponent.Card(Value.One)),
        List(CardComponent.Card(Value.Eleven), CardComponent.Card(Value.Ten), CardComponent.Card(Value.Nine), CardComponent.Card(Value.Eight), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Six)
          , CardComponent.Card(Value.Five), CardComponent.Card(Value.Four), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Two), CardComponent.Card(Value.One))),
        List(player1, player2),
        List(CardComponent.Card(Value.Three), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Eight), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Joker),
          CardComponent.Card(Value.Five), CardComponent.Card(Value.Six), CardComponent.Card(Value.Nine), CardComponent.Card(Value.Eight)))
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
        controller.startGame(5)
        controller.gameState should be(START)
      }
    }
  }
}