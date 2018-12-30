# cicp-proto

Simple proof-ground of how Google [Gloud Identity for Customers and Partners](https://cloud.google.com/identity-cp/) can be made to work.

Intentionally omits any web framework.

Based off the [Firenotes](https://github.com/GoogleCloudPlatform/python-docs-samples/tree/master/appengine/standard/firebase/firenotes) (GitHub) Google-provided sample, but unlike that one:

- login handled on a separate page (in Firenote's `index.html`, there are two "logged in" and "logged out" sections)
- no backend

## Aim

Authentication needs are slightly app specific. Here's our chosen playing field.

- **No guests** - all access to the app must start with an authentication
- **Component implementation** - ideally, we wish to componentalize the authentication so it's really, really easy to add.
- **Session cookies** used for passing the login status
- **Just pasting in a URL should work**
	- If already logged in, one gets directly to the page.
	- If not logged in, one should eventually land in that view; this is a no-brainer but there are web apps that take one to a default page, instead.

## Path

Decided to not go [this way](https://medium.com/@evangow/server-authentication-basics-express-sessions-passport-and-curl-359b7456003d) (blog Oct 2017), since it gets complicated and needs a database. We want to store a JWT token in a session cookie.

Then [this](https://www.codementor.io/mayowa.a/how-to-build-a-simple-session-based-authentication-system-with-nodejs-from-scratch-6vn67mcy3) (blog Apr 2017) 

Then [this](https://paweljw.github.io/2017/09/vue.js-front-end-app-part-3-authentication/) (blog Sep 2017)


## Requirements

...

## References

...

