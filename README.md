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
Método: GET

endpoint: http://localhost:8080/api/teams/Indicar_id

JSON: 
{
    "name": String,
    "teamCode": String,
    "country": String
}
