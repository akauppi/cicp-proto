# cicp-proto

Simple proof-ground of how Google [Cloud Identity for Customers and Partners](https://cloud.google.com/identity-cp/) can be made to work.

Intentionally omits any web framework.

<!-- is this still relevant?
Based off the [Firenotes](https://github.com/GoogleCloudPlatform/python-docs-samples/tree/master/appengine/standard/firebase/firenotes) (GitHub) Google-provided sample, but unlike that one:

- login handled on a separate page (in Firenote's `index.html`, there are two "logged in" and "logged out" sections)
-->

## Aim

Authentication needs are slightly app specific. Here's our chosen playing field.

- **No guests** - all access to the app must start with an authentication
- **Session cookies** used for passing the login status
- **Just pasting in a URL should work**
	- If already logged in, one gets directly to the page.
	- If not logged in, one should eventually land in that view [^1]
- **Single-page web app** that interfaces with a back-end REST API that uses authentication and needs to be able to validate it.

[^1]: This is a no-brainer but there are web apps that take one to a default page, instead.

Once you are in, you should be able to:

- see your logged in identity (something like `index.html#me`)
- see who other people have signed-up and maybe when (something like `index.html#others`)

The back-end service can be run just in-memory, without persistence.

When you want out, we offer:

- sign-out
- sign-off (being forgotten)

i.e. the user's id is likely shown in top right, with these options, if logged in.

### We're not into Firebase...

Though CICP is related to Firebase and we cannot quite avoid touching it, we wish to remain as independent of it as possible.



## Path so far..

Decided to not go [this way](https://medium.com/@evangow/server-authentication-basics-express-sessions-passport-and-curl-359b7456003d) (blog Oct 2017), since it gets complicated and needs a database. We want to store a JWT token in a session cookie.

Then [this](https://www.codementor.io/mayowa.a/how-to-build-a-simple-session-based-authentication-system-with-nodejs-from-scratch-6vn67mcy3) (blog Apr 2017) 

Then [this](https://paweljw.github.io/2017/09/vue.js-front-end-app-part-3-authentication/) (blog Sep 2017)

Picked this project up in Aug 2019. [...]



## Requirements

- `npm`
- `sbt`

### Your config

Create the file `static/config.js` that provides:

```
var API_KEY = "AIza...qab0";
var AUTH_DOMAIN = "<your-project>.firebaseapp.com";

// Google OAuth Client ID, needed to support One-tap sign-up.
//
// You see this in: GCP Console > APIs & Services > Credentials > "Web client (Auto-created for Google Sign-in)"
//
// Note: There were two other client id's on that page. Not sure if it matters, which one to pick.
//
var CLIENT_ID = '3145...e8fm.apps.googleusercontent.com';
```

> <font color=red>tbd. How secret are these? We do end up hosting them along the page.</font>



## Getting started

For things to work locally, we need to serve both the static files in `static/` (at port 8081) and the REST API (port 8082). 

1. Launch the static pages (this will watch for changes, so it's kind of dynamic after all):

   ```
   # in a separate terminal
   $ npm install
   $ PORT=8081 npm run serve-static
   ```

2. Compile and launch the REST API:

   ```
   # in a separate terminal
   $ cd back-end
   $ PORT=8082 sbt run
   ```

Note: If you use another port for the backend, adjust also `static/index.html`.

Now, open [http://localhost:8081](http://localhost:8081).






### Note: just opening `index.html` won't work

---

>Firebase and FirebaseUI do not work when executed directly from a file (i.e. opening the file in your browser, not through a web server). Always run firebase serve (or your preferred local server) to test your app locally.
[source](https://github.com/firebase/firebaseui-web)

---


## Further work

### Component implementation

Ideally, we wish to componentalize the authentication so it's really, really easy to add.


## References

- [CICP Quickstart](https://cloud.google.com/identity-platform/docs/quickstart-cicp) (Google Cloud documentation)
- Cloud Identity for Customers and Partners > Concepts > [Authentication](https://cloud.google.com/identity-cp/docs/concepts-authentication)
  - good intro to what CICP wants to be
- [firebaseui-web](https://github.com/firebase/firebaseui-web) (GitHub)
  - CICP really is mostly Firebase, so this applies.

