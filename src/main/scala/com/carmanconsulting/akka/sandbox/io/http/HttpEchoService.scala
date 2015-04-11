package com.carmanconsulting.akka.sandbox.io.http

import akka.actor.{Props, Actor, ActorLogging}
import akka.io.IO
import spray.can.Http

class HttpEchoService(host: String, port: Int) extends Actor with ActorLogging {

  import context.system

  IO(Http) ! Http.Bind(self, host, port)

  override def receive: Receive = {
    case Http.Connected(remote, _) =>
      log.debug("Remote address {} connected.", remote)
      sender ! Http.Register(context.actorOf(HttpEchoHandler.props(remote, sender())))
  }
}

object HttpEchoService {
  def props(host: String, port: Int): Props =
    Props(new HttpEchoService(host, port))
}
