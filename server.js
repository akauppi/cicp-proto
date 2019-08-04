//
// Serving following files, for development:
//  - index.html
//
// References:
//  - https://stackoverflow.com/questions/6084360/using-node-js-as-a-simple-web-server
//
require('http')
    .createServer( require('serve-static')('.') )
    .listen(3000)
