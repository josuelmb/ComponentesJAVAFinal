# ComponentesJAVAFinal
Examen final de Componentes - Biblioteca
# 📚 Sistema de Gestión de Biblioteca - API REST

Proyecto desarrollado en **Spring Boot** que permite gestionar libros, usuarios, préstamos y devoluciones en una biblioteca universitaria, incorporando autenticación mediante **JWT** y control de acceso por roles.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate
- MySQL / PostgreSQL
- Maven
- Swagger (OpenAPI)
- Postman (pruebas)

---

## 📌 Funcionalidades

✔ Registro y autenticación de usuarios (JWT)  
✔ Gestión de roles (ADMIN, USER)  
✔ CRUD de libros  
✔ Registro de préstamos  
✔ Registro de devoluciones  
✔ Consulta de préstamos por usuario  
✔ Seguridad basada en roles  
✔ Manejo de errores centralizado  

---

## 🔐 Seguridad

- Autenticación con JWT (sin sesiones)
- Roles:
  - `ADMIN`: puede gestionar libros y préstamos
  - `USER`: solo consulta sus propios préstamos

---

## 📂 Estructura del proyecto
src/
├── controller
├── service
├── repository
├── entity
├── dto
├── mapper
├── security
└── config

---

## ⚙️ Configuración del proyecto

### 1. Clonar repositorio

```bash
git clone https://github.com/josuelmb/ComponentesJAVAFinal.git
cd ComponentesJAVAFinal
```
---
### 2. Ejecutar el proyecto
```bash
./mvnw spring-boot:run
```
---

 ### 📑 Documentación con Swagger
 
 ```bash
http://localhost:8070/api/v1/swagger-ui/index.html
```
---
# 🧪 Pruebas con Postman
### 🔐 Login
 ```bash
POST /auth/login
{
  "username": "admin",
  "password": "123456"
}
 ```
### 📚 Crear libro (ADMIN)
 ```bash

POST /libros
Authorization: Bearer TOKEN

{
 "idLibro": 2,
 "titulo": "El principito",
 "autor": "Paulo Cohelo",
 "editorial": "Bruno",
 "categoria": "Cuento",
 "idioma": "Español",
 "cantidadTotal": 5,
 "cantidadDisponible": 5
 }
```

### 📦 Crear préstamo (ADMIN)
 ```
POST /prestamos

{
  "idUsuario": 1,
  "fechaLimite": "2026-04-30 14:00",
  "observaciones": "Préstamo inicial",
  "libros": [
    {
      "idLibro": 1,
      "cantidad": 2
    }
  ]
}
```
### 🔄 Registrar devolución
```bash
PATCH /prestamos/{id}/devolucion
```

### 👤 Ver préstamos del usuario autenticado
```bash
GET /prestamos/mis-prestamos
Authorization: Bearer TOKEN "USER"
```

## 📊 Modelo de datos

### Entidades principales:

- Usuario  
- Persona  
- Rol  
- Libro  
- Préstamo  
- PrestamoLibro (detalle)  

### Relaciones:

- Usuario → Persona (1:1)  
- Usuario ↔ Rol (N:M)  
- Préstamo → Usuario (N:1)  
- Préstamo → Libros (1:N mediante PrestamoLibro)  

---

## ⚠️ Validaciones implementadas

- Fechas no pueden ser anteriores a la actual  
- Control de stock de libros  
- No se permite devolver un préstamo ya devuelto  
- Validación de campos obligatorios  
