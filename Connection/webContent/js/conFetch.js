function $(id) { return document.getElementById(id); }

function lol (type){
    var x = { "methodName": type };
    
    switch(type){
        case 'Login':
        case 'Register':
            x.email = $("username").value;
            x.password = $("password").value;
            break;
        case 'ChangePass':
            x.email = $("tittle").value;
            x.oldPass = $("oldPassword").value
            x.newPass = $("newPassword").value
            break;
    }

    return { 
        headers: { 'Content-Type': 'application/json' },
        method: 'POST',
        body: JSON.stringify(x)
    }
}

function exec(data, type){
    switch(data.status){
        case '200':
            switch(type){
                case 'Login':
                case 'Register':
                    $('base').style.display = "none";
                    showEnter();
                    break;
                case 'ChangePass':
                    alert(data.response);
                    showEnter();
                    break;
                case 'Logout':
                    alert(data.response);
                    showLogin();
                    break;
            }
            break;
        default:
            alert('ERROR ' + data.status + ': ' + data.response);
            break;
    }
}

function send (path, type){
    fetch(path, lol(type))
        .then(response => { return response.json(); })
        .then(data =>  { exec(data, type) })
        .catch( err => { $('tittle').innerHTML = 'ERROR... '+ err });
}

function showLogin (){
    $('base').style.display = "block";
    $('tittle').innerHTML = 'Login';
    $("register").style.display = "none";
    $("updatePass").style.display = "none";
    $("enter").style.display = "none";
    $("login").style.display = 'block';
}

function showRegister (){
    $('base').style.display = "block";
    $('tittle').innerHTML = 'Register';
    $("login").style.display = "none";
    $("updatePass").style.display = "none";
    $("enter").style.display = "none";
    $("register").style.display = 'block';
}

function showUpdate (){
    $('tittle').innerHTML = 'Update Password <br>' + $('username').value;
    $("register").style.display = "none";
    $("login").style.display = "none";
    $("enter").style.display = "none";
    $("updatePass").style.display = 'block';
}

function showEnter (){
    $('tittle').innerHTML = $('username').value;
    $("register").style.display = "none";
    $("updatePass").style.display = "none";
    $("login").style.display = "none";
    $("enter").style.display = 'block';
}