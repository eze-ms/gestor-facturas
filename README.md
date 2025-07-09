# Gestor de Clientes y Facturas — Spring Boot + JPA + Thymeleaf

![Backend](https://img.shields.io/badge/backend-Java%2017-orange?style=flat-square)
![Framework](https://img.shields.io/badge/framework-Spring%20Boot-6db33f?style=flat-square)
![Template](https://img.shields.io/badge/frontend-Thymeleaf-blue?style=flat-square)
![Database](https://img.shields.io/badge/database-H2--MySQL-lightgrey?style=flat-square)

---

## 📄 Descripción

Aplicación web construida con Java 17 y Spring Boot que permite gestionar clientes y facturas. Incluye operaciones CRUD completas, subida de imágenes, generación de facturas dinámicas con productos y cálculos automáticos. Usa JPA para persistencia, Thymeleaf como motor de vistas y Bootstrap para el diseño.

---

## ✨ Funcionalidades

- CRUD completo de clientes con región y foto.
- Creación de facturas por cliente con múltiples productos.
- Cálculo dinámico de totales e importes.
- Autocompletado de productos con jQuery UI.
- Paginación personalizada con helper reusable.
- Validaciones y control de errores en formularios.
- Gestión de archivos en sistema local (`/uploads`).
- Diseño responsive con Bootstrap 5.

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
- **Spring Boot**
- **Spring Data JPA** 
- **Thymeleaf** 
- **Bootstrap** 
- **jQuery UI Autocomplete**
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
spring-boot:run

```

#### Acceso a la aplicación
Una vez iniciada, accede a:
```bash
http://localhost:8080/listar

```

---

© 2025. Proyecto desarrollado por Ezequiel Macchi Seoane