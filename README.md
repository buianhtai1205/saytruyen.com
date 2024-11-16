# Web Saytruyen.com

## Project Overview

Web Novel Reader is a full-stack application designed for reading web novels online. It provides a user-friendly interface for readers to browse, read, and interact with various web novels.

## Technologies Used

### Frontend
- React.js
- SCSS Modules
- Axios for API calls

### Backend
- Spring Boot (Microservices architecture)
- MySQL (or your preferred database)
- Spring Cloud for microservice communication

## Project Structure

The project is divided into two main parts:

1. Frontend (React.js application)
2. Backend (Spring Boot microservices)

### Frontend Structure

### Backend Structure

## Installation

### Prerequisites

- Node.js (v14 or later)
- Java JDK 11 or later
- Maven
- MySQL (or your preferred database)

### Frontend Setup

1. Navigate to the frontend directory:
   \`\`\`
   cd frontend
   \`\`\`

2. Install dependencies:
   \`\`\`
   npm install
   \`\`\`

3. Create a \`.env\` file in the frontend root and add your backend API URL:
   \`\`\`
   REACT_APP_API_URL=http://localhost:8080/api
   \`\`\`

### Backend Setup

1. Navigate to the backend directory:
   \`\`\`
   cd backend
   \`\`\`

2. Build the project:
   \`\`\`
   mvn clean install
   \`\`\`

3. Set up your database and update the \`application.properties\` file in each service with your database credentials.

## Running the Application

### Frontend

In the frontend directory, run:
\`\`\`
npm start
\`\`\`

The application will be available at \`http://localhost:3000\`.

### Backend

1. Start the Discovery Service:
   \`\`\`
   java -jar discovery-service/target/discovery-service.jar
   \`\`\`

2. Start the API Gateway:
   \`\`\`
   java -jar api-gateway/target/api-gateway.jar
   \`\`\`

3. Start each microservice:
   \`\`\`
   java -jar novel-service/target/novel-service.jar
   java -jar user-service/target/user-service.jar
   java -jar reading-service/target/reading-service.jar
   \`\`\`

The backend services will be available through the API Gateway at \`http://localhost:8080\`.

## Additional Notes

- Ensure all microservices are running before starting the frontend application.
- For production deployment, consider using environment-specific configuration files and a process manager like PM2 for the frontend.
- Implement proper security measures, including HTTPS and authentication, before deploying to production.

## Contributing

Please read \`CONTRIBUTING.md\` for details on our code of conduct and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the \`LICENSE.md\` file for details.
