# Springboot-vue-calculator

Dette prosjektet var det siste arbeidskravet i faget IDATT2105 ved NTNU Trondheim, våren 2025.

Prosjektet er en webapplikasjon som består av en kalkulator med brukerinnlogging, autorisasjon via JWT-token og lagring i database med JPA. Frontend er bygget med Vue.js, og backend er en Spring Boot-applikasjon.

## Systemkrav

- **JDK 21**
- **maven 3.8+**
- **node.js 20+**

## Oppsett

### Backend

Etter at du har klonet repoet:

1. navigerer til backend mappen
```
cd backend
```

2. bygg prosjektet med maven
```
mvn clean install
```

3. kjør spring-boot applikasjonen
```
mvn spring-boot:run
```

backenden kjører nå på http://localhost:8080

### Frontend

1. navigerer til frontend mappen
```
cd frontend
```

2. Installer nødvendige pakker
```
npm install
```

3. kjør utviklingsserveren
```
npm run dev
```

frontenden kjører nå på http://localhost:5173

## Lisens
Dette prosjektet er lisensiert under Apache 2.0. Se LICENSE-filen for mer informasjon.
