//
// Serving following files, for development:
//  - index.html
//
// References:
//  - https://stackoverflow.com/questions/6084360/using-node-js-as-a-simple-web-server
//
const app = require('http').createServer(
    require('serve-static')('static', { 'index': ['index.html'] })
)

const PORT = process.env.PORT || 8081;

app.listen(PORT, () => {
    console.log(`Serving static pages on port ${PORT}`);
});

