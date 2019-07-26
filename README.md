Peanut - Technical interview take-away exercice.

## Newsfeed

We'd like you to start building Newsfeed, a small social network app with a
single newsfeed.

### Introduction

To prepare for the technical interview, we'd like you to create a minimal app
that presents a feed of posts to the user.

- Use our API to fetch the newsfeed (documented below).
- Show the content on a vertical list. This should simply list the title and
the first few lines of the text of the body. You are not being tested on
UI design, so please use the provided layouts.
- As this is a paginated API we expect the app to fetch more content
automatically as the user scrolls.
- Display a loading spinner at the bottom of the newsfeed while fetching the
next page, please use the provided layout.

### API

For the newsfeed you'll be working with one endpoint:

```
http://exercice.production.backend.teampeanut.com/posts
```

#### Request format

All request use Basic HTTP authentication.
As this is an exercice, all usernames and passwords are valid.
We encourage you to use your first name as the username.

Pagination is supported by passing  a cursor as a query parameter `after`.
For example `/posts?after=next_cursor`.

#### Response format

```
{
    "posts": [
        {
            // uid is a required unique field
            "uid": "post_uid",
            // title is a required field
            "title": "Post title",
            // body is an optional field that might be missing from the response
            "body": "Post body"
        },
        // etc.
    ],
    "paging":  {
        // next_cursor will be empty if we are on the last page.
        "next_cursor": "a3d"
    }
}
```

### Your solution

#### Submitting

Please provide your solution in a format that we can easily compile and run,
on Android we would expect to be able to run this command:

```
# ./gradlew clean installDebug
```

#### Tips

Please use the tools and libraries you feel most confident in and fully
implement the requirements.

This is your opportunity to show us how you think an app should be architected,
and to what degree.

During the second stage interview we will pair with you to extend the app. You
might want to read your code before that if it has been a while.
