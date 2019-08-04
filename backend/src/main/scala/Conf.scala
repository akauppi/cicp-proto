package backend

import com.typesafe.config.ConfigFactory

object Conf {
  import collection.JavaConverters._

  private
  val c = ConfigFactory.load.getConfig("app")

  val port: Int = c.getInt("port")
}

