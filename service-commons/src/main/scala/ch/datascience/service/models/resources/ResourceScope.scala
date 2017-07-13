package ch.datascience.service.models.resources

/**
  * Created by johann on 13/07/17.
  */
sealed abstract class ResourceScope(val name: String)

object ResourceScope {

  val scopes: Set[ResourceScope] = Set(StorageRead, StorageWrite, StorageCreate)

  def valueOf(name: String): ResourceScope = ResourceScope.apply(name)

  def apply(name: String): ResourceScope = name.toLowerCase match {
    case StorageRead.name => StorageRead
    case StorageWrite.name => StorageWrite
    case StorageCreate.name => StorageCreate
  }

  case object StorageRead extends ResourceScope("storage:read")

  case object StorageWrite extends ResourceScope("storage:write")

  case object StorageCreate extends ResourceScope("storage:create")

}
