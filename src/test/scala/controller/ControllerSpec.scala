package controller

import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model.{Card, Game, Player, Value}
import de.htwg.se.Skip_Bo.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should{

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
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = {updated = true; updated}

        override def error(throwable: Throwable): Unit = print("fehler")
      }
      controller.add(observer)
      "notify the observer after starting a game" in {
        controller.startGame()
        observer.updated should be(true)
      }
      "notify its observer after someone puts a card from hand on a helpstack" in {
        controller.pushCardHand(0, 1, 0, true)
        observer.updated should be(true)
      }
      "notify its observer after someone puts a card from hand on a stack" in {
        controller.pushCardHand(0, 0, 1, false)
        observer.updated should be(true)
        }
      "notify its observer after someone puts a card from helpstack on a stack" in {
        controller.pushCardHelp(0,0,1)
        observer.updated should be(true)
      }
      "notify its observer after someone puts a card from playerstack on a stack" in {
        controller.pushCardPlayer(0, 1)
        observer.updated should be(true)
      }
            "print a helpoverview" in {
        controller.hilfe should be("""---------Hilfe-----------
                                     ||       Handkarten      ||
                                     |
                                     || H1 | H2 | H3 | H4 | S ||
                                     |
                                     ||  A1 |  A2 |  A3 |  A4 ||
                                     |-------------------------
                                     |ph i j true = legt Handkarte(j) auf Hilfestapel(i) vom Spieler
                                     |ph i j false = legt Handkarte(j) auf Ablagestapel(i) vom Spieler
                                     |ps i = legt Karte von Spielerstapen vom Spieler  auf Ablagestapel(i)
                                     |philfe i j = Spieler legt Karte von Hilfestapel(i) auf Ablagestapel(j)
                                     |"""
          .stripMargin)
      }


    }
  }
}
