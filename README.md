# Android Clean MVVM architecture


The app is built in a multi-module structure and attempts to use the latest tools and libraries. In
a summary:

* Entirely written in Kotlin.
* Uses MVVM Architecture, Clean Architecture, Clean Code.
* Uses Room as data persistence, Retrofit for network request handling.
* Uses Kotlin Coroutines throughout threading.
* Uses Hilt for dependency injection
* Uses Material design Library
* Uses Open street map

### The basic flow looks like this :

<p align="center">
 <img src='https://user-images.githubusercontent.com/45559398/172233712-a350738b-453d-415d-a9e2-71838dad82d5.png' width='500'>
</p>

The Repository layer handles data operations. The app's data comes from a few different sources -
data is stored (either remotely or in a local cache for offline use), and the repository modules are
responsible for handling all data operations and abstracting the data sources from the rest of the
app.

The app uses an offline first algorithm. If online data can't be requested, but offline data is
available, use the offline data.

A lightweight domain layer sits between the data layer and the presentation layer and handles
discrete pieces of business logic off the UI thread.

## Sum up

For the purpose of providing an overview of this application, I have only included general information in
this document