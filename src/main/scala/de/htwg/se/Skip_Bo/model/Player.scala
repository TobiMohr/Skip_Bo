package de.htwg.se.Skip_Bo.model

import de.htwg.se.Skip_Bo.util.Util

import scala.util.{Failure, Success, Try}

case class Player(name: String,
                  cards: List[Card],
                  helpstack:List[List[Card]] = (0 until 4).map(_=>List.empty).toList,
                  stack: List[Card]) {
   override def toString:String = name

   //int=Index welche Handkarte
   def getCard(int :Int): Try[(Card, Player)] ={
      if(int>=cards.size) {
         Failure(InvalidHandCard(int))
      }
      val card = cards(int)
      val x = Util.listRemoveAt(cards, int)
      Success (card, copy(cards = x))
   }

   def stackCard(): Try[(Card, Player)] = {
      val card = stack.head
      val x = Util.listRemoveAt(stack, 0)
      Success (card, copy(stack = x))
   }
}

