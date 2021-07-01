package de.htwg.se.Skip_Bo.model.fileIOComponent.fileIOXmlImpl

import de.htwg.se.Skip_Bo.model.CardComponent.{Card, Value}
import de.htwg.se.Skip_Bo.model.CardComponent.Value.Values
import de.htwg.se.Skip_Bo.model.GameComponent.GameBaseImpl.Game
import de.htwg.se.Skip_Bo.model.GameComponent.GameInterface
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerBaseImpl.Player
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerInterface
import de.htwg.se.Skip_Bo.model.fileIOComponent.fileIOInterface

import scala.xml.{Elem, NodeSeq, PrettyPrinter}

class fileIO extends fileIOInterface {

  // für ablagestapel
  var stack1: List[Card] = List.empty
  var stack2: List[Card] = List.empty
  var stack3: List[Card] = List.empty
  var stack4: List[Card] = List.empty

  var cp1: List[Card] = List.empty
  var cp2: List[Card] = List.empty

  var hs1p1: List[Card] = List.empty
  var hs2p1: List[Card] = List.empty
  var hs3p1: List[Card] = List.empty
  var hs4p1: List[Card] = List.empty

  var hs1p2: List[Card] = List.empty
  var hs2p2: List[Card] = List.empty
  var hs3p2: List[Card] = List.empty
  var hs4p2: List[Card] = List.empty

  var sp1: List[Card] = List.empty
  var sp2: List[Card] = List.empty

  var cover: List[Card] = List.empty

  override def load: GameInterface = {
    var game: GameInterface = Game(List(List.empty, List.empty, List.empty, List.empty), List.empty, List.empty)
    val file = scala.xml.XML.loadFile("game.xml")
    loadStacks(file)
    val player1 = loadPlayer1(file)
    val player2 = loadPlayer2(file)
    val cstackN2 = getSeqFromFile(file, "cover", "csC")
    for(card <- cstackN2){
      val cardtmp:Card = Card(stringToValue(card.text))
      cover = cardtmp +: cover
    }
    cover = cover.reverse

    game = Game(List(stack1, stack2, stack3, stack4), List(player1, player2), cover)
    game

  }

    def loadStacks(file: Elem): Unit = {

      val s1Cards = getSeqFromFile(file, "stack1", "cC")
      for (card <- s1Cards) {
        val cardtmp: Card = Card(stringToValue(card.text))
        stack1 = cardtmp +: stack1
      }
      stack1 = stack1.reverse

      val s2Cards = getSeqFromFile(file, "stack2", "cC")
      for (card <- s2Cards) {
        val cardtmp: Card = Card(stringToValue(card.text))
        stack2 = cardtmp +: stack2
      }
      stack2 = stack2.reverse

      val s3Cards = getSeqFromFile(file, "stack3", "cC")
      for (card <- s3Cards) {
        val cardtmp: Card = Card(stringToValue(card.text))
        stack3 = cardtmp +: stack3
      }
      stack3 = stack3.reverse

      val s4Cards = getSeqFromFile(file, "stack4", "cC")
      for (card <- s4Cards) {
        val cardtmp: Card = Card(stringToValue(card.text))
        stack4 = cardtmp +: stack4
      }
      stack4 = stack4.reverse
    }

  def loadPlayer1(file: Elem): Player = {
    val p1 = (file \\ "p1")

    val p1n: String = (p1 \\ "name").text
    val handCard = getSeqFromSeq(p1, "cards", "cC")
    for(card <- handCard){
      val cardtmp: Card = Card(stringToValue(card.text))
      cp1 = cardtmp +: cp1
    }
    val hstacks = (p1 \\ "helpstacks")

    val hsCards1 = getSeqFromSeq(hstacks, "hstack1", "cC")
    for (card <- hsCards1){
      val cardtmp: Card = Card(stringToValue(card.text))
      hs1p1 = cardtmp +: hs1p1
    }

    val hsCards2 = getSeqFromSeq(hstacks, "hstack2", "cC")
    for (card <- hsCards2){
      val cardtmp: Card = Card(stringToValue(card.text))
      hs2p1 = cardtmp +: hs2p1
    }

    val hsCards3 = getSeqFromSeq(hstacks, "hstack3", "cC")
    for (card <- hsCards3){
      val cardtmp: Card = Card(stringToValue(card.text))
      hs3p1 = cardtmp +: hs3p1
    }

    val hsCards4 = getSeqFromSeq(hstacks, "hstack4", "cC")
    for (card <- hsCards4){
      val cardtmp: Card = Card(stringToValue(card.text))
      hs4p1 = cardtmp +: hs4p1
    }

    val psCards = getSeqFromSeq(p1, "stack", "cC")
    for(card <- psCards){
      val cardtmp: Card = Card(stringToValue(card.text))
      sp1 = cardtmp +: sp1
    }
    cp1 = cp1.reverse
    hs1p1 = hs1p1.reverse
    hs2p1 = hs2p1.reverse
    hs3p1 = hs3p1.reverse
    hs4p1 = hs4p1.reverse
    sp1 = sp1.reverse
    val player1 = Player(p1n, cp1, List(hs1p1, hs2p1, hs3p1, hs4p1), sp1)
    player1
  }

