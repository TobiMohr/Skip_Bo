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

  override def load: GameInterface = {
    var game: GameInterface = Game(List(List.empty, List.empty, List.empty, List.empty), List.empty, List.empty)
    val file = scala.xml.XML.loadFile("game.xml")

    // für ablagestapel
    var stacks:List[Card] = List.empty
    var stacks2:List[Card] = List.empty
    var stacks3:List[Card] = List.empty
    var stacks4:List[Card] = List.empty

    var hand1:List[Card] = List.empty
    var hand2:List[Card] = List.empty

    var hstack1:List[Card] = List.empty
    var hstack2:List[Card] = List.empty
    var hstack3:List[Card] = List.empty
    var hstack4:List[Card] = List.empty

    var hstack1p2:List[Card] = List.empty
    var hstack2p2:List[Card] = List.empty
    var hstack3p2:List[Card] = List.empty
    var hstack4p2:List[Card] = List.empty

    var ps1:List[Card] = List.empty
    var ps2:List[Card] = List.empty

    var covered: List[Card] = List.empty


    val test1 = (file \\ "stack1")
    val test2 = (test1 \\ "cC")
    val s1Cards = (test2 \\ "card")
    for (card <- s1Cards) {
      val cardtmp: Card = Card(stringToValue(card.text))
      stacks = cardtmp +: stacks
    }
    stacks = stacks.reverse

    val test21 = (file \\ "stack2")
    val test22 = (test21 \\ "cC")
    val s2Cards = (test22 \\ "card")
    for (card <- s2Cards) {
      val cardtmp: Card = Card(stringToValue(card.text))
      stacks2 = cardtmp +: stacks2
    }
    stacks2 = stacks2.reverse

    val test31 = (file \\ "stack3")
    val test32 = (test31 \\ "cC")
    val s3Cards = (test32 \\ "card")
    for (card <- s3Cards) {
      val cardtmp: Card = Card(stringToValue(card.text))
      stacks3 = cardtmp +: stacks3
    }
    stacks3 = stacks3.reverse

    val test41 = (file \\ "stack4")
    val test42 = (test41 \\ "cC")
    val s4Cards = (test42 \\ "card")
    for (card <- s4Cards) {
      val cardtmp: Card = Card(stringToValue(card.text))
      stacks4 = cardtmp +: stacks4
    }
    stacks4 = stacks4.reverse


    //Spieler section

    //player1
    val p1 = (file \\ "p1")
    //name
    val p1n: String = (p1 \\ "name").text
    //hand
    val handNodes = (p1 \\ "cards")
    val hand = (handNodes \\"cC")
    val handCard = (hand \\ "card")
    for(card <- handCard){
      val cardtmp: Card = Card(stringToValue(card.text))
      hand1 = cardtmp +: hand1
    }
    //hepstacks
    val hstacks = (p1 \\ "helpstacks")
    val hstack1N = (hstacks \\"hstack1")
    val hstack1N2 = (hstack1N \\"cC")
    val hsCards1 = (hstack1N2 \\"card")
    for (card <- hsCards1){
      val cardtmp: Card = Card(stringToValue(card.text))
      hstack1 = cardtmp +: hstack1
    }

    val hstack2N = (hstacks \\"hstack2")
    val hstack2N2 = (hstack2N \\"cC")
    val hsCards2 = (hstack2N2 \\"card")
    for (card <- hsCards2){
      val cardtmp: Card = Card(stringToValue(card.text))
      hstack2 = cardtmp +: hstack2
    }

    val hstack3N = (hstacks \\"hstack3")
    val hstack3N2 = (hstack3N \\"cC")
    val hsCards3 = (hstack3N2 \\"card")
    for (card <- hsCards3){
      val cardtmp: Card = Card(stringToValue(card.text))
      hstack3 = cardtmp +: hstack3
    }

    val hstack4N = (hstacks \\"hstack4")
    val hstack4N2 = (hstack4N \\"cC")
    val hsCards4 = (hstack4N2 \\"card")
    for (card <- hsCards4){
      val cardtmp: Card = Card(stringToValue(card.text))
      hstack4 = cardtmp +: hstack4
    }

    //pstack
    val pstack = (p1 \\"stack")
    val pstackN2 = (pstack \\"cC")
    val psCards = (pstackN2 \\ "card")
    for(card <- psCards){
      val cardtmp: Card = Card(stringToValue(card.text))
      ps1 = cardtmp +: ps1
    }

    hand1 = hand1.reverse
    hstack1 = hstack1.reverse
    hstack2 = hstack2.reverse
    hstack3 = hstack3.reverse
    hstack4 = hstack4.reverse
    ps1 = ps1.reverse
    val player1 = Player(p1n, hand1, List(hstack1, hstack2, hstack3, hstack4), ps1)

    val p2 = (file \\ "p2")
    val p2n: String = (p2 \\ "name").text

    val handNodesp2 = (p2 \\ "cards")
    val handp2 = (handNodesp2 \\"cC")
    val handCardp2 = (handp2 \\ "card")
    for(card <- handCardp2){
      val cardtmp: Card = Card(stringToValue(card.text))
      hand2 = cardtmp +: hand2
    }
    //hepstacks
    val hstacksp2 = (p2 \\ "helpstacks")
    val hstack1Np2 = (hstacksp2 \\"hstack1")
    val hstack1N2p2 = (hstack1Np2 \\"cC")
    val hsCards1p2 = (hstack1N2p2 \\"card")
    for (card <- hsCards1p2){
      val cardtmp: Card = Card(stringToValue(card.text))
      hstack1p2 = cardtmp +: hstack1p2
    }

    val hstack2Np2 = (hstacksp2 \\"hstack2")
    val hstack2N2p2 = (hstack2Np2 \\"cC")
    val hsCards2p2 = (hstack2N2p2 \\"card")
    for (card <- hsCards2p2){
      val cardtmp: Card = Card(stringToValue(card.text))
      hstack2p2 = cardtmp +: hstack2p2
    }

    val hstack3Np2 = (hstacksp2 \\"hstack3")
    val hstack3N2p2 = (hstack3Np2 \\"cC")
    val hsCards3p2 = (hstack3N2p2 \\"card")
    for (card <- hsCards3p2){
      val cardtmp: Card = Card(stringToValue(card.text))
      hstack3p2 = cardtmp +: hstack3p2
    }

    val hstack4Np2 = (hstacksp2 \\"hstack4")
    val hstack4N2p2 = (hstack4Np2 \\"cC")
    val hsCards4p2 = (hstack4N2p2 \\"card")
    for (card <- hsCards4p2){
      val cardtmp: Card = Card(stringToValue(card.text))
      hstack4p2 = cardtmp +: hstack4p2
    }

    //pstack
    val pstack2 = (p2 \\"stack")
    val pstack2N2 = (pstack2 \\"cC")
    val psCards2 = (pstack2N2 \\ "card")
    for(card <- psCards2){
      val cardtmp: Card = Card(stringToValue(card.text))
      ps2 = cardtmp +: ps2
    }
    hand2 = hand2.reverse
    hstack1p2 = hstack1p2.reverse
    hstack2p2 = hstack2p2.reverse
    hstack3p2 = hstack3p2.reverse
    hstack4p2 = hstack4p2.reverse
    ps2 = ps2.reverse
    val player2 = Player(p2n, hand2, List(hstack1p2, hstack2p2, hstack3p2, hstack4p2), ps2)

    // cardsCovered

    val cc = (file \\ "cover")
    val cc2 = (cc \\ "csC")
    val cstackN2 = (cc2 \\ "card")
    for(card <- cstackN2){
      val cardtmp:Card = Card(stringToValue(card.text))
      covered = cardtmp +: covered
    }
    covered = covered.reverse
    game = Game(List(stacks, stacks2, stacks3, stacks4), List(player1, player2), covered)
    game

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
