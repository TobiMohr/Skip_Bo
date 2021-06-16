package de.htwg.se.Skip_Bo.aview.gui

import de.htwg.se.Skip_Bo.controller.Controller

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._

class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)

}
