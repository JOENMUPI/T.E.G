class CCC {
    constructor() {
        this.components = [];
    }
    
    registerComponent(component, configs) {
        this.components.push({
            component: component,
            id: configs.id,
            secret: configs.secret
        });
    }

    initApp() {
        this.addScript("utilities/loader").then(response => {
            console.info("<---- app loaded and started ----->")
        }).catch(err => {
            console.error(err)
        })
    }
    
    chargeScript(url) {
        this.addScript(`${url}`).then((result) => {
            console.log(`${url} loaded`)
        }).catch((err) => {
            console.error(err)
        });
    }

    getComponents() {
        console.info(this.components)
        return this.components;
    }

    addScript(url) {
        return new Promise((resolve, reject) => {
            let script = document.createElement('script');
            script.async = true;
            script.src = url + '.js';
            script.onload = resolve;
            script.onerror = reject;
            document.head.appendChild(script);
        });
    }

    sendRequest(options) {
        return new Promise((resolve,reject) => {
            this.addScript("utilities/fetch").then(response => {
                console.info("fetch component ready to send request")
                
                fetching(options, 'POST', './Siva', msg => {
                    resolve(msg)
                })

            }).catch(err => {
                reject(err)
            })
        })
    }

//    startLogger() {
//        this.addScript('utilities/log4javascript').then(response => {
//            console.info("<---- logger loaded and started ----->")
//            this.initApp()
//        }).catch(err => {
//            console.error(err)
//        })
//    }

}