package codeAholics;

public final class Authentication {
	
	//Se verifica los permisos para el paciente
	public static boolean autPatients (String pUser, String pPwd){
		//se consulta en la bd si el paciente existe
		return true;
	}
	
	// Se verifican permisos para el doctor
	public static boolean autDocs (String pUser, String pPwd){
		// se consulta en la bd si el doc exite
		return true;
	}

}
