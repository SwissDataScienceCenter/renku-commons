package ch.datascience.service.models.resource

/**
 * Created by johann on 13/07/17.
 */
sealed abstract class ScopeQualifier( val name: String )

object ScopeQualifier {

  val scopes: Set[ScopeQualifier] = Set( StorageRead, StorageWrite, StorageCreate, BucketCreate )

  def valueOf( name: String ): ScopeQualifier = ScopeQualifier.apply( name )

  def apply( name: String ): ScopeQualifier = name.toLowerCase match {
    case StorageRead.name      => StorageRead
    case StorageWrite.name     => StorageWrite
    case StorageCreate.name    => StorageCreate
    case DeploymentCreate.name => DeploymentCreate
    case BucketCreate.name     => BucketCreate
    case ContextsWrite.name    => ContextsWrite
    case ContextsRead.name     => ContextsRead
    case ExecutionsWrite.name  => ExecutionsWrite
    case ExecutionsRead.name   => ExecutionsRead
  }

  case object StorageRead extends ScopeQualifier( "storage:read" )

  case object StorageWrite extends ScopeQualifier( "storage:write" )

  case object StorageCreate extends ScopeQualifier( "storage:create" )

  case object BucketCreate extends ScopeQualifier( "storage:bucket_create" )

  case object DeploymentCreate extends ScopeQualifier( "deploy:create" )

  case object ContextsWrite extends ScopeQualifier( "contexts:write" )

  case object ContextsRead extends ScopeQualifier( "contexts:read" )

  case object ExecutionsWrite extends ScopeQualifier( "executions:write" )

  case object ExecutionsRead extends ScopeQualifier( "executions:read" )
}
