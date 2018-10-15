class SivaTreeviewRow extends HTMLElement {
	constructor() {
		super();
	}

	connectedCallback() {
		this.setAttribute('class', 'tree-leaf');
		
		this.content = document.createElement('div');
		this.content.setAttribute('class', 'tree-leaf-content');
		this.setExpando(this);
		this.setTreeviewRow();
		
	    ccc.registerComponent(this, {
	        id: this.id,
	        secret: 'Treeview Row'
	    });
	    
	    this.insertBefore(this.content, this.querySelector('siva-treeview-child'));
	    this.setCollapse(this);
	}
	
	setExpando(that){
		if(that.querySelector('siva-treeview-child') !== null){
			this.expando = document.createElement('div');
			this.expando.setAttribute('class', 'tree-expando');
			this.expando.textContent = '+';
			this.content.append(this.expando);
		}
	}
	setCollapse(that){
		if(that.querySelector('siva-treeview-child') !== null){
			let expando = this.querySelector('.tree-leaf-content .tree-expando');
			expando.addEventListener("click", function(){
				let textNode = expando.textContent;
				if(textNode == '+'){
					that.querySelector('.tree-child-leaves').classList.remove('hidden');
					expando.textContent = '-';
				}
				else if(textNode == '-'){
					that.querySelector('.tree-child-leaves').classList.add('hidden');
					expando.textContent = '+';
				}
			});
		}
	}

	addObjects(...elements) {
        elements.forEach(element => {
            this.appendChild(element)
		});
	}

	setTreeviewRow() {	

		if (this.id) {
			this.content.innerHTML += `<input class="tree-leaf-element" id="${this.id}-input" type="checkbox" />`;
		}

		if(this.name){
			this.content.innerHTML += `<div class="tree-leaf-text">${this.name}</div>`;
		}
		
		if(this.getAttribute('name')){
			this.content.innerHTML += `<div class="tree-leaf-text">${this.getAttribute('name')}</div>`;
		}
	}
	
}

customElements.define("siva-treeview-row", SivaTreeviewRow);