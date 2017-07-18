#!/usr/bin/env node

const path = require('path')
const fs = require('fs')
const exec = require('child_process').exec
const commander = require('commander')
const yaml = require('js-yaml')

commander
  .option('-H, --host <host>', 'Replace host by this value')
  .option('-o, --output <dir>', 'Replace host by this value')
  .parse(process.argv)

const output = commander.output || 'dist'

console.log(relPath('node_modules'))

exec(`mkdir -p ${output}`, err => {
  if (err)
    unexpectedError(err)

  // Copy swagger-ui
  exec(`mkdir -p ${output}/node_modules`, err => {
    if (err)
      unexpectedError(err)
    exec(`cp -R ${relPath('node_modules/swagger-ui-dist')} ${output}/node_modules/.`, err => {
      if (err)
        unexpectedError(err)
      console.log('Copied swagger-ui')
    })
  })

  // Copy index.html
  exec(`cp -R ${relPath('index.html')} ${output}/.`, err => {
    if (err)
      unexpectedError(err)
    console.log('Copied index.html')
  })

  // List src and process files
  exec(`mkdir -p ${output}/src`, err => {
    fs.readdir(relPath('src'), (err, files) => {
      if (err)
        unexpectedError(err)
      files.forEach(file => {
        console.log(file)
        fs.readFile(path.join(relPath('src'), file), (err, data) => {
          if (err)
            unexpectedError(err)
          let content = yaml.safeLoad(data, file)
          if (commander.host !== undefined) {
            const portMatch = content['host'].match(/^(.*):(\d+)$/)
            const oldHost = portMatch ? portMatch[1] : content['host']
            const oldPort = portMatch ? portMatch[2] : null
            const newPortMatch = commander.host.match(/^(.*):(\d+)$/)
            const newHost = newPortMatch ? newPortMatch[1] : commander.host
            const newPort = oldPort ? oldPort : ( newPortMatch ? newPortMatch[2] : oldPort )
            const newHostFull = newPort ? `${newHost}:${newPort}` : newHost
            content['host'] = newHostFull
          }
          const result = yaml.dump(content)
          fs.writeFile(`${output}/src/${file}`, result, err => {
            if (err)
              unexpectedError(err)
            console.log(`Processed src/${file}`)
          })
        })
      })
    })
  })
})

function relPath(p) {
  return path.join(__dirname, p)
}

function unexpectedError(err) {
  console.log('Unexpected error: ', err)
  process.exit(1)
}
