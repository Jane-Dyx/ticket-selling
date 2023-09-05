# ticket-selling
This is a record on the assignment with my friend kouqin in distributed system this year from March to June


Introduction 

The goal of this project is to implement a small enterprise system developed in Java using the concepts we have learned in the course about distributed systems. We decided to develop an event ticketing system for our university given the inspiration from our own campus experience: we had purchased tickets for an event organized by the music club, and the tickets were sold by filling out a form on Google Form. A few days later we received a message from the event promoter at Microsoft Teams that the tickets we had purchased were actually sold out and we needed to re- select new seats. Based on our knowledge, it could be handled by applying locking, synchronization, concurrency and scheduling concepts and programming techniques. In addition, this project has a more important meaning than facilitating campus life: today online ticketing has almost replaced offline ticketing counters, such as movie tickets, airline tickets, and even hotel reservations. The main issues these systems need to address are not selling the same ticket multiple times with high concurrent user access, as well as not having significant performance degradation and scalability (Zupanovic, 2015). This project will discuss and provide some solution ideas for the above problems through our mini-campus system.


Background

Client-Server Architecture
Client-server architecture refers to a computing model where clients and servers interact to distribute and manage resources or services. It involves dividing responsibilities between clients and servers, with clients initiating requests and servers providing the requested resources or services(Jan & Qayum, 2018). As a result, client-server architecture offers reliability and efficiency in handling a substantial volume of users and transactions within a ticketing system. This is achieved through the centralization of management, effective scalability and concurrency management, implementation of robust security measures, and optimization of system performance. By adopting a client-server architecture, the ticketing system can ensure reliable and efficient operations, accommodating a large number of users and transactions while maintaining data integrity, providing secure access, and delivering optimal performance (Hu et al., 2010).

Remote Procedure Call
Remote Procedure Call (RPC) is a mechanism that extends local procedure calls to enable the execution of procedures on remote machines over a network. It allows the calling environment to temporarily suspend, pass parameters to the remote procedure, execute it on the remote machine, and receive the results. RPC simplifies distributed computing by abstracting communication between machines, making it easier to interact with remote resources and integrate distributed systems seamlessly (Birrell & Nelson, 1983). In the context of a ticketing system, Remote Procedure Call (RPC) can be employed to facilitate various functionalities and interactions between the client-side and server-side components of the system. For example, when a user initiates a ticket purchase request through the client application, RPC can be utilized to invoke a remote procedure on the server-side that handles the ticket booking process. The client-side application would suspend temporarily while passing the necessary parameters, such as event details and seat preferences, to the server-side procedure via RPC.

Synchronisation
In a ticketing system, the occurrence of race condition during the ticket purchasing process can pose a challenge (Stergiopoulos et al., 2016). This situation arises when multiple users are simultaneously viewing the same seating area, but the seating availability information is not updated dynamically after the seating page is displayed. Consequently, there is a possibility that a seat may be purchased by one user while another user is in the process of viewing the same seat, resulting in the server unintentionally selling the same ticket to different individuals. To mitigate this issue, a synchronization mechanism is employed whereby the seat is locked during the first transaction. As a result, if the first transaction successfully reserves the seat, subsequent transactions attempting to purchase the same seat will be notified of the failure, indicating that the seat is no longer available.
By implementing this synchronization mechanism, the ticketing system ensures that only one user can successfully reserve a specific seat even more than one person transaction for the same seat (Lipari et al., 2004). The lock prevents other users from concurrently attempting to purchase the same seat, thereby avoiding conflicts and potential discrepancies in seat allocation. In cases where multiple users access the seating area simultaneously, this approach ensures that the first user to initiate the transaction secures the seat, and subsequent users are promptly informed of its unavailability to avoid any further contention or confusion. Such synchronization mechanisms enhance the reliability and accuracy of the ticketing system by maintaining consistency in seat reservations and preventing the occurrence of conflicting transactions.
Figure 1: keyword “synchronized” and transaction are used together


Functionalities

1. Client
• Login: Allow users to authenticate themselves using their email and password to access
the ticketing system.
• SignUp: Provide a registration process for new users to create an account in the ticketing
system with new email and password.
• Change Password: Enable users to change their password for account security purposes.
a. Admin
• SignUp: Provide a registration process for new users to create an account in the ticketing
system with new email and password.
• Change Password: Enable users to change their password for account security purposes.
• View current events: Allow the administrator to view a list of current events available for
ticket purchase.
• View current transaction of existed events: Provide the administrator with access to view the current total transaction data related to existing events.
• Create new events: Allow the administrator to create new events to the ticketing system with event title, time, venue and price.
b. Customer
• View current detailed information of current events: Enable customers to access detailed
information about the ongoing events, such as event descriptions, dates, venues, and ticket
prices.
• View seats situation: Provide customers with an overview of the seating arrangements and
availability for a specific event.
• Choose a seat: Allow customers to select their preferred seat(s) from the available options
for a particular event..
• Confirm transaction: Enable customers to confirm their ticket purchase, initiating the
transaction process.
• View history transaction: Allow customers to access the list of ticket purchase history,
displaying previously purchased tickets. 2. Server
• Display server function: Provide a detailed log or record of server operations along with timestamps to track the sequence and timing of various server activities.
 
