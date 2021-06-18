package de.htwg.se.Skip_Bo.controller


import de.htwg.se.Skip_Bo.controller.GameState.{GameState, IDLE, NEXT, PLACEHS, PLACES, PLACESS, START}
import de.htwg.se.Skip_Bo.model.Game
import de.htwg.se.Skip_Bo.util.{Observable, UndoManager}

import scala.swing.Publisher
import scala.util.{Failure, Success, Try}



class Controller(var game: Game=Game()) extends Observable {

  private val undoManager = new UndoManager
  var gameState: GameState = IDLE
  var newGameState:GameState = IDLE
  var oldGameState: GameState = IDLE
  var playerState: PlayerState = PlayerA

  def startGame(size: Int = 5): Unit ={
    game = game.startGame(size)
    gameState = START
    notifyObservers
  }

  //legt Handkarte auf Ablegestapel
  /*def pushCardHand(i: Int, j: Int,n: Int,helpst: Boolean ): Unit = {
    undoManager.doStep(new PushCardHandCommand(i, j, n, helpst, this))
    if(n == 0) {
      if (helpst) {
        println("Spieler(A) legt Karte auf " + (i + 1) + ". Hilfestapel")
        beenden(playerState.turnChange.getPlayer)
      } else {
        println("Spieler(A) legt Karte auf " + (i + 1) + ". Ablagestapel")

      }
    } else if(n == 1){
      if (helpst) {
        println("Spieler(B) legt Karte auf " + (i + 1) + ". Hilfestapel")
        beenden(playerState.turnChange.getPlayer)
      } else {
        println("Spieler(B) legt Karte auf " + (i + 1) + ". Ablagestapel")
      }
    }
    notifyObservers
  }*/

  def pushCardHand(i: Int, j: Int,n: Int,helpst: Boolean ): Unit = {
    undoManager.doStep(new PushCardHandCommand(i, j, n, helpst, this))
    if (helpst) {
        beenden(playerState.turnChange.getPlayer)
    } else {
      oldGameState = gameState
      gameState = PLACES
    }
    notifyObservers
  }


  /*//legt Karte vom Hilfsstapel auf Ablegestapel
  def pushCardHelp(i: Int, j:Int, n: Int): Unit = {
    undoManager.doStep(new PushCardHelpCommand(i, j, n, this))
    if(n == 0) {
      println("Spieler(A) legt Karte vom " + (j + 1) + ". Hilfestapel auf den " + (i + 1) + ". Ablagestapel")
    } else if(n == 1) {
      println("Spieler(B) legt Karte vom " + (j + 1) + ". Hilfestapel auf den " + (i + 1) + ". Ablagestapel")
    }
    notifyObservers
  }*/

  //legt Karte vom Hilfsstapel auf Ablegestapel
  def pushCardHelp(i: Int, j:Int, n: Int): Unit = {
    undoManager.doStep(new PushCardHelpCommand(i, j, n, this))
    oldGameState = gameState
    gameState = PLACEHS
    notifyObservers
  }


  /*//legt Karte vom Spielerstapel auf Ablegestapel
  def pushCardPlayer(i: Int, n: Int):Unit = {
    undoManager.doStep(new PushCardPlayerCommand(i, n, this))
    if(n == 0) {
      println("Spieler(A) legt karte vom Spielerstapel auf " + (i + 1) + ". Ablagestapel")
    } else if(n == 1){
      println("Spieler(B) legt karte vom Spielerstapel auf " + (i + 1) + ". Ablagestapel")
    }
    notifyObservers
  }*/

  def pushCardPlayer(i: Int, n: Int):Unit = {
    undoManager.doStep(new PushCardPlayerCommand(i, n, this))
    oldGameState = gameState
    gameState = PLACESS
    if(game.player(playerState.getPlayer).stack.size == 0) {
      gameState = WIN
      notifyObservers
      System.exit(0)
    }

    notifyObservers
  }


  def beenden(n:Int): Unit = {
    game = game.pull(n)
    oldGameState = gameState
    gameState = NEXT
  }

  def refill(j:Int): Unit = {
    game = game.refill(j)
  }

  def gameToString(n:Int): String = game.toString(n)

  def undo: Unit={
    undoManager.undoStep
    newGameState = gameState
    gameState = oldGameState
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    oldGameState = gameState
    gameState = newGameState
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


  //def statusText:String = GameState.message(gameState)


}
