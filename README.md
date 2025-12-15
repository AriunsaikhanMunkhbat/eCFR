 USDS eCFR Simple SpringBoot Project

This project is a simple Java SpringBoot application that downloads Title 1 from the eCFR API, stores it server-side as JSON, and provides APIs to retrieve and analyze the data.

---

Features

- Download eCFR data from the official API.

- Store current and previous snapshots in the data folder.

- Analyze the data for: word count, 
                        unique terms, 
                        average sentence length
                        SHA-256 checksum

- Front end UI includes index.html, style.css and app.js in resources/static/ folder

- REST endpoints are POST and GET
                        POST -> /api/download #it simply downloads the Title 1 and saves the previous snapshot)
                        GET -> /api/current #retrieve current data 
                        GET -> /api/previous # retrieve previous data

----

This project uses: Java 16, SpringBoot 2.7, Maven 3.11 and uses no Database   #See pom.xml for details 

---


Setup & Run

- Clone the repository

git clone https://github.com/eUSDSRecruiting/Ariunsaikhan-M-s-Take-Home-Assessment.git 


- Configure storage directory

Set the storage directory in src/main/resources/application.properties:
      
      ecfr.storage.dir=./data

This folder will store:
      current.json — latest download
      previous.json — previous snapshot

- Build the application
    mvn clean install


- Run the application
    mvn spring-boot:run

The application will start on http://localhost:8080/

Screenshot 
![Main Dashboard](Screenshots/Dashboard.png)

![After Download](Screenshots/After_Download.png)

---

Implementation Approach/Explaination:

For this assessment, I chose to work with Title 1 of the eCFR because it is well organized and large enough to demonstrate the required functionality without making the project too complex. Title 1 includes multiple sections and parts, which makes it a good candidate for analyzing metrics such as word count, unique terms, average sentence length, and checksums. This approach can also be expanded later to include additional titles or agencies if needed.
When choosing tools, I did consider using Python and related development tools; however, I had previously worked on a Spring Boot project using Java for a graduate school project, which made me more familiar and comfortable with that stack. Because of that experience, I decided to use Java and Spring Boot so I could focus more on the problem itself rather than learning a new framework at the same time. In general, I decided to keep the solution simple enough to understand quickly while still being easy to extend with additional metrics or features in the future. Given the time constraints and scope of a take-home assessment, I focused on building a solid working foundation. There is still plenty of room for improvement, and this is something I would expect to refine further through team discussions and collaboration around design decisions and future direction.