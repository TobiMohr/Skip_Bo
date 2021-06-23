package controller


import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.controller.PushCardHelpCommand
import de.htwg.se.Skip_Bo.model.CardComponent.{Card, Value}
import de.htwg.se.Skip_Bo.model.CardComponent
import de.htwg.se.Skip_Bo.model.GameComponent.GameImpl.Game
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerImpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PushCardHelpCommandSpec extends AnyWordSpec with Matchers {
  "A PushCardHelpCommand" when {
    "new" should {
      val player1 = new Player("A",
        List(Card(Value.Seven), Card(Value.Eleven), CardComponent.Card(Value.Five), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two)),
        List(List(CardComponent.Card(Value.Twelve)), List(CardComponent.Card(Value.Eleven)), List(CardComponent.Card(Value.Three)), List(CardComponent.Card(Value.Five))),
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
      val command1 = new PushCardHelpCommand(0, 0, 1, controller)
      val command2 = new PushCardHelpCommand(0, 3, 0, controller)

      "memento" in {
      }
      "newGame" in {
        command1.doStep
        controller.game.stack(0).size should be(4)
        controller.game.player(1).helpstack(0).size should be(1)
      }
      "back to old" in {
        command1.undoStep
        controller.game.stack(0).size should be(3)
        controller.game.player(1).helpstack(0).size should be(2)
      }
      "go back to newGame again" in {
        command1.redoStep
        controller.game.stack(0).size should be(4)
        controller.game.player(1).helpstack(0).size should be(1)
      }
      "refill" in {
        command1.undoStep
        command2.doStep
        controller.game.stack(3).size should be(0)
        controller.game.player(0).helpstack(0).size should be(0)
        controller.game.cardsCovered.size should be(21)
      }
      "undo refill correctly" in {
        command2.undoStep
        controller.game.stack(3).size should be(11)
        controller.game.player(0).helpstack(0).size should be(1)
        controller.game.cardsCovered.size should be(9)
      }
      "redo refill correctly" in {
        command2.redoStep
        controller.game.stack(3).size should be(0)
        controller.game.player(0).helpstack(0).size should be(0)
        controller.game.cardsCovered.size should be(21)
      }
    }
  }
}



