# Fluxenture - Servidor Administrativo

Este es el backend del sistema administrativo de **Fluxenture**, una aplicación robusta construida con **Java 21** y **Spring Boot 3.5.10**, diseñada bajo los principios de la **Arquitectura Hexagonal** (Puertos y Adaptadores).

## 🚀 Tecnologías Utilizadas

- **Lenguaje:** Java 21 (Amazon Corretto / Temurin)
- **Framework:** Spring Boot 3.5.10
- **Base de Datos:** MongoDB (vía Spring Data MongoDB)
- **Seguridad:** Spring Security con JSON Web Tokens (JWT)
- **Integraciones:**
  - Google Drive API (Almacenamiento de archivos)
  - Google Sheets API (Exportación de datos)
- **Herramientas de Construcción:** Gradle (Kotlin DSL)
- **Despliegue:** Docker, GitHub Actions, EC2.

## 🏛️ Arquitectura

El proyecto sigue una **Arquitectura Hexagonal**, lo que permite desacoplar la lógica de negocio de las tecnologías externas (bases de datos, APIs, frameworks).

Cada módulo principal se divide en tres capas:

1.  **Domain (Dominio):** Contiene las entidades de negocio y las interfaces (Puertos de Salida) que definen cómo el sistema interactúa con el mundo exterior.
2.  **Application (Aplicación):** Implementa los Casos de Uso (Use Cases). Es el centro de la lógica de negocio.
3.  **Infrastructure (Infraestructura):**
    - **Input (Entrada):** Adaptadores que reciben peticiones (Controladores REST).
    - **Output (Salida):** Adaptadores que persisten datos o llaman a servicios externos (Repositorios MongoDB, Google Drive Service).

## 📦 Estructura de Módulos

El proyecto está organizado en los siguientes paquetes principales bajo `com.fluxenture.core`:

### 1. `absent` (Gestión de Ausencias)
Permite el registro y consulta de ausencias de empleados.
- **Funcionalidad clave:** Búsqueda de ausencias filtradas por mes y año.
- **Endpoints:** `POST /api/absents`, `GET /api/absents/month/{year}/{month}`, `DELETE /api/absents/{id}`.

### 2. `employees` (Gestión de Empleados)
Administra la información básica de los empleados, sectores, género y estado civil.

### 3. `users` & `shared.security` (Autenticación)
Gestión de usuarios y seguridad mediante JWT.
- **Autenticación:** `/api/auth/login`, `/api/auth/validate-token`.
- **Autorización:** Filtro JWT que valida los claims de usuario en cada petición protegida.

### 4. `storage` (Integración con Google)
Servicios especializados para interactuar con la nube de Google.
- **DriveStorage:** Maneja la creación de carpetas y subida de archivos de forma estructurada.
- **SheetsService:** Maneja la manipulación de hojas de cálculo.

### 5. `cd` & `docs` (Gestión Documental y Trackings)
Módulos específicos para el seguimiento de documentos y entidades "Cd" (posiblemente referidas a control de datos o envíos).

## 🛠️ Configuración y Variables de Entorno

El sistema requiere las siguientes variables de entorno para funcionar:

| Variable | Descripción |
| :--- | :--- |
| `FLUX_DB` | URI de conexión a MongoDB. |
| `GOOGLE_CLIENT_ID` | ID de cliente para la API de Google. |
| `GOOGLE_CLIENT_SECRET` | Secreto de cliente para la API de Google. |
| `GOOGLE_REFRESH_TOKEN` | Token de refresco para acceso persistente a Google Drive. |
| `PORT` | Puerto de escucha de la aplicación (por defecto 8080). |

## 🐳 Docker y Despliegue

El proyecto incluye un `Dockerfile` multietapa para optimizar el tamaño de la imagen final:

```bash
# Construir la imagen
docker build -t fluxenture-back .

# Correr el contenedor
docker run -p 8080:8080 -e FLUX_DB=tu_uri fluxenture-back
```

El despliegue está automatizado mediante **GitHub Actions** (`deploy.yml`), que compila el proyecto y lo despliega en una instancia EC2 tras cada push a la rama `main`.

## 📜 Licencia

Propiedad privada de **Fluxenture**. Todos los derechos reservados.
