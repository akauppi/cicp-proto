# cicp-proto

Simple proof-ground of how Google [Cloud Identity for Customers and Partners](https://cloud.google.com/identity-cp/) can be made to work.

Intentionally omits any web framework.

Based on the [Firenotes](https://github.com/GoogleCloudPlatform/python-docs-samples/tree/master/appengine/standard/firebase/firenotes) (GitHub) Google-provided sample, but unlike that one:

- login handled on a separate page (in Firenote's `index.html`, there are two "logged in" and "logged out" sections)

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


### Firebase project

Create a project in the Firebase console.

Enable the following authentication mechanisms for it:

- [Email link authentication](https://firebase.google.com/docs/auth/web/email-link-auth)
- [Google sign-in](https://firebase.google.com/docs/auth/web/google-signin)
- [GitHub](https://firebase.google.com/docs/auth/web/github-auth)

<!-- my outlook.com was tied to another organization (joined a Teams); wasn't able to fulfill the instructions. Retry after Teams access is gone. 5-Aug-2019
- [Microsoft](https://firebase.google.com/docs/auth/web/microsoft-oauth)
--> 

Pick the configuration entry from [Firebase console](https://console.firebase.google.com) > (project) > `Project Settings` > `Web apps`[^2] to `static/index.html`.

[^2]: create an app if needed

<!-- disabled
---

Microsoft needs something like this:

<i>To begin building apps that sign in social and local accounts, you'll need to create an Azure AD B2C tenant. To begin, follow creating an Azure AD B2C tenant.</i> [source](https://docs.microsoft.com/en-us/azure/active-directory/develop/quickstart-create-new-tenant)

---
-->


---

**Note about Firebase tiers**

At the [Firebase console](https://console.firebase.google.com) > (your project), check what the lower left edge states. 

![](.images/firebase-blaze.png)

If it's "Blaze", you may consider changing to a free tier. Not sure if this affects the service-side use or not (validation of tokens when deployed to Google cloud). Please tell.


---

### Firebase service account (admin)

For the `backend` service to be able to talk to Firebase (e.g. validate users' identities), you need a configuration file with your service account's credentials.

- Visit [Add the Firebase Admin SDK to Your Server](https://firebase.google.com/docs/admin/setup/) (Firebase docs) and read the instructions.

- Visit [Firebase Console](https://console.firebase.google.com) > (your project) > (your app) > `Settings` > Service accounts > `Generate new private key`.

   - Pick up the private key file and place it in `backend/secrets/<name-it>.json`

We'll show you later how to provide that secrets file to the backend, so it can do its work. 

<!-- tbd. Add something like this once deployed:
Note that once deployed to Google ecosystem, authentication happens automatically ... 
-->


## Getting started

For things to work locally, we need to serve both the static files in `static/` (at port 8081)[^3] and the REST API (port 8082). 

[^3]: Firebase authentication does not work if you just open the `index.html` file. Instead, we serve it.

1. Launch the static pages (this will watch for changes, so it's kind of dynamic after all):

   ```
   # in a separate terminal
   $ npm install
   $ PORT=8081 npm run serve-static
   ```

   The port you choose here only matters to where you point your browser.
   
2. Compile and launch the REST API:

   ```
   # in yet another terminal
   $ cd back-end
   $ PORT=8082 sbt run
   ```

   This port matters more. If you choose another, also adjust `static/index.html`.

3. Open [http://localhost:8081](http://localhost:8081)

   You should see a browser asking you to log in.
   
   ![](.images/tbd.png)

4. Log in with one or multiple accounts (ask your friend to help?). 

   ![](.images/tbd.png)

	If you see the "others" list updated, the whole chain from browser -> Firebase auth -> backend -> Firebase Admin API and back works.
	
	If you have problems, please report to the [GitHub repo](http://github.com/akauppi/cicp-proto) as issues. :)


---

Note: 

>Firebase and FirebaseUI do not work when executed directly from a file (i.e. opening the file in your browser, not through a web server). Always run firebase serve (or your preferred local server) to test your app locally.
[source](https://github.com/firebase/firebaseui-web)

---


## Customizing

The basic framework is here. You can obviously change it to match your app, but this should get you going...

<!-- tbd. later
Also worth checking may be:

- [Firebase Realtime Database](...)
- [Cloud Firestore](...)

-->


## Deployment

*Disclaimer:* If the code works on your local machine, you can obviously deploy it in any cloud. Google and Firebase have some options for this. This is just a pick, but may help you further.



<font color=red>...tbd...</font>



<!-- Later:

- monitoring
- alerts
-->


## Further work

### User permissions

>The Firebase Admin SDK lets you set custom attributes on user accounts. With custom user claims, you can give users different levels of access (roles), which are then enforced in an application's security rules.
[source](https://firebase.google.com/docs/auth/admin/)

That means we can do some kind of roles management with Firebase (who can access what, e.g. making some people admins of their groups).

( Also Firebase Admin API states something about roles. )


## References

- [CICP Quickstart](https://cloud.google.com/identity-platform/docs/quickstart-cicp) (Google Cloud documentation)
- Cloud Identity for Customers and Partners > Concepts > [Authentication](https://cloud.google.com/identity-cp/docs/concepts-authentication)
  - good intro to what CICP wants to be
- [Easily add sign-in to your Web app with FirebaseUI](https://firebase.google.com/docs/auth/web/firebaseui) (Firebase docs)
- [firebaseui-web](https://github.com/firebase/firebaseui-web) (GitHub)
- [Introduction to the Admin Auth API](https://firebase.google.com/docs/auth/admin/) (Firebase docs)

CICP really is mostly Firebase UI/auth.

