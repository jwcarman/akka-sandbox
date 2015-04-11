package com.carmanconsulting.akka.sandbox.flow

import akka.actor.ActorSystem
import akka.http.Http
import akka.http.model._
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.Source
import akka.http.model.HttpMethods._
import akka.stream.scaladsl.{ Flow, Sink }
import scala.concurrent.Future

object HttpFlowServer extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorFlowMaterializer()

  val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
    Http(system).bind(interface = "0.0.0.0", port = 8080)

  val requestHandler: HttpRequest => HttpResponse = {
    case HttpRequest(GET, uri, _, _, _) =>
      HttpResponse(entity=uri.path.toString())

    case _: HttpRequest =>
      HttpResponse(404, entity = "Unknown resource!")
  }


  val bindingFuture: Future[Http.ServerBinding] = serverSource.to(Sink.foreach { connection =>
    connection handleWithSyncHandler requestHandler
  }).run()
}
