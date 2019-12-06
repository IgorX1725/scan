package utils;

import org.json.JSONObject;

public class GetDatasJson {
	private static JSONObject jsonRA = new JSONObject();


	public static String getRA(Integer ra) {
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
