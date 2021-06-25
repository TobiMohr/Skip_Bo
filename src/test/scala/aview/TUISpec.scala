package aview

import de.htwg.se.Skip_Bo.aview.TUI
import de.htwg.se.Skip_Bo.controller.controllerComponent.GameState.{IDLE, PLACEHS, PLACESS, START}
import de.htwg.se.Skip_Bo.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.Skip_Bo.model.CardComponent
import de.htwg.se.Skip_Bo.model.CardComponent.{Card, Value}
import de.htwg.se.Skip_Bo.model.GameComponent.GameBaseImpl.Game
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerBaseImpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUISpec extends AnyWordSpec with Matchers {
  "A TUI " should {
    val player1 = new Player("A",
      List(CardComponent.Card(Value.Seven), CardComponent.Card(Value.Eleven), CardComponent.Card(Value.Five), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two)),
      List(List(CardComponent.Card(Value.Three)), List(CardComponent.Card(Value.Eleven)), List(CardComponent.Card(Value.Three)), List(CardComponent.Card(Value.Five))),
      List(CardComponent.Card(Value.Five), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two), CardComponent.Card(Value.Four)))
    val player2 = new Player("B",
      List(CardComponent.Card(Value.Four), CardComponent.Card(Value.Ten), CardComponent.Card(Value.Eight), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Seven)),
      List(List(CardComponent.Card(Value.Four), CardComponent.Card(Value.Six)), List(CardComponent.Card(Value.Eleven)), List(CardComponent.Card(Value.Ten)), List(CardComponent.Card(Value.Eight))),
      List(CardComponent.Card(Value.One), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Two), CardComponent.Card(Value.Seven)))

    val game = Game(List(List(CardComponent.Card(Value.Three), CardComponent.Card(Value.Two), CardComponent.Card(Value.One)),
      List(CardComponent.Card(Value.Two), CardComponent.Card(Value.Joker)),
      List(CardComponent.Card(Value.One)),
      List(CardComponent.Card(Value.Four), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Two), CardComponent.Card(Value.One))),
      List(player1, player2),
      List(CardComponent.Card(Value.Three), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Eight), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Joker),
        CardComponent.Card(Value.Five), CardComponent.Card(Value.Six), CardComponent.Card(Value.Nine), CardComponent.Card(Value.Eight)))
    val controller = new Controller(game)
    val tui = new TUI(controller)

    "do nothing on input q" in {
      tui.processInputLine("q")
    }
    "place a card on a helpstack" in {
      tui.processInputLine("ph 0 1 true")
      controller.game.player should be(List(controller.game.player(0), controller.game.player(1)))
    }
    "place a card on a stack" in {
      tui.processInputLine("ph 0 1 false")
      controller.game.player should be(List(controller.game.player(0), controller.game.player(1)))
    }
    "place a card from playerstack on stack" in {
      tui.processInputLine("ps 3")
      controller.game.player should be(List(controller.game.player(0), controller.game.player(1)))
    }
    "place a card from helpstack on stack" in {
      tui.processInputLine("philfe 0 0")
      controller.game.player should be(List(controller.game.player(0), controller.game.player(1)))
    }
    "undo" in {
      tui.processInputLine("u")
      controller.gameState should be(PLACESS)
    }
    "redo" in {
      tui.processInputLine("r")
      controller.gameState should be(PLACEHS)
    }
    "get help" in {
      tui.processInputLine("help")
      controller.hilfe should be(
        """---------Hilfe-----------
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
    "start game" in {
      tui.processInputLine("s")
      controller.gameState should be(START)
    }

  }
}
