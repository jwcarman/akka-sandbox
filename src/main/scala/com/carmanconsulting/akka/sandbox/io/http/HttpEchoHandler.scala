package com.carmanconsulting.akka.sandbox.io.http

import java.net.InetSocketAddress

import akka.actor._
import akka.io.Tcp
import spray.http.HttpMethods._
import spray.http.{HttpRequest, HttpResponse}

class HttpEchoHandler(remote: InetSocketAddress, connection: ActorRef) extends Actor with ActorLogging {
  context.watch(connection)

  override def receive: Receive = {
    case HttpRequest(GET, uri, _, _, _) => sender ! HttpResponse(entity = uri.path.toString)
    case _: Tcp.ConnectionClosed =>
      log.debug("Stopping, because connection for remote address {} closed.", remote)
      context.stop(self)
    case Terminated(`connection`) =>
      log.debug("Stopping, because connection for remote address {} died.", remote)
      context.stop(self)
  }
}

object HttpEchoHandler {
  def props(remote: InetSocketAddress, connection: ActorRef): Props =
    Props(new HttpEchoHandler(remote, connection))
}
