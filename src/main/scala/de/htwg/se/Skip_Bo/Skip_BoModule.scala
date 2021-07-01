package de.htwg.se.Skip_Bo

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.Skip_Bo.controller.controllerComponent.ControllerInterface
import de.htwg.se.Skip_Bo.controller.controllerComponent.controllerBaseImpl.{Controller => BaseController}
import de.htwg.se.Skip_Bo.model.CardComponent.Card
import de.htwg.se.Skip_Bo.model.GameComponent.GameBaseImpl.Game
import de.htwg.se.Skip_Bo.model.GameComponent.GameInterface
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerBaseImpl.Player
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerInterface
import de.htwg.se.Skip_Bo.model.fileIOComponent.fileIOInterface
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.Skip_Bo.model.fileIOComponent._

class Skip_BoModule extends AbstractModule with ScalaModule{

  val defaultSize:Int = 5
  val cards: List[Card] = List.empty
  val players: List[Player] = List.empty
  val stacks: List[List[Card]] = (0 until 4).map(_ => List.empty).toList

  override def configure(): Unit = {

    bindConstant().annotatedWith(Names.named("DefaultHandSize")).to(defaultSize)

    bind[ControllerInterface].to[BaseController]
    bind[GameInterface].to[Game]
    bind[PlayerInterface].to[Player]

    bind[List[Card]].annotatedWith( Names.named( "cards" ) ).toInstance( cards)
    bind[List[Player]].annotatedWith( Names.named("players")).toInstance( players )
    bind[List[List[Card]]].annotatedWith( Names.named("stacks")).toInstance( stacks )

    bind[fileIOInterface].to[fileIOXmlImpl.fileIO]

  }
}
