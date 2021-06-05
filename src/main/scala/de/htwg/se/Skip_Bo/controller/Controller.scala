package de.htwg.se.Skip_Bo.controller


import de.htwg.se.Skip_Bo.model.Game
import de.htwg.se.Skip_Bo.util.{Observable, UndoManager}

import scala.util.{Failure, Success, Try}



class Controller(var game: Game=Game()) extends Observable{

  private val undoManager = new UndoManager

  def startGame(size: Int = 5): Unit ={
    game = game.startGame(size)
    notifyObservers
  }

  //legt Handkarte auf Ablegestapel
  def pushCardHand(i: Int, j: Int,n: Int,helpst: Boolean ): Unit = {
    game.pushCardHand(i, j, n, helpst) match {
      case Failure(exception) => onError(exception)
      case Success(value) =>
        game = value
        if (helpst) {
          println("Spieler(" + n + ") legt Karte auf " + i + 1 + ". Hilfestapel")
        } else {
          println("legt Karte auf" + i + 1 + ". Ablagestapel")
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
        println("Spieler(" + n + ") legt Karte vom " + j + 1  + ". Hilfestapel auf den " +  i + 1 + ". Ablagestapel")
        notifyObservers
    }
  }

  //legt Karte vom Spielerstapel auf Ablegestapel
  def pushCardPlayer(i: Int, n: Int):Unit = {
    game.pushCardPlayer(i, n) match {
      case Failure(exception) => onError(exception)
      case Success(value) =>
        game = value
        println("Spieler(" + n + ") legt karte vom Spielerstapel auf " + i + 1 + ". Ablagestapel")
        notifyObservers
    }
  }

  def beenden(n:Int): Unit = {
    game = game.pull(n)
    println("Spieler(" + n +") hat seinen Zug beendet")
    println("Spieler(" + (n + 1) +") ist am Zug")
    notifyObservers
  }

  def gameToString: String = game.toString

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
      |p1-4 = legt Handkarte auf Ablegestapel
      |ps1-4 = legt Karte vom Spielerstapel auf Ablegestapel ab
      |ph1-4a1-4 = legt Karte vom Hilfstapel auf Ablegestapel ab
      |a1-4 = legt Karte vom Spielerstapel auf Hilfstapel ab
      |"""
      .stripMargin
  }


}
