package com.carmanconsulting.akka.sandbox

import org.junit.Test
import akka.actor.Props

class HelloTest extends AkkaTestCase {

  @Test
  def testHello(): Unit = {
    val hello = system.actorOf(Props[HelloActor], "hello")
    hello ! "Akka"
    expectMsg("Hello, Akka!")
  }

}
