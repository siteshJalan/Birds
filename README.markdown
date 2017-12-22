# Birds API

## Used libraries
 * MongoDb
 * Tomcat 7
 * Jersey

## Steps

- Install MongoDb
- Start mongod shell
	`mongod.exe --dbpath "c:\work\mongodb\data"`

- Install tomcat 7
- Import the given Dynamic Web project to your eclipse
- Configure server to Apache tomcat 7
- Launch the Project on Server(tomcat7)

Open any REST Client(Postman etc) or use Curl

## Test cases:

- GET http://localhost:8080/Birds/birds
  This should yield No result

- POST http://localhost:8080/Birds/birds
with Content-Type as application/json
payload: {"name":"Makau","family":"Parrot","continents":["USA","Africa"],"visible":true}

- POST http://localhost:8080/Birds/birds
with Content-Type as application/json
payload: {"name":"Parrot","family":"Parrot","continents":["Asia","Europe", "USA"],"added":"2012-12-14","visible":true}

- POST http://localhost:8080/Birds/birds
with Content-Type as application/json
payload: {"name":"Crow","family":"Crow","continents":["Asia","Europe"],"added":"2010-12-14"}

- GET http://localhost:8080/Birds/birds
  This should yield 2 result( id's for items added in 2(say "id1") and 3(say "id2"), 4 will not be yielded as visible will be set to false)

- GET http://localhost:8080/Birds/birds/id1
this should yield result the following item with today's date(UTC)
{ "_id" : { "$oid" : "566f0355e032d2cb2ec35411"} , "name" : "Makau" , "family" : "Parrot" , "continents" : [ "Asia" , "Europe"] , "added" : "2015-12-14" , "visible" : true}

- GET http://localhost:8080/Birds/birds/id2
this should yield result the following item with today's date(UTC)
{ "_id" : { "$oid" : "566f0b9ce032c2cd8abc33f3"} , "name" : "Parrot" , "family" : "Parrot" , "continents" : [ "Asia" , "Europe" , "USA"] , "added" : "2012-12-14" , "visible" : true}

- DELETE http://localhost:8080/Birds/birds/566f0b9ce032c2cd8abc33f3
this should delete the entry in 7 and return 200

- DELETE http://localhost:8080/Birds/birds/566f0b9ce032c2cd8abc33f3
this should do nothing as item is already deleted. Return 404

## Enhancements which should be further done:

a> Create unit test Case suite instead of manual testing
b> Create factory patterna nd supply mongo implementation as one of the available implementations
c> More error and exception handling
d> Code clean up.
e> Maven dependency clean up.
