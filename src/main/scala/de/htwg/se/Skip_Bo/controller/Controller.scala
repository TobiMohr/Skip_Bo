package de.htwg.se.Skip_Bo.controller


import de.htwg.se.Skip_Bo.controller.GameState.{GameState, IDLE, PLACE}
import de.htwg.se.Skip_Bo.model.Game
import de.htwg.se.Skip_Bo.util.{Observable, UndoManager}

import scala.util.{Failure, Success, Try}



class Controller(var game: Game=Game()) extends Observable{

  private val undoManager = new UndoManager

  var gameState: GameState = IDLE
  var playerState: PlayerState = PlayerA

  def startGame(size: Int = 5): Unit ={
    game = game.startGame(size)
    println("Spieler A ist am Zug")
    gameState = PLACE
    notifyObservers
  }

  //legt Handkarte auf Ablegestapel
  def pushCardHand(i: Int, j: Int,n: Int,helpst: Boolean ): Unit = {
    game.pushCardHand(i, j, n, helpst) match {
      case Failure(exception) => onError(exception)
      case Success(value) =>
        game = value
        if(n == 0) {
          if (helpst) {
            println("Spieler(A) legt Karte auf " + (i + 1) + ". Hilfestapel")
            beenden(playerState.getPlayer)
          } else {
            println("Spieler(A) legt Karte auf " + (i + 1) + ". Ablagestapel")

          }
        } else if(n == 1){
          if (helpst) {
            println("Spieler(B) legt Karte auf " + (i + 1) + ". Hilfestapel")
            beenden(playerState.getPlayer)
          } else {
            println("Spieler(B) legt Karte auf " + (i + 1) + ". Ablagestapel")
          }
        }
        notifyObservers
    }
  }

  //legt Karte vom Hilfsstapel auf Ablegestapel
  def pushCardHelp(i: Int, j:Int, n: Int): Unit = {
    game.pushCardHelp(i, j, n) match {
      case Failure(exception) => onError(exception)
      case Success(value) =>
        game = value
        if(n == 0) {
          println("Spieler(A) legt Karte vom " + (j + 1) + ". Hilfestapel auf den " + (i + 1) + ". Ablagestapel")
        } else if(n == 1) {
          println("Spieler(B) legt Karte vom " + (j + 1) + ". Hilfestapel auf den " + (i + 1) + ". Ablagestapel")
        }
        notifyObservers
    }
  }

  //legt Karte vom Spielerstapel auf Ablegestapel
  def pushCardPlayer(i: Int, n: Int):Unit = {
    game.pushCardPlayer(i, n) match {
      case Failure(exception) => onError(exception)
      case Success(value) =>
        game = value
        if(n == 0) {
          println("Spieler(A) legt karte vom Spielerstapel auf " + (i + 1) + ". Ablagestapel")
        } else if(n == 1){
          println("Spieler(B) legt karte vom Spielerstapel auf " + (i + 1) + ". Ablagestapel")
        }
        notifyObservers
    }
  }

  def beenden(n:Int): Unit = {
    if(n == 0) {
      game = game.pull(1)
      playerState = playerState.turnChange
      println("Spieler(A) hat seinen Zug beendet")
      println("Spieler(B) ist am Zug")
    } else if(n == 1) {
      game = game.pull(0)
      playerState = playerState.turnChange
      println("Spieler(B) hat seinen Zug beendet")
      println("Spieler(A) ist am Zug")
    }
  }

  def gameToString(n:Int): String = game.toString(n)

  def undo: Unit={
    undoManager.undoStep
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
  }

  def hilfe: String = {
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


}
