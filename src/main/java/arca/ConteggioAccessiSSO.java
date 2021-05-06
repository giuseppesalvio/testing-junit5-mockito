package arca;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public
class ConteggioAccessiSSO
{
	String data;
	String bancaSso;
	int    conteggioAccessi;

}
