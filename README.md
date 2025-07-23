# 📊 StatBroker

Simulador educativo de compra/venta de acciones y criptomonedas con estadísticas en tiempo real y asistente inteligente basado en IA.

---
## Índice

- [📝 Descripción](#📝-descripción)
- [🚀 Demo](#🚀-demo)
- [🧱 Tecnologías utilizadas](#🧱-tecnologías-utilizadas)
  - [🔧 Backend](#🔧-backend)
  - [💻 Frontend](#💻-frontend)
- [🔐 Seguridad](#🔐-seguridad)
- [📌 Funcionalidades principales](#📌-funcionalidades-principales)
- [⚙️ Instalación y ejecución](#⚙️-instalación-y-ejecución)
  - [🐳 Docker Compose](#🐳-opción-1-usando-docker-compose)
  - [🧰 Instalación manual](#🧰-opción-2-instalación-manual)
- [⚠️ Estado del proyecto](#⚠️-estado-del-proyecto)


---
## 📝 Descripción

**StatBroker** es una plataforma diseñada para aprender sobre inversión en mercados financieros mediante simulaciones con dinero ficticio. Permite a los usuarios:

- Visualizar métricas y estadísticas en tiempo real de acciones y criptomonedas.
- Simular compras y ventas con una billetera virtual.
- Seguir la evolución y desempeño de su portafolio de inversión.
- Interactuar con un asistente inteligente que ofrece análisis y consejos financieros personalizados.

---

## 🚀 Demo

[Accede a la demo en línea]()

![Vista previa de StatBroker](./assets/preview.png)

---

## 🧱 Tecnologías utilizadas

### 🔧 Backend

- **Spring Boot 3**: Framework Java para desarrollar APIs robustas y mantenibles.
- **PostgreSQL**: Base de datos confiable para el almacenamiento seguro de la información.
- **JWT**: Mecanismo de autenticación para asegurar el acceso solo a usuarios autorizados.
- **Docker**: Contenerización que garantiza consistencia en distintos entornos.
- **GitHub Actions**: Automatización de pruebas y despliegues para mayor eficiencia.
- **Clean Architecture**: Estructura de código que facilita escalabilidad y mantenimiento.

### 💻 Frontend

- **React + Vite + TypeScript**: Herramientas modernas para construir interfaces rápidas y tipadas.
- **Tailwind CSS**: Framework CSS que permite diseñar de forma ágil y responsiva.
- **Zustand**: Manejo sencillo y eficiente del estado global de la aplicación.
- **Zod**: Validación de datos para asegurar integridad y evitar errores.
- **Axios**: Cliente HTTP para comunicación fluida con la API.
- **React Query**: Optimiza la gestión y actualización de datos remotos.
- **Chart.js**: Librería para crear gráficos interactivos y visualizaciones claras.

---

## 🔐 Seguridad

- Autenticación robusta mediante JWT
- Validación exhaustiva de entradas para evitar vulnerabilidades
- Configuración correcta de CORS para controlar orígenes permitidos
- Gestión segura de variables de entorno para proteger datos sensibles como contraseñas y claves API

---

## 📌 Funcionalidades principales

- 📈 Estadísticas en tiempo real de mercados financieros (cripto y bolsa)
- 💰 Simulador de compra/venta con dinero ficticio
- 📊 Historial detallado de inversiones
- 🤖 Asistente inteligente basado en OpenAI API para soporte financiero
- 👤 Registro, login y panel de usuario personalizado

---

## ⚙️ Instalación y ejecución

### 🐳 Opción 1: Usando Docker Compose

```bash
docker-compose up --build
```

### 🧰 Opción 2: Instalación manual


Backend
```bash
cd backend
./mvnw spring-boot:run
```

Frontend
```bash
cd frontend
npm install
npm run dev
```

🧪 Pruebas
Para ejecutar las pruebas unitarias y de integración:

Backend
```bash
cd backend
./mvnw test
```

Frontend
```bash
cd frontend
npm run test
```


---

## ⚠️ Estado del proyecto

**StatBroker** está actualmente en desarrollo activo. Algunas funcionalidades pueden no estar completas o pueden cambiar significativamente.  
Agradecemos cualquier feedback, reporte de bugs o contribución para mejorar la plataforma.

---
