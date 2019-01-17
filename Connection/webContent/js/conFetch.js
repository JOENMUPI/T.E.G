function $(id) { return document.getElementById(id); }

function lol (type){
    var x = { "objectName": 'Connection', "methodName": type };
    
    switch(type){
        case 'Login':
            x.params = '['+$("username").value+','+$("password").value+']'; 
        	x.typeParams = '[String,String]';
            break;
        case 'Register':
            x.params = '['+$("username").value+','+$("fullname").value+','+$("password").value+']'; 
        	x.typeParams = '[String,String,String]';
        	break;
        case 'UpdatePass':
            x.params = '['+$("password").value+']'; 
        	x.typeParams = '[String]';
            break;
    }

    return { 
        headers: { 'Content-Type': 'application/json' },
        method: 'POST',
        body: JSON.stringify(x)
    }
}

function hget (){
    return { 
        headers: { 'Content-Type': 'application/json' },
        method: 'GET',
    }
}

function basic(path){
    var x ={ 
        headers: { 'Content-Type': 'application/json' },
        method: 'POST',
        body: JSON.stringify({ "objectName": 'Basic', "methodName": $('methodName').value,"params": $('params').value,"typeParams": $('typeParams').value })
    }
    fetch(path, x)
        .then(response => { return response.json(); })
        .then(data =>  { $('response').innerHTML = data.response })
        .catch( err => { $('response').innerHTML = 'ERROR... '+ err });
}

function get (path, type){
    fetch(path+'?objectName=Connection&methodName='+type, hget())
        .then(response => { return response.json(); })
        .then(data =>  { $('response').innerHTML = data.response })
        .catch( err => { $('response').innerHTML = 'ERROR... '+ err });
}

function post (path, type){
    fetch(path, lol(type))
        .then(response => { return response.json(); })
        .then(data =>  { $('response').innerHTML = data.response })
        .catch( err => { $('response').innerHTML = 'ERROR... '+ err });
}