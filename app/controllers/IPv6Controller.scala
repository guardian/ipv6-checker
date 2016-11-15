package controllers

import play.api.mvc.{Action, Controller}
import play.api.Logger

class IPv6Controller extends Controller {
  val allowOriginHeader = ACCESS_CONTROL_ALLOW_ORIGIN -> "*"

  def check = Action { request =>
    val response = request.headers.get("X-Forwarded-For") match {
      case Some(ipv6) if ipv6 contains ':' =>
        Logger.info(s"Detected $ipv6 as IPv6")
        Ok("ipv6")
      case Some(ipv4) => Ok("ipv4")
      case None => Ok("unknown")
    }
    response.withHeaders(allowOriginHeader)
  }
}