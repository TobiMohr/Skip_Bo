package de.htwg.se.Skip_Bo.controller

import de.htwg.se.Skip_Bo.util.Command


import scala.util.{Failure, Success}

class PushCardHandCommand (i: Int,j: Int,n: Int,helpst :Boolean, controller : Controller) extends Command{

  override def doStep: Unit = controller.game = controller.game.pushCardHand(i, j, n, helpst)

  override def undoStep: Unit = {}

  override def redoStep: Unit = controller.game = controller.game.pushCardHand(i, j, n, helpst)
}
