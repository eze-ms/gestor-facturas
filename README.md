# Gestor de Clientes y Facturas — Spring Boot + JPA + Thymeleaf

![Backend](https://img.shields.io/badge/backend-Java%2017-orange?style=flat-square)
![Framework](https://img.shields.io/badge/framework-Spring%20Boot-6db33f?style=flat-square)
![Template](https://img.shields.io/badge/frontend-Thymeleaf-blue?style=flat-square)
![Database](https://img.shields.io/badge/database-H2--MySQL-lightgrey?style=flat-square)

---

## 📄 Descripción

Aplicación web construida con Java 17 y Spring Boot que permite gestionar clientes y facturas. Incluye operaciones CRUD completas, subida de imágenes, generación de facturas dinámicas con productos y cálculo de totales. Usa JPA para persistencia, Thymeleaf como motor de vistas y Bootstrap para el diseño.

---

## ✨ Funcionalidades

- CRUD de clientes con región y subida de foto.
- Formulario de creación de facturas con validación completa.
- Búsqueda dinámica de productos con jQuery UI.
- Añadir múltiples líneas de factura con totales automáticos.
- Detalle completo de factura con cliente, ítems y observación.
- Detalle del cliente con sus facturas.
- Eliminación de facturas desde la vista del cliente.
- Consulta optimizada (`fetch join`) para evitar lazy loading en vistas de detalle.
- Diseño oscuro, limpio y responsive.
- Gestión de archivos en el sistema local (`/uploads`).

---

## 🧱 Estructura del Proyecto

```bash
com.gestordefacturas.springboot.datajpa.app
├── controllers/          # Controladores MVC (Cliente, Factura)
├── models/
│   ├── dao/              # Interfaces DAO (JPA)
│   └── entity/           # Entidades JPA (Cliente, Factura, Producto)
├── service/              # Interfaces e implementaciones de servicios
├── util/paginator/       # Componentes para paginación
├── MvcConfig.java        # Configuración de recursos estáticos y uploads
└── SpringBootDataJpaApplication.java

```
---

## 💻 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA** 
- **Thymeleaf** 
- **Bootstrap 5** 
- **jQuery UI**
- **MySQL**
- **Maven**
---

## 📋 Requisitos

- **Java 17** 
- **IDE compatible con Spring (recomendado: IntelliJ IDEA)**
- **Maven 3+**

---

## 🛠️ Instalación
```bash
git clone https://https://github.com/eze-ms/gestor-facturas
cd gestor-facturas
mvn spring-boot:run
```

#### Acceso a la aplicación
Una vez iniciada, accede a:
```bash
http://localhost:8080/listar

```

---

© 2025. Proyecto desarrollado por Ezequiel Macchi Seoane