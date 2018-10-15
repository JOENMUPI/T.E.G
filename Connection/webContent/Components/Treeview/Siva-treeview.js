class SivaTreeview extends HTMLElement {
	constructor(json) {
		super();
		if (json !== undefined) {
			this.tv = json;
		}
		else {
			this.tv = [];
		}
	}

	connectedCallback() {
		this.setAttribute('class', 'treeview');
		this.setTreeview();
		this.defaultProperties();
		this.environment(this.env);
		
	    ccc.registerComponent(this, {
	        id: this.id,
	        secret: 'Treeview Parent'
	    });
	}

	loadJSON(json){
		var renderLeaf = function(item) {
     		var leaf = new SivaTreeviewRow();
			leaf.name = item.name;
			leaf.id = item.id;
			if (item.children && item.children.length > 0) {
				var children = new SivaTreeviewChild();
				item.children.forEach(function (child) {
				  children.addObjects(renderLeaf(child));
				});
				leaf.addObjects(children);
			}
		    return leaf;
		};   
		json.forEach( (item) => {
			this.appendChild(renderLeaf(item));
		});
	}
	
	render(element){ 
		if (element == undefined) {
			element = 'body';
		}
		document.querySelector(element).appendChild(this);
	}
	
	addNode(name, id){
		let obj = {
			name: name,
			id: id,
			children: []
		}
		this.tv.push(obj);
	}
	
	addChild(name, id, node){
		let obj = {
			name: name, 
			id: id,
			children: []
		}
		this.tv.forEach((e) => {
			if(e.id == node){
				e.children.push(obj);
			}
			else if(e.children.length > 0){
				this.exploreChildren(e.children, node, obj);
			}
		});
		
	}
	exploreChildren(children, node, obj){
		children.forEach((item) => {
			if(item.id == node){
				item.children.push(obj);
			}
			else if(children.length > 0){
				this.exploreChildren(item.children, node, obj);
			}
		});
	}

	defaultProperties(){
		this.style.display = 'inline-block';
		this.style.height = '300px';
		this.style.width = '300px';
		this.style.padding = '15px';
		this.style.marginBottom = '10px';
		this.style.marginTop = '8px';
		this.style.border = '2px solid rgb(204, 204, 204)';
		this.style.borderRadius = '5px';
	}
	
	environment(env) {
		switch (env) {
			case 'jungle':
				this.setFont('arial', '14px', 'green');
				this.setBorder('green', '2px solid transparent', '8px');
				this.setBackground('white', 'none')
			break;

			case 'sky':
				this.setFont('arial', '14px', '#337ab7');
				this.setBorder('black', '2px solid transparent', '8px');
				this.setBackground('white', 'none')
			break;

			case 'dark':
				this.setFont('arial', '14px', 'white');
				this.setBorder('white', '2px solid transparent', '4px');
				this.setBackground('#9E1F1E', 'none')
			break;

			default:
				this.setFont('arial', '14px', 'white');
				this.setBorder('#D4AF37', '1px solid transparent', '8px');
				this.setBackground('purple', 'none')
			break;
		}
	}
		
	setObjectProperties(props,keys) {
		if (props.lenght === keys.lenght) {
			for(let index in props) {
				this.setAttribute(props[index], keys[index])
			}
		} else {
			console.error('both arrays must be same lenght')
		}
	}
		
	setBorder(color,val, radius) {
		this.style.border = val;
		this.style.borderColor = color;
		this.style.borderRadius = radius;
	}

	setFont(val, size, color) {
		this.style.fontFamily = val;
		this.style.fontSize = size;
		this.style.color = color;
	}

	setFontStyle(s, w) {
		this.style.fontStyle = s;
		this.style.fontWeight = w;
	}

	setBackground(color, img) {
		this.style.background = color;
		img = 'none' 
			? img = ''	
			: this.style.backgroundImage = 'url(' + this.pathImg + img + ')'
	}

    addObjects(...elements) {
        elements.forEach(element => {
            this.appendChild(element)
        });
	}
	
	setTreeview() {	
		
		if (this.getAttribute('env')) {
			this.env = this.getAttribute('env')
		}

		if (this.id) {
			this.setAttribute('id', this.id)
		}

		if (this.getAttribute('id')) {
			this.setAttribute('id', `${this.getAttribute('id')}-treeview`)
		}

		if (this.col) {
			this.setAttribute('class', 'treeview ' + this.col)
		}

		if (this.getAttribute('col')) {
			this.setAttribute('class', 'treeview ' + this.getAttribute('col'))
		}

		if (this.offset) {
			this.setAttribute('class', 'treeview ' + this.offset)
		}

		if (this.getAttribute('offset')) {
			this.setAttribute('class', 'treeview ' + this.getAttribute('offset'))
		}

		if (this.col && this.offset) {
			this.setAttribute('class', `treeview ${this.col} ${this.offset}`)
		}

		if (this.getAttribute('col') && this.getAttribute('offset')) {
			this.setAttribute('class', `treeview ${this.getAttribute('col')} ${this.getAttribute('offset')}`)
		}
		

	}
	
}

customElements.define("siva-treeview", SivaTreeview);