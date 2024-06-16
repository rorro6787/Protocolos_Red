# Proyectos de Protocolos de Red: UDP, TCP y HTTP

Este repositorio contiene tres proyectos implementados en Java. Cada uno de ellos aborda un protocolo o servicio diferente. A continuación se describen cada uno de los proyectos y cómo puedes ejecutarlos.

## Contenido

1. Prtocolo TCP
2. Prtocolo UDP
4. Prtocolo HTTP

## Proyecto 1: Protocolo TCP

### Descripción
Este proyecto implementa el protocolo TCP (Transmission Control Protocol) entre un cliente y un servidor. El cliente establece una conexión con el servidor y pueden intercambiar mensajes de manera confiable.

### Cómo Ejecutar
1. Navega al directorio `src-tcp`.
2. Compila los archivos Java:
   
   ```sh
   javac src/*.java -d out
3. Ejecuta el servidor:

   ```sh
   java -cp out ServerTCP
4. En otra terminal, ejecuta el cliente:

   ```sh
   java -cp out ClienteTCP

## Proyecto 2: Protocolo UDP

### Descripción
Este proyecto implementa el protocolo UDP (User Datagram Protocol) entre un cliente y un servidor. El cliente envía mensajes al servidor y el servidor responde a cada mensaje recibido.

### Cómo Ejecutar
1. Navega al directorio `src-udp`.
2. Compila los archivos Java:
   
   ```sh
   javac src/*.java -d out
3. Ejecuta el servidor:

   ```sh
   java -cp out ServerUDP
4. En otra terminal, ejecuta el cliente:

   ```sh
   java -cp out ClienteUDP

## Proyecto 3: Protocolo HTTP

### Descripción
Este proyecto implementa el protocolo UDP (User Datagram Protocol) entre un cliente y un servidor. El cliente envía mensajes al servidor y el servidor responde a cada mensaje recibido.

### Cómo Ejecutar
1. Navega al directorio `src-udp`.
2. Compila los archivos Java:
   
   ```sh
   javac src/*.java -d out
3. Ejecuta la aplicación:

   ```sh
   java -cp out Main
