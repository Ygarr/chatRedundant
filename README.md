Simple chat for websockets techology acquisition.

index.jsp contains form for user's nickname and password
 entering and binding it
 to new User's instance.


It is passed to Spring MVC controller MainController.java. 
Controller creates new ModelAndView
 linked to chat.jsp view page
and passes User's instance to it

for user's nickname displaying in the header
and in the chat window.

chat.jsp also contains js code providing connection
to websocket endpoint(WHY! FOR WHAT!) in Chat.java.

TODO: remove to the Underground JavaScript dependency on other PC, hibernate text config, UTF-DB support.