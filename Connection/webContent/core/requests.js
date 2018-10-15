const bobjects = 'siva.bobjects.'
const security = 'siva.security.'
const securityManager = 'siva.security.SecurityManager'
const HelloWorldObject = 'siva.bobjects.HelloWorld'

Authenticate = Form => {
    let params = []
    let typeParams = []
    const size = Form.getInputs()

    Form.getInputValues().forEach(values => {
        if (values.filled === true) {
            params.push(values.value)
            typeParams.push(values.type)

            if (size.length === (params.length && typeParams.length)) {
                
                if (params[1] === params[2]) {
                    const options = {
                        objName: `${securityManager}`,
                        metName: 'authenticate',
                        params: params,
                        typeParams: typeParams
                    }
                    console.info(options)
                    ccc.sendRequest(options).then(response => {
                        console.info(response)
                    })
                } else {
                    console.error('passwords doesnt match')
                }
            } else {
            	console.error(`you havent filled all the inputs`)
            }

        } else {
            console.error('no inputs filled')
        }
    });
}

createUser = Form => {
    let params = []
    let typeParams = []
    const size = Form.getInputs()

    Form.getInputValues().forEach(values => {
        if (values.filled === true) {
            params.push(values.value)
            typeParams.push(values.type)

            if (size.length === (params.length && typeParams.length)) {
               
                if (params[2] === params[3]) {
                    const options = {
                        objName: `siva.security.Concierge`,
                        metName: 'registerUser',
                        params: params,
                        typeParams: typeParams
                    }
                    console.info(options)
                    ccc.sendRequest(options).then(response => {
                        console.info(response)
                    })
                } else {
                    console.error('passwords doesnt match')
                }
            } else {
                console.error(`you havent filled all the inputs`)
            }

        } else {
            console.error('no inputs filled')
        }
    });
}

//THIS IS A MOCKUP FUNCTION TO TEST EVERY BUSSINESS OBJECT
//DONT WRITE ON THIS FUNCTION, JUST COPY AND PASTE AND START
//SETTING YOUR OPTIONS
BussinessObject_Test = () => {
    let params = []
    let typeParams = []

    params.push()
    typeParams.push()
            
    const options = {
        objName: `YOUR OWN PARAMS!!!`,
        metName: '',
        params: params,
        typeParams: typeParams
    }
    console.info(options)
    // ccc.sendRequest(options);    
}


HelloWorld = () => {
  let params = []
  let typeParams = []

  params.push('jo')
  typeParams.push('string')

  const options = {
    objName: `siva.bobjects.HelloWorld`,
    metName: 'getGreeting',
    params: params,
    typeParams: typeParams
  }
  console.info(options)
  ccc.sendRequest(options).then(data => console.log(data));    
}