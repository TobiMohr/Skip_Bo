package controller

import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model.{Card, Game, Player, Value}
import de.htwg.se.Skip_Bo.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {

      val player1 = new Player("A",
        List(Card(Value.Seven), Card(Value.Eleven), Card(Value.Five), Card(Value.Twelve), Card(Value.Two)),
        List(List(Card(Value.Three)), List(Card(Value.Eleven)), List(Card(Value.Three)), List(Card(Value.Five))),
        List(Card(Value.Five), Card(Value.Joker), Card(Value.Twelve), Card(Value.Two), Card(Value.Four)))
      val player2 = new Player("B",
        List(Card(Value.Four), Card(Value.Ten), Card(Value.Eight), Card(Value.Seven), Card(Value.Seven)),
        List(List(Card(Value.Four), Card(Value.Six)), List(Card(Value.Eleven)), List(Card(Value.Ten)), List(Card(Value.Eight))),
        List(Card(Value.One), Card(Value.Joker), Card(Value.Joker), Card(Value.Two), Card(Value.Seven)))

      val game = Game(List(List(Card(Value.Three), Card(Value.Two), Card(Value.One)),
        List(Card(Value.Two), Card(Value.Joker)),
        List(Card(Value.One)),
        List(Card(Value.Four), Card(Value.Joker), Card(Value.Two), Card(Value.One))),
        List(player1, player2),
        List(Card(Value.Three), Card(Value.Seven), Card(Value.Eight), Card(Value.Twelve), Card(Value.Joker),
          Card(Value.Five), Card(Value.Six), Card(Value.Nine), Card(Value.Eight)))
      val controller = new Controller(game)

      "place a card" in {
        controller.pushCardHand(0, 0, 1, false) should be ()
        controller.game.player(1).cards.size should be(4)
        controller.game.stack(0).head.toString should be("4")
      }
      "undo" in {
        controller.pushCardHand(0, 0, 1, false)
        controller.undo
        controller.game.player(1).cards.size should be(5)
        controller.game.stack(0).head.toString should be("3")
      }
      "redo" in {
        controller.pushCardHand(0, 0, 1, false)
        controller.undo
        controller.redo
        controller.game.player(1).cards.size should be(4)
        controller.game.stack(0).head.toString should be("4")
      }
    }
  }
}
