class SivaTreeviewChild extends HTMLElement {
	constructor() {
		super();
	}

	connectedCallback() {
		this.setAttribute('class', 'tree-child-leaves hidden');
		
		
	    ccc.registerComponent(this, {
	        id: this.id,
	        secret: 'Treeview Child'
		});
		
	}

	addObjects(...elements) {
		elements.forEach(element => {
			this.appendChild(element)
		});
	}
	
}

customElements.define("siva-treeview-child", SivaTreeviewChild);