# Project Overview

## Technology Stack
- **Database:** PostgreSQL
- **Backend:** Spring Boot
- **Real-Time Updates:** WebSocket Messaging
- **Security:** Spring Security with @PreAuthorize for endpoint protection
- **Validation:** Spring Validations for JSON request validation
- **Persistence:** Spring Data JPA

## Database Structure
The project consists of the following tables:
1. **Car**
2. **User**
3. **Repair**
4. **RepairDetail**

## User Roles
The system supports three types of users:
- **CUSTOMER**
- **MECHANIC**
- **MANAGER**

## Features
- **Real-time updates** using WebSocket messaging.
- **Secure API endpoints** with role-based access with JWT using Spring Security.
- **Validated JSON requests** with Jakarta validation @NotNull, @NotBlank, etc.
- **Database operations** using Spring Data JPA.

---

## Future Enhancements
1. **Mobile Notifications:** Implement Firebase Cloud Messaging for real-time status updates and repair details.
2. **Real-Time Chat:** Utilize Redis Stream and WebSocket for chat messaging between users.
3. **Message Distribution:** Integrate Kafka for message distribution across services and instances.