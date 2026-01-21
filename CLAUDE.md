# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

SIP Header Validator is a Spring Boot application that validates Session Initiation Protocol (SIP) headers and complete messages using the JAIN-SIP library. It provides both a REST API and a web interface, with automatic RFC specification reference linking for validated headers.

## Commands

### Build and Run
```bash
# Build the project
./mvnw clean package

# Run tests
./mvnw test

# Run a specific test class
./mvnw -Dtest=TestClassName test

# Run the application (localhost:8080)
./mvnw spring-boot:run

# Run the packaged JAR
java -jar target/sip-validator-0.0.1-SNAPSHOT.jar
```

### Useful Maven Commands
```bash
# Compile without running tests
./mvnw compile

# Clean and install to local Maven repository
./mvnw clean install
```

## Architecture

### Component Layers

**Controllers** → **Service** → **JAIN-SIP Libraries** ← **RFC Mapping**

1. **Web Layer** (`controller/`)
   - `SipValidatorWebController`: Serves the Thymeleaf template at "/"
   - `SipValidatorRestController`: Exposes REST API endpoints:
     - `POST /api/sip/validate/message` - Validates complete SIP messages
     - `POST /api/sip/validate/header` - Validates individual headers
     - `GET /api/sip/health` - Health check

2. **Service Layer** (`service/`)
   - `SipValidatorService`: Core validation logic using JAIN-SIP factories
     - Distinguishes between SIP requests and responses based on first line
     - Normalizes line endings to CRLF per SIP specification
     - Extracts headers and enriches them with RFC references
   - `SipHeaderRfcMapping`: Static mapping of 44+ SIP header names to RFC specifications
     - Covers RFC 3261 (core), RFC 4028 (timers), RFC 6665 (events), and more
     - Includes compact form aliases (m→Contact, f→From, etc.)

3. **Model Layer** (`model/`)
   - `ValidationRequest`: DTOs with `messageText` or `headerText` fields
   - `ValidationResponse`: Contains `valid` flag, `messages[]`, `errors[]`, `headers[]`
   - `HeaderInfo`: Represents a header with name, type, and RFC reference link

4. **View Layer** (`resources/templates/`)
   - `index.html`: Bootstrap-based single-page UI with JavaScript fetch calls to REST API

### Key Design Patterns

- **Factory Pattern**: Uses JAIN-SIP's `MessageFactoryImpl` and `HeaderFactoryImpl` for parsing
- **DTO Pattern**: Request/Response objects separate API contracts from internal models
- **Static Registry**: `SipHeaderRfcMapping` provides O(1) RFC lookups via HashMap
- **Result Object**: `ValidationResult` inner class encapsulates validation outcomes

### Message Validation Flow

```
User Input → Controller → Service.validateMessage()
                             ↓
                   Normalize CRLF line endings
                             ↓
                   Check first line for "SIP/2.0"
                             ↓
              ┌──────────────┴──────────────┐
              ↓                             ↓
    Request Message                  Response Message
    (INVITE, OPTIONS, etc.)          (200 OK, 404, etc.)
              ↓                             ↓
              └──────────────┬──────────────┘
                             ↓
                   Extract all headers
                             ↓
                   Lookup RFC for each header
                             ↓
                   Return ValidationResult with HeaderInfo[]
```

### Header Validation Flow

```
Header String → Controller → Service.validateHeader()
                                ↓
                     HeaderFactoryImpl.createHeader()
                                ↓
                     Extract canonical name (e.g., "i" → "Call-ID")
                                ↓
                     SipHeaderRfcMapping.getRfcReference()
                                ↓
                     Return ValidationResult with single HeaderInfo
```

## Configuration

**application.yaml** settings:
- `server.port: 8080` - HTTP port
- `logging.level.gov.nist.javax.sip: WARN` - Suppress verbose JAIN-SIP logs
- `spring.thymeleaf.cache: false` - Disable template caching for development

## Dependencies

Key libraries:
- **JAIN-SIP API 1.2.1.4**: SIP protocol interfaces
- **JAIN-SIP RI 1.3.0-91**: NIST reference implementation from `https://maven.nist.gov/nexus/`
- **Spring Boot 3.4.7**: Web framework, dependency injection, embedded Tomcat
- **Thymeleaf**: Server-side HTML templating

Note: JAIN-SIP dependencies require the NIST Maven repository configured in `pom.xml`.

## Common Modifications

### Adding a New RFC Mapping
Edit `SipHeaderRfcMapping.java` and add to the static initializer:
```java
rfcMap.put("Header-Name", new RfcReference("RFC XXXX", "Section X.X", "https://..."));
```

### Adding a New Validation Endpoint
1. Add method to `SipValidatorRestController`
2. Implement validation logic in `SipValidatorService`
3. Return `ValidationResponse` via `ResponseEntity.ok()`

### Modifying the Web UI
Edit `src/main/resources/templates/index.html` (Thymeleaf template with Bootstrap 5 and inline JavaScript)

## Project Constraints

- **Java 22 Required**: Configured in `pom.xml` and Maven wrapper
- **Port 8080**: Default HTTP port, configurable in `application.yaml`
- **No Database**: Stateless validation service with no persistence
- **CRLF Line Endings**: SIP specification requires CRLF; service normalizes input automatically
- **NIST Repository**: JAIN-SIP dependencies must be fetched from NIST's Maven repository
