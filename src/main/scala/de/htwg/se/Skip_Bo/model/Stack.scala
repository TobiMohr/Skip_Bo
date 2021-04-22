package de.htwg.se.Skip_Bo.model

 case class Stack(lst:List[Card]) {

  val stacks: List[Card] = lst
  def topCard(): String = { stacks.head.toString }
  def empty(): Boolean = { stacks.isEmpty}
  }
