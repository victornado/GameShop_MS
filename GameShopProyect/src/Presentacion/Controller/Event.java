package Presentacion.Controller;

/**
 * @author GameShop
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Event {
	
	// EVENT TO INIT THE APPLICATION
	public static final int INIT_GAMESHOP								= 1337;

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

