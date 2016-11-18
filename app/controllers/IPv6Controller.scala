package controllers

import play.api.mvc.{Action, Controller}
import play.api.Logger

class IPv6Controller extends Controller {

  val allowOriginHeader = ACCESS_CONTROL_ALLOW_ORIGIN -> "*"

  def check = Action { request =>
    val xForwarderFor = request.headers.get(X_FORWARDED_FOR)
    val ips = xForwarderFor.toList flatMap toListOfIps
    val response = ips.reverse match {
      case ip :: tail if isIpv6(ip) =>
        Logger.info(s"${request.headers(HOST)} detected $ip as IPv6 - raw: $xForwarderFor")
        Ok("ipv6")
      case ip :: tail => Ok("ipv4")
      case _ => Ok("unknown")
    }
    response.withHeaders(allowOriginHeader)
  }

  private def isIpv6(str: String): Boolean = str.contains(":")

  private def toListOfIps(str: String): List[String] = str.split(",").toList.map(_.trim)

}