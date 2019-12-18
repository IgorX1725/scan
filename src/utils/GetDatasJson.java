package utils;

import org.json.JSONObject;
//classe respons�vel por gerar um JSON com os dados de um aluno fict�cio para fins de teste
//esta classe ser� substitu�da por classe que ir� se comunicar com a API para retornar os dados reais dos alunos
public class GetDatasJson {
	private static JSONObject jsonRA = null;


	public static String getRA(Integer ra) {
		jsonRA = new JSONObject();
		switch (ra) {
		case 123456:
			jsonRA.put("ra", "123456");
			jsonRA.put("name", "Carlos Ad�o");
			jsonRA.put("CPF", "902.759.410-43");
			jsonRA.put("level", "[gradua��o, pos-gradua��o]");
			jsonRA.put("course", "[matematica,Economia,fisica]");
			jsonRA.put("category", "[documentos acad�micos,documntos pessoais]");
			jsonRA.put("type", "[RG,Certid�o de nascimento, Hist�rico escolar]");
			return jsonRA.toString();
		case 654321:
			jsonRA.put("ra", "123456");
			jsonRA.put("name", "Maria Jos�");
			jsonRA.put("CPF", "516.323.970-80");
			jsonRA.put("level", "[gradua��o, mestrado]");
			jsonRA.put("course", "[matematica,Economia,fisica]");
			jsonRA.put("category", "[documentos acad�micos,documntos pessoais]");
			jsonRA.put("type", "[RG,Certid�o de nascimento,comprovante de resid�ncia]");
			return jsonRA.toString();
		default:
			return jsonRA.toString();
		}
	}

}
