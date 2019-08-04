# cicp-proto

<font color=red>tbd. This is unfinished.

The Google CICP is still (Aug 2019) in "beta" and its instructions are conflicting with those on the Firebase side.

They should make this easier.

</font>

---

Simple proof-ground of how Google [Gloud Identity for Customers and Partners](https://cloud.google.com/identity-cp/) can be made to work.

Intentionally omits any web framework.

Based off the [Firenotes](https://github.com/GoogleCloudPlatform/python-docs-samples/tree/master/appengine/standard/firebase/firenotes) (GitHub) Google-provided sample, but unlike that one:

- login handled on a separate page (in Firenote's `index.html`, there are two "logged in" and "logged out" sections)
- no backend

## Aim

Authentication needs are slightly app specific. Here's our chosen playing field.

- **No guests** - all access to the app must start with an authentication
- **Session cookies** used for passing the login status
- **Just pasting in a URL should work**
	- If already logged in, one gets directly to the page.
	- If not logged in, one should eventually land in that view [^1]

[^1]: This is a no-brainer but there are web apps that take one to a default page, instead.

Once you are in, you should be able to:

- see your logged in identity
- see who other people have signed-up (and maybe when)

The back-end can be run just in-memory, without persistence (that's out of scope).

When you want out, we offer:

- sign-out
- sign-off (being forgotten)


## Path so far..

Decided to not go [this way](https://medium.com/@evangow/server-authentication-basics-express-sessions-passport-and-curl-359b7456003d) (blog Oct 2017), since it gets complicated and needs a database. We want to store a JWT token in a session cookie.

Then [this](https://www.codementor.io/mayowa.a/how-to-build-a-simple-session-based-authentication-system-with-nodejs-from-scratch-6vn67mcy3) (blog Apr 2017) 

Then [this](https://paweljw.github.io/2017/09/vue.js-front-end-app-part-3-authentication/) (blog Sep 2017)

Picked this project up in Aug 2019. [...]





## Requirements

- `npm` version 6.x


...



## Getting started

`$ npm dev:serve`

<font color=red>tbd. This is unfinished.</font>



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

