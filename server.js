//
// Serving following files, for development
//
// References:
//  - https://stackoverflow.com/questions/6084360/using-node-js-as-a-simple-web-server
//

// Based on https://www.npmjs.com/package/serve-static#simple
//
const express = require('express');
const serveStatic = require('serve-static');

const app = express();

app.use( serveStatic('static', { 'index': ['index.html'] }) );

const PORT = process.env.PORT || 8081;

app.listen(PORT, () => {
    console.log(`Serving static pages on port ${PORT}`);
});
