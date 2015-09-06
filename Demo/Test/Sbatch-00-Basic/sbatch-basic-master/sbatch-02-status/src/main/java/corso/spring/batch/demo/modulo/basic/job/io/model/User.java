package corso.spring.batch.demo.modulo.basic.job.io.model;

public class User {

	@Override
	public String toString() {
		return "User [id ="+id+"cognome=" + cognome + ", email=" + email + ", nome="
				+ nome + ", telefono=" + telefono + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	private String nome;
	private String cognome;
	private String email;
	private String telefono;
	private long id;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
