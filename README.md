# Acceso a Datos - 02 - Ejercicios - 2021-2022
Acceso a Datos - 02 XML. 2DAM. Ejercicios realizados por el alumnado. Curso 2021-2022


![imagen](https://woobro.design/thumbnails/26/development-illustration-5de17f18abc70.png)

## ¿Cómo Colaborar?
Estos ejercicios están resueltos por el alumnado y están basados en la relación de la [Unidad 2: XML](https://github.com/joseluisgs/AccesoDatos-02-2021-2022).

Para subir tu ejercicio a GitHub, **POR FAVOR SIGUE ESTAS NORMAS**:

- Hazte un fork de este repositorio
- Trabaja con GitFlow
- Crea una reama feature con tu Iniciales y apellido y el nombre del problema realizado por el profesor, por ejemplo: /feature/JLGonzalez/rss-dom
- Crea un directorio dentro del directorio del problema del repositorio con tu Iniciales y Apellido, por ejemplo rss-dom/JLGonzalez. 
- Copia tu proyecto de IntellIJ creado y gestionado con Maven a tu directorio creado. Debes tener en cuenta que el gitignore de ese proyecto debe evitar subir el directorio /out y /target de Intellij.
- Cierra la Feature siguiendo el flujo de GitFlow, fusionando los cambios a Develop, pero no borres esa rama por si la vuelves a necesitar.
- Confirma los cambios y sube los cambios a tu repositorio GitHub.
- Hazme un pull request para que acepte los cambios y explícame dichos cambios en la rama **Develop** en tu commit.
- Aplica las acciones oportunas para tener todo sincronizado.
- **SI NO SE SIGUEN ESTAS NORMAS SE TE INVALIDARÁ EN PULL REQUEST. PIENSA QUE ES POR EL BIEN DE TODOS/AS Y TU EJERCICIO CONSTARÁ COMO NO ENTREGADO.**
- **ESTE REPOSITORIO SIRVE PARA TENER DISTINTOS PUNTOS DE VISTA, PUEDEN QUE FALLEN SI LOS PRUEBAS O QUE UN ERROR PERJUDIQUE A OTROS FICHEROS.** Te aconsejo que los pruebes en un proyecto vació con la estructuras de directorios propuesta, llamándolo main.ts y usando el módulo que necesite para que no arrastre errores a otros ficheros o problemas. Cualquier duda usa Discord o pregunta en clase.

!!! FIN !!! 😀 🤝

## Problemas

### RSS-DOM
Implementa a tu manera un lector RSS usando las librerías DOM que vienen integradas en Java

Entrega 13/10/2021

### RSS-SAX
Implementa a tu manera un lector RSS usando las librerías SAX que vienen integradas en Java

Entrega 20/10/2021

### RSS-JDOM
Implementa a tu manera un lector RSS usando las librerías JDOM vista en clase. Usa XPATH para hacer algunas consultas que consideres oportunas, por ejemplo obtener todos los vídeos, o la tercera noticia, o las noticias de una categoria determinada.
Sería interesante que la noticia tuviese un atributo con el identificador que tú quieras darle. Además un elemento o propiedad que sea multimedia, y pueda ser imagen o video y su URL.
Cambia la fecha para que aparezca en formato DD/MM/AAAA a las HH:MM:SS
Repite dicas busquedas usando la API Stream.
Reflexiona sobre qué librería de las tres utilizadas y sistemas de consultas son más apropiados en cada momento.
Este ejercicio puede ser un ejercicio de examen (de hecho lo fue una vez)

Entrega 20/10/2021

 ### Usando JAXB
Usando información de internet: https://www.ibm.com/docs/es/was/9.0.5?topic=services-using-jaxb-xml-data-binding

Procesar el RSS del País usando algunos de los parser creados (quizás debas ajustarlo un poco para lo que se te pide), creando una lista de noticias con los campos que elijas. Esta lista de Noticias la vamos a almacenar en en un base de datos llamada mis_noticias.xml usando JAXB, pero teniendo lo siguiente en cuenta:
- Debemos darle un ID a la noticia basado en un UUID como atributo.
- Una noticia esta compuesta de un objeto multimedia, que puede ser vídeo o imagen.
- Además debe tener un campo de cuándo se ha agregado.

En nuestro programa accedemos con JASX, cuando lo iniciemos nos debe permitir hacer un CRUD con nuestra base de datos siguiendo estas opciones:
- Recargar noticias. Añade del RSS noticias que no existan, deberás comprar su existencia por el titulo o algún identificador único que te ofrezca el RSS una vez parseado para no repetir valores.
- Mostrar todas las noticias
- Mostrar noticias de hoy de 5 en 5.
- Añadir una noticia pidiéndole los campos de manera manual
- Actualiza una noticia
- Eliminar una noticia.

Por supuesto después de cada operación debemos salvar nuestra base de datos. Además se debe generar el esquema automatizado de las noticias opcional, por si nos ofrecen un XML de noticias podamos cargarlo y sustituir el nuestro.

Entrega 25/10/2021


## Autor

Codificado con :sparkling_heart: por [José Luis González Sánchez](https://twitter.com/joseluisgonsan)

[![Twitter](https://img.shields.io/twitter/follow/joseluisgonsan?style=social)](https://twitter.com/joseluisgonsan)
[![GitHub](https://img.shields.io/github/followers/joseluisgs?style=social)](https://github.com/joseluisgs)

### Contacto
<p>
  Cualquier cosa que necesites házmelo saber por si puedo ayudarte 💬.
</p>
<p>
    <a href="https://twitter.com/joseluisgonsan" target="_blank">
        <img src="https://i.imgur.com/U4Uiaef.png" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://github.com/joseluisgs" target="_blank">
        <img src="https://cdn.iconscout.com/icon/free/png-256/github-153-675523.png" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://www.linkedin.com/in/joseluisgonsan" target="_blank">
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/LinkedIn_logo_initials.png/768px-LinkedIn_logo_initials.png" 
    height="30">
    </a>  &nbsp;&nbsp;
    <a href="https://joseluisgs.github.io/" target="_blank">
        <img src="https://joseluisgs.github.io/favicon.png" 
    height="30">
    </a>
</p>
