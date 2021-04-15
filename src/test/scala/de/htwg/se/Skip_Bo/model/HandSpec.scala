package de.htwg.se.Skip_Bo.model

import org.scalatest.{Matchers, WordSpec}

class HandSpec extends WordSpec with Matchers{

  "A Hand" when{
      "start of new round" should{
        val Hand = Hand()
        "have value <= 5" in{
          Hand.size should be <= 5
        }

    }
  }


}
