package br.unitins.emidia.model;

public enum TipoMidia {
	DIGITAL(1, "Digital"), 
	FISICO(2, "Fisico");
	
	private int id;
	private String label;
	
	TipoMidia(int id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static TipoMidia valueOf(int id) {
		for (TipoMidia tipo : values()) {
			if (id == tipo.getId())
				return tipo;
		}
		return null;
	}
}
