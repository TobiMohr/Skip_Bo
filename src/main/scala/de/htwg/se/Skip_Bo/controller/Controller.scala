package de.htwg.se.Skip_Bo.controller


import de.htwg.se.Skip_Bo.model.{Game}
import de.htwg.se.Skip_Bo.util.Observable

import scala.collection.immutable._

class Controller(var game: Game) extends Observable{

  def startGame(size: Int = 5): Unit ={
    game = Game(size)
    notifyObservers
  }

  def pushCard1A(s: Int): Unit = {
    game = game.pushCard1A(s)
    println("legt Karte auf 1. Hilfstapel von Spieler A")
    notifyObservers
  }
  def pushCard2A(s: Int): Unit = {
    game = game.pushCard2A(s)
    println("legt Karte auf 2. Hilfstapel von Spieler A")
    notifyObservers
  }

  def pushCard3A(s: Int): Unit = {
    game = game.pushCard3A(s)
    println("legt Karte auf 3. Hilfstapel von Spieler A")
    notifyObservers
  }
  def pushCard4A(s: Int): Unit = {
    game = game.pushCard4A(s)
    println("legt Karte auf 4. Hilfstapel von Spieler A")
    notifyObservers
  }
  def ablegen1A(i: Int): Unit = {
    game = game.ablegen1A(i)
    println("legt Karte auf 1. Ablagestapel")
    notifyObservers
  }

  def ablegen2A(i: Int): Unit = {
    game = game.ablegen2A(i)
    println("legt Karte auf 2. Ablagestapel")
    notifyObservers
  }
  def ablegen3A(i: Int): Unit = {
    game = game.ablegen3A(i)
    println("legt Karte auf 3. Ablagestapel")
    notifyObservers
  }
  def ablegen4A(i: Int): Unit = {
    game = game.ablegen4A(i)
    println("legt Karte auf 4. Ablagestapel")
    notifyObservers
  }

  def Beenden: Unit = {
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
