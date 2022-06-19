# SpringOAuth2WebClientTest
#Pre-requisite - KeyCloak server is up and running, client configured.
#Update application.properties file in each application for client id, client secret.

Test URLs
http://localhost:8083/photon2/albumsDataWebClientImpl   - Testing Photon2AppWebClient using WebClient, 
http://localhost:8083/photon2/albumsData   - Testing Photon2AppWebClient using RestOperations(RestTemplate)
http://localhost:8083/albums  - Albums application - Resource Server
http://localhost:8083/photoAlbums/Welcome 
http://localhost:8083/photoAlbums/albums  - Testing PhotoAppWebClient using RestOperations(RestTemplate)
