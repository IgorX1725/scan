package utils;

import org.json.JSONObject;
//classe responsável por gerar um JSON com os dados de um aluno fictício para fins de teste
//esta classe será substituída por classe que irá se comunicar com a API para retornar os dados reais dos alunos
public class GetDatasJson {
	private static JSONObject jsonRA = null;


	public static String getRA(Integer ra) {
		jsonRA = new JSONObject();
		switch (ra) {
		case 123456:
			jsonRA.put("ra", "123456");
			jsonRA.put("name", "Carlos Adão");
			jsonRA.put("CPF", "902.759.410-43");
			jsonRA.put("level", "[graduação, pos-graduação]");
			jsonRA.put("course", "[matematica,Economia,fisica]");
			jsonRA.put("category", "[documentos acadêmicos,documntos pessoais]");
			jsonRA.put("type", "[RG,Certidão de nascimento, Histórico escolar]");
			return jsonRA.toString();
		case 654321:
			jsonRA.put("ra", "123456");
			jsonRA.put("name", "Maria José");
			jsonRA.put("CPF", "516.323.970-80");
			jsonRA.put("level", "[graduação, mestrado]");
			jsonRA.put("course", "[matematica,Economia,fisica]");
			jsonRA.put("category", "[documentos acadêmicos,documntos pessoais]");
			jsonRA.put("type", "[RG,Certidão de nascimento,comprovante de residência]");
			return jsonRA.toString();
		default:
			return jsonRA.toString();
		}
	}

}
