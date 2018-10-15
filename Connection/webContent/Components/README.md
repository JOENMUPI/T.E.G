# Siva-components

## Documentaton and ussage

>hola mundo

### Siva-button

```
contiene las propiedades de estilo del componente 
defaultProperties(){}

recibe un parametro y devuelve propiedades que seran agregadas a dich componente 
environment(env) {}

metodo para agregar atributos al componente
setButton(){}

llama al componente visual especificado 
getButton(){}

agrega estilo de borde al componente
sus parametro son color: para el color que se le quiera especificar al borde, val: valor o grosor del borde, radius: radio de las esquinas del componente

setBorder(color,val,radius){}

estilo del fundo sus parametros son color: especifica el color del fondo, img: la imagen que se le quiera agregar al fondo(se debe espesificar la direccion, url de la imagen)

setBackground(color, img){}

// faltan estos 2
setFontStyle(s, w) {}

setFont(val, size, color) {}
```

### Siva-input

```

getInputValue() {

llama al componente visual especificado
getInput() {}

metodo para agregar atributos al componente
setInput(){}

borra el contenido dentro del componente
onClear() {

ubica el componente en la posicion segun las coordenadas (x,y), especificadas
setPos(x, y) {}

modifica el tamaño(ancho, largo) del componente
setDimensions(width, height) {}

evalua la cadena ingresada en el componente y devuelve true o false si hay alguna coincidencia 
caps() {}
integer() {}
floatTest() {}
lowerCaps() {}

devuelve true o false, si el componente esta vacio
validateValue() {}
```

### Siva-form

```
devuelve o llama a dicho elemento(button)
getButton() {}
deveuelve o llama a todos los elementos(input), 
getInputs() {}
devuelve todos los elementos dentro del componente
getObjects(obj) {}

metodo para agregar atributos al componente
setForm() {}

agrega solo un elemento en el componente
addSingleObject(element) {}

/// tengo duda 
addMultipleObjects(...elements) {

agrega multiples elementos al componente
createMultipleObjects(element,quantity){}   
```

### Siva-col
```
metodo para insertar atributos al comonente
setCol(){}
```
