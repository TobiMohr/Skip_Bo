package de.htwg.se.Skip_Bo.controller


import de.htwg.se.Skip_Bo.model.Game
import de.htwg.se.Skip_Bo.util.{Observable, UndoManager}

import scala.util.{Failure, Success}



class Controller(var game: Game=Game()) extends Observable{

  private val undoManager = new UndoManager

  def startGame(size: Int = 5): Unit ={
    game = game.startGame(size)
    notifyObservers
  }

  //legt Handkarte auf Ablegestapel
  def pushCardHand(i: Int, j: Int,n: Int,helpst: Boolean ): Unit = {
    game.pushCardHand(i, j, n, helpst) match{
      case Failure(exception) => onError(exception)
      case Success(value)=>
        game = value
        println("legt Karte auf 1. Ablegestapel")
        notifyObservers
    }
  }

  //legt Karte vom Spielerstapel auf Ablegestapel ab
  def pushCardStapel1A(): Unit = {
    game = game.pushCardStapel1A()
    println("legt Karte vom Spielerstapel auf 1. Ablegestapel")
    notifyObservers
  }
  def pushCardStapel2A(): Unit = {
    game = game.pushCardStapel2A()
    println("legt Karte vom Spielerstapel auf 1. Ablegestapel")
    notifyObservers
  }
  def pushCardStapel3A(): Unit = {
    game = game.pushCardStapel3A()
    println("legt Karte vom Spielerstapel auf 1. Ablegestapel")
    notifyObservers
  }
  def pushCardStapel4A(): Unit = {
    game = game.pushCardStapel4A()
    println("legt Karte vom Spielerstapel auf 1. Ablegestapel")
    notifyObservers
  }

  //legt Karte von Hilfstapel auf Ablegestapel
  def pushCardH1A1A(): Unit = {
    game = game.pushCardH1A1A()
    println("legt Karte vom 1. Hilfstapel auf 1. Ablegestapel")
  }
  def pushCardH1A2A(): Unit = {
    game = game.pushCardH1A2A()
    println("legt Karte vom 1. Hilfstapel auf 2. Ablegestapel")
  }
  def pushCardH1A3A(): Unit = {
    game = game.pushCardH1A3A()
    println("legt Karte vom 1. Hilfstapel auf 3. Ablegestapel")
  }
  def pushCardH1A4A(): Unit = {
    game = game.pushCardH1A4A()
    println("legt Karte vom 1. Hilfstapel auf 4. Ablegestapel")
  }
  def pushCardH2A1A(): Unit = {
    game = game.pushCardH2A1A()
    println("legt Karte vom 2. Hilfstapel auf 1. Ablegestapel")
  }
  def pushCardH2A2A(): Unit = {
    game = game.pushCardH2A2A()
    println("legt Karte vom 2. Hilfstapel auf 2. Ablegestapel")
  }
  def pushCardH2A3A(): Unit = {
    game = game.pushCardH2A3A()
    println("legt Karte vom 2. Hilfstapel auf 3. Ablegestapel")
  }
  def pushCardH2A4A(): Unit = {
    game = game.pushCardH2A1A()
    println("legt Karte vom 2. Hilfstapel auf 4. Ablegestapel")
  }
  def pushCardH3A1A(): Unit = {
    game = game.pushCardH3A1A()
    println("legt Karte vom 3. Hilfstapel auf 1. Ablegestapel")
  }
  def pushCardH3A2A(): Unit = {
    game = game.pushCardH3A2A()
    println("legt Karte vom 3. Hilfstapel auf 2. Ablegestapel")
  }
  def pushCardH3A3A(): Unit = {
    game = game.pushCardH3A3A()
    println("legt Karte vom 3. Hilfstapel auf 3. Ablegestapel")
  }
  def pushCardH3A4A(): Unit = {
    game = game.pushCardH3A4A()
    println("legt Karte vom 3. Hilfstapel auf 4. Ablegestapel")
  }
  def pushCardH4A1A(): Unit = {
    game = game.pushCardH4A1A()
    println("legt Karte vom 4. Hilfstapel auf 1. Ablegestapel")
  }
  def pushCardH4A2A(): Unit = {
    game = game.pushCardH4A2A()
    println("legt Karte vom 4. Hilfstapel auf 2. Ablegestapel")
  }
  def pushCardH4A3A(): Unit = {
    game = game.pushCardH4A3A()
    println("legt Karte vom 4. Hilfstapel auf 3. Ablegestapel")
  }
  def pushCardH4A4A(): Unit = {
    game = game.pushCardH4A4A()
    println("legt Karte vom 4. Hilfstapel auf 4. Ablegestapel")
  }


  def checkCardHand(i : Int): Boolean ={
    game.checkCardHand(i)
  }

  def Beenden: Unit = {
    game = game.pullA()
    println("Der Zug ist beendet")
    println("Nächster Spieler ist am Zug")
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
