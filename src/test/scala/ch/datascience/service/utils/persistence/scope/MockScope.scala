package ch.datascience.service.utils.persistence.scope

import javax.inject.{ Inject, Singleton }

import ch.datascience.graph.init.InitApplication.readResource
import ch.datascience.graph.init.{ TypeInit, TypeInitFormat }
import ch.datascience.graph.scope.persistence.dummy.DummyScope
import play.api.libs.json.{ JsValue, Json }

/**
 * Created by johann on 13/06/17.
 */
@Singleton
class MockScope @Inject() ( override protected val persistenceLayer: MockPersistenceLayer )
  extends Scope( persistenceLayer = persistenceLayer )
  with DummyScope {

  def init(): Unit = {
    val typeInitJson: JsValue = Json.parse( readResource( "/type_init.json" ) )
    val typeInit = typeInitJson.as[TypeInit]( TypeInitFormat )

    val propertyKeyDefinitions = for {
      propertyKey <- typeInit.propertyKeys
    } yield propertyKey.key -> propertyKey

    this.addPropertyDefinitions( propertyKeyDefinitions )

    val namedTypeDefinitions = for {
      namedType <- typeInit.namedTypes
    } yield namedType.typeId -> namedType

    this.addNamedTypeDefinitions( namedTypeDefinitions )
  }

}
