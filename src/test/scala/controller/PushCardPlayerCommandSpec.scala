package controller

import de.htwg.se.Skip_Bo.controller.controllerComponent.controllerBaseImpl.{Controller, PushCardPlayerCommand}
import de.htwg.se.Skip_Bo.model.CardComponent
import de.htwg.se.Skip_Bo.model.CardComponent.{Card, Value}
import de.htwg.se.Skip_Bo.model.GameComponent.GameBaseImpl.Game
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerBaseImpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PushCardPlayerCommandSpec extends AnyWordSpec with Matchers {
  "A PushCardPlayerCommand" when {
    "new" should {
      val player1 = new Player("A",
        List(CardComponent.Card(Value.Seven), CardComponent.Card(Value.Eleven), CardComponent.Card(Value.Five), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two)),
        List(List(CardComponent.Card(Value.Twelve)), List(CardComponent.Card(Value.Eleven)), List(CardComponent.Card(Value.Three)), List(CardComponent.Card(Value.Five))),
        List(CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two), CardComponent.Card(Value.Four)))
      val player2 = new Player("B",
        List(CardComponent.Card(Value.Four), CardComponent.Card(Value.Ten), CardComponent.Card(Value.Eight), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Seven)),
        List(List(CardComponent.Card(Value.Four),CardComponent.Card(Value.Six)), List(CardComponent.Card(Value.Eleven)), List(CardComponent.Card(Value.Ten)), List(CardComponent.Card(Value.Eight))),
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
      val command1 = new PushCardPlayerCommand(2, 1, controller)
      val command2 = new PushCardPlayerCommand(3, 0, controller)

      "memento" in {
      }
      "newGame" in {
        command1.doStep
        controller.game.stack(2).size should be(2)
        controller.game.player(1).stack.head.toString should be("J")
        controller.game.player(1).stack.size should be(4)
      }
      "back to old" in {
        command1.undoStep
        controller.game.stack(2).size should be(1)
        controller.game.player(1).stack.head.toString should be("2")
        controller.game.player(1).stack.size should be(5)
      }
      "go back to newGame again" in {
        command1.redoStep
        controller.game.stack(2).size should be(2)
        controller.game.player(1).stack.head.toString should be("J")
        controller.game.player(1).stack.size should be(4)
      }
      "refill" in {
        command1.undoStep
        command2.doStep
        controller.game.stack(3).size should be(0)
        controller.game.player(0).stack.head.toString should be("J")
        controller.game.player(0).stack.size should be(4)
        controller.game.cardsCovered.size should be(21)
      }
      "undo refill" in {
        command2.undoStep
        controller.game.stack(3).size should be(11)
        controller.game.player(0).stack.head.toString should be("12")
        controller.game.player(0).stack.size should be(5)
        controller.game.cardsCovered.size should be(9)
      }
      "redo refill" in {
        command2.redoStep
        controller.game.stack(3).size should be(0)
        controller.game.player(0).stack.head.toString should be("J")
        controller.game.player(0).stack.size should be(4)
        controller.game.cardsCovered.size should be(21)
      }
    }
  }
}

