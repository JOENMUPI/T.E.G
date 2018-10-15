# Core

## Documentaton and ussage

### CCC

```
recibe lo para metros component y configs para luegos ser añadidos en un arreglo de components

 registerComponent(component, configs) {
        this.components.push({
            component: component,
            id: configs.id,
            secret: configs.secret
        });
    }


recibe la url del archivo js que se quiere añadir al head
addScript(url) {
        return new Promise((resolve, reject) => {
            let script = document.createElement('script');
            script.async = true;
            script.src = url + '.js';
            script.onload = resolve;
            script.onerror = reject;
            document.head.appendChild(script);
            console.log(`sucessfully loaded ${url}`)
        });
    }

remueve el cualquier del head el script con la direccion especificada la direccion
removeScript(url) {
        let script = document.querySelector('script');
        script.src = url + '.js'
        if (script){
            document.head.removeChild(script);
            console.log('removed the script!');
            console.log(document.head);
        }else{
            console.log('its already been removed!');
        }
    }


Es donde se inicia la App, cargando los script especificados 
 initApp() {
        this.addScript("utilities/loader").then(response => {
            console.log(" ")
            console.log("<---- Siva-Framework Started ----->")
        }).catch(err => {
            console.log(err)
        })
    }

Se recibe la direccion de los script que van a ser cargado,
haciendo instancia del metodo addScript
   chargeScript(url) {
        this.addScript(`${url}`).then((result) => {
            console.log(`${url} loaded`)
        }).catch((err) => {
            console.error(err)
        });
    }

Devuelve los todos los componentes agredados 

getComponents() {
        return this.components;
    }


Metodo para hacer peticiones al servido
 sendRequest(options) {
     se añade el script al head
        this.addScript("utilities/fetch").then(response => {
            console.log("fetch component ready to send request")
            console.log('a')
        se ejecuta la peticion al servidor    
            fetching(options, 'POST', './Siva', msg => {
                console.log(msg)
        es removido el script añadido en el head 
                this.removeScript("utilities/fetch");
            })


        }).catch(err => {
            console.log(err)
        })
    }

es instanciado el metodo addScript para añadir log4javascript al head 
    startLogger() {
        this.addScript('utilities/log4javascript').then(response => {
            console.info("<---- logger loaded and started ----->")
            this.initApp()
        }).catch(err => {
            console.error(err)
        })
    }

```
>                        Requests
>
