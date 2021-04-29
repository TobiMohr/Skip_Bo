package de.htwg.se.Skip_Bo.controller

import de.htwg.se.Skip_Bo.model.Colour.Colour
import de.htwg.se.Skip_Bo.model.{Card, Stack}
import de.htwg.se.Skip_Bo.util.Observable

class Controller(var stack: Stack, var card: Card, var firstCard: String) extends Observable{

  def makeCard(colour: Colour, rank: Int):Unit = {
    card = Card(colour, rank)
    notifyObservers
  }

  def printCard(): Unit = {
    card.toString
    notifyObservers
  }

  def topCard(): Unit = {
    firstCard = stack.topCard()
    notifyObservers
  }

  def makeStack(lst: List[Card]):Unit = {
    stack = Stack(lst)
    notifyObservers
  }



}
