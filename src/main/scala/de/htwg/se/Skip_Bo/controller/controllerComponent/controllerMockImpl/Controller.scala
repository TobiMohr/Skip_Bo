package de.htwg.se.Skip_Bo.controller.controllerComponent.controllerMockImpl

import de.htwg.se.Skip_Bo.controller.controllerComponent.GameState.{GameState, IDLE}
import de.htwg.se.Skip_Bo.controller.controllerComponent.{ControllerInterface, GameState, PlayerA, PlayerState}
import de.htwg.se.Skip_Bo.model.GameComponent.GameInterface

class Controller(var game: GameInterface) extends ControllerInterface{
  override def startGame(size: Int): Unit = {  }

  override def pushCardHand(i: Int, j: Int, n: Int, helpst: Boolean): Unit = { }

  override def pushCardHelp(i: Int, j: Int, n: Int): Unit = { }

  override def pushCardPlayer(i: Int, n: Int): Unit = {  }

  override def beenden(n: Int): Unit = { }

  //override def refill(j: Int): Unit = { }

  override def gameToString(n: Int): String = game.toString(n)

  override def undo: Unit = { }

  override def redo: Unit = { }

  override def hilfe: String = {
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
  }
  override def gameState: GameState = IDLE

  override def statusText: String = GameState.message(gameState)

  override def newGameState: GameState = IDLE

  override def oldGameState: GameState = IDLE

  override def playerState: PlayerState = PlayerA

  override def playerStateNow: PlayerState = PlayerA

  override def save: Unit = {}

  override def load: Unit = {}
}
