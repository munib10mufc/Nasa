# Nasa
Nasa Eonet project is self learning restful webservice implemented in Java using Spring boot framework, hibernate ORM, Spring security, 
JWT tokens and Swagger 2 for api documentation. Webservice can register user, validate user to get JWT token for further processing. 
This webservice pulls data from NASA servers to identify if any disaster is taking place or it's about to take place. 
System creates a boundry box around the region specified in request and gets disasters information from NASA servers.

# Sequence is as follow:

1- User registers itself using Post: /nasa-eonet/register
2- User authenticates itself using and uses JWT token returned by this api using Post: /nasa-eonet/authenticate
3- User can get information of available disaster categories using Get: /nasa-eonet/getcategories
4- User can get information of available disaster sources list using Get: /nasa-eonet/getsources
5- User can get information of disasters in boundry box around region specified by user using Post: /nasa-eonet/disasterInquiry