System Architecture
 
The system adopts a client-server architecture (as shown in Figure 2), using MySQL on the server side for data persistence, and using remote procedure calls for communication between the client and the server, which is implemented using HTTPS to access the RESTful API provided by the server.
Thread Pool
In a ticketing system, client requests can be highly concurrent, and the number of clients is usually much higher than the number of servers, which means that the server must have the ability to handle multiple requests at the same time (Olmsted, 2016). One common approach is to use multiple threads, which can be created in Java by implementing the Runnable interface or by inheriting from the Threads class. However, as the number grows, too many new threads opened manually can deplete the server’s resources, and a better approach is to leave it to the thread pool to manage. The user can specify the size of the thread pool according to the demand and the performance of the server, so that the threads can be reused.
Figure 3: Configure thread pool and set parameters 5 of 10
 
SSL Layer
In a ticketing system, the adoption of HTTPS (HTTP Secure) for client-server communication is preferable to HTTP (Hypertext Transfer Protocol) due to several key reasons. HTTPS employs data encryption, safeguarding sensitive information exchanged between the client and server against interception and tampering. Moreover, it ensures data integrity by utilizing cryptographic protocols that verify the authenticity of the server and protect against unauthorized modifications. The implementation of SSL/TLS certificates in HTTPS enhances trust and authentication, reducing the risk of phishing and man-in-the-middle attacks. (Clark & van Oorschot, 2013) In our system, we generate our own server key pairs and issue our own certificates as a demonstration. In fact, in the real case, system owner should register the domain name and then apply for a certificate from authoritative certificate authority.


Limitation

System Functionalities
The system is not perfect in terms of functionality, such as the lack of payment interface, support for uploading images of activities also failed to complete, etc. The user interface of the client side also has some areas that need to be improved.

Server Cluster
In fact, in this scenario, a distributed system can simply be represented by the client and server running on different computers, and the server can also be considered to be deployed as a cluster rather than a single machine. The scenario in this project is a school event ticketing, where the number of users may not be large, but for a system with more users, such as a concert ticketing site, server clustering may be a necessary option. First of all, it is more economical and practical to have multiple hosts with average performance than to buy a single super host. Secondly, multiple hosts can do load balancing, prevent single point of failure, etc.

Data Cache
MySQL is the only database service in this system, but because MySQL is stored on the hard disk, a lot of reading and writing can make it a bottleneck in server performance (Ni et al., 2018). For data that is often read but not often modified, consider adding a layer of data caching services before MySQL, such as Redis, which is a key-value database that runs in memory and is therefore very fast, and also supports features such as clustering that help ensure high system availability.


Conclusion

In conclusion, the development of a distributed event ticketing system using the concepts of distributed systems has yielded a robust and efficient solution for managing ticket sales considering to XMUM conditions. By adopting a client-server architecture and leveraging remote procedure calls (RPC), synchronization, and concurrency management, the system successfully addresses common challenges in ticketing systems while ensuring reliability, performance, and scalability. The system's functionalities cater to the needs of different stakeholders, including clients, administrators, and customers. Users can authenticate themselves, view event details, select seats, and make transactions. Administrators have additional capabilities such as event creation and transaction monitoring. Although the system's functionality is comprehensive, future iterations could consider enhancements such as integrating payment interfaces and supporting image uploads to provide a more seamless and user-friendly experience.
Looking ahead, scalability and performance improvements can be achieved through server clustering and data caching. Server clustering enables load balancing, fault tolerance, and resource utilization optimization, making it suitable for systems with a growing user base. Data caching services like Redis can enhance performance by storing frequently accessed data in memory, reducing reliance on disk-based databases.
Overall, the development of the distributed event ticketing system demonstrates the practical application of distributed systems concepts. By incorporating client-server architecture, RPC, synchronization, and concurrency management, the system provides an efficient and reliable solution for ticket sales. With further refinements and optimizations, the system holds the potential to serve as an invaluable tool for managing ticketing operations in various event scenarios, delivering a seamless and satisfying experience for administrators and customers alike.
