# eCFR Simple Spring Boot Project

This project is a **minimal Java Spring Boot application** that downloads Title 1 from the **Electronic Code of Federal Regulations (eCFR) API**, stores it server-side as JSON, and provides APIs to retrieve and analyze the data.

---

## Features

- Download **Title 1** eCFR data from the official API.
- Store **current** and **previous** snapshots in the `data/` folder.
- Analyze the data for:
  - Word count
  - Unique terms
  - Average sentence length
  - SHA-256 checksum
- Minimal front-end with:
  - `index.html`
  - `style.css`
  - `app.js` in `/static/` folder
- Simple REST endpoints:
  - **POST** `/api/download` — download Title 1 (saves previous snapshot)
  - **GET** `/api/current` — retrieve current snapshot metrics
  - **GET** `/api/previous` — retrieve previous snapshot metrics

---

## Technology Stack

- **Java 17** (or compatible)
- **Spring Boot 3.x**
- **Maven** for build management
- No database required (server-side storage is file-based)
- Minimal front-end (HTML, CSS, JS)

---

## Setup & Run

1. **Clone the repository**

```bash
git clone <repository-url>
cd ecfr-simple


2. Configure storage directory

Set the storage directory in src/main/resources/application.properties:

ecfr.storage.dir=./data


This folder will store:

current.json — latest download
previous.json — previous snapshot

3. Build the application
    mvn clean install


4. Run the application
    mvn spring-boot:run

The application will start on http://localhost:8080/



## Justification

1. **Title 1 as representative dataset**  
   - Title 1 (“General Provisions”) provides a structured but manageable dataset to demonstrate the required functionality.  
   - It contains multiple sections and parts, which is sufficient to showcase metrics like word count, unique terms, average sentence length, and checksum.  
   - The same architecture can easily be extended to additional titles or agencies.

2. **No database used**  
   - The instructions allow storing data server-side.  
   - Using local JSON files (`current.json` and `previous.json`) keeps the project simple, eliminates database setup, and satisfies the requirement to store snapshots.

3. **No Angular / minimal front-end**  
   - A lightweight front-end with `index.html`, `style.css`, and `app.js` in the Spring Boot `/static/` folder is sufficient to trigger downloads and view metrics.  
   - This keeps the project easy to understand and maintain, focusing on the required functionality.

4. **Custom metric support**  
   - The architecture supports custom metrics via `AnalysisService`.  
   - Additional metrics (e.g., frequent terms, change in word count) can be added without modifying the core pipeline.

5. **Compliance with assessment requirements**  
   - The project demonstrates:  
     - Downloading current eCFR data.  
     - Storing server-side snapshots.  
     - Providing APIs for retrieving metrics.  
     - Displaying metrics in a simple UI.  
   - The solution is **minimal, understandable, and fully functional**, meeting the core assessment objectives.
