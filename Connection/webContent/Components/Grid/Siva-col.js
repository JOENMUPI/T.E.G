class SivaCol extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {   
        this.setCol()
        this.defaultProperties()
        ccc.registerComponent(this, {
            id: this.id,
            secret: 'Col Parent'
        });
    }

    defaultProperties() {
        this.style.display = 'inline-block';
        this.style.lineHeight = '1.42857143';
        this.style.marginBottom = '2.5px';
        this.style.marginTop = '2.5px';
    }
    
    setCol() {
        if (this.id) {
            this.setAttribute('id', this.id)
        }

        if (this.col) {
            this.setAttribute('class', this.col)
        }

        if (this.getAttribute('col')) {
            this.setAttribute('class', this.getAttribute('col'))
        }

        if (this.offset) {
            this.setAttribute('class', this.offset)
        }

        if (this.getAttribute('offset')) {
            this.setAttribute('class', this.getAttribute('offset'))
        }

        if (this.col && this.offset) {
            this.setAttribute('class', `${this.col} ${this.offset}`)
        }

        if (this.getAttribute('col') && this.getAttribute('offset')) {
            this.setAttribute('class', `${this.getAttribute('col')} ${this.getAttribute('offset')}`)
        }
    }

    getObjects(obj) {
        return this.querySelectorAll(`${obj}`);
    }

    addSingleObject(element) {
        const object = document.createElement(`${element}`)
        this.appendChild(object)
    }
    
    addMultipleObjects(...elements) {
        elements.forEach(element => {
            this.appendChild(element)
        });
    }

    createMultipleObjects(element, quantity) {
        for (let i = 0; i < quantity; i++) {
            const object = document.createElement(`${element}`)
            object.id = i
            this.appendChild(object)
        }
    }


}

customElements.define("siva-col", SivaCol);