package de.htwg.se.Skip_Bo.model

class Board {
  val midStack1 = Stack(Nil)
  val midStack2 = Stack(Nil)
  val midStack3 = Stack(Nil)
  val midStack4 = Stack(Nil)

  val p1Stack1 = Stack(Nil)
  val p1Stack2 = Stack(Nil)
  val p1Stack3 = Stack(Nil)
  val p1Stack4 = Stack(Nil)

  val p2Stack1 = Stack(Nil)
  val p2Stack2 = Stack(Nil)
  val p2Stack3 = Stack(Nil)
  val p2Stack4 = Stack(Nil)

  override def toString():String = {
    """-------Hilfe---------
      || p1 | p2 | p3 | p4 |
      |
      || m1 | m2 | m3 | m4 |
      |---------------------
      |"""
      .stripMargin
  }


}
