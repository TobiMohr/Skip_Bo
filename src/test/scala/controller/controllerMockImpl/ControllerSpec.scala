package controller.controllerMockImpl

import de.htwg.se.Skip_Bo.controller.controllerComponent.controllerMockImpl.Controller
import de.htwg.se.Skip_Bo.model.CardComponent
import de.htwg.se.Skip_Bo.model.CardComponent.Value
import de.htwg.se.Skip_Bo.model.GameComponent.GameBaseImpl.Game
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerBaseImpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.Skip_Bo.controller.controllerComponent.GameState.IDLE
import de.htwg.se.Skip_Bo.controller.controllerComponent.PlayerA

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Mock Controller" when {
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

    "used" should {
      "do nothing" in {
        val gameOld = game
        controller.startGame(5) should be()
        controller.pushCardHand(0, 0, 1, false) should be()
        controller.pushCardHand(0, 0, 1, true) should be()
        controller.pushCardHelp(0, 0, 1) should be()
        controller.pushCardPlayer(0, 1) should be()
        controller.beenden(0) should be()
        controller.refill(1) should be()
        controller.undo should be()
        controller.redo should be()
        controller.gameState should be(IDLE)
        controller.oldGameState should be(IDLE)
        controller.newGameState should be(IDLE)
        controller.playerState should be(PlayerA)
        controller.statusText should be("")
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
            .stripMargin
        )
        controller.gameToString(0) should be(
          "Handkarten: " + "Vector(| " + game.player(0).cards(0).toString +
            " | , | " + game.player(0).cards(1).toString + " | , | " + game.player(0).cards(2).toString
            + " | , | " + game.player(0).cards(3).toString  + " | , | " + game.player(0).cards(4).toString + " | )" + "\n\n" +
            "Hilfsstapel: " + "| " + game.player(0).helpstack(0).head.toString +
            " | " + "\t" + "| " + game.player(0).helpstack(1).head.toString +
            " | " + "\t" + "| " + game.player(0).helpstack(2).head.toString +
            " | " + "\t" + "| " + game.player(0).helpstack(3).head.toString +
            " | " + "\t" + "Spielerstapel: " + "| " + game.player(0).stack.head.toString +
            " | " + "\t" + "| " + game.player(0).stack.size.toString + " |" + "\n\n" +
            "Ablagestapel: " + "| " + game.stack(0).head.toString +
            " | " + "\t" + "| " + game.stack(1).head.toString +
            " | " + "\t" + "| " + game.stack(2).head.toString +
            " | " + "\t" + "| " + game.stack(3).head.toString + " | " + "\t"

        )
        var gameNew = game
        gameNew = gameOld
      }
    }
  }
}
