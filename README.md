# AgroSat API

## AgroSat

O AgroSat é uma plataforma de monitoramento agrícola inteligente que combina dados de satélites, sensores de campo e inteligência artificial para auxiliar produtores rurais na tomada de decisão.

A solução permite acompanhar condições climáticas, monitorar plantações, identificar riscos, emitir alertas preventivos e gerar recomendações automatizadas para aumentar a produtividade e reduzir perdas causadas por eventos climáticos adversos.

Esta API é responsável por fornecer todos os recursos necessários para o gerenciamento de usuários, fazendas, plantações, dados climáticos e recomendações inteligentes da plataforma.

---

## Sumário

* Pitch
* Apresentação Técnica
* Deploy
* Endpoints
* Autenticação
* Como Executar o Projeto
* Integrantes

---

## Pitch

Vídeo Pitch:

https://youtu.be/SzybolMV8Rg?si=_euTkDvdvW_iKIW4

---

## Apresentação Técnica

Vídeo de Demonstração Técnica:

https://youtu.be/OZEM9qkhSGk?si=z16NZnRUZ3Pnr-wI

---

## Deploy

API:

https://agrosat-rm560169.azurewebsites.net

Swagger:

https://agrosat-rm560169.azurewebsites.net/swagger-ui/index.html

---

## Endpoints

### Autenticação

POST /api/auth/login

### Usuários

POST /api/users

GET /api/users/{id}

PUT /api/users/{id}

DELETE /api/users/{id}

GET /api/users/{id}/farms

### Fazendas

POST /api/farms

GET /api/farms/{id}

PUT /api/farms/{id}

DELETE /api/farms/{id}

GET /api/farms/{id}/plantations

GET /api/farms/{id}/weather-datas

### Plantações

POST /api/plantations

GET /api/plantations/{id}

PUT /api/plantations/{id}

DELETE /api/plantations/{id}

### Recomendações de IA

GET /api/ai-recommendations/{id}

DELETE /api/ai-recommendations/{id}

### Dados Climáticos

POST /api/weather-datas

GET /api/weather-datas/{id}

DELETE /api/weather-datas/{id}

### Estados

GET /api/states

### Cidades

GET /api/cities

### Tipos de Cultura

GET /api/crop-types

### Status de Plantação

GET /api/plantation-status

---

## Como Autenticar

Realize login utilizando o endpoint:

POST /api/auth/login

Exemplo:

{
"email": "[usuario@email.com](mailto:usuario@email.com)",
"password": "senha123"
}

Resposta:

{
"token": "jwt_token"
}

Após o login, envie o token em todas as requisições protegidas:

Authorization: Bearer <jwt_token>

---

## Como Executar o Projeto

### Pré-requisitos

* Java 17
* Maven
* Git

### Clonar o Repositório

git clone https://github.com/matteusviegas/projetoGsJava.git

cd agrosat-api

### Configurar Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto:

DB_URL=

DB_USERNAME=

DB_PASSWORD=

OPENWEATHER_API_KEY=

### Executar a Aplicação

mvn spring-boot:run

A aplicação ficará disponível em:

http://localhost:8080

Swagger:

http://localhost:8080/swagger-ui/index.html

---

## Integrantes

* RM561089 Sulamita
* RM561090 Matteus Viegas
* RM560914 Lucas
* RM560169 Antonio
