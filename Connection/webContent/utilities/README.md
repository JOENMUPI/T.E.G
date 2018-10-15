# Utilities 

## Documentation and ussage

### Loader

> Instancia al metodo addScript de CCC
```
    el parametro enviado es una cadena con la direccion del archivo que se quiere mostrar

ccc.addScript('Components/renders/login').then(response => {
    logger.info('added!')
}).catch(err => {
    logger.error(err);
})

ccc.addScript('Components/renders/signup').then(response => {
    logger.info('added!')
}).catch(err => {
    logger.error(err);
})

ccc.addScript('Components/renders/themes').then(response =>{
    logger.info('added!')
}).catch(err => {
    logger.error(err);
})

```

### Fetch

> Es donde se hace la peticion al servidor
```
    Este metodo tiene como parametros:
    data, son los datos que se enviaran al servidor
    method, el metodo en que se va a enviar la data 
    url, la direccion del Servlet en el servidor
    
fetching = (data, method, url, cb) => {}
```