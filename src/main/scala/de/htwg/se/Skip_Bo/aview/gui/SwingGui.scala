package de.htwg.se.Skip_Bo.aview.gui

import de.htwg.se.Skip_Bo.controller.{CardPlaced, Controller}

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._

class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)

  this.visible = true
  title = "HTWG Skip_Bo"

  def testPanel: FlowPanel = new FlowPanel{
    contents += new Label("Highlight: ")
    val button = Button("") {
      controller.game.toString(controller.playerState.getPlayer)
    }
    button.preferredSize_=(new Dimension(30,30))
    contents += button
    listenTo(button)
  }


  contents = new BorderPanel {
    add(testPanel, BorderPanel.Position.North)
    add(testPanel, BorderPanel.Position.Center)
    add(testPanel, BorderPanel.Position.East)
    add(testPanel, BorderPanel.Position.West)
    add(testPanel, BorderPanel.Position.South)

  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("Start") { controller.startGame() })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") { controller.undo })
      contents += new MenuItem(Action("Redo") { controller.redo })
    }
    contents += new Menu("Options") {
      mnemonic = Key.O
      contents += new MenuItem(Action("Place first Card on first helpstack") { controller.pushCardHand(0, 0, 0, true)})
      contents += new MenuItem(Action("Show Board of Player B") { controller.game.toString(1)})

    }
  }

  visible = true

  reactions += {
    case event: CardPlaced => redraw
  }

  def redraw: Unit = {
    repaint
  }
}
