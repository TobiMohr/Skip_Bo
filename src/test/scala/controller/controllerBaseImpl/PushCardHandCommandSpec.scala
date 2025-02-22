package controller.controllerBaseImpl

import de.htwg.se.Skip_Bo.controller.controllerComponent.controllerBaseImpl.{Controller, PushCardHandCommand}
import de.htwg.se.Skip_Bo.model.CardComponent
import de.htwg.se.Skip_Bo.model.CardComponent.Value
import de.htwg.se.Skip_Bo.model.GameComponent.GameBaseImpl.Game
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerBaseImpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PushCardHandCommandSpec extends AnyWordSpec with Matchers {
  "A PushCardHandCommand" when {
    "new" should {
      val player1 = new Player("A",
        List(CardComponent.Card(Value.Seven), CardComponent.Card(Value.Eleven), CardComponent.Card(Value.Five), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two)),
        List(List(CardComponent.Card(Value.Three)), List(CardComponent.Card(Value.Eleven)), List(CardComponent.Card(Value.Three)), List(CardComponent.Card(Value.Five))),
        List(CardComponent.Card(Value.Five), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two), CardComponent.Card(Value.Four)))
      val player2 = new Player("B",
        List(CardComponent.Card(Value.Four), CardComponent.Card(Value.Ten), CardComponent.Card(Value.Eight), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Seven)),
        List(List(CardComponent.Card(Value.Four),CardComponent.Card(Value.Six)), List(CardComponent.Card(Value.Eleven)), List(CardComponent.Card(Value.Ten)), List(CardComponent.Card(Value.Eight))),
        List(CardComponent.Card(Value.One), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Two), CardComponent.Card(Value.Seven)))

      val game = Game(List(List(CardComponent.Card(Value.Three), CardComponent.Card(Value.Two), CardComponent.Card(Value.One)),
        List(CardComponent.Card(Value.Two), CardComponent.Card(Value.Joker)),
        List(CardComponent.Card(Value.One)),
        List(CardComponent.Card(Value.Eleven), CardComponent.Card(Value.Ten), CardComponent.Card(Value.Nine), CardComponent.Card(Value.Eight), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Six)
          , CardComponent.Card(Value.Five), CardComponent.Card(Value.Four), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Two), CardComponent.Card(Value.One))),
        List(player1, player2),
        List(CardComponent.Card(Value.Three), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Eight), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Joker),
          CardComponent.Card(Value.Five), CardComponent.Card(Value.Six), CardComponent.Card(Value.Nine), CardComponent.Card(Value.Eight)))
      val controller = new Controller(game)
      val command1 = new PushCardHandCommand(0, 0, 1, false, controller)
      val command2 = new PushCardHandCommand(3, 3, 0, false, controller)
      val command3 = new PushCardHandCommand(0, 3, 0, true, controller)

      "newGame" in {
        command1.doStep
        controller.game.stack(0).size should be(4)
        controller.game.player(1).cards.size should be(4)
      }
      "back to old" in {
        command1.undoStep
        controller.game.stack(0).size should be(3)
        controller.game.player(1).cards.size should be(5)
      }
      "go back to newGame again" in {
        command1.redoStep
        controller.game.stack(0).size should be(4)
        controller.game.player(1).cards.size should be(4)
      }
      "refill?" in {
        command1.undoStep
        command2.doStep
        controller.game.stack(3).size should be(0)
        controller.game.cardsCovered.size should be(21)
      }
      "undo refill" in {
        command2.undoStep
        controller.game.stack(3).size should be(11)
        controller.game.cardsCovered.size should be(9)
      }
      "redo refill" in {
        command2.redoStep
        controller.game.stack(3).size should be(0)
        controller.game.cardsCovered.size should be(21)
      }
      "command3" in {
        command2.undoStep
        command3.doStep
        controller.game.player(0).cards.size should be(4)
      }
    }
  }


}
