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

   def helpCard(int: Int): Try[(Card, Player)] = {
      if(helpstack(int).isEmpty){
         Failure(InvalidMove)
      } else {
         val card = helpstack(int).head
         val x = Util.listRemoveAt(helpstack(int), 0)
         Success(card, copy(helpstack = helpstack.updated(int, x)))
      }
   }

   def putInHelp(int: Int, card: Card): Try[Player] = {
      val s2 = card +: helpstack(int)
      Success (copy(helpstack = helpstack.updated(int, s2)))

   }
}

