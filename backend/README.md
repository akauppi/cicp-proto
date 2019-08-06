# Backend

## APIs

### GET `/others` 

Lists the users who've visited since the server was started. Adds you as a visitor for similar requests, by others.

Result:

- 200: `[ username, ... ]`

### PUT `forget`

Removes you from the visitors tracked and reported.

- 200: (text, to be ignored)

