# 📚 Sistema de Gestión Bibliotecaria – Spring Boot Arquitectura Hexagonal y Relaciones

Este proyecto es la segunda práctica guiada del programa **"De Junior a Senior"**.  
Su propósito es reforzar los conceptos de **modularidad, relaciones entre entidades, comunicación entre servicios** y cómo diseñar un sistema preparado para crecer y dividirse en microservicios en el futuro.

---

## 🎯 Objetivo General
Construir una **API REST modular** para gestionar bibliotecas, libros, autores y usuarios.  
Este proyecto busca que el alumno entienda cómo **dividir dominios lógicamente, trabajar con relaciones entre entidades y diseñar APIs desacopladas entre módulos.**

---

## 📐 Identificar dominios
Identificar **dominios claros** dentro de la aplicación, cada uno pensado como un futuro microservicio (utiliza esto como guía pero no como definición de dominio).

### Bibliotecas
- Cada biblioteca tiene un nombre, dirección y una colección de libros.

**Relación:** Una biblioteca tiene muchos libros.

### Autores y Libros
- Cada autor puede tener muchos libros.
- Cada libro tiene un único autor y está asignado a una única biblioteca.

**Relaciones:**
- Autor (1) ➔ (N) Libro
- Biblioteca (1) ➔ (N) Libro

### Usuarios y Reservas
- Un usuario puede buscar libros por título o autor.
- Un usuario puede reservar un libro.

**Relaciones:**
- Usuario (N) ➔ (N) Libro (a través de Reserva)
- Un usuario no puede reservar dos veces el mismo libro activo.

---

## 🧱 Tecnologías usadas
- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- H2 Database (en memoria)
- Maven
- Lombok

---

## ✅ Requisitos funcionales

### 📗 Biblioteca
- Registrar una nueva biblioteca

### 📘 Autores y Libros
- Registrar un nuevo autor
- Registrar un nuevo libro (requiere autor y biblioteca)
- Consultar todos los libros
- Consultar libros por biblioteca
- Consultar libros por autor
- Buscar libros por título o autor

### 📙 Usuarios y Reservas
- Registrar un nuevo usuario
- Reservar un libro
- Ver reservas activas del usuario
- Cancelar una reserva

---

## ✍️ Tareas para completar

### 📕 Entidades
- [ ] Crear la entidad **Biblioteca** (`id`, `nombre`, `dirección`)
- [ ] Crear la entidad **Autor** (`id`, `nombre completo`)
- [ ] Crear la entidad **Libro** (`id`, `título`, `ISBN`, relación con `Autor` y `Biblioteca`)
- [ ] Crear la entidad **Usuario** (`id`, `nombre`, `email`)
- [ ] Crear la entidad **Reserva** (`id`, relación con `Usuario` y `Libro`, `fecha de reserva`)

---

### 📗 Repositorios
- [ ] Implementar la **capa Repository** para cada dominio usando Spring Data JPA.
- [ ] Definir queries específicas donde sea necesario (`findByAutorId`, `findByBibliotecaId`, etc.).

---

### 📘 Servicios
- [ ] Implementar los servicios de cada dominio.
- [ ] Separar la lógica en **interfaces + implementación**.
- [ ] Asegurar que cada servicio gestiona solo su propio dominio.

---

### 📙 Controladores REST
- [ ] Definir controladores por dominio.
- [ ] Usar **DTOs para entrada y salida**. Nunca exponer las entidades directamente.

---

### 📒 Validaciones
- [ ] Validar unicidad de campos como `ISBN`, `email`, etc.
- [ ] Validar que un usuario no pueda reservar dos veces el mismo libro activo.

---

### 📂 Estructura del proyecto limpia
- [ ] Separar claramente las **capas**: Controller, Service, Repository, DTO, Entity.

---

### 🔄 Mapeadores
- [ ] Crear **mapeadores (Mappers)** para transformar:
  - [ ] Entidad ➔ DTO
  - [ ] DTO ➔ Entidad
- [ ] Usar MapStruct si se desea automatizar esta conversión.


---

## 🧠 Consideraciones de diseño (modularidad y escalabilidad)

### 🔹 Modularización en paquetes
Cada módulo se encapsula en su propio paquete, como si fuera un futuro microservicio.

### 🔹 Relaciones entre entidades
- Relaciones claras usando `@OneToMany`, `@ManyToOne`, `@ManyToMany`.
- No acoplar lógicas entre dominios directamente, siempre usar **DTOs y servicios**.

### 🔹 Servicios bien definidos
- `BibliotecaService`: sólo gestiona bibliotecas.
- `LibroService`: sólo gestiona libros y sus relaciones.
- `UsuarioService`: sólo gestiona usuarios y reservas.
- Crea el servicio que creas correspondiente para que sea modular

### 🔹 Uso de DTOs
- Cada dominio define sus propios **DTOs**.
- No exponer entidades directamente.

### 🔹 Flexibilidad para crecer
- Facilitar que en el futuro pueda convertirse en microservicios sin romper nada.

---

## 🚀 Cómo ejecutar
```bash
./mvnw spring-boot:run
```

### 🛡️ Buenas prácticas a reforzar
- Principio de responsabilidad única en cada clase.
- DTOs para entrada/salida, nunca exponer entidades.
- Relaciones bien definidas en JPA.
- Servicios que no dependan de la estructura interna de otros dominios.
- Pensar en cómo escalar hacia microservicios: cada dominio puede vivir separado.
