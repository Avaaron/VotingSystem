**VoteRestController**

### getAllRestaurants
`curl -i -X GET http://localhost:8080/rest/restaurants`

#### deleteVote
(you can only cancel the voice that was created today and the current time before 11:00.
 If you want to test this query after 11.00, you must correct the value of the
  SEPARATED_TIME constant in the DateTimeUtil file to a later value. And change value 
  LocalDate.now() on LocalDate.of(2018, 9, 19) in VoteRestController.deleteVote())
  
`curl -i -X GET http://localhost:8080/rest/vote/cancel --user user2@yandex.ru:password`

### readMenu
(for test this query, replace fragment LocalDate.now() on LocalDate.of(2018, 9, 8) in VoteRestController.readMenu())

`curl -i -X GET -d "{\"id\":\"100005\",\"name\":\"Roga and Kopita\"}" -H "Content-Type: application/json" http://localhost:8080/rest/vote/menu --user user2@yandex.ru:password`
`curl -i -X GET -d "{\"id\":\"100005\",\"name\":\"Roga and Kopita\"}" -H "Content-Type: application/json" http://localhost:8080/rest/vote/menu --user admin1@gmail.com:admin`

### voting
`curl -i -X GET -d "{\"id\":\"100007\",\"date\":\"2018-09-19\",\"restaurant\":{\"id\":\"100005\",\"name\":\"Roga and Kopita\"}}" -H "Content-Type: application/json" http://localhost:8080/rest/vote/voting --user user3@yandex.ru:password`

### createRestaurant
`curl -i -X GET -d "{\"id\":\"null\",\"name\":\"Lapunte\"}" -H "Content-Type: application/json" http://localhost:8080/rest/admin/restaurant/add --user admin1@gmail.com:admin`

### crateMenu
`curl -i -X GET -d "{\"id\":\"100005\",\"name\":\"Roga and Kopita\"}" -H "Content-Type: application/json" http://localhost:8080/rest/admin/menu/add --user admin1@gmail.com:admin`

### createDish
`curl -i -X GET -d "{\"id\":\"null\",\"name\":\"created\",\"price\":\"300\"}" -H "Content-Type: application/json" http://localhost:8080/rest/admin/dish/add/?menuId=100007 --user admin1@gmail.com:admin`

### updateDish
`curl -i -X GET -d "{\"id\":\"100016\",\"name\":\"updated\",\"price\":\"1000\"}" -H "Content-Type: application/json" http://localhost:8080/rest/admin/dish/update/?menuId=100008 --user admin2@gmail.com:admin`

### deleteDish
`curl -i -X GET http://localhost:8080/rest/admin/dish/delete/100012 --user admin1@gmail.com:admin`