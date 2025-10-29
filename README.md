#  Midas Core — JP Morgan Chase Software Engineering Virtual Experience

##  Overview
**Midas Core** is a modular financial transaction processing system built as part of the **JP Morgan Chase Software Engineering Virtual Internship** on Forage.  
The project simulates how large-scale banking systems handle transactions, interact with external APIs, and expose RESTful endpoints — all while maintaining data integrity and architectural scalability.

Through five progressive engineering tasks, the system evolved from an empty scaffold into a production-ready backend service capable of validating transactions, integrating external APIs, and exposing user-facing endpoints.

---

##  Architecture
The Midas Core system is designed around **modularity and separation of concerns**, implementing:
- **Kafka Consumer Layer** – Handles real-time transaction ingestion.
- **Service Layer** – Manages validation, incentive calculation, and balance updates.
- **Persistence Layer** – Stores data in the H2 in-memory database using JPA entities.
- **External API Integration** – Connects to an Incentive microservice.
- **REST Controller Layer** – Exposes user balances via `/balance` endpoint.

---

##  Key Learning Objectives
- Architecting scalable financial systems.
- Ensuring atomicity and transactional safety using `@Transactional`.
- Integrating internal and external services via REST.
- Configuring and debugging Kafka consumers for reliable message delivery.
- Managing data persistence and mapping relationships via JPA.
- Deploying multi-port Spring Boot microservices.

---

##  Technology Stack
| Category | Tools & Frameworks |
|-----------|--------------------|
| Language | Java 17 |
| Framework | Spring Boot / Spring Data JPA |
| Messaging | Apache Kafka |
| Database | H2 (In-memory SQL) |
| Build Tool | Maven |
| Configuration | YAML |
| REST Client | RestTemplate |
| Testing | JUnit |

---

##  Task Breakdown

### **Task 1: Project Setup & Dependencies**
- Configured Maven dependencies and project structure.
- Fixed malformed POM errors and added missing libraries.
- Added `application.yml` to define the Kafka topic.
- Verified environment setup via `TaskOneTests`.

---

### **Task 2: Kafka Consumer Integration**
- Implemented a Kafka listener using `@KafkaListener`.
- Configured deserializers, trusted packages, and consumer groups.
- Resolved offset and message consumption issues.
- Successfully consumed and printed incoming transactions.

---

### **Task 3: Database Integration & Transaction Validation**
- Introduced `TransactionRecord.java` as a JPA entity.
- Created `TransactionRecordRepository` and enhanced `UserRepository` with `findByName()`.
- Implemented `TransactionService.java` with:
  - Validation (user existence and balance sufficiency).
  - Atomic updates using `@Transactional`.
- Persisted valid transactions into the H2 database.
- Verified data integrity — final balance for *waldorf*: **627**.

---

### **Task 4: Incentive API Integration**
- Integrated external REST API (`http://localhost:8080/incentive`) using `RestTemplate`.
- Created `IncentiveApi.java` and `Incentive.java` for handling API communication.
- Implemented incentive-based logic:
  - Rewards credited to recipients.
  - Incentive not deducted from sender.
- Resolved circular dependency via `RestTemplateConfig.java`.
- Ensured data type safety (`float` ↔ `double` conversions).
- Final computed balance for *wilbur*: **3089**.

---

### **Task 5: Balance REST API Exposure**
- Added new REST endpoint `/balance?userId={id}` using `@RestController`.
- Configured Midas Core to run on port **33400**.
- Implemented `BalanceController.java` and fallback for non-existent users.
- Created `BalanceApiQuerier.java` to test REST endpoint.
- Resolved bean name conflict using unique component naming.
- Verified correct JSON serialization of `Balance` object.

---

##  Final System Capabilities
   Consumes transaction messages from Kafka.  
   Validates and records transactions in an H2 database.  
   Integrates with an external REST-based Incentive API.  
   Maintains accurate user balances with incentive rewards.  
   Exposes a secure REST API to query current user balances.

---

## Key Technical Skills Gained
- **Spring Boot Application Architecture**
- **Kafka Stream Processing**
- **Database Transaction Management**
- **External API Integration (REST)**
- **Entity Relationship Mapping (JPA)**
- **Debugging & Exception Resolution**
- **System Design Thinking**

---

##  Author
**Rakesh Roshan Allam**  
Software Engineer | Java Developer | Backend Systems Enthusiast  

Connect on [LinkedIn](www.linkedin.com/in/rakesh-roshan-allam) | [GitHub](https://github.com/RakeshRoshanA)

---

## Acknowledgment
This project was developed as part of the **J.P. Morgan Chase Software Engineering Virtual Internship** hosted on **Forage**.  
It provided an authentic experience of backend engineering workflows and enterprise-grade system integration.

---

