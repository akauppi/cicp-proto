# Cookies and JWTs

There are a few (that I know of) ways to keep the authentication alive. You perhaps shouldn't trust the first source, which is why I marked down ;) the approach taken and some references.

## Approach

Use a session cookie, storing the JWT token (hopefully) received from Google CICP APIs.

With server side (which creates the cookie) setting something called `SameSite=strict`, `secure=true` (only send cookies over https), `httponly` (don't allow JavaScript to access the cookie).

All authentication information is included in the JWT token - there's no database involved. You'd make a data store of some kind for a real app, but that's another story.


## References

- [Where to Store your JWTs – Cookies vs HTML5 Web Storage](https://stormpath.com/blog/where-to-store-your-jwts-cookies-vs-html5-web-storage) (blog, Jan 2016)
- [Please Stop Using Local Storage](https://dev.to/rdegges/please-stop-using-local-storage-1i04) (blog, Jan 2018)

