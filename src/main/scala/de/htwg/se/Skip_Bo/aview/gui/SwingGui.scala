package de.htwg.se.Skip_Bo.aview.gui

import de.htwg.se.Skip_Bo.controller.{CardPlaced, Controller}

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import scala.io.Source._


class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)

  title = "HTWG Skip_Bo"

  val statusline = new TextField(controller.statusText, 30)

  def playField: FlowPanel = new FlowPanel(){
    contents += new Label("First Line")
    contents += new Label("Second Line")
    contents += new Label("Third Line")
    val button = Button("moin"){
      controller.startGame()
    }
    contents += button
    listenTo(button)


  }

  contents = new BorderPanel {
    add(playField, BorderPanel.Position.West)
    add(playField, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") {
        controller.startGame()
      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") {
        controller.undo
      })
      contents += new MenuItem(Action("Redo") {
        controller.redo
      })
    }
  }

  visible = true
  redraw

  reactions += {
    case event: CardPlaced => redraw

  }



  def redraw: Unit = {
    statusline.text = controller.statusText
    repaint
  }

}
