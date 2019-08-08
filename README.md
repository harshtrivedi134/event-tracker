# event-tracker
Java Spring Boot application implementing JMS and ActiveMq to track and persist application events in the  database.  
  
## **Multi-Module project** 
a) **Microservice1** . 
**event-tracker:** Spring Boot application/microservice consisting of a REST API endpoint to send event messages to a queue.(Test-1) 
    
b)**event-processor:** Spring Boot application/microservice which is consuming messages from the queue and persisting events int the database.(Test-2)
    
  
Queue implemented using **ActiveMQ**.
  
## **Setup(macOS)
  
1. clone the project.
  
2. Install postgres: `brew install postgres`.
  
3. Start postgres server `pg_ctl -D /usr/local/var/postgres start`

4. `psql postgres` and `CREATE ROLE username WITH LOGIN PASSWORD 'quoted password';`.
  
5. `ALTER ROLE username CREATEDB;`.  

6. `createdb event;`
   
7. Install activemq `brew install activemq`. It will run on the default port 61616. Go to `http://localhost:8161/admin/` to view the web console.

8. go to the project directory in a new terminal. `cd event-tracker`.
  
9. Run `mvn clean install` to check whether it compiles correctly. Then run `mvn clean spring-boot run` . 
This will start the event tracker application/microservice which pushes event to the queue.
  
10. Go to `http://localhost:8090/swagger-ui.html` to view the UI of the REST API. (Post endpoint)
  
11. Populate the request parameters and execute the request. 
  
12. Check queue status `EventTrackingQueue` in the web console `http://localhost:8161/admin/`. You will the see the pending message which is not consumed yet in the `Queues` section. 

  
13. Now in the project structure change directory to `event-processor` module. This microservice consumes the pending message in the queue and persists it in the `event` table of postgres db setup earlier. 
  
14. `mvn clean install` and `mvn clean spring-boot run` Look at the event message in the console logs. 

15. Check ActiveMq web cosole the pending message should be consumed.
  
16. Check `event` table ----> the message will be peristed. 







      
  
  
