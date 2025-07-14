# 🐶 Prueba Técnica - Registro de Mascotas

## 📌 Descripción del ejercicio

Desarrollar un backend con Java + Spring Boot utilizando arquitectura hexagonal que permita registrar mascotas y enriquecer sus datos desde una API externa de razas de perros. Además, se deben exponer endpoints RESTful para registrar y consultar mascotas, incluir mecanismos de caché, pruebas unitarias e integración, y una documentación clara.

---

## ✅ Solución implementada

- Arquitectura Hexagonal (Ports and Adapters).
- Modelo desacoplado mediante DTOs (`MascotaDto`, `RazaDto`).
- TheDogAPI se usa para obtener datos de razas.
- `@Cacheable` se implementa para evitar llamadas repetidas a la misma raza.
- Base de datos en memoria (H2).
- Pruebas unitarias y de integración incluidas.
- Documentación y reporte de cobertura de código generable con Jacoco.


## 🗃️ Esquema de base de datos (H2)

Se utiliza una base en memoria autogenerada con Hibernate (`ddl-auto=update`).

### Tabla: `mascotas`

| Campo           | Tipo    | Descripción                         |
|----------------|---------|-------------------------------------|
| id             | Long    | ID autogenerado                     |
| nombre         | String  | Nombre de la mascota                |
| edad           | Integer | Edad en años                        |
| direccion      | String  | Dirección del dueño                 |
| ciudad         | String  | Ciudad del dueño                    |
| nombreRaza     | String  | Nombre de la raza                   |
| grupo          | String  | Grupo de la raza                    |
| temperamento   | String  | Temperamento típico                 |
| proposito      | String  | Uso histórico de la raza            |
| edadPromedio   | Integer | Años de vida estimados              |
| pesoPromedio   | Integer | Peso promedio (kg)                  |
| alturaPromedio | Integer | Altura promedio (cm)                |

### Acceso a H2 Console:

```
http://localhost:8080/h2-console
```

- JDBC URL: `jdbc:h2:mem:mascotas-db`
- Usuario: `sa`
- Contraseña: *(vacía)*

---

## 🌐 Consumo del API externo: TheDogAPI

Se consume:

```
GET https://api.thedogapi.com/v1/breeds
```

### Implementación técnica:

- Cliente: `DogApiClient` con `RestTemplate`
- Adaptador externo implementa `RazaServicePort`
- Se aplica `@Cacheable` a nivel de método para optimizar rendimiento
- Se hace parsing manual de los campos: `name`, `group`, `temperament`, `weight.metric`, `height.metric`, `bred_for`

---

## 🚀 Cómo levantar y probar el aplicativo

### Requisitos:

- Java 17+
- Maven 3.8+
- Docker (opcional)
- Conexión a Internet (para TheDogAPI)

### Ejecución local

```bash
mvn clean install
mvn spring-boot:run
```

### Ejecución vía Docker

```bash
mvn clean package
docker build -t mascota-api .
docker run -p 8080:8080 mascota-api
```

---

## 🔗 Endpoints disponibles

### POST /api/mascotas

Registra una mascota, consulta la raza en TheDogAPI y guarda toda la información enriquecida.

```json
{
  "nombre": "Max",
  "edad": 5,
  "direccion": "Av. Siempre Viva",
  "ciudad": "Quito",
  "nombreRaza": "Affenpinscher"
}
```

### GET /api/mascotas

Consulta con filtros opcionales:

```
GET /api/mascotas?raza=Beagle&grupo=Hound&pesoHasta=30
```

---

## 📦 Tecnologías y librerías usadas

| Componente       | Tecnología                       |
|------------------|----------------------------------|
| Framework        | Spring Boot 3                    |
| Lenguaje         | Java 17                          |
| Arquitectura     | Hexagonal (Ports and Adapters)   |
| DB               | H2 en memoria                    |
| Cliente HTTP     | RestTemplate                     |
| Caching          | Spring Cache (`@EnableCaching`)  |
| Testing          | JUnit 5, Mockito, MockMvc        |
| Build            | Maven                            |
| Cobertura        | JaCoCo                           |
| Contenedores     | Docker                           |

---

## 🧠 Estructura de paquetes

```
com.hiberus.prueba_tecnica
├── adapter
│   ├── in.rest (MascotaController)
│   └── out
│       ├── dogapi (DogApiClient)
│       └── persistence (Entity, Repository, Mapper)
├── domain
│   ├── model (MascotaDto, RazaDto)
│   ├── port (in/out)
│   └── usecase (RegistrarMascotaService)
├── config (UseCaseConfig, RestTemplateConfig)
└── PruebaTecnicaApplication.java
```