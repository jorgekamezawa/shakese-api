package com.shakese.validacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErroFormularioDto {

	private String campo;
	private String erro;

}
