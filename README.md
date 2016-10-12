Simple chat with websockets techology.

index.jsp - form with user's nickname and password
 to enter and binding them
 to new User's instance.

User passes through SpringMVCcontroller  - MainController.java. 

Controller creates new MAV linked 
SUDDENLY
to chat.jsp view page
and map User to it. To display user's nick in header and chat window.

chat.jsp also contains js code providing connection
to websocket endpoint(WHY! FOR WHAT! EXCLUDE!) in Controller Chat.java(with java.websocket.* imports)

TODO: remove to the Underground JavaScript dependency on other PC, hibernate text config, UTF-DB support.
