# Tour-Backend
API Rest para los equipos de ciclismo y sus ciclistas

## Por:
Rodrigo Gallego Arboleda

## Instrucciones
La API se despliega en el puerto http://localhost:8080 y se encuentra enlazada a una base de datos no relacional para pruebas.

### Crear Equipo
Método: POST

endpoint: http://localhost:8080/api/teams/registro

JSON: 
{
    "name": String,
    "teamCode": String,
    "country": String
}

### Obtener Todos Los Equipos
Método: GET

endpoint: http://localhost:8080/api/teams

### Obtener Los Equipos Por País
Método: GET

endpoint: http://localhost:8080/api/teams/country/Indicar_Pais

### Editar Equipo
Método: PUT

endpoint: http://localhost:8080/api/teams/Indicar_id

JSON: 
{
    "name": String,
    "teamCode": String,
    "country": String
}

### Eliminar Equipo
Método: DELETE

endpoint: http://localhost:8080/api/teams/Indicar_id

### Agreagr Ciclista
Método: POST

endpoint: http://localhost:8080/api/cyclists/registro

JSON: 
{
    "name": String,
    "number": Integer,
    "teamCode": String,
    "nationality": String
}

### Obtener Todos Los Ciclistas
Método: GET

endpoint: http://localhost:8080/api/cyclists

### Editar Ciclista
Método: PUT

endpoint: http://localhost:8080/api/cyclists/Indicar_id

JSON: 
{
    "name": String,
    "number": Integer,
    "teamCode": String,
    "nationality": String
}

### Eliminar Ciclista
Método: DELETE

endpoint: http://localhost:8080/api/cyclists/Indicar_id

### Obtener Los Ciclistas por el Código del Equipo
Método: GET

endpoint: http://localhost:8080/api/cyclists/Indicar_teamCode

### Obtener Los Ciclistas por su Nacionalidad
Método: GET

endpoint: http://localhost:8080/api/cyclists/nationality/Indicar_nacionalidad
