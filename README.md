# ABS

Traveling is a burden, from packing the bags, booking a ticket or even boarding at the airport. Therefore, if the person manages to save time by booking his ticket online, the whole process would become easier. Flight reservation system has a lot of advantages. To start with, it saves a lot of time. Nowadays, it’s becoming very difficult to multitask during the day, thus imagine that you can eat and reserve a ticket instead of actually driving a certain distance to book a ticket and wasting a lot of time. Saving time is always very important especially for people engaged in business. Also, it saves money since online tickets are usually cheaper since it doesn’t require any expenses as a tourism agency. In addition, the customer may be able to book his ticket at any time since the system is active 24/7. The system presents all airlines with specific details about their flights. The user will be able to choose between different flights in the easiest way possible without having to call a travel agency and ask them a lot of questions at the same time.  While building the application, we should consider the type of users that will be interested in this application. Our target starts from parents on vacation to businessmen; therefore, we will try to include different airline classes. The flights price would range from middle-class income to high-class income.

The application is easy to use, it is very direct. All the user has to do is sign up to create a account. Then, he can log in to book a flight. The flights will appear as huge icons with pictures of the country of destination. Once user book a flight, he verifies it through a message sent on his phone number. Then, payment is done and reservation is complete. Now, every time the user opens his username, a small reminder will keep showing until the time of his flight. The application is built in a way that no two flights that overlap in time can be booked at the same time. A special feature is that once the user is present in the airport on his day of traveling, he has the right to log in as a passenger checker to verify his arrival. The system would check him in and, thus, his name wouldn’t appear under the function absent in flight... The application is basically managed by the admin, who is the only person that have accessibility to the dashboard.


![hy](https://user-images.githubusercontent.com/104697889/189723324-c1de12af-cb67-4a5d-8a60-89a939365b4e.PNG)

 If the user already has an account, he will directly log in. However, if the user doesn’t have an account, the user will start by signing up, he will enter all the required data and create an account.  The data are a user name, a password, full name, gender, address, and age and phone number. If the username already exists in the database, an error message will appear, stating that we cannot use this username. Once the username is accepted, the account is activated; the user will be able to log in using his username and password.
 
 Upon logging in, the user will be able to scan all the available flights, presented in the system’s database.  
 
![2](https://user-images.githubusercontent.com/104697889/189723251-2e98778b-6bb7-4595-ba0f-fbf5f5cd03e6.PNG)

When the user chooses the flight, another interface appears, where filling a form to complete his reservation should complete the registration process. The form includes the username, number of children that will travel, the class of the flight, and if there is another user he want to reserve to. If there are no children coming with the user, he has to put zero. Also, upon choosing the flight, the price of each class will appear in red beneath the box. If the user clicks the box of “other user with you”, the system allows him to reserve for another user by entering the other user’s username, his password, and if there are any children

![3](https://user-images.githubusercontent.com/104697889/189723276-3f683428-5253-4be5-9f14-253d93fa76ee.PNG)

Once done, he clicks the reserve and pay button. A new interface pops out, it shows the number of tickets booked, the total amount of money required, and the name of the users at the top. Then, the user should enter the card number, the cardholder’s name, expiry date of the card, and the security code. Then, he clicks the place reservation and pay button.  

![hy4](https://user-images.githubusercontent.com/104697889/189723349-80e4a2ce-2653-4384-bc6a-430d354e6b4d.png)

Once his reservation is completed, the user would be able to return to the welcome page. A small paragraph appears at the top of the interface indicating the flight destination, the flight date, the time at which the flight will leave. Then, the user could log out. If the user logs in at any time, the reminder with the flight information will always appear until his flight date.

![33](https://user-images.githubusercontent.com/104697889/189726139-abf66578-23a7-4d3b-8abd-81edab0ed60c.PNG)

The application is managed by an admin. The admin logs in by entering his username and password. A specific dashboard will appear. The dashboard contains very important information. At the right of the dashboard, multiple buttons appear for the users, flights, airlines, reservations, makes, and passengers. In the center of the dashboard, each button also appears in the form of an icon.

![4](https://user-images.githubusercontent.com/104697889/189723375-1b045844-8a03-4e59-bc78-58bff457ba39.PNG)

Once he clicks on the button, a table appears with all details specific to each topic. The users button once clicked, a table containing all the registered users appear. In the table, all the data filled by the users appear as columns, from the Id of the user, full name, gender, address, age, number of children to the phone number. The admin can also add or delete any user.

The flight button once clicked, a table containing all the flights appear. In the table, the columns will represent the number of flights, date of the flight, number of airlines, destination, time of taking off of the plane, and time of plane arrival to destination. The airline button once clicked, a table with two columns appear, one representing the ID and one the airline name. The makes button once clicked, a table with 7 columns appear, representing the reservation number, ticket amount, total price, date of flight, flight number, class and destination. The passengers button once clicked, a table with two columns appear, one representing user ID and one the reservation number. 
In the dashboard, another button under the name of absent in flight appears, the admin can write the number of flights he wants to know if anyone missed the flight. Once he submits the number of flights, the absent user’s information appears, which are the customer ID, his username, his full name and number of children. At the bottom, a sentence stating the total number of absent customers is written.


![hy5](https://user-images.githubusercontent.com/104697889/189723389-4f40831a-9b57-4ba9-be6a-327655d2b218.PNG)

A button for who else reserve appears. The admin enters the reservation number and submit it. A statement appears stating the number of users under this reservation. Also, a button for the total number of one reserve is present, we click it and then write the number of reservations, then a statement stating the total number of members for this reservation appears. Also, a button for the total number of passengers for one flight appears, we enter the flight number and submit. The passenger ID, username, full name, and children appear. A statement stating the total number of passengers on this flight appears. An update ticket cost button is also present, where admin should enter the reservation number and the new ticket amount, he wants to change. 

![hyy](https://user-images.githubusercontent.com/104697889/189723410-e1fc00a1-6cbe-44d8-a449-288c9c554b4c.PNG)

<h1>Passenger checker</h1>

At the airport, the user has to log in as a passenger checker so that the system takes his attendance. The passenger will enter his username and password. Once he logs in, a welcoming interface appears indicating the flight destination. The passenger would be asked to validate the number of children who came with him. 

![pp](https://user-images.githubusercontent.com/104697889/189727624-84654030-1486-4820-a278-7e089d6be86b.PNG)
![bbb](https://user-images.githubusercontent.com/104697889/189727702-38d5100e-981d-4477-aaf3-9863f5b43ce1.PNG)

