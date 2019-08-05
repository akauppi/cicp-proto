# Todo

- Getting this to work (backend)

- Deploy

  - Firebase hosting: https://firebase.google.com/docs/web/setup#install-cli_deploy

- Add:
  - [Microsoft](https://firebase.google.com/docs/auth/web/microsoft-oauth)
  
        - This seems to need creation of a new Azure account (my existing @outlook.com identity is not enough to create an app); leaving it for now. Contributions welcome!!
      
- Test "One tap sign up": https://developers.google.com/identity/one-tap/web/
  - follow-up availability; currently says "beta ended" (Aug 2019)

- "Email link" that would work without user needing to set password (see [here](https://firebase.google.com/docs/auth/web/email-link-auth)). Asko tried Aug-19 but it looked like normal email sign-up. Maybe Firebase UI doesn't support it, yet?  (= we could consider doing code other than the UI, or fork it, fix it and contribute)
 

## Bugs

<!-- tbd. likely fixed?
- The static server sometimes fails:

   ```
Serving static pages on port 8081
/Users/asko/Git/cicp-proto/node_modules/serve-static/index.js:121
      next()
      ^
   ```

   Use some other arrangement than `static-server` npm module?
-->

   
   
