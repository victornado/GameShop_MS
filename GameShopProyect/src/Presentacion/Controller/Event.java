package Presentacion.Controller;

public class Event {
	
	// EVENT TO INIT THE APPLICATION
	public static final int INIT_GAMESHOP								= 6666;

	// PROVIDER (1)
	public static final int REGISTER_PROVIDER 							= 0;
	public static final int UNSUBSCRIBE_PROVIDER 						= 1;
	public static final int MODIFY_PROVIDER 							= 2;
	public static final int READ_PROVIDER 								= 3;
	public static final int READ_ALL_PROVIDERS 							= 4;
	public static final int RES_REGISTER_PROVIDER_OK 					= 100;
	public static final int RES_REGISTER_PROVIDER_FAILED 				= 101;
	public static final int RES_UNSUBSCRIBE_PROVIDER_OK 				= 102;
	public static final int RES_UNSUBSCRIBE_PROVIDER_FAILED 			= 103;
	public static final int RES_MODIFY_PROVIDER_OK 						= 104;
	public static final int RES_MODIFY_PROVIDER_FAILED 					= 105;
	public static final int RES_READ_PROVIDER_OK						= 106;
	public static final int RES_READ_PROVIDER_FAILED 					= 107;
	public static final int RES_READALL_PROVIDERS_OK 					= 108;
	public static final int RES_READALL_PROVIDERS_FAILED 				= 109;

	// PRODUCT (2)
	public static final int REGISTER_PRODUCT 							= 5;
	public static final int UNSUBSCRIBE_PRODUCT 						= 6;
	public static final int MODIFY_PRODUCT 								= 7;
	public static final int READ_PRODUCT 								= 8;
	public static final int READ_ALL_PRODUCT 							= 9;
	public static final int RES_REGISTER_PRODUCT_OK 					= 110;
	public static final int RES_REGISTER_PRODUCT_FAILED 				= 111;
	public static final int RES_UNSUBSCRIBE_PRODUCT_OK 					= 112;
	public static final int RES_UNSUBSCRIBE_PRODUCT_FAILED 				= 113;
	public static final int RES_MODIFY_PRODUCT_OK 						= 114;
	public static final int RES_MODIFY_PRODUCT_FAILED 					= 115;
	public static final int RES_READ_PRODUCT_OK 						= 116;
	public static final int RES_READ_PRODUCT_FAILED 					= 117;
	public static final int RES_READALL_PRODUCT_OK 						= 118;
	public static final int RES_READALL_PRODUCT_FAILED 					= 119;


	// TICKET (3)
	public static final int REGISTER_TICKET 							= 10;
	public static final int UNSUBSCRIBE_TICKET 							= 11;
	public static final int READ_TICKET 								= 12;
	public static final int READ_ALL_TICKET 							= 13;
	public static final int RES_REGISTER_TICKET_OK 						= 120;
	public static final int RES_REGISTER_TICKET_FAILED 					= 121;
	public static final int RES_UNSUBSCRIBE_TICKET_OK 					= 122;
	public static final int RES_UNSUBSCRIBE_TICKET_FAILED 				= 123;
	public static final int RES_READ_TICKET_OK 							= 124;
	public static final int RES_READ_TICKET_FAILED 						= 125;
	public static final int RES_READALL_TICKET_OK 						= 126;
	public static final int RES_READALL_TICKET_FAILED 					= 127;
	
	// CONFERENCE (4)
	public static final int REGISTER_CONFERENCE 							= 14;
	public static final int UNSUBSCRIBE_CONFERENCE 							= 15;
	public static final int MODIFY_CONFERENCE 								= 70;
	public static final int READ_CONFERENCE 								= 16;
	public static final int READ_ALL_CONFERENCE 							= 17;
	public static final int RES_REGISTER_CONFERENCE_OK 						= 128;
	public static final int RES_REGISTER_CONFERENCE_FAILED 					= 129;
	public static final int RES_UNSUBSCRIBE_CONFERENCE_OK 					= 130;
	public static final int RES_UNSUBSCRIBE_CONFERENCE_FAILED 				= 131;
	public static final int RES_MODIFY_CONFERENCE_OK 						= 1140;
	public static final int RES_MODIFY_CONFERENCE_FAILED 					= 1150;
	public static final int RES_READ_CONFERENCE_OK 							= 132;
	public static final int RES_READ_CONFERENCE_FAILED 						= 133;
	public static final int RES_READALL_CONFERENCE_OK 						= 134;
	public static final int RES_READALL_CONFERENCE_FAILED 					= 135;
	public static final int UPDATE_LIST_CONFERENCE							= 13009;
	
