package br.edu.ufape.agiota.negocio.basica;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Lembrete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date data;
    private String texto;
    private long usuarioId;
    
    @ManyToOne
    private Parcela parcela;

	public static List<Lembrete> listarLembrete() {
		// TODO Auto-generated method stub
		return null;
	}
}