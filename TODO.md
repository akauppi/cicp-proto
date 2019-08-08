# Todo

- [ ] Getting this to work (backend)

- [ ] Deploy

  - Firebase hosting: https://firebase.google.com/docs/web/setup#install-cli_deploy

## Frontend

- Add [Microsoft](https://firebase.google.com/docs/auth/web/microsoft-oauth)
  
   - This seems to need creation of a new Azure account (my existing @outlook.com identity is not enough to create an app); leaving it for now. Contributions welcome!!
      
- Test "One tap sign up": https://developers.google.com/identity/one-tap/web/
  - follow-up availability; currently says "beta ended" (Aug 2019)

## Backend

- Tests. It's bad example to not make such. `cd backend; sbt test` should check that the backend works. (contributions welcome! 🤩
 
 

<!-- disabled (bring back if non-empty)
## Bugs

<!_-- tbd. likely fixed?
- The static server sometimes fails:

   ```
Serving static pages on port 8081
/Users/asko/Git/cicp-proto/node_modules/serve-static/index.js:121
      next()
      ^
   ```

   Use some other arrangement than `static-server` npm module?
-->

   
   
