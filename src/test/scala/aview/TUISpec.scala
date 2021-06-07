package aview

import de.htwg.se.Skip_Bo.aview.TUI
import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model.{Card, Game, Player, Value}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUISpec extends AnyWordSpec with Matchers {
  "A TUI " should {
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
    val tui = new TUI(controller)

    "do nothing on input q" in {
      tui.processInputLine("q")
    }
    "start a new game" in {
      tui.processInputLine("s")
      controller.gameToString should be(controller.gameToString)
    }
    "place a card on a helpstack" in {
      tui.processInputLine("ph 0 1 0 true")
      controller.game.player should be (List(controller.game.player(0), controller.game.player(1)))
    }
    "place a card on a stack" in {
      tui.processInputLine("ph 0 1 0 false")
      controller.game.player should be (List(controller.game.player(0), controller.game.player(1)))
    }
    "place a card from playerstack on stack" in {
      tui.processInputLine("ps 3 0")
      controller.game.player should be (List(controller.game.player(0), controller.game.player(1)))
    }
    "place a card from helpstack on stack" in {
      tui.processInputLine("philfe 0 0 1")
      controller.game.player should be (List(controller.game.player(0), controller.game.player(1)))
    }
    "get help" in {
      tui.processInputLine("help")
      controller.hilfe should be("""---------Hilfe-----------
                                   ||       Handkarten      ||
                                   |
                                   || H1 | H2 | H3 | H4 | S ||
                                   |
                                   ||  A1 |  A2 |  A3 |  A4 ||
                                   |-------------------------
                                   |ph i j n true = legt Handkarte(j) auf Ablegestapel(i) von Spieler n
                                   |ph i j n false = legt Handkarte(j) auf Hilfestapel(i) von Spieler n
                                   |ps i n = legt Karte von Spielerstapen von Spieler n  auf Ablagestapel(i)
                                   |philfe i j n = Spieler n legt Karte von Hilfestapel(i) auf Ablagestapel(j)
                                   |end n = Spieler n beendet seinen Zug und nimmt auf bis er 5 Karten auf der Hand hat
                                   |"""
        .stripMargin)
    }
  }

}
