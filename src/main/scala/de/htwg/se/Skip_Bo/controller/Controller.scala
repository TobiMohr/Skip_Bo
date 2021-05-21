package de.htwg.se.Skip_Bo.controller


import de.htwg.se.Skip_Bo.model.{Game}
import de.htwg.se.Skip_Bo.util.Observable

import scala.collection.immutable._

class Controller(var game: Game) extends Observable{

  def startGame(size: Int = 5): Unit ={
    game = Game(size)
    notifyObservers
  }

  def pushCardHand1A(s: Int): Unit = {
    game = game.pushCardHand1A(s)
    println("legt Karte auf 1. Ablegestapel")
    notifyObservers
  }
  def pushCardHand2A(s: Int): Unit = {
    game = game.pushCardHand2A(s)
    println("legt Karte auf 2. Ablegestapel")
    notifyObservers
  }

  def pushCardHand3A(s: Int): Unit = {
    game = game.pushCardHand3A(s)
    println("legt Karte auf 3. Ablegestapel")
    notifyObservers
  }
  def pushCardHand4A(s: Int): Unit = {
    game = game.pushCardHand4A(s)
    println("legt Karte auf 4. Ablegestapel")
    notifyObservers
  }
  def ablegen1A(i: Int): Unit = {
    game = game.ablegen1A(i)
    println("legt Karte auf 1. Hilfstapel von Spieler A")
    notifyObservers
  }

  def ablegen2A(i: Int): Unit = {
    game = game.ablegen2A(i)
    println("legt Karte auf 2. Hilfstapel von Spieler A")
    notifyObservers
  }
  def ablegen3A(i: Int): Unit = {
    game = game.ablegen3A(i)
    println("legt Karte auf 3. Hilfstapel von Spieler A")
    notifyObservers
  }
  def ablegen4A(i: Int): Unit = {
    game = game.ablegen4A(i)
    println("legt Karte auf 4. Hilfstapel von Spieler A")
    notifyObservers
  }

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

  def hilfe: String = {
    """-------Hilfe---------
      || p1 | p2 | p3 | p4 |
      |
      || m1 | m2 | m3 | m4 |
      |---------------------
      |"""
      .stripMargin
  }

//  def makeCard(rank: Value):Unit = {
//    card = Card(rank)
//    notifyObservers
//  }
//
//  def printCard: String = {
//    card.toString
//  }
//
//  def makeStack(lst: List[Card]):Unit = {
//    stack = Stack(lst)
//    notifyObservers
//  }



}