	// DEPARTMENT (5)
	public static final int REGISTER_DEPARTMENT 							= 18;
	public static final int UNSUBSCRIBE_DEPARTMENT 							= 19;
	public static final int MODIFY_DEPARTMENT 								= 190;
	public static final int READ_DEPARTMENT 								= 20;
	public static final int READ_ALL_DEPARTMENT 							= 21;
	public static final int RES_REGISTER_DEPARTMENT_OK 						= 136;
	public static final int RES_REGISTER_DEPARTMENT_FAILED 					= 137;
	public static final int RES_UNSUBSCRIBE_DEPARTMENT_OK 					= 138;
	public static final int RES_UNSUBSCRIBE_DEPARTMENT_FAILED 				= 139;
	public static final int RES_MODIFY_DEPARTMENT_OK 						= 1104;
	public static final int RES_MODIFY_DEPARTMENT_FAILED 					= 1105;
	public static final int RES_READ_DEPARTMENT_OK 							= 140;
	public static final int RES_READ_DEPARTMENT_FAILED 						= 141;
	public static final int RES_READALL_DEPARTMENT_OK 						= 142;
	public static final int RES_READALL_DEPARTMENT_FAILED 					= 143;
	public static final int UPDATE_LIST_DEPARTMENT					 		= 1499;
	public static final int CALCULAR_NOMINA_DEPARTAMENTO			 		= 149779;
	public static final int CALCULAR_NOMINA_DEPARTAMENTO_OK			 		= 147719;
	public static final int CALCULAR_NOMINA_DEPARTAMENTO_FAILED		 		= 114979;
	
	// EMPLOYEE (6)
	public static final int REGISTER_EMPLOYEE 								= 22;
	public static final int UNSUBSCRIBE_EMPLOYEE 							= 23;
	public static final int MODIFY_EMPLOYEE 								= 1999;
	public static final int READ_EMPLOYEE 									= 24;
	public static final int READ_ALL_EMPLOYEE 								= 25;
	public static final int RES_REGISTER_EMPLOYEE_OK 						= 144;
	public static final int RES_REGISTER_EMPLOYEE_FAILED 					= 145;
	public static final int RES_UNSUBSCRIBE_EMPLOYEE_OK 					= 146;
	public static final int RES_UNSUBSCRIBE_EMPLOYEE_FAILED 				= 147;
	public static final int RES_MODIFY_EMPLOYEE_OK 							= 10104;
	public static final int RES_MODIFY_EMPLOYEE_FAILED 						= 10105;
	public static final int RES_READ_EMPLOYEE_OK 							= 148;
	public static final int RES_READ_EMPLOYEE_FAILED 						= 149;
	public static final int RES_READALL_EMPLOYEE_OK 						= 150;
	public static final int RES_READALL_EMPLOYEE_FAILED 					= 151;
	public static final int UPDATE_LIST_EMPLOYEE							= 1300;
	
	// REALIZA (7)
	public static final int REALIZA_ASIGNAR									= 13050;
	public static final int REALIZA_ASIGNAR_FAILED							= 13060;
	public static final int REALIZA_ASIGNAR_OK								= 17700;
	public static final int REALIZA_DESASIGNAR								= 13800;
	public static final int REALIZA_DESASIGNAR_FAILED						= 19300;
	public static final int REALIZA_DESASIGNAR_OK							= 11300;
	public static final int REALIZA_MODIFICAR								= 13100;
	public static final int REALIZA_MODIFICAR_FAILED						= 13002;
	public static final int REALIZA_MODIFICAR_OK							= 13010;
	
	// EVENTS TO SHOW THE QUERY CHARTS
	public static final int SHOW_PROVIDER_QUERY		 					= 777;
	public static final int SHOW_PROVIDER_QUERY_OK		 				= 778;
	public static final int SHOW_PROVIDER_QUERY_FAILED 					= 779;
	
	public static final int SHOW_PRODUCT_QUERY		 					= 888;
	public static final int SHOW_PRODUCT_QUERY_OK		 				= 889;
	public static final int SHOW_PRODUCT_QUERY_FAILED 					= 890;
	
	public static final int SHOW_TICKET_QUERY		 					= 990;
	public static final int SHOW_TICKET_QUERY_OK		 				= 995;
	public static final int SHOW_TICKET_QUERY_FAILED 					= 999;
	
}

