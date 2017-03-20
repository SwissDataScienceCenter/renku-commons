# rest-api-definitions

This repository contains all the API definitions, using the OpenAPI Specification standard (http://swagger.io/specification/).

This is still in exploration phase and will change quite a lot until the first alpha version.

You can visualise the APIs using swagger-UI:

 * [graph](https://swissdatasciencecenter.github.io/rest-api-definitions/swagger-ui/?url=/rest-api-definitions/target/graph.json)
 * [user](https://swissdatasciencecenter.github.io/rest-api-definitions/swagger-ui/?url=/rest-api-definitions/target/users.json)
 * [typesystem](https://swissdatasciencecenter.github.io/rest-api-definitions/swagger-ui/?url=/rest-api-definitions/target/typesystem.json)

To merge all YAML files into a JSON file, you can run (for example for the graph API):

´´´sh
node build.js src/graph.yaml
´´´

You may need to install nodejs, npm and json-schema-ref-parser

´´´sh
sudo apt install npm
npm install json-schema-ref-parser
´´´
