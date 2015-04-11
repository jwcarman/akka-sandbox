package com.carmanconsulting.akka.sandbox.io.http

import akka.actor.ActorSystem

object HttpEchoApp extends App {
  val system = ActorSystem("http-echo-system")
  system.actorOf(HttpEchoService.props("0.0.0.0", 8080), "http-echo-service")

  readLine(s"Hit ENTER to exit ...${System.getProperty("line.separator")}")
  system.shutdown()
}
