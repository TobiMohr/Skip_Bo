package de.htwg.se.Skip_Bo.controller

import de.htwg.se.Skip_Bo.model.Colour.Colour
import de.htwg.se.Skip_Bo.model.{Card, Stack}
import de.htwg.se.Skip_Bo.util.Observable

class Controller(var stack: Stack, var card: Card) extends Observable{

  def p1(): Unit = {
    println("legt Karte auf 1. Spieler Stack")
    notifyObservers
  }
  def p2(): Unit = {
    println("legt Karte auf 2. Spieler Stack")
    notifyObservers
  }
  def p3(): Unit = {
    println("legt Karte auf 3. Spieler Stack")
    notifyObservers
  }
  def p4(): Unit = {
    println("legt Karte auf 4. Spieler Stack")
    notifyObservers
  }
  def m1(): Unit = {
    println("legt Karte auf 1. Mittel Stack")
    notifyObservers
  }

  def m2(): Unit = {
    println("legt Karte auf 2. Mittel Stack")
    notifyObservers
  }
  def m3(): Unit = {
    println("legt Karte auf 3. Mittel Stack")
    notifyObservers
  }
  def m4(): Unit = {
    println("legt Karte auf 4. Mittel Stack")
    notifyObservers
  }

  def Beenden: Unit = {
    println("Der Zug ist beendet")
    println("Nächster Spieler ist am Zug")
    notifyObservers
  }

  def makeCard(colour: Colour, rank: Int):Unit = {
    card = Card(colour, rank)
    notifyObservers
  }

  def printCard: String = {
    card.toString
  }

  def makeStack(lst: List[Card]):Unit = {
    stack = Stack(lst)
    notifyObservers
  }

  def BoardtoString: String
  """-------Hilfe---------
    || p1 | p2 | p3 | p4 |
    |
    || m1 | m2 | m3 | m4 |
    |---------------------
    |"""
    .stripMargin


}
