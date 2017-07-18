package controllers

import javax.inject._

import controllers.storageBackends.Backends
import org.pac4j.core.profile.{CommonProfile, ProfileManager}
import org.pac4j.play.PlayWebContext
import org.pac4j.play.store.PlaySessionStore
import play.api.libs.streams._
import play.api.mvc._

import scala.collection.JavaConversions.asScalaBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.matching.Regex
import scala.util.Try


@Singleton
class IOController @Inject()(config: play.api.Configuration, val playSessionStore: PlaySessionStore, backends: Backends) extends Controller {

  private def getProfiles(implicit request: RequestHeader): List[CommonProfile] = {
    val webContext = new PlayWebContext(request, playSessionStore)
    val profileManager = new ProfileManager[CommonProfile](webContext)
    val profiles = profileManager.getAll(true)
    asScalaBuffer(profiles).toList
  }

  val RangePattern: Regex = """bytes=(\d+)?-(\d+)?.*""".r


  def objectRead = Action.async { implicit request =>
    Future {
      val profile = getProfiles(request).head
      val bucket = Try(profile.getAttribute("bucket").toString)
      val name = Try(profile.getAttribute("name").toString)
      val backend = Try(profile.getAttribute("backend").toString)

      backends.getBackend(backend.getOrElse("")) match {
        case Some(back) =>
          (for { n <- name; b <- bucket } yield
              back.read(request, b, n) match {
                case Some(dataContent) => Ok.chunked(dataContent)
                case None => NotFound
              }).getOrElse(BadRequest)
        case None => BadRequest(s"The backend $backend is not enabled.")
      }
    }
  }

  def objectWrite = EssentialAction { req =>

        val profile = getProfiles(req).head
        val bucket = Try(profile.getAttribute("bucket").toString)
        val name = Try(profile.getAttribute("name").toString)
        val backend = Try(profile.getAttribute("backend").toString)

        backends.getBackend(backend.getOrElse("")) match {
          case Some(back) =>
            (for {n <- name; b <- bucket} yield
                    back.write(req, b, n)
              ).getOrElse(Accumulator.done(BadRequest))
          case None => Accumulator.done(BadRequest(s"The backend $backend is not enabled."))
        }
      }


  def bucketCreate = Action.async { implicit request =>
    Future {
      val profile = getProfiles(request).head
      val bucket = Try(profile.getAttribute("bucket").toString)
      val backend = Try(profile.getAttribute("backend").toString)

      backends.getBackend(backend.getOrElse("")) match {
        case Some(back) =>
          bucket.map( b =>
            if (back.createBucket(request, b))
              Created
            else
              Conflict
          ).getOrElse(BadRequest)
        case None => BadRequest(s"The backend $backend is not enabled.")
      }
    }
  }
}