class SivaRow extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        this.setAttribute('class', 'row')
        ccc.registerComponent(this, {
            id: this.id,
            secret: 'Row Parent'
        });
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

customElements.define("siva-row", SivaRow);