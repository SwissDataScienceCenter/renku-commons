SBT = sbt
SBT_COMPILE_TARGET = compile
SBT_DOCKER_TARGET = docker:publishLocal

sbt_graph_services += graph-typesystem-service
sbt_graph_services += graph-mutation-service

sbt_platform_services += resources-manager-service

sbt_services += $(sbt_graph_services)
sbt_services += $(sbt_platform_services)

sbt_services-docker = $(foreach s,$(sbt_services),$(s)-docker)

.PHONY: all
all: docker-all

.PHONY: docker-all
docker-all:	$(sbt_services-docker)

.PHONY: $(sbt_services-docker)
$(sbt_services-docker):
	$(eval target = $(subst -docker,,$@))
	cd $(target) && $(SBT) $(SBT_DOCKER_TARGET)
