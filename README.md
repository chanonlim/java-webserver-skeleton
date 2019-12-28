# Java Web Server Skeleton
Welcome! This is the GitHub repository of my Java Web Server Skeleton.  
It can be a base for many applications as it includes a session tracking engine.  
This skeleton is also very hackable, so you can toy around with it.  
and, this skeleton is also packaged neatly in packages! (pun unintended)  
This skeleton includes
- a MainPage handler for handling your main page and 404s
- a SessionRequestHandler interface and a SessionHandler handler for handling user-sessions
- a StaticFile handler for serving static content
- and a Utils file for doing certain things!  

You should already be slightly proficient with Java and using com.sun.net.httpserver to use this.  
Here's a little more detail about what each package does.
- Main: the main file of the program. Used for configuration if needed. Create contexts here.
- Handlers: the place where handlers are stored, one per file
- Model: a place where JavaBean models can be stored
- Utils: the place where utilities are stored.
