package br.com.solutis.votacao.dtos;

import br.com.solutis.votacao.domain.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class UsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="O preenchimento do campo Nome é obrigatório")
	@Length(min=10, max=100, message="O campo Nome deve conter entre 10 e 100 caracteres")
	private String nome;
	
	private String cpf;
	
	@Email(message="Email inválido.")
	private String email;


	public UsuarioDTO(Usuario obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