  def loadPlayer2(file: Elem): Player = {
    val p2 = (file \\ "p2")
    val p2name: String = (p2 \\ "name").text
    val handCards = getSeqFromSeq(p2, "cards", "cC")
    for (card <- handCards) {
      val cardtmp: Card = Card(stringToValue(card.text))
      cp2 = cardtmp +: cp2
    }
    val hstacksp2 = (p2 \\ "helpstacks")
    val hsCards1p2 = getSeqFromSeq(hstacksp2, "hstack1", "cC")
    for (card <- hsCards1p2) {
      val cardtmp: Card = Card(stringToValue(card.text))
      hs1p2 = cardtmp +: hs1p2
    }
    val hsCards2p2 = getSeqFromSeq(hstacksp2, "hstack2", "cC")
    for (card <- hsCards2p2) {
      val cardtmp: Card = Card(stringToValue(card.text))
      hs2p2 = cardtmp +: hs2p2
    }
    val hsCards3p2 = getSeqFromSeq(hstacksp2, "hstack3", "cC")
    for (card <- hsCards3p2) {
      val cardtmp: Card = Card(stringToValue(card.text))
      hs3p2 = cardtmp +: hs3p2
    }
    val hsCards4p2 = getSeqFromSeq(hstacksp2, "hstack4", "cC")
    for (card <- hsCards4p2) {
      val cardtmp: Card = Card(stringToValue(card.text))
      hs4p2 = cardtmp +: hs4p2
    }
    val psCards2 = getSeqFromSeq(p2, "stack", "cC")
    for (card <- psCards2) {
      val cardtmp: Card = Card(stringToValue(card.text))
      sp2 = cardtmp +: sp2
    }
    cp2 = cp2.reverse
    hs1p2 = hs1p2.reverse
    hs2p2 = hs2p2.reverse
    hs3p2 = hs3p2.reverse
    hs4p2 = hs4p2.reverse
    sp2 = sp2.reverse
    val player2 = Player(p2name, cp2, List(hs1p2, hs2p2, hs3p2, hs4p2), sp2)
    player2
  }

  def getSeqFromFile(file: Elem, s:String, s2: String): NodeSeq = {
    val first = (file \\ s)
    val second = (first \\ s2)
    val end = (second \\ "card")
    end
  }

  def getSeqFromSeq(file: NodeSeq, s:String, s2: String): NodeSeq = {
    val first = (file \\ s)
    val second = (first \\ s2)
    val end = (second \\ "card")
    end
  }

  override def save(game: GameInterface): Unit = saveString(game)

  def saveString(game: GameInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("game.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gameToXml(game))
    pw.write(xml)
    pw.close
  }

  def gameToXml(game: GameInterface): Elem = {

    <game>
      {stacksToXml(game.stack)}
      {playerToXml(game.player)}
      <cover>{coverStackToXml(game.cardsCovered)}</cover>
    </game>

  }

  def stacksToXml(stack: List[List[Card]]): Elem = {
    val stack1 = stack(0)
    val stack2 = stack(1)
    val stack3 = stack(2)
    val stack4 = stack(3)

    <stacks>
      <stack1>{ stackToXml(stack1) } </stack1>
      <stack2>{ stackToXml(stack2) } </stack2>
      <stack3>{ stackToXml(stack3) } </stack3>
      <stack4>{ stackToXml(stack4) } </stack4>
    </stacks>
  }

  def playerToXml(player: List[PlayerInterface]): Elem  = {
    val player1 = player(0)
    val player2 = player(1)

<player>
  <p1>
    <name>{player1.name}</name>
    <cards>{stackToXml(player1.cards)}</cards>
    <helpstacks>{helpstacksToXml(player1.helpstack)}</helpstacks>
    <stack>{stackToXml(player1.stack)}</stack>
  </p1>
  <p2>
    <name>{player2.name}</name>
    <cards>{stackToXml(player2.cards)}</cards>
    <helpstacks>{helpstacksToXml(player2.helpstack)}</helpstacks>
    <stack>{stackToXml(player2.stack)}</stack>
  </p2>
</player>
  }

  def helpstacksToXml(stack: List[List[Card]]): Elem = {
    val stack1 = stack(0)
    val stack2 = stack(1)
    val stack3 = stack(2)
    val stack4 = stack(3)

    <hstack>
      <hstack1>{ stackToXml(stack1) } </hstack1>
      <hstack2>{ stackToXml(stack2) } </hstack2>
      <hstack3>{ stackToXml(stack3) } </hstack3>
      <hstack4>{ stackToXml(stack4) } </hstack4>
    </hstack>
  }

  def stackToXml(stack: List[Card]): Elem = {
    <cC>{
      for (card <- stack.indices) yield cardToXml(stack(card))
      }</cC>
  }

  def coverStackToXml(stack: List[Card]): Elem = {
    <csC>{
      for (card <- stack.indices) yield cardToXml(stack(card))
      }</csC>
  }


  def cardToXml(card1: Card): Elem = {
    <card>{card1.toString}</card>
  }

  def stringToValue(s: String): Values = {
    s match {
      case "1" => Value.One
      case "2" => Value.Two
      case "3" => Value.Three
      case "4" => Value.Four
      case "5" => Value.Five
      case "6" => Value.Six
      case "7" => Value.Seven
      case "8" => Value.Eight
      case "9" => Value.Nine
      case "10" => Value.Ten
      case "11" => Value.Eleven
      case "12" => Value.Twelve
      case "J" => Value.Joker
    }
  }



}
