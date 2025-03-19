Project Details:
1. Project is on master branch.
2.I have used Websocket Messaging for real time updates.
3.I have used Spring validations for requested json validating object fields.
4.Postgresql DB
5. I added spring security for securiting endpoints and checked Authority for these endpoints @PreAuthorize
6. Used spring-data-jpa for DB operations
7. Project consist of 4 tables: Car, User, Repair, RepairDetail
8. There are 3 types of users : CUSTOMER, MECHANIC, MANAGER

Suggestions for Next version : 
1. In the next steps For mobile notifications while status changes or if repair detail is added or updated, we can use Firebase cloud messaging.
2. Maybe We use Redis Stream and WebSocket for real-time chats
3. Maybe We use Kafka for message distributing for other services or instances. 

