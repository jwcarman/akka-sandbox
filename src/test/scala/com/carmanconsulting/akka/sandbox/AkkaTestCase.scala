package com.carmanconsulting.akka.sandbox

import akka.testkit.{ImplicitSender, TestKit}
import akka.actor.ActorSystem
import org.junit.After

class AkkaTestCase extends TestKit(ActorSystem("AkkaTesting")) with ImplicitSender {

  @After
  def shutdownActorSystem(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

}
