package controller


import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.controller.PushCardHandCommand
import de.htwg.se.Skip_Bo.model.{Card, Game, Player, Value}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PushCardHandCommandSpec extends AnyWordSpec with Matchers {
  "A PushCardHandCommand" when {
    "new" should {
      val player1 = new Player("A",
        List(Card(Value.Seven), Card(Value.Eleven), Card(Value.Five), Card(Value.Twelve), Card(Value.Two)),
        List(List(Card(Value.Three)), List(Card(Value.Eleven)), List(Card(Value.Three)), List(Card(Value.Five))),
        List(Card(Value.Five), Card(Value.Joker), Card(Value.Twelve), Card(Value.Two), Card(Value.Four)))
      val player2 = new Player("B",
        List(Card(Value.Four), Card(Value.Ten), Card(Value.Eight), Card(Value.Seven), Card(Value.Seven)),
        List(List(Card(Value.Four),Card(Value.Six)), List(Card(Value.Eleven)), List(Card(Value.Ten)), List(Card(Value.Eight))),
        List(Card(Value.One), Card(Value.Joker), Card(Value.Joker), Card(Value.Two), Card(Value.Seven)))

      val game = Game(List(List(Card(Value.Three), Card(Value.Two), Card(Value.One)),
        List(Card(Value.Two), Card(Value.Joker)),
        List(Card(Value.One)),
        List(Card(Value.Four), Card(Value.Joker), Card(Value.Two), Card(Value.One))),
        List(player1, player2),
        List(Card(Value.Three), Card(Value.Seven), Card(Value.Eight), Card(Value.Twelve), Card(Value.Joker),
          Card(Value.Five), Card(Value.Six), Card(Value.Nine), Card(Value.Eight)))
      val controller = new Controller(game)
      val command = new PushCardHandCommand(0, 0, 1, false, controller)

      "memento" in {
        command.memento.toString(1) should be()
      }
      "newGame" in {
        command.doStep
        command.memento.toString(1) should be()
      }
      "back to old" in {
        command.doStep
        command.undoStep
        controller.game.toString(1) should be()
      }
      "go back to newGame again" in {
        command.doStep
        command.undoStep
        command.redoStep
        controller.game.toString(1) should be()
      }
    }
  }


}