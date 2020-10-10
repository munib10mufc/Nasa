# Nasa
Nasa Eonet project is self learning restful webservice implemented in Java using Spring boot framework, hibernate ORM, Spring security, JWT tokens and Swagger 2 for API documentation. Application is deployed in docker containers. 

Webservice can register user, validate user to get JWT token for further processing. This webservice pulls data from NASA servers to identify if any disaster is taking place or it's about to take place. System creates a boundary box around the region specified in request and gets disasters information from NASA servers.

# Sequence is as follow:

- User registers itself using Post: /nasa-eonet/register
- User authenticates itself using and uses JWT token returned by this api using Post: /nasa-eonet/authenticate
- User can get information of available disaster categories using Get: /nasa-eonet/getcategories
- User can get information of available disaster sources list using Get: /nasa-eonet/getsources
- User can get information of disasters in boundry box around region specified by user using Post: /nasa-eonet/disasterInquiry
