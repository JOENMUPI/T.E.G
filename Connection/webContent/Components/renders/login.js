// /* </-------------- START GRID-ROW-COL-FORM-INPUT-BUTTON ----------------/> */

// const grid_login = new SivaGrid()

// const row_login = new SivaRow()

// const col_login = new SivaCol()
// col_login.col = 'col-12'

// const title_login = document.createElement('h1')
// title_login.setAttribute('class', 'offset-4')
// title_login.innerText = 'Login Form'

// const username_login = new SivaInput()
// username_login.col = 'col-6'
// username_login.env = 'default'
// username_login.id = 'username_login'
// username_login.offset = 'offset-3'
// username_login.placeholder = 'username_login from js'

// const password_login = new SivaInput()
// password_login.col = 'col-6'
// password_login.env = 'default'
// password_login.id = 'password_login'
// password_login.offset = 'offset-3'
// password_login.placeholder = 'password_login from js'

// const password_confirmation_login = new SivaInput()
// password_confirmation_login.col = 'col-6'
// password_confirmation_login.env = 'default'
// password_confirmation_login.id = 'password_confirmation_login'
// password_confirmation_login.offset = 'offset-3'
// password_confirmation_login.placeholder = 'password_confirmation_login from js'

// const button_login = new SivaButton()
// button_login.innerText = 'Login'
// button_login.col = 'col-6'
// button_login.env = 'default'
// button_login.offset = 'offset-3'
// button_login.method = 'Authenticate(login_form)'

// const login_form = new SivaForm()
// login_form.col = 'col-6'
// login_form.offset = 'offset-3'
// login_form.env = 'default'
// login_form.addMultipleObjects(title_login, username_login, password_login, password_confirmation_login, button_login)

// col_login.addMultipleObjects(login_form)
// row_login.appendChild(col_login)

// grid_login.appendChild(row_login)
// document.body.appendChild(grid_login)

/* </----------------- END GRID-ROW-COL-FORM-INPUT-BUTTON --------------------/> */
// Ejemplo de JSON
var tree = [{
    name: 'Vegetables',
    id: 'vegetables',
    children: []
   }, {
    name: 'Fruits',
    id: 'tw1',
    children: [{
      name: 'Apple', 
      id: 'tw2',
      children: []
    }, {
      name: 'Orange',
      id: 'tw3',
      children: []
    }, {
      name: 'Lemon',
      id: 'tw4',
      children: []
    }]
   }, {
    name: 'Candy',
    id: 'tw5',
    children: [{
      name: 'Gummies',
      id: 'tw6',
      children: []
    }, {
      name: 'Chocolate',
      id: 'tw7',
      children: [{
        name: 'M & M\'s',
        id: 'tw8',
        children: []
      }, {
        name: 'Hershey Bar',
        id: 'tw9',
        children: []
      }]
    }, ]
   }, {
    name: 'Bread',
    id: 'tw10',
    children: []
   }];
const tw = new SivaTreeview()
tw.loadJSON(tree)
tw.render()
