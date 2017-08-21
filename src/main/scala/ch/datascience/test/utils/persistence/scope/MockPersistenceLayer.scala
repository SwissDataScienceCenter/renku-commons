package ch.datascience.test.utils.persistence.scope

import javax.inject.Singleton

import ch.datascience.graph.types.{ NamedType, PropertyKey }
import ch.datascience.service.utils.persistence.scope.RemotePersistenceLayer

import scala.concurrent.Future

@Singleton
class MockPersistenceLayer()
  extends RemotePersistenceLayer( client = null ) {

  override def fetchPropertyFor( key: PropertyKey#Key ): Future[Option[PropertyKey]] = Future.successful( None )

  override def fetchPropertiesFor( keys: Set[PropertyKey#Key] ): Future[Map[PropertyKey#Key, PropertyKey]] = Future.successful( Map.empty )

  override def fetchNamedTypeFor( typeId: NamedType#TypeId ): Future[Option[NamedType]] = Future.successful( None )

  override def fetchNamedTypesFor( typeIds: Set[NamedType#TypeId] ): Future[Map[NamedType#TypeId, NamedType]] = Future.successful( Map.empty )

}
