package de.htwg.se.Skip_Bo.model

 case class Stack(lst:List[Card]) {

  val stacks: List[Card] = lst

  def topCard(): String = {
   if (stacks.nonEmpty) {
    stacks.head.toString
   } else {
    "Keine Karte im Stapel"
   }
  }

  def empty(): Boolean = {
   stacks.isEmpty
  }

  def contains(): String = {
   (for (n <- stacks) yield {
    n.toString
   }).mkString(", ")
  }

 }
