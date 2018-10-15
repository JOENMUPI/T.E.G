class SivaInput extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        this.innerHTML = `<input/>`
        this.setInput();
        this.defaultProperties()
        this.objectProperties(this.env)
        
        ccc.registerComponent(this, {
            id: this.id,
            secret: 'Input Parent'
        });
    }

    defaultProperties() {
        this.getInput().style.display = 'inline-block';
        this.getInput().style.width = '95%';
        this.getInput().style.heigth = '25px';
        this.getInput().style.padding = '0px 12px';
        this.getInput().style.marginBottom = '5px';
        this.getInput().style.marginTop = '5px';
        this.getInput().style.outline = 'none'
    }

    objectProperties(env) {
        switch (env) {
            case 'jungle':
                this.setFont('arial', '14px', 'green');
                this.setBorder('white', '1px solid transparent', '8px');
                this.setBackground('white', 'none')
            break;

            case 'sky':
                this.setFont('arial', '14px', '#337ab7');
                this.setBorder('black', '1px solid transparent', '8px');
                this.setBackground('white', 'none')
            break;

            case 'dark':
                this.setFont('arial', '14px', '#9E1F1E');
                this.setBorder('white', '1px solid transparent', '8px');
                this.setBackground('white', 'none')
            break;

            default:
                this.setFont('arial', '14px', 'purple');
                this.setBorder('#D4AF37', '1px solid transparent', '8px');
                this.setBackground('white', 'none')
            break;
        }
    }

    setObjectProperties(props, keys) {
        if (props.lenght === keys.lenght) {
            for (let index in props) {
                this.getInput().setAttribute(props[index], keys[index])
            }
        } else {
            console.error('both arrays must be same lenght')
        }
    }

    setBorder(color, value, radius) {
        this.getInput().style.borderColor = color;
        this.getInput().style.border = value;
        this.getInput().style.borderRadius = radius;
    }

    setFont(value, size, color) {
        this.getInput().style.fontFamily = value;
        this.getInput().style.fontSize = size;
        this.getInput().style.color = color;
    }

    setFontStyle(s, w) {
        this.getInput().style.fontStyle = s;
        this.getInput().style.fontWeight = w;
    }

    setBackground(color, img) {
        this.getInput().style.background = color;
        img = 'none' 
            ? img = '' 
            : this.getInput().style.backgroundImage = 'url(' + this.pathImg + img + ')'
    }

    getInputValue() {
        let data = []

        let response = this.getInput()
            let id = '';
            response.id || response.id == undefined
                ? id = response.id 
                : id = 'no id input';

            response.value !== "" 
                ? data.push({
                    value: response.value,
                    type: typeof response.value,
                    id: id,
                    filled: true
                })
                : data.push({
                    value: response.value,
                    type: typeof response.value,
                    id: id,
                    filled: false
                });

        return data
    }    

    getInput() {
        return this.querySelector('input');
    }

    getValueType() {
        return typeof this.getInput().value;
    }

    setInput() {
        if(this.getAttribute('env')) {
            this.env = this.getAttribute('env')
        }

        if (this.id) {
            this.getInput().setAttribute('id', this.id)
        }

        if (this.getAttribute('id')) {
            this.getInput().setAttribute('id', `${this.getAttribute('id')}-input`)
        }

        if (this.col) {
            this.getInput().setAttribute('class', this.col)
        }

        if (this.getAttribute('col')) {
            this.getInput().setAttribute('class', this.getAttribute('col'))
        }

        if (this.offset) {
            this.getInput().setAttribute('class', this.offset)
        }

        if (this.getAttribute('offset')) {
            this.getInput().setAttribute('class', this.getAttribute('offset'))
        }

        if (this.col && this.offset) {
            this.getInput().setAttribute('class', `${this.col} ${this.offset}`)
        }

        if (this.getAttribute('col') && this.getAttribute('offset')) {
            this.getInput().setAttribute('class', `${this.getAttribute('col')} ${this.getAttribute('offset')}`)
        }

        if (this.placeholder) {
            this.getInput().placeholder = this.placeholder;
        }

        if (this.getAttribute('placeholder')) {
            this.getInput().placeholder = this.getAttribute('placeholder');
        }

    }

    onClear() {
        this.getInput().value = "";
        this.getInput().placeholder = "Cleared";
    }

    setPos(x, y) {
        this.getInput().style.top = x + "px";
        this.getInput().style.left = y + "px";
        this.getInput().style.position = "relative";
    }

    setDimensions(width, height) {
        this.getInput().style.width = width + "px";
        this.getInput().style.height = height + "px";
    }

    caps() {
        alert(/[A-Z]/.test(this.getInput().value) ? true : false);
    }

    integer() {
        alert(/[^0-9]/.test(this.getInput().value) ? true : false);
    }

    floatTest() {
        alert(/[0-9]/.test(this.getInput().value) ? true : false);
    }

    lowerCaps() {
        alert(/[a-z]/.test(this.getInput().value) ? true : false);
    }

    validateValue() {
        (this.getInput().value == '') ? true : false;
    }
}

customElements.define("siva-input", SivaInput);