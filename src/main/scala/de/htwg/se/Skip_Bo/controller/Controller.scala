package de.htwg.se.Skip_Bo.controller

import de.htwg.se.Skip_Bo.model.Colour.Colour
import de.htwg.se.Skip_Bo.model.{Card, Game}
import de.htwg.se.Skip_Bo.util.Observable

import scala.collection.immutable._

class Controller(var game: Game) extends Observable{

  def startGame(size: Int = 5): Unit ={
    game = Game(size)
    notifyObservers
  }

  def pushCard1A(s: String): Unit = {
    val card = game.getCardA(s)
    game = game.pushCard1A(card)
    println("legt Karte auf 1. Hilfstapel von Spieler A")
    notifyObservers
  }
  def pushCard2A(s: String): Unit = {
    val card = game.getCardA(s)
    game = game.pushCard2A(card)
    println("legt Karte auf 2. Hilfstapel von Spieler A")
    notifyObservers
  }
  def pushCard3A(s: String): Unit = {
    val card = game.getCardA(s)
    game = game.pushCard3A(card)
    println("legt Karte auf 3. Hilfstapel von Spieler A")
    notifyObservers
  }
  def pushCard4A(s: String): Unit = {
    val card = game.getCardA(s)
    game = game.pushCard4A(card)
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
