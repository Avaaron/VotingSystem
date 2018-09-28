**VoteRestController**

###getAllRestaurants
`curl -i -X GET http://localhost:8080/rest/vote/restaurants`

#### deleteVote
(you can only cancel the voice that was created today and the current time before 11:00.
 If you want to test this query after 11.00, you must correct the value of the
  SEPARATED_TIME constant in the DateTimeUtil file to a later value. And change value 
  LocalDate.now() on LocalDate.of(2018, 9, 19) in VoteRestController.deleteVote())
`curl -i -X GET http://localhost:8080/rest/vote/cancel --user user2@yandex.ru:password`

###readMenu
(for test this query, replace fragment LocalDate.now() on LocalDate.of(2018, 9, 8) in VoteRestController.readMenu())
`curl -i -X GET -d "{\"id\":\"100005\",\"name\":\"Roga and Kopita\"}" -H "Content-Type: application/json" http://localhost:8080/rest/vote/menu --user user2@yandex.ru:password`
`curl -i -X GET -d "{\"id\":\"100005\",\"name\":\"Roga and Kopita\"}" -H "Content-Type: application/json" http://localhost:8080/rest/vote/menu --user admin1@gmail.com:admin`
###voting
`curl -i -X GET -d "{\"id\":\"100007\",\"date\":\"2018-09-19\",\"restaurant\":{\"id\":\"100005\",\"name\":\"Roga and Kopita\"}}" -H "Content-Type: application/json" http://localhost:8080/rest/vote/voting --user user3@yandex.ru:password`
