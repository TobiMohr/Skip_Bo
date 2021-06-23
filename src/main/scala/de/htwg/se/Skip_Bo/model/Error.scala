package de.htwg.se.Skip_Bo.model

case class InvalidHandCard(i :Int) extends Throwable

case object InvalidMove extends Throwable