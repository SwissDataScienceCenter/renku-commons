# rest-api-definitions

This repository contains all the API definitions, using the OpenAPI Specification standard (http://swagger.io/specification/).

This is still in exploration phase and will change quite a lot until the first alpha version.

You can visualise the APIs using swagger-UI:

 * [definitions](https://swissdatasciencecenter.github.io/rest-api-definitions/swagger-ui/dist/?url=/rest-api-definitions/target/definitions.json)
 * [graph-navigation](https://swissdatasciencecenter.github.io/rest-api-definitions/swagger-ui/dist/?url=/rest-api-definitions/target/graph-navigation.json)
 * [graph-mutation](https://swissdatasciencecenter.github.io/rest-api-definitions/swagger-ui/dist/?url=/rest-api-definitions/target/graph-mutation.json)

Using node LTS v6.10.3

Install dependencies:

```sh
npm install
```

Start the local server, to have local swagger-ui reflecting changes:

```sh
npm start
```

Then open a browser at: http://localhost:3000

To manually build the files, do:

```sh
npm run make
```
